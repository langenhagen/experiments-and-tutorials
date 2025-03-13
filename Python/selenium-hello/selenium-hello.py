#!/usr/bin/env python3
"""Hello World for Selenium web driver.

Tested with selenium==4.16.0 and webdriver-manager==4.0.1.
"""

import time

from selenium.webdriver import Chrome, ChromeService
from selenium.webdriver.chrome.options import Options
from selenium.webdriver.common.by import By
from selenium.webdriver.common.keys import Keys
from webdriver_manager.chrome import ChromeDriverManager


def main() -> None:
    """Main program entry point."""
    driver_path = ChromeDriverManager().install()
    print(f"{driver_path=}")

    options = Options()
    options.add_argument("--disable-extensions")
    options.add_argument("--disable-gpu")
    options.add_argument("--headless=new")  # for Chrome >= 109
    # options.headless = True # also works

    service = ChromeService(executable_path=driver_path)
    driver = Chrome(options=options, service=service)

    try:
        # Open Google
        driver.get("https://duckduckgo.com/")
        # Find search box and write to it
        search_box = driver.find_element("name", "q")
        search_box.send_keys("Selenium WebDriver")
        search_box.send_keys(Keys.RETURN)

        # Wait for a few seconds to let the search results load
        time.sleep(2)

        # Find and print titles and URLs of the search results
        search_result_xpath = "/html/body/div[2]/div[5]/div[4]/div/div/div/div/section[1]/ol/li[*]/article/div[2]/h2/a/span"
        search_results = driver.find_elements(by=By.XPATH, value=search_result_xpath)
        for result in search_results:
            title = result.text
            url = result.find_element(by=By.XPATH, value="..").get_attribute("href")
            print(f"Title: {title}\nURL: {url}\n")

    finally:
        # Close the browser window
        driver.quit()


if __name__ == "__main__":
    main()
