<h1>PostgressSql 101</h1>

TOC

- [Install on Ubuntu](#install-on-ubuntu)
- [Install pgadmin4](#install-pgadmin4)
- [Columns attributes](#columns-attributes)
  - [`PRIMARY KEY`](#primary-key)
  - [`UNIQUE`](#unique)
  - [`NOT NULL`](#not-null)


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


# Columns attributes

## `PRIMARY KEY`

Is one column of the table that uniquely identifies the elements in the table (e.g. id, ssn etc.)

## `UNIQUE`

Force a column to have unique values for

## `NOT NULL`

Force the column to contain a value (i.e. it can't be NULL), otherwise the entry is rejected (n.b. an entry that is left blank is equal to NULL)
