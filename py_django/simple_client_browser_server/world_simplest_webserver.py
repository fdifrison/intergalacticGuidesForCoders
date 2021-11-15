from socket import *

'''
When the web server is up and running it is gonna be in an infinite loop, waiting for requests
'''

def createServer():
    serversocket = socket(AF_INET, SOCK_STREAM)
    try :
        # creating a socket that can receive on port 9000 of the machine
        # only one application per port can run
        serversocket.bind(('localhost', 9000))
        # listen serve to hold incominq request if one is already being processed
        # otherwise, if occupied it would instatntly deny the second request
        serversocket.listen(5)  
        while(1):
            # accept means that the web server is ready to answer the request
            # untill a request come in, the line belowe won't be executed
            (clientsocket, address) =  serversocket.accept()
            
            print('The call has been made!')
            # request and answer between browser and server can be simultaneous
            rd = clientsocket.recv(5000).decode()
            pieces = rd.split("\n") # split each line
            if (len(pieces) > 0) : print(pieces[0]) # look at the first line
            
            # construct the server response based on RFC 2616 -> obsolete
            # data will contain the structure of the server response
            data = "HTTP/1.1 200 OK\r\n"
            data += "Content-Type: Text/html: charset=utf-8\r\n"
            data += "\r\n"
            data += "<html><body>Hello World</body></html>\r\n\r\n"
            clientsocket.sendall(data.encode()) # encode python unicode before send
            clientsocket.shutdown(SHUT_WR) # close the connection server side
            # go back to the top and wait for next request

    except KeyboardInterrupt:
        print("\nShutting down...\n")
    except Exception as exc:
        print("Error:\n")
        print(exc)

    serversocket.close()


if __name__ == '__main__':
    print("Access http://localhost:9000")
    createServer() # begin the infinite loop; the server is up and running
