<h1>PostgressSql 101</h1>

TOC

- [Install on Ubuntu](#install-on-ubuntu)
- [Install pgadmin4](#install-pgadmin4)
- [Data Types](#data-types)
- [Columns attributes](#columns-attributes)
  - [`PRIMARY KEY`](#primary-key)
  - [`UNIQUE`](#unique)
  - [`NOT NULL`](#not-null)
- [Common SQL queries](#common-sql-queries)
  - [SELECT](#select)
  - [INSERT](#insert)
  - [DELETE](#delete)
  - [UPDATE](#update)


# Install on Ubuntu

```s
sudo apt update 
sudo apt install wget curl ca-certificates 

wget -O - https://www.postgresql.org/media/keys/ACCC4CF8.asc | sudo apt-key add - 

sudo sh -c 'echo "deb [arch=amd64] http://apt.postgresql.org/pub/repos/apt/ focal-pgdg main" >> /etc/apt/sources.list.d/pgdg.list' 


# initialize the server

sudo -u postgres psql

postgres=# ALTER USER username PASSWORD 'password.'

psql -U postgres -h localhost
```

# Install pgadmin4

```s
curl https://www.pgadmin.org/static/packages_pgadmin_org.pub | sudo apt-key add -

sudo sh -c 'echo "deb https://ftp.postgresql.org/pub/pgadmin/pgadmin4/apt/focal pgadmin4 main" > /etc/apt/sources.list.d/pgadmin4.
list'
```
# Data Types

- https://www.postgresql.org/docs/9.5/datatype.html

# Columns attributes

## `PRIMARY KEY`

Is one column of the table that uniquely identifies the elements in the table (e.g. id, ssn etc.)

## `UNIQUE`

Force a column to have unique values for

## `NOT NULL`

Force the column to contain a value (i.e. it can't be NULL), otherwise the entry is rejected (n.b. an entry that is left blank is equal to NULL)

# Common SQL queries

n.b. best practice dictate that sql commands are written in capital letters to facilitates the reading of queries

## SELECT

> select the whole table

```sql
SELECT * FROM tablename
```

> select columns

```sql
SELECT col1, col2 FROM tablename
```

> select column and rename

```sql
SELECT col1 AS c1, col2 AS c2 FROM tablename
```

> select rows with a specific value

```sql
SELECT * FROM tablename
WHERE col1 = 1; /* for string matching use single quote ''
```

> select rows with specific multiple values

```sql
SELECT * FROM tablename
WHERE col1 IN (1,2,3); /* same as col1 = 1 OR col1 = 2 OR col1 = 3
```

> select rows matching regular expression

```sql
SELECT * FROM tablename
WHERE col1 LIKE 'r%'; /* es. item in col1 starting with r 
```

> sort column

```sql
SELECT * FROM tablename
ORDER BY col1 DESC;
```

> select only a certain number of rows

```sql
SELECT * FROM tablename
LIMIT 5 OFFSET 1; /* select 5 rows skipping the first one
```

## INSERT

> insert new row (at least no-null fields must be passed)

```sql
INSERT INTO tablename (col1, col2) VALUES ('item1', 2); 
```

> insert multiple rows

```sql
INSERT INTO tablename (col1, col2) VALUES ('item1', 1), ('item2', 2), ('item3', 3); 
```

> insert new row a show it (add returning)

```sql
INSERT INTO tablename (col1, col2) VALUES ('item1', 2) RETURNING *; 
```

## DELETE

> delete selected rows

```sql
DELETE from tablename WHERE col1 = 10; 
```

## UPDATE

> update rows entires

```sql
UPDATE tablename SET col1 = 'updated_name' WHERE col1 = 'old_name'; 
```

