'''
HTTP HyperText Transfer Protocol
http://websitename/page1.html  
The Uniform  Resource Locator is composed by:
    * protocol -> http
    * host -> website name
    * document -> page1.html
'''

import socket

mysock = socket.socket(socket.AF_INET, socket.SOCK_STREAM) # create a socket
mysock.connect(('data.pr4e.org', 80)) # making the call to the server
# cmd è l'istruzione che vogliamo eseguire
# encode send the request in UTF-8 format
cmd = 'GET http://data.pr4e.org/page1.htm HTTP/1.0\r\n\r\n'.encode() # \r\n\r\n -> press enter, leave a blank line, press enter again and leave a blank line
mysock.send(cmd)

# loop to return the request
while True:
    data = mysock.recv(512) # 512 is the length of the string we are expecting to receive, it is needed for the socket to wait for the server response
    if len(data) < 1: # break when there is no more data to receive
        break
    print(data.decode(),end='') # decode return the value in unicode from UTF-8
    

mysock.close() # close socket connection