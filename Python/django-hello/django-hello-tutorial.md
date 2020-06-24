# Build a Django 3 Webapp
- distilled from https://www.youtube.com/watch?v=4RWFvXDUmjo

## Prerequisites
```bash
pip install django
```


## Create a Project:
```bash
django-admin startproject hello_mysite
cd hello_mysite
python manage.py runserver
```
Check it out at `127.0.0.1:8000`


## II First Migrate, Create Superuser, Create an App
```bash
python manage.py migrate
python manage.py create superuser
python manage.py runserver
# log in to 127.0.0.1:8000/admin
# shut down the server

python manage.py startapp myapp
#
# let django know about that app in its settings.py file:
# add your app name into settings.py:INSTALLED_APPS list

```


## III Setup a First View
1. Add in hello_mysite/myapp/views.py:
```python
from django.http import HttpResponse

def index(request):
    return HttpResponse("Hello World!")
```

2. Create a file hello_mysite/myapp/urls.py:
```python
from django.urls import path

from . import views

urlpatterns = [
    path("", views.index)
]
```

3. Add the new urlpatterns in the master routing table in hello_mysite/urls.py:
```python
from django.urls import path, include

urlpatterns = [
    # [...]
    path("", include("myapp.urls"))
]
```

4. Test it
```bash
python manage.py runserver
```


## IV Render Nice Templates
1. Create a templates folder and a template:
```bash
mkdir -p hello_mysite/myapp/templates/myapp
touch hello_mysite/myapp/templates/myapp/mytemplate.html
# then populate the template nicely
```

2. Change the response in hello_mysite/myapp/views.py:
```python
from django.http import HttpResponse

def index(request):
    # return HttpResponse("Hello World!")  # comment that out
    return render(request, "myapp/mytemplate.html")
```

2. a) I also had to add to `settings.py`:
- see https://stackoverflow.com/questions/41623788/django-not-searching-templates-directory-defined-in-app-dirs-setings
```python
TEMPLATES = [
    {
        # [...]
        'DIRS': [os.path.join(BASE_DIR, 'templates')],   # this was necessary to find my templates
        # [...]
    }
]
```
- I later adjusted the path to the templates to my own liking

3. Create a model in hello_mysite/myapp/models.py:
```python

class MyTask(models.Model):
    title = models.CharField(max_length=200)
    complete = models.BooleanField(default=false)
    created = models.DateTimeField(auto_now_add=True)

    def __str__(self):
        return self.title
```

4. Make a migration to include the new model
```bash
python manage.py makemigrations  # add the new tables to the db
python manage.py migrate  # do the actual migration
```

5. Register the new Models in the admin panel via hello_mysite/myapp/admin.py:
```python

from .models import *

admin.site.register(MyTask)
```

6. Check you registered the new model at the admin page under `127.0.0.1/admin/`.
Play around with it.

7. Use the new db-models in the hello_mysite/myapp/views.py:
```python
from .models import *

def index(request):
    tasks = Task.objects.all()
    context = {'tasks': tasks}
    return render(request, "myapp/mytemplate.html", context)
```

8. Change the template hello_mysite/myapp/mytemplate.html:
```html
<h1>Hi there!</h1>

{% for task in tasks %}
<div>
    <p>{{task}}</p>
</div>

{% endfor %}
```
