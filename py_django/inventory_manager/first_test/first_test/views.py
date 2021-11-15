"""
This file render htlm web pages
"""

from django.http import HttpResponse
from articles.models import Article
from django.template.loader import render_to_string

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



def home_view(request):
    """
    Inherit from html template
    """
    article_obj = Article.objects.get(id=1)
    
    context = {
        "article_title" : article_obj.title,
        "article_content": article_obj.content
        }

    HTML_STRING = render_to_string("home_view.html", context=context)
    return HttpResponse(HTML_STRING)