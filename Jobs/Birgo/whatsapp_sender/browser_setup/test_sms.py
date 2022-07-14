# -*- coding: utf-8 -*-
"""
Created on Wed Jul 13 15:22:34 2022

@author: inggi
"""
import time
from urllib.parse import quote

from browser_setup import Whatsapp

from selenium.webdriver.common.keys import Keys
from selenium.webdriver import ActionChains

from selenium.common.exceptions import UnexpectedAlertPresentException

import pandas as pd
driver = Whatsapp()
driver_handler = driver.connect()
time.sleep(0.5)

start = time.time()

numeri = [
    #3406446098,        3920135963,
    3342133404,        3922199508,
    3394078859,        3313055299,        3245838604,        3296836793,
    3342953563,        3461842705,        3478217190,        3345316360,
    3913622147,        3343837225,        3463975386,        3511256728,
    3282340447,        3519534434,        3932291686,        3402125107,
    3289595799,        3336061193,        3285392999,        3464085918,
    3482664100,        3939594806,        3393134981,        3313577803,
    3404690724,        3923183503]

how_many = len(numeri)

i = 0
while i < how_many:
    # try:

    print(f'Processing number {i+1} of {how_many}')
    n = '+39'+str(numeri[i])
    driver.send_msg(
        n, '*123 PROVA* Ciao Ragazzi! \r\n*QUESTO è un TEST* fate finta di niente \r\nGrazie per la pazienza e Buona giornata!')

    i += 1
    # except UnexpectedAlertPresentException:
    #     # breakpoint()
    #     print('UnexpectedAlertPresentException')
    #     # breakpoint()
    #     driver_handler.switch_to.alert.accept()  # solves
    #     # driver.retry_connection()
    # except Exception as e:
    #     print('general exception')
    #     # print(e.__doc__)
    #     print(e)
    #     print('\n\ntrying restoring connection\n\n')
    #     driver.retry_connection()
    #     continue
    stop = time.time()

    print(f'Elapsed {stop - start}')

stop = time.time()

print(f'Elapsed {stop - start}')
