# Alembic Hello
A small showcase on how to use `alembic` with `sqlalchemy` on a `PostgreSQL` database.

Show how to use `alembic` to create an enum and a table in `PostgreSQL`.

## Usage
Basic usage:
```bash
bash setup.sh
source .venv/bin/activate.fish

# if the folder `alembic/` is missing
alembic init alembic

# if you want to add a new revision
alembic revision -m "create table"
# write the revision to your likings ...

# start the db
bash start-database.sh

# upgrade
alembic upgrade head  # upgrade to the latest, i.e. `head`
# alembic upgrade 99e2064da4b1  # alternatively, upgrade to a specific revision

# inspect the results
pgcli -h 127.0.0.1 -p 5432 -U user -d mydb

# create another revision
alembic revision -m "add enum and column"
# write the revision to your likings ...

# upgrade again
alembic upgrade head
```

Some additional commands:
```bash
alembic downgrade -1
alembic history
```

## Testing
Enter an SQL client and inspect stuff in the DB, e.g.:
```bash
pgcli -h 127.0.0.1 -p 5432 -U user -d mydb  # password: pass
```

```sql
insert into mytable (name, type) values ('anton','b4d');
insert into mytable (name) values ('carl');
select * from mytable
```

For verification, consider following PostgreSQL statement that find user-defined enums:
```sql
select n.nspname as enum_schema,
    t.typname as enum_name,
    e.enumlabel as enum_value
from pg_type t
    join pg_enum e on t.oid = e.enumtypid
    join pg_catalog.pg_namespace n ON n.oid = t.typnamespace;
```

## More Info
A list of further resources.

1. simple tutorial about alembic https://alembic.sqlalchemy.org/en/latest/tutorial.html
2. postgres, enums, sqlalchemy and alembic https://stackoverflow.com/questions/37848815/sqlalchemy-postgresql-enum-does-not-create-type-on-db-migrate
2. changing an PostreSQL enum later: https://medium.com/makimo-tech-blog/upgrading-postgresqls-enum-type-with-sqlalchemy-using-alembic-migration-881af1e30abe


