# Gorm Hello
A hello world in `gorm` against a Postgres Database.

## Setup

```bash
go mod init gorm-hello
go mod tidy  # create a go.sum file
```


## Usage

```bash
bash start-database.sh --rm

go run .
```


## Helpful
```
export PGPASSWORD=pass
```