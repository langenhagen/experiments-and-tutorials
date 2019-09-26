from datetime import datetime

from wsgiref.simple_server import make_server
from pyramid.config import Configurator
from pyramid.response import Response
from pyramid.httpexceptions import HTTPServerError

def default_view(request):
    # raise HTTPServerError(detail="Hallo")  # gives: this server has either erred... and on the next line "Hallo"
    raise HTTPServerError("MiauMiauMiau")  # same
    return Response('Hello World!')

def view2(request):
    return Response(HTTPServerError.explanation)

def view3(request):
    # controller/view
    now = datetime.now()

    # dd/mm/YY H:M:S
    dt_string = now.strftime("%d/%m/%Y %H:%M:%S")

    return Response('<h1>hey there! It is: {} </h2>'.format(dt_string))


if __name__ == "__main__":
    config=Configurator()
    config.add_route("hello", "/")
    config.add_view(default_view, route_name="hello")
    config.add_route("route_two", "2")
    config.add_view(view2, route_name="route_two")
    config.add_route("route_three", "foo")
    config.add_view(view3, route_name="route_three")
    app = config.make_wsgi_app()
    server = make_server("0.0.0.0", 6543, app)
    server.serve_forever()

