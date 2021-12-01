<h1>Django User Guide</h1>

TOC

- [Project Structure](#project-structure)
- [Basic Commands at the beginning of a project](#basic-commands-at-the-beginning-of-a-project)
  - [`admin.py`](#adminpy)
  - [`manage.py`](#managepy)
  - [`views.py`](#viewspy)
  - [`urls.py`](#urlspy)
  - [`settings.py`](#settingspy)
- [Templates](#templates)
  - [`HTML template`](#html-template)

# Project Structure

projectname_folder

- `manage.py`

- app_folder

  - migration_folder
  - `admin.py`
  - `apps.py`
  - `models.py`
  - `tests.py`
  - `views.py`

- projectname_folder

  - `asgi.py`
  - `settings.py`
  - `urls.py`
  - `views.py`
  - `wgsi.py`

# Basic Commands at the beginning of a project

Start a new project. The below command will create automatically the projectname_folder structure

    python3 -m  django startproject <projectname>

Create an administrator account (superuser)

    python manage.py createsuperuser

n.b if you forget the password you can create another superuser

## `admin.py`

Once the admin is created we can add models to it in the `admin.py` file:

```py
from .models import Article

# create functionality for the Article model in the admin page
class ArticleAdmin(admin.ModelAdmin):
    list_display = ['id', 'title']
    search_fields = ['title', 'content']

admin.site.register(Article, ArticleAdmin)

```

## `manage.py`

Launch server

    python3 manage.py runserver

Create an app

    python manage.py startapp <appname>

Migrate and apply migrations (i.e. after adding an app to `settings.py` or  updating a model in `models.py`)

    python3 manage.py makemigrations
    python3 manage.py migrate

Interact with the Django shell to create objects contained in `models.py` and to retrieve in `view.py` as mockup database entries (i.e.). Each object will have an __id__ that cab be retrieved by the view to be displayed in the browser. Simulates a database entry.

    python3 manage.py shell

```py
# import class object
# es. based on inventory_manager/first_test
from article.models import Article 

obj = Article()
obj.title = 'Hello World'    
obj.save()
# in this case the id is not specified so it will be a progressive number based on the number of object already present

# alternatively
obj = Article.objects.create(title='')
obj.id = 2
obj.save()
```

N.B. the `id` will be refereed to in the `views.py` as follow:

```py
from articles.models import Article
article_obj = Article.objects.get(id=2)
article_title = article_obj.title
article_content = article_obj.content
```

## `views.py`

Contains the function that renders the HTML of each page in the project directory. Functions take a Django request and return an HTML response. The project apps can be imported into the view to retrieve and display data.

```py
from django.http import HttpResponse
def home_view(request):
    HTML_STRING = '<h1>Hello World</h1>'
    return HttpResponse(HTML_STRING)
```

It is common to import HTML templates into views.

The views can load local objects or database queries and display them trough the html template:

```py
def home_view(request):
    """
    Inherit from html template
    """
    article_obj = Article.objects.get(id=2) # import a specific object
    article_queryset = Article.objects.all() # import all the objects in the db
    
    context = {
        "obj_list": article_queryset,
        "article_title" : article_obj.title,
        "article_content": article_obj.content
        }

    HTML_STRING = render_to_string("home_view.html", context=context)
    

    return HttpResponse(HTML_STRING)
```

## `urls.py`

Contains the URLs reference to the webpages. Connects a path (URL) with one of the views contained in `views.py`.

```py
from .views import home_view

urlpatterns =   [path('', home_view), # root page
                path('admin/', admin.site.urls)]
```

Patterns can have a dynamic path to be bale to accommodate a variable number of items coming from a db for example.

```py
urlpatterns = [
    path('articles/<int:id>/', views.article_detail_view), #
]
```

in this case, the `id` parameters needs to be passed in the pertinent function contained in `views.py`.

## `settings.py`

Django project settings

- INSTALLED_APPS -> list of applications connected to the project; some are built-in (i.e. admin, auth, etc..), but also custom apps created with `manage.py startapp` have to be appended to be displayed.

- TEMPLATES -> the paths to specific templates used in `view.py` to render the webpage are appended into the DIRS list

- OPTIONS -> the `context_processor` key contains a list of predefined options; these are needed for the templates to recognize certain django/python construct without being explicitly imported in the templates (e.g. request, auth etc..).

# Templates

It is common use to create a folder called `templates` in the main dir of our project. This should contain the `*.html` files that we are going to use to render the webpage. N.B. even if the language is html, to be rendered as a django view, fields must be encapsulated in double parenthesis (e.g. `< >{{title}}< />` and not just `< >{title}< />` as expected in html code)

## `HTML template`

HTML templates can be constructed ad hoc to receive inputs from python objects trough django. The templates is called in the `views.py` with a context parameter that populates the variables in {{}}. Also python operations can be inserted like the for loop below.

Usually is good practice to have a base html file that hold hte mask of the webpage, while other views are embedded inside it:

```html
<!DOCTYPE html>
<html>
    </head>
    <body>
            {% block content %}
            <h3>This will be replaced</h3>
            {% endblock content %}

    </body>
</html>
```

the block content can be later called by the other views:

```html
{%extends "base.html" %}

{% block content %}

<h1>{{article_title}}</h1>
<p>{{article_content}}</p>

<ul>
    {% for x in obj_list %}
    <li><a href='/articles/{{ x.id }}/'>{{ x.title }}</a></li>
    {% endfor %}
</ul>

{% endblock content %}

```
