## IMPORTS ##
from selenium import webdriver
from selenium.webdriver.edge.service import Service
from selenium.webdriver.common.by import By
from selenium.webdriver.support.ui import WebDriverWait
from selenium.webdriver.chrome.options import Options
from selenium.webdriver.support import expected_conditions as EC
from urllib.parse import quote
from re import fullmatch
import time
import os
import pathlib

# Driver Managers
from webdriver_manager.chrome import ChromeDriverManager

def element_presence(by,xpath, wait):
    element_present = EC.presence_of_element_located((By.XPATH, xpath))
    WebDriverWait(driver, wait).until(element_present)

## RUNTIME VARIABLES ##
"""
Place the associated driver manager above depending on the browser you want to launch.
Selenium supports the following:
- Chrome
- Edge
- Firefox
- Opera
- Brave

Below the script is configured set to use an Edge browser. Be sure to change it according to the browser you use.
"""
browser = "Chrome"

## INPUT ##
"""
Takes input related to sending a text from the recipient phone number and message.
"""
print("\n")
print("Write recipient phone number in format +[Country Code][Area Code][Rest]:")
phone = '+393920135963'
phone = '+393492156902'
print("\nWrite message:")
message = 'Lukaku è il nostro Dio'

## HELPERS ##
"""
Functions that do self explanatory tasks
"""
def modify_number(phone_no):
    phone_no = phone_no.replace(" ", "").replace("-", "").replace("(", "").replace(")", "")
    return phone_no

def validate_number(phone_no):
    def check_number(number):
        return "+" in number or "_" in number

    if not check_number(number=phone_no):
        raise Exception("Country Code Missing in Phone Number!")

    if not fullmatch(r"^[\+]?[(]?[0-9]{3}[)]?[-\s\.]?[0-9]{3}[-\s\.]?[0-9]{4,6}$", phone_no):
        raise Exception("Invalid Phone Number.")
    return True

def set_browser(browser):
    install = lambda dm : dm.install()
    chrome_options = webdriver.ChromeOptions()
    # 'user-data-dir=' +
    # chrome_settings_path =  r'user-data-dir=C:\Users\inggi\AppData\Local\Google\Chrome\User Data\Default'
    chrome_settings_path =  'user-data-dir=C:\\Users\\inggi\\Documents\\GitHub\\Birgo\\whatsapp_sender\\session_saver'
    chrome_options.add_argument(chrome_settings_path)
    try:
      
        bm = ChromeDriverManager()
        return webdriver.Chrome(service=Service(install(bm)), options=chrome_options)

    except:
        raise Exception("Browser not found")

## SCRIPT ##
"""
Uses Selenium to send a text
"""

phone = modify_number(phone)
if (validate_number(phone)):

    # Loads browser
    driver = set_browser(browser)
    
    # Goes to site
    # site = f"https://web.whatsapp.com/send?phone={phone}&text={quote(message)}&app_absent=1"
    site = f"https://web.whatsapp.com/send?phone={phone}&text={quote(message)}&app_absent=1"
    site_img = f"https://web.whatsapp.com/send?phone={phone}&source=&data=#"
    driver.get(site_img)
    
    # attach images
    # element_presence(By.XPATH,'//*[@id="main"]/footer/div[1]/div[2]/div/div[2]', 30)
    # time.sleep(1.5)
    element = lambda d : d.find_element(by=By.XPATH, value='//div[@title = "Allega"]')
    attachment_box = WebDriverWait(driver, 1000).until(method=element, message="User never signed in")
    attachment_box.click()
    image_box = driver.find_element(by=By.XPATH, value='//input[@accept="image/*,video/mp4,video/3gpp,video/quicktime"]')
    image_box.send_keys(r'C:\Users\inggi\Documents\GitHub\Birgo\whatsapp_sender\birgo_logo.png')
    
    # imagemsg = driver.find_elements(by=By.XPATH, value='//div[@role = "textbox"]')
    
    # imagemsg[0].send_keys("Benevnuti all'aperimarinara")
    element = lambda d : d.find_element(by=By.XPATH, value='//*[@id="app"]/div/div/div[2]/div[2]/span/div/span/div/div/div[2]/div/div[1]/div[3]/div/div/div[2]/div[1]/div[2]')
    imagemsg = WebDriverWait(driver, 1000).until(method=element, message="User never signed in")
    imagemsg.send_keys("Benvenuti all'aperimarinara! Questo messaggio è stato creato in automatico da Ing.Frison")
    
    time.sleep(2)
    send = driver.find_element(by=By.XPATH, value="//div[@aria-label='Invia']")
    send.click()
    
    # # Uses XPATH to find a send button
    # element = lambda d : d.find_elements(by=By.XPATH, value="//div//button/span[@data-icon='send']")
    
    # # # Waits until send is found (in case of login)
    # loaded = WebDriverWait(driver, 1000).until(method=element, message="User never signed in")
    
    # # # Loads a send button
    # driver.implicitly_wait(10)
    # send = element(driver)[0]
    
    # # # Clicks the send button
    # send.click()
    
    # # Sleeps for 5 secs to allow time for text to send before closing window
    # time.sleep(1.5) 
    
    # # Closes window
    # driver.close()
