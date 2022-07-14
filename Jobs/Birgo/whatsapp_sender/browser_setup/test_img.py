# -*- coding: utf-8 -*-
"""
Created on Wed Jul 13 15:22:34 2022

@author: inggi
"""
import time


from browser_setup import Whatsapp

from selenium.webdriver.common.keys import Keys
from selenium.webdriver import ActionChains

from selenium.common.exceptions import UnexpectedAlertPresentException

import pandas as pd
driver = Whatsapp()
driver_handler = driver.connect()
time.sleep(1)

start = time.time()

numeri = [3406446098,        3920135963,        3342133404,        3922199508,
          3394078859,        3313055299,        3245838604,        3296836793,
          3342953563,        3461842705,        3478217190,        3345316360,
          3913622147,        3343837225,        3463975386,        3511256728,
          3282340447,        3519534434,        3932291686,        3402125107,
          3289595799,        3336061193,        3285392999,        3464085918,
          3482664100,        3939594806,        3393134981,        3313577803,
          3404690724,        3923183503]

how_many = len(numeri)

# def a_capo():
#     ActionChains(driver_handler).key_down(Keys.SHIFT).key_down(Keys.ENTER).perform()
#     return ''

i = 29
while i < how_many:
    # try:
        
    messaggio = '''
    *LO SAPEVI CHE BIRGO BURGER CONSEGNA AL MARE* ? È un servizio speciale usato ogni sera da tantissime persone! Le consegne dei nostri hamburger di campagna, per le vostre cene al tramonto sotto l’ombrellone, arrivano in tutte le spiagge della riviera di *Rimini*, *Viserba*, *Viserbella*, *Torre Pedrera*, *Bellaria Igea Marina*, *San Mauro Mare*, *Gatteo Mare*, *Valverde*, *Cesenatico*!☀️
    Tutti i giorni d’estate, dalle 18 fino a notte ! Su vBirgo Burger App* puoi vedere il nostro menù estivo aggiornatissimo: https://apps.apple.com/it/app/birgo-burger/id1557621310"
    '''
    
    print(f'Processing number {i+1} of {how_many}')
    n = '+39'+str(numeri[i])
    n = '+39'+str(3406446098)
    driver.send_img(n,
                    r"C:\Users\inggi\Documents\GitHub\Birgo\whatsapp_sender\browser_setup\header.jpg",
                    message=messaggio
                    # message='*123 PROVA* Ciao Ragazzi! \r\n*QUESTO è un TEST* fate finta di niente \r\nGrazie per la pazienza e Buona giornata!'
                    )
    i += 1
    # except UnexpectedAlertPresentException:
    #     # breakpoint()
    #     print('UnexpectedAlertPresentException')
    #     driver_handler.switch_to.alert().accept() # solves
    # except Exception as e:
    #     print('general exception')
    #     print(e.__doc__)
    #     print(e)
    #     print('\n\ntrying restoring connection\n\n')
    #     driver.retry_connection()
    #     continue
    stop = time.time()

    print(f'Elapsed {stop - start}')

stop = time.time()

print(f'Elapsed {stop - start}')
