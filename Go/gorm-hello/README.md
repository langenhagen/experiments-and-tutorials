# Gorm Hello
A hello world in `gorm` against a Postgres Database.

## Setup

```bash
go mod init gorm-hello
go mod tidy  # create a go.sum file
```


## Usage

```bash
bash start-database.sh -rm

# install the postgres extension `uuid-ossp` that brings the function `uuid_generate_v4()`
psql -h localhost -U user -d mydb -a -c 'create extension "uuid-ossp";'

go run .
```
