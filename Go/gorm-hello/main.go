// Showcase the usage of the ORM `gorm`, especially in combination with UUIDs and postgres
// databases.
// based on: https://gist.github.com/udondan/97b3c9178848012e50b61aec45d429f3
package main

import (
	"encoding/json"
	"fmt"
	"os"

	"github.com/davecgh/go-spew/spew"
	"github.com/jinzhu/gorm"
	"github.com/jinzhu/gorm/dialects/postgres"
	uuid "github.com/satori/go.uuid"
)

// table name: parents
type Parent struct {
	ID   uuid.UUID `gorm:"primary_key;type:uuid;default:uuid_generate_v4()"`
	Name string
}

// table name: children
type Child struct {
	ID       uuid.UUID `gorm:"primary_key;type:uuid;default:uuid_generate_v4()"`
	ParentID uuid.UUID `gorm:"type:uuid REFERENCES parents(id)"`
	Parent   Parent    `gorm:"ForeignKey:ParentID;AssociationForeignKey:ID`
	Name     string
	Blob     postgres.Jsonb
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

	//dropTables(db)
	createTables(db)
	newID := createNewRecord(db)
	showRecord(db, newID)
}

// func dropTables(db *gorm.DB) {
// 	db.DropTable(&Child{})
// 	db.DropTable(&Parent{})
// }

func createTables(db *gorm.DB) {
	db.CreateTable(&Parent{})
	db.CreateTable(&Child{})
}

func createNewRecord(db *gorm.DB) uuid.UUID {

	jsonBlob := []byte(`{"hello":"world"}`)
	testObject := Child{
		Name: "I am the child",
		Parent: Parent{
			Name: "I am the parent",
		},
		Blob: postgres.Jsonb{RawMessage: jsonBlob},
	}
	db.Create(&testObject)
	return testObject.ID
}

func showRecord(db *gorm.DB, key interface{}) {
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
