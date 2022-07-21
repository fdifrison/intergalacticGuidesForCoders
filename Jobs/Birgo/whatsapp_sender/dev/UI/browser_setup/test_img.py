# -*- coding: utf-8 -*-
"""
Created on Wed Jul 13 15:22:34 2022

@author: inggi
"""
import time
import os
from datetime import datetime

from browser_setup import Whatsapp

from selenium.webdriver.common.keys import Keys
from selenium.webdriver import ActionChains

from selenium.common.exceptions import UnexpectedAlertPresentException

import pandas as pd


cwd = os.getcwd()

driver = Whatsapp()
driver_handler = driver.connect()
time.sleep(1.5)

start = time.time()

numeri = [3920135963, 3406446098,
          3342133404,        3922199508,
          3394078859,        3313055299,        3245838604,        3296836793,
          3342953563,        3461842705,        3478217190,        3345316360,
          3913622147,        3343837225,        3463975386,        3511256728,
          3282340447,        3519534434,        3932291686,        3402125107,
          3289595799,        3336061193,        3285392999,        3464085918,
          3482664100,        3939594806,        3393134981,        3313577803,
          3404690724,        3923183503
          ]

how_many = len(numeri)

failed = []
f = 0
sent = 0
while True:
    for num, n in enumerate(numeri):
        try:
            k = (num % 3) + 1
            messaggio = f'''*ATTENZIONE* \r\nSono aperte le candidature come *miglior dipendente 2022!* \r\n*VOTATEMI!!*
            '''

            n = '+39'+str(n)
            driver.send(n,
                        r"C:\Users\inggi\Documents\GitHub\Birgo\whatsapp_sender\browser_setup\the_boss.jpg",
                        message=messaggio
                        )
            stop = time.time()
            print(f'\nconnecting to {n}\n\n')

            sent += 1

            print(f'\nElapsed {stop - start}')
            print(f'\nMessage sent= {sent}\n')

        except Exception as e:

            print('\n\ntrying restoring connection\n\n')
            driver.retry_connection()
            time.sleep(10)
            continue

    stop = time.time()

    print(f'Elapsed {stop - start}')
