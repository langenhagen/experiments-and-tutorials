// Showcase the usage of the ORM `gorm`, especially in combination with UUIDs and postgres
// databases.
// based on: https://gist.github.com/udondan/97b3c9178848012e50b61aec45d429f3
package main

import (
	"encoding/json"
	"fmt"
	"os"
	"time"

	"github.com/davecgh/go-spew/spew"
	"github.com/jinzhu/gorm"
	"github.com/jinzhu/gorm/dialects/postgres"
	uuid "github.com/satori/go.uuid"
)

// table name: parents
type Parent struct {
	ID   uuid.UUID `gorm:"primary_key;type:uuid;default:uuid_generate_v4()"`
	Name string
	Bird myEnum `gorm:"type:"MyEnum""` // better use all-lowercase enums, otherwise the quotes `"` get ugly
}

// table name: children
type Child struct {
	ID       uuid.UUID `gorm:"primary_key;type:uuid;default:uuid_generate_v4()"`
	ParentID uuid.UUID `gorm:"type:uuid REFERENCES parents(id)"`
	Parent   Parent    `gorm:"ForeignKey:ParentID;AssociationForeignKey:ID`
	Name     string
	Blob     postgres.Jsonb
}

// table name pokemon
type Pokemon struct {
	gorm.Model // brings some default fields
	Name       string
}

// Gorm, at least Gorm < 1.20, has issues with enums in Postgres. One can circumvent the issue.
func createEnumType(db *gorm.DB) {
	db.Exec(`
		DO $$
			BEGIN
			IF NOT EXISTS (SELECT 1 FROM pg_type WHERE typname = 'MyEnum') THEN
				CREATE TYPE "MyEnum" AS ENUM ('albatross', 'penguin', 'seagull');
			END IF;
		END $$;
	`)
}

func dropTables(db *gorm.DB) {
	db.DropTable(&Child{})
	db.DropTable(&Parent{})
	db.DropTable(&Pokemon{})
}

func createTables(db *gorm.DB) {
	db.CreateTable(&Parent{})
	db.CreateTable(&Child{})
	db.CreateTable(&Pokemon{})
}

func createNewRecord(db *gorm.DB) uuid.UUID {
	jsonBlob := []byte(`{"hello":"world"}`)
	testObject := Child{
		Name: "I am the child",
		Parent: Parent{
			Name: "I am the parent",
			Bird: penguin,
		},
		Blob: postgres.Jsonb{RawMessage: jsonBlob},
	}
	db.Create(&testObject)
	return testObject.ID
}

func showChildRecord(db *gorm.DB, key interface{}) {
	var child Child
	db.Set("gorm:auto_preload", true).Find(&child, "id = ?", key)
	fmt.Println("The child looks like:")
	spew.Dump(child) // spew.Dump() nicely prints an object

	var blobObj map[string]interface{}
	err := json.Unmarshal(child.Blob.RawMessage, &blobObj)
	if err != nil {
		panic(err)
	}
	fmt.Printf("unmarshalled object: %+v\n", blobObj)
}

func parentChildJsonExperiment(db *gorm.DB) {
	newID := createNewRecord(db)
	showChildRecord(db, newID)
}

func createPokemon(db *gorm.DB) uint {
	p := Pokemon{Name: "Pikachu"}
	db.Create(&p)
	return p.ID
}

func gormModelExperiment(db *gorm.DB) {
	createPokemon(db)

	now := time.Now()

	var younger []Pokemon
	db.Where("name = ? AND updated_at > ?", "Pikachu", now).Find(&younger)
	fmt.Print("Pokemon younger than now:\n")
	spew.Dump(younger) // empty

	var older []Pokemon
	db.Where("name = ? AND updated_at < ?", "Pikachu", now).Find(&older)
	fmt.Print("\nPokemon older than now:\n")
	spew.Dump(older) // shows value

}

// Connect to a postgres db, create some tables, create an entry, get an entry and print it.
func main() {

	dbString := "postgres://user:pass@localhost/mydb?sslmode=disable"

	db, err := gorm.Open("postgres", dbString)
	if err != nil {
		fmt.Fprintf(os.Stderr, "db connection failed: %s", err.Error())
		os.Exit(1)
	}
	defer db.Close()

	db.LogMode(false) // set to `false` to avoid annoying database logs in the program output

	createEnumType(db)
	// the tables use some server-side functions `uuid_generate_v4()` from a plugin, thus install it
	db.Exec(`CREATE EXTENSION IF NOT EXISTS "uuid-ossp"`)

	dropTables(db)
	createTables(db)

	fmt.Print("--- 1 models with gorm.Model ---\n")
	gormModelExperiment(db)

	fmt.Print("\n--- 2 Parent-Child Relationships and JSON ---\n")
	parentChildJsonExperiment(db)
}
