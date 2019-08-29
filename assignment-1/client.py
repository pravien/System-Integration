import socket

clientsocket = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
clientsocket.connect(('localhost', 59090))
bankacc = input('Insert bank account number : ')
amount = input('Insert amount : ')
out = bankacc+' '+amount+'\n'
clientsocket.send(out.encode('UTF-8'))
print(str(clientsocket.recv(1024)))