#!/usr/env/bin python3
"""Hello World for Selenium web driver.
https://selenium-python.readthedocs.io/installation.html#drivers

1. pip install selenium
2. download browser drivers, e.g. geckodriver or chrome driver
3. put driver into path. I had to put geckodrver/chromedriver/phantomjs into
   /usr/local/bin/ in order to make it work. I also tried to put it onto the
   desktop and added the desktop to the path. That did not work.

"""
import os
import pathlib

import selenium.webdriver

driver = selenium.webdriver.Firefox()
driver.get("https://calendar.google.com")

# elem = driver.find_element_by_name("q")
# elem.clear()
# elem.send_keys("pycon")

# Keys = selenium.webdriver.common.keys.Keys
# elem.send_keys(Keys.RETURN)
# assert "No results found." not in driver.page_source
# driver.close()
