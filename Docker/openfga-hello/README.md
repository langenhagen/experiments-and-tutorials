# OpenFGA Hello

Taken from:  https://openfga.dev/docs/getting-started/setup-openfga/docker

```bash
docker compose up

psql -h '0.0.0.0' -p 5432 -U postgres  # pw as per docker-compose.yml: password
```
```sql
select count(*) from assertion;  -- 0
# ...
```

Also open http://localhost:3000/playground; had ininitially issues with Chrome, worked with Firefox,
later also worked with Chrome, but wanted a completely store, which was weird, I expected the stuff
to be stored and fetched from the postgres database. But what do I know.
