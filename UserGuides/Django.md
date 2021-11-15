# Django User Guide

## Project Structure

projectname_folder

* `manage.py`

* app_folder

    - migration_folder
    - `admin.py`
    - `apps.py`
    - `models.py`
    - `tests.py`
    - `views.py`

* projectname_folder

    - `asgi.py`
    - `settings.py`
    - `urls.py`
    - `views.py`
    - `wgsi.py`

## Basic Commands

    python3 -m  django startproject <projectname>

### `manage.py`

Launch server

    python3 manage.py runserver

Create an app

    python manage.py startapp <appname>

### `views.py`

Contains the function that renders the html of each pages in the project directory. Functions takes a django requests and returns an html response.

    from django.http import HttpResponse
    def home_view(request):
        HTML_STRING = '<h1>Hello World</h1>'
        return HttpResponse(HTML_STRING)


### `urls.py`

Contains the urls reference to the webpages. Connects a path (ulr) with one of the views contained in `views.py`

from .views import home_view

    urlpatterns = [
                    path('', home_view), # root page
                    path('admin/', admin.site.urls),
                    ]
