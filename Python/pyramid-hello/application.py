from datetime import datetime
from wsgiref.simple_server import make_server

from pyramid.config import Configurator
from pyramid.httpexceptions import HTTPServerError
from pyramid.renderers import render_to_response
from pyramid.response import Response


def default_view(request):
    # raise HTTPServerError(detail="Hallo")  # gives: this server has either erred... and on the next line "Hallo"
    raise HTTPServerError("MiauMiauMiau")  # same
    return Response("Hello World!")


def view2(request):
    return Response(HTTPServerError.explanation)


def view3(request):
    # controller/view
    now = datetime.now()
    dt_string = now.strftime("%d/%m/%Y %H:%M:%S")

    return Response("<h1>hey there! It is: {} </h2>".format(dt_string))


def view_from_template(request):
    # use abs path here, templates are relative to some config, not to the pwd
    return render_to_response(
        "/home/andreasl/Dev/experiments-and-tutorials/Python/pyramid-hello/mytemplate.pt",
        {},
        request=request,
    )


if __name__ == "__main__":
    config = Configurator()

    # for chameleon template rendering with newer versions
    # see: https://docs.pylonsproject.org/projects/pyramid/en/latest/whatsnew-1.5.html#major-backwards-incompatibilities
    # not necessary for the old version c1 uses
    # config.include("pyramid_chameleon")

    config.add_route("hello", "/")
    config.add_view(default_view, route_name="hello")

    config.add_route("route_two", "2")
    config.add_view(view2, route_name="route_two")

    config.add_route("route_three", "foo")
    config.add_view(view3, route_name="route_three")

    config.add_route("route_4", "bar")
    config.add_view(view_from_template, route_name="route_4")

    app = config.make_wsgi_app()
    server = make_server("0.0.0.0", 6543, app)
    server.serve_forever()
