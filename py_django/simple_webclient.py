'''
First launch world_simplest_webserver and than send a request to it
'''


import socket

mysock = socket.socket(socket.AF_INET, socket.SOCK_STREAM) # create a socket
'''
making a loop call (127.0.0.1 is the ipv of your machine)
nb. the port must be the same of the webserver -> 9000 in this case
'''
mysock.connect(('127.0.0.1', 9000)) 
cmd = 'GET http://data.pr4e.org/page1.htm HTTP/1.0\r\n\r\n'.encode() # \r\n\r\n -> press enter, leave a blank line, press enter again and leave a blank line
mysock.send(cmd)
