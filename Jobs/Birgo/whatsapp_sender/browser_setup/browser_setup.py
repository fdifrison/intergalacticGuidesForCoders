import os
import sys
import time
from urllib.parse import quote

from selenium import webdriver
from selenium.webdriver.edge.service import Service
from selenium.webdriver.common.by import By
from selenium.webdriver.chrome.webdriver import WebDriver
from selenium.webdriver.support.ui import WebDriverWait
from webdriver_manager.chrome import ChromeDriverManager
# TODO create a function to style text
from selenium.webdriver.common.keys import Keys
from selenium.webdriver import ActionChains

from selenium.common.exceptions import UnexpectedAlertPresentException


class color:
    BOLD = '\033[1m'
    END = '\033[0m'


class Whatsapp:
    '''
    TODO write docstring
    '''

    # default session path to store qr code login session
    if 'win' in sys.platform.lower():
        session_path = 'C:\\tmp'
    else:
        session_path = '\\tmp'

    def __init__(self, sleep=0.4) -> None:
        self.sleep = sleep
        self.chrome_options = webdriver.ChromeOptions()
        self.chrome_options.add_argument('user-data-dir=' + self.session_path)

    def __repr__(self) -> str:
        return f'{self.__class__.__name__}()'

    @property
    def options(self) -> str:
        return self.session_path

    @options.setter
    def options(self, path: str) -> None:
        if not os.path.exists(path) and isinstance(path, str):
            return print(f'"{path}" is not a valid path')
        self.session_path = path
        # TODO can this work headless? will it save time?
        self.chrome_options.add_argument('user-data-dir=' + self.session_path)

    def connect(self, *, wait=10) -> WebDriver:
        self.driver = webdriver.Chrome(service=Service(ChromeDriverManager().install()),
                                       options=self.chrome_options)
        self.driver.implicitly_wait(wait)
        return self.driver

    def retry_connection(self):
        self.driver.close()
        self.connect()

    def _get_element(self, xpath: str, msg: str, type_: str):
        def element(driver):
            return driver.find_element(by=By.XPATH,
                                       value=xpath)
        print(f'looking for {type_}')
        WebDriverWait(self.driver, 100, poll_frequency=0.2, ignored_exceptions=UnexpectedAlertPresentException).until(
            method=element, message=msg)

        # time.sleep(self.sleep)
        return element(self.driver)

    def send_msg(self,  phone_num: str, message: str):
        if not isinstance(phone_num, str) and isinstance(message, str):
            return print(f'phone number and message must be strings; got ({type(phone_num), type(message)}) instead')

        url = f"https://web.whatsapp.com/send?phone={phone_num}&text={quote(message)}&app_absent=1"
        self.driver.get(url)
        time.sleep(self.sleep)
        msg = self._get_element("//div//button/span[@data-icon='send']",
                                "can't find message box",
                                type_='icon send')

        msg.click()
        time.sleep(self.sleep)

    def send_img(self, phone_num: str, file_path: str, *, message=''):
        if not isinstance(phone_num, str) and isinstance(message, str):
            return print(f'phone number and message must be strings; got ({type(phone_num), type(message)}) instead')
        if not os.path.isfile(file_path):  # TODO define legal img format
            return print(f'"{file_path}" is not a valid file')

        url = f"https://web.whatsapp.com/send?phone={phone_num}&text={quote(message)}&app_absent=1"
        self.driver.get(url)
        time.sleep(self.sleep)
        print('got url')

        attachment = self._get_element('//div[@title = "Allega"]',
                                       "can't find attachment button",
                                       type_='attachment')

        print('got attachment')

        time.sleep(self.sleep)

        attachment.click()

        img = self._get_element('//input[@accept="image/*,video/mp4,video/3gpp,video/quicktime"]',
                                "can't fin image button",
                                type_='img button')

        print('got image')

        img.send_keys(file_path)

        time.sleep(self.sleep)


        send = self._get_element("//div[@aria-label='Invia']",
                                 "Can't find button Invia",
                                 type_='button Invia')

        # time.sleep(self.sleep)
        breakpoint()
        send.click()

        time.sleep(self.sleep)

    # TODO EXCEPTION LIST
    # NoSuchElementException
    # KeyboardInterrupt
    # UnexpectedAlertPresentException
    # WebDriverException
