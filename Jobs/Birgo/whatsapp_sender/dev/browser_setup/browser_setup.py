__author__ = "Giovanni Frison"
__credits__ = ["Giovanni Frison"]
__License__ = "GNU AGPLv3"
__maintainer__ = "Giovanni Frison"
__email__ = "ing.giovanni.frison@gmail.com"
__status__ = "Development"
__version__ = "0.0.1"

# Default python packages
import os
import sys
import time
from urllib.parse import quote

# pip installed python packages
from selenium import webdriver
from selenium.webdriver.chrome.webdriver import WebDriver
from selenium.webdriver.common.by import By
from selenium.webdriver.edge.service import Service
from selenium.webdriver.support.ui import WebDriverWait
from selenium.common.exceptions import UnexpectedAlertPresentException

from webdriver_manager.chrome import ChromeDriverManager


# TODO create a function to style text


class Whatsapp:
    """
    This class takes care of the connection to the chrome drive

    # TODO 
    # EXCEPTION LIST
    # NoSuchElementException
    # KeyboardInterrupt
    # UnexpectedAlertPresentException
    # WebDriverException
    """

    # default session path to store qr code login session
    if 'win' in sys.platform.lower():
        session_path = 'C:\\tmp'
    else:
        session_path = '\\tmp'

    def __init__(self) -> None:
        self.chrome_options = webdriver.ChromeOptions()
        self.chrome_options.add_argument('user-data-dir=' + self.session_path)

    def __repr__(self) -> str:
        return f'{self.__class__.__name__}()'

    @property
    def options(self) -> str:
        return self.session_path

    @options.setter
    def options(self, path: str) -> None:
        """Set chrome options user-data-dir

        Args:
            path (str): path to the folder which will save the chrome's cache
            together with the qr-code login

        Returns:
            None
        """
        if not os.path.exists(path) and isinstance(path, str):
            return print(f'"{path}" is not a valid path')
        self.session_path = path
        self.chrome_options.add_argument('user-data-dir=' + self.session_path)

    def connect(self, *, wait=3) -> WebDriver:
        """Connect to the browser

        Args:
            wait (int, optional): Sets a sticky timeout to implicitly wait for an element 
            to be found. Defaults to 3 seconds.

        Returns:
            WebDriver: handler of the browser
        """
        self.driver = webdriver.Chrome(service=Service(ChromeDriverManager().install()),
                                       options=self.chrome_options)
        self.driver.implicitly_wait(wait)
        return self.driver

    def retry_connection(self):
        """Close the webdriver and re-open it
        """
        self.driver.close()
        self.connect()

    def _get_element(self, xpath: str, msg: str):
        def element(driver):
            return driver.find_element(by=By.XPATH,
                                       value=xpath)
        WebDriverWait(self.driver, 100, poll_frequency=0.1, ignored_exceptions=UnexpectedAlertPresentException).until(
            method=element, message=msg)

        return element(self.driver)

    def send_msg(self,  phone_num: str, message: str):
        """Send a whatsapp message to a specific number

        Args:
            phone_num (str): phone number (with country code)
            message (str): message (styled as if it was written directly in whatsapp)

        Returns:
            None
        """
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
        """Send a whatsapp message that contains an image and optionally some text

        Args:
            phone_num (str): phone number (with country code)
            file_path (str): path to the image to be sent
            message (str, optional): message (styled as if it was written directly in whatsapp). Defaults to ''.

        Returns:
            None
        """
        if not isinstance(phone_num, str) and isinstance(message, str):
            return print(f'phone number and message must be strings; got ({type(phone_num), type(message)}) instead')
        if not os.path.isfile(file_path):  # TODO define legal img format
            return print(f'"{file_path}" is not a valid file')

        url = f"https://web.whatsapp.com/send?phone={phone_num}&text={quote(message)}&app_absent=0"

        self.driver.get(url)

        time.sleep(3.5)  # 3.5 works

        print('got url...')

        attachment = self._get_element('//div[@title = "Allega"]',
                                       "can't find attachment button")

        print('got attachment...')

        attachment.click()

        img = self._get_element('//input[@accept="image/*,video/mp4,video/3gpp,video/quicktime"]',
                                "can't fin image button")

        print('got image...')

        img.send_keys(file_path)

        print('image sent...')

        send = self._get_element("//div[@aria-label='Invia']",
                                 "Can't find button Invia")

        print('got invia button...')

        send.click()
        print('sending...')

        time.sleep(2.5)  # 2.5 Works
