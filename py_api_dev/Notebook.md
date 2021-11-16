# Python API Development Notebook

This notebook is to keep track of the various topics in the course of the

## Basic commands

Start a server with FastAPI and uvicorn.
uvicorn is shipped with pip install fastapi[all]

    uvicorn main:app --reload 

Create a dummy server on localhost 127.0.0.1:8000 that can be accessed with `postman` -> https://www.postman.com/


## `CRUD` - `C`reate-`R`ead-`U`pdate-`D`elete

CRUD represents the basic functionality that any API or database has to have.

es. for an api that works with blog posts (in FASTAPI):

* Create

    @app.`post`("/posts")

* Read

    @app.`get`("/posts/{id}") -> retrieve a specific post (id is unique)

    @app.`get`("/posts") -> retrieve multiple post

* Update

    @app.`put`("/posts/{id}")

* Delete

    @app.`delete`("/posts/{id}")