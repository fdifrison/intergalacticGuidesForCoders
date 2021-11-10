from fastapi import FastAPI
from fastapi.params import Body
from pydantic import schema

app = FastAPI()


# Path operation or Rout
@app.get("/") # GET HTTP method # / -> root path
async def root(): 
    # async is optional, only needed if async operation are needed
    # return -> what is returned to the user; fastapi converts it in json
    return {"message": "Hello World"}



@app.get("/posts") 
async  def get_posts():
    return {"data": "This is your post"}



@app.post("/createposts") # POST method -> send information to url
async def create_posts(payLoad: dict = Body(...)):
    '''
    payLoad is a dummy name for the argument which will inherit the content of Body request    as a dictionary
    '''
    print(payLoad)
    return {"message": f"title {payLoad['title']} content: {payLoad['content']}"}





'''
* start the server 
    > uvicorn filename:app
    > uvicorn filename:app --reload -> automatically refresh server after saving the file

* inside the get method there is the url. Only one can be defined for file; 
if more than one function has the same url, the first encountered is valid

* postman https://www.postman.com/ is a framework to easily test api

* pydentic is a library to define schema. A schema is a predefine structure for
ensure that what the user input is somehow restricted to what we expect to receive in the api
'''