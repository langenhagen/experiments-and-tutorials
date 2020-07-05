from .models import bookmark

from django.shortcuts import render

def index(request):
    bookmarks = bookmark.load_bookmarks()
    context = {"bookmarks": bookmarks}
    return render(request, "bookmarks/mytemplate.html", context)
