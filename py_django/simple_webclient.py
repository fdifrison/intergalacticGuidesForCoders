'''
First launch world_simplest_webserver and than send a request to it
'''

def make_request_with_socket():

    import socket

    mysock = socket.socket(socket.AF_INET, socket.SOCK_STREAM) # create a socket
    '''
    making a loop call (127.0.0.1 is the ipv of your machine)
    nb. the port must be the same of the webserver -> 9000 in this case
    '''
    mysock.connect(('127.0.0.1', 9000))  # will fail if the webserve is not running
    cmd = 'GET http://127.0.0.1/romeo.txt HTTP/1.0\r\n\r\n'.encode() # \r\n\r\n -> press enter, leave a blank line, press enter again and leave a blank line
    mysock.send(cmd)

    # loop to return the request
    while True:
        data = mysock.recv(512) # 512 is the length of the string we are expecting to receive, it is needed for the socket to wait for the server response
        if len(data) < 1: # break when there is no more data to receive
            break
        print(data.decode(),end='') # decode return the value in unicode from UTF-8
        

    mysock.close() # close socket connection
    
    return 'connection closed'


'''
We can achive the same client with higher level packages, without creating directly the socket
'''

def make_request_with_urllib():
    import urllib.request

    fhand = urllib.request.urlopen('http://localhost:9000/romero.txt') # localhost == 127.0.0.1
    for line in fhand:
        print(line.decode().strip())


if __name__ == '__main__':
    make_request_with_urllib()