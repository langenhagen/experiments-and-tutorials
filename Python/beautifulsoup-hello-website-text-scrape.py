#!/usr/env/bin python3
"""Scrape the text from a website using beautifulsoup.

Simple but flawed. Also scrapes the javascript-scripts.
"""
import re

import bs4
import requests

response = requests.get("https://langenhagen.github.io/recipes/Waffeln")
# response = requests.get("https://google.com")
soup = bs4.BeautifulSoup(response.text, "html5lib")

website_text = soup.get_text()  # has linebreaks "\n" escaped as b"\\n"
print(f"Type: {type(website_text)}")  #  Type: <class 'str'>

# remove excessive newlines
website_text_compacted = re.sub(r" *\n( *\n)+", r"\n\n", website_text)
print(website_text_compacted)
# print(website_text)
