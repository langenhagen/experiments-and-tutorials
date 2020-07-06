import json
import random

from django.shortcuts import render

import bookmarks.models.bookmark as bm
from django.core.serializers.json import DjangoJSONEncoder  # can convert datetimes to json


def index(request):
    bookmarks = bm.load_bookmarks_as_json()
    context = {"bookmarks_json": json.dumps(bookmarks, cls=DjangoJSONEncoder)}
    return render(request, "bookmarks/mytemplate.html", context)
