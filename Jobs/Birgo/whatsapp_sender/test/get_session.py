from selenium import webdriver
from selenium.webdriver.edge.service import Service
from selenium.webdriver.common.by import By
from selenium.webdriver.support.ui import WebDriverWait
from selenium.webdriver.chrome.options import Options
from selenium.webdriver.support import expected_conditions as EC
from urllib.parse import quote


DEFAULT_WAIT = 1000
EXTRACT_SESSION = 'extract_session.js'

def _wait_for_presence_of_an_element(browser, selector):
    element = None
      
    try:
        element = WebDriverWait(browser, DEFAULT_WAIT).until(
            EC.presence_of_element_located(selector)
        )
    except:
        pass
    finally:
        return element
    

def sessionGenerator(sessionFilePath):
  
    # 1.1 Open Chrome browser
    browser = webdriver.Chrome()
  
    # 1.2 Open Web Whatsapp
    browser.get("https://web.whatsapp.com/")
  
    # 1.3 Ask user to scan QR code
    print("Waiting for QR code scan...")
  
    # 1.4 Wait for QR code to be scanned
    _wait_for_presence_of_an_element(
      browser, MAIN_SEARCH_BAR__SEARCH_ICON)
  
    # 1.5 Execute javascript in browser and 
    # extract the session text
    session = browser.execute_script(EXTRACT_SESSION)
  
    # 1.6 Save file with session text file with
    # custom file extension ".wa"
    with open(sessionFilePath, "w", encoding="utf-8") as sessionFile:
        sessionFile.write(str(session))
  
    print("Your session file is saved to: " + sessionFilePath)
  
    # 1.7 Close the browser
    browser.close()
    


def sessionOpener(sessionFilePath):
  
    # 2.1 Verify that session file is exist
    if sessionFilePath == "":
        raise IOError('"' + sessionFilePath + '" is not exist.')
  
    # 2.2 Read the given file into "session" variable
    with open(sessionFilePath, "r", encoding="utf-8") as sessionFile:
        session = sessionFile.read()
  
    # 2.3 Open Chrome browser
    browser = webdriver.Chrome()
  
    # 2.4 Open Web Whatsapp
    browser.get("https://web.whatsapp.com/")
  
    # 2.5 Wait for Web Whatsapp to be loaded properly
    _wait_for_presence_of_an_element(browser, QR_CODE)
  
    # 2.6 Execute javascript in browser to inject
    # session by using vaarible "session"
    print("Injecting session...")
    browser.execute_script(INJECT_SESSION, session)
  
    # 2.7 Refresh the page
    browser.refresh()
  
    # 2.8 Ask for user to enter any key to close browser
    input("Press enter to close browser.")