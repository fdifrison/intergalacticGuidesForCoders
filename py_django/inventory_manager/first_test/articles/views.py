from django.shortcuts import render

from .models import Article


def article_search_view(request):
    # print(dir(request)) # to see the request methods
    query_dict = request.GET
    '''
    # <input type="text" name='q' placeholder="search"/>
    in the base.html file where we defined the search bar
    query store the query input by the user in the searchbox
    '''
    query = query_dict.get("q") 
    try:
        int(query)
    except ValueError as exc:
        print(f'{query} is not a valid query!')
        query = None

    article_obj = None
    if query:
        article_obj = Article.objects.get(id=query)
    
    context ={
        "object": article_obj
        }

    return render(request, "articles/search.html", context=context)


def article_create_view(request):
    post =request.POST
    title = post.get('title')
    content = post.get('content')
    Article.objects.create(title=title, content=content)
    context ={'title': title, 'content': content}
    return render(request, "articles/create.html", context=context)


def article_detail_view(request, id=None):
    article_obj = None
    if id:
        article_obj = Article.objects.get(id=id)
    
    context ={
        'object': article_obj
        }

    return render(request, "articles/detail.html", context=context)