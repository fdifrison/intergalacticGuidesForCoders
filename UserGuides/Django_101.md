# Django User Guide

## Project Structure

projectname_folder

* `manage.py`

* app_folder

  * migration_folder
  * `admin.py`
  * `apps.py`
  * `models.py`
  * `tests.py`
  * `views.py`

* projectname_folder

  * `asgi.py`
  * `settings.py`
  * `urls.py`
  * `views.py`
  * `wgsi.py`

## Basic Commands

Start a new project. The below command will create automatically the projectname_folder structure

    python3 -m  django startproject <projectname>

### `manage.py`

Launch server

    python3 manage.py runserver

Create an app

    python manage.py startapp <appname>

Migrate and apply migrations (i.e. after adding an app to `settings.py` or  updating a model in `models.py`)

    python3 manage.py makemigrations
    python3 manage.py migrate

Interact with the Django shell to create objects contained in `models.py` and to retrieve in `view.py` as mockup database entries (i.e.). Each object will have an __id__ that cab be retrieved by the view to be displayed in the browser. Simulates a database entry.

    python3 manage.py shell
    obj = classFromModels()
    obj.title = 'Hello World'    

### `views.py`

Contains the function that renders the HTML of each page in the project directory. Functions take a Django request and return an HTML response. The project apps can be imported into the view to retrieve and display data.

    from django.http import HttpResponse
    def home_view(request):
        HTML_STRING = '<h1>Hello World</h1>'
        return HttpResponse(HTML_STRING)

It is common to import HTML templates into views

### `urls.py`

Contains the URLs reference to the webpages. Connects a path (URL) with one of the views contained in `views.py`.

from .views import home_view

    urlpatterns =   [path('', home_view), # root page
                     path('admin/', admin.site.urls)]

### `settings.py`

Django project settings

* INSTALLED_APPS -> list of applications connected to the project; some are built-in (i.e. admin, auth, etc..), but also custom apps created with `manage.py startapp` have to be appended to be displayed.

* TEMPLATES -> the paths to specific templates used in `view.py` to render the webpage are appended into the DIRS list
