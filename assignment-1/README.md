# Assignment-1

## Running program

- Start the server by running the "Server.java".
- Start the client by running the "client.py".

## Client

Change "localhost" on line 4 in the "client.py" to the ip-address of the server. If the server is running on a different computer.

## Homework Programming Exercise: Bank Service

1. Create a Client application, which
opens connection to a server with a specific IP address and port
reads data from the user: account number and (+ or -) amount of money
sends the data to the Server

2. Create Server application that defines initial amount of money in the user’s account opens connection at the specific port and accepts to listen to the client
reads the request of the client and processes it:
if the user wants to make savings (+), add the input amount to the current amount
if the user wants to withdraw money (-), checks the availability, and then either subtracts the input amount from the current amount or prints a warning message
returns a response