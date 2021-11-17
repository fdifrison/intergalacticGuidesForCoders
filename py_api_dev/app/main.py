from typing import Optional
from fastapi import FastAPI, Response, status, HTTPException
from fastapi.params import Body
from pydantic import schema
from pydantic.main import BaseModel
from random import randrange

app = FastAPI()

# pydantic schema
class Post(BaseModel):
    '''
    Inherit from pydantic schema in order to validate the message sent by the user.
    '''
    title: str
    content: str
    published: bool = True 
    rating: Optional[int] = None  

# local storage for posts; later will be a database
my_posts = [{'title': 'title1', 'content': 'content1', 'id': 1},
            {'title': 'title2', 'content': 'content2', 'id': 2}] 

# Path operation or Rout
@app.get("/") # GET HTTP method # / -> root path
async def root(): 
    # async is optional, only needed if async operation are needed
    # return -> what is returned to the user; fastapi converts it in json
    return {"message": "Hello World"}



@app.get("/posts") 
async  def get_posts():
    return {"data": my_posts} # FastAPI will automatically convert to json



# @app.post("/createposts") # POST method -> send information to url
# async def create_posts(payLoad: dict = Body(...)):
#     '''
#     payLoad is a dummy name for the argument which will inherit the content
#     of Body request as a dictionary
#     '''
#     print(payLoad)
#     return {"message": f"title {payLoad['title']} content: {payLoad['content']}"}


# @app.post("/posts") # POST method -> send information to url
# async def create_posts(post: Post):
#     '''
#     Pass the class Post as argument to the POST method
#     '''
#     print(post.title)
#     print(post.dict()) # convert Post object properties to dictionary
#     return {"message": f"title {post.title}"}


@app.post("/posts", status_code = status.HTTP_201_CREATED) # POST method -> send information to url
async def create_posts(post: Post):
    '''
    Store the new post in the local my_posts list
    '''
    post_dict = post.dict()
    post_dict['id'] = randrange(0, 10000000000)
    my_posts.append(post_dict)
    return {"data": post }

def find_post_by_id(id, response: Response):
    for num, p in enumerate(my_posts):
        if p['id'] == int(id):
            return {'id':num, 'msg':p}
    # status_code = response.status_code = status.HTTP_404_NOT_FOUND
    # return {'message': f'{status_code} - post with id {id} not found'}
    return HTTPException(status_code=status.HTTP_404_NOT_FOUND, 
                        detail=f'post with id {id} not found')

# get a single post
@app.get("/posts/{id}") # id is a "path parameter"
def get_post(id: int, response: Response): 
    post = find_post_by_id(id, response)
    if isinstance(post, dict):
        return {'post_detail' :post['msg']}
    else:
        return {'post_detail' :post}

@app.delete("/posts/{id}", status_code = status.HTTP_204_NO_CONTENT)
def delete_post(id: int, response: Response):
    post = find_post_by_id(id, response)
    if isinstance(post, dict):
        my_posts.pop(post['id'])
        return Response(status_code = status.HTTP_204_NO_CONTENT)
    else:
        return {'post_detail' :post}

@app.put("/posts/{id}", status_code = None)
def update_post(id: int, upd_post: Post, response: Response):
    '''
    upd_post correspond to the data inputted
    by the user that has to fit the Post schema
    '''
    post = find_post_by_id(id, response)
    if isinstance(post, dict):
        upd_post = upd_post.dict()
        upd_post['id'] = id
        my_posts[post['id']] = upd_post 
        return {'data': upd_post}
    else:
        return {'post_detail' :post}
 