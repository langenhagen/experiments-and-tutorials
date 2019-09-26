import urllib.request

url = 'http://www.google.com'
data = urllib.request.urlopen(url).read()
