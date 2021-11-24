"""
This file render htlm web pages.
When multiple functions have the same name, only the last one that appear
is relevant.
"""

from django.http import HttpResponse
from articles.models import Article
from django.template.loader import render_to_string, get_template

def home_view(request):
    """
    Take in a request (Django sends the request)
    return HTML as response
    """
    HTML_STRING = '<h1>Hello World</h1>'
    return HttpResponse(HTML_STRING)


def home_view(request):
    """
    Use the article app
    """
    article_obj = Article.objects.get(id=1)
    article_title = article_obj.title
    article_content = article_obj.content

    HTML_STRING = f'''
    <h1>{article_title}</h1>
    <p>{article_content}</p>
    '''
    return HttpResponse(HTML_STRING)



def home_view(request, *args, **kwargs):
    """
    Inherit from html template
    """
    article_obj = Article.objects.get(id=2)
    all_article_obj = Article.objects.all()
    
    context = {
        "obj_list" : all_article_obj,
        "article_title" : article_obj.title,
        "article_content": article_obj.content
        }

    HTML_STRING = render_to_string("home_view.html", context=context)
    
    # alternative to render_to_string if more template are needed
    # template = get_template("home_view.html")
    # template = template.render(context=context)

    return HttpResponse(HTML_STRING)


def home_view(request, *args, **kwargs):
    """
    Dynamic urls redirect to articles pages
    """
    article_obj = Article.objects.get(id=2)
    all_article_obj = Article.objects.all()
    
    context = {
        "obj_list" : all_article_obj,
        "article_title" : 'Homepage',
        "article_content": 'List of the available articles:'
        }

    HTML_STRING = render_to_string("home_view.html", context=context)
    

    return HttpResponse(HTML_STRING)