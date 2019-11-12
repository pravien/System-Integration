import os
import sys
import pika
import json
from nodebank_adapter import NodeBankAdapter
from javabank_adapter import JavaBankAdapter

momHost="localhost";

if "ADAPTER" not in os.environ:
    print("Please pass an 'ADAPTER=nodebank|javabank' env variable")
    sys.exit(1)

if os.environ["ADAPTER"].lower() == 'javabank':
    bankUrl="http://localhost:8080/"
    adapter=JavaBankAdapter(bankUrl)
else:
    bankUrl="http://localhost:3000/"
    adapter=NodeBankAdapter(bankUrl)


connection = pika.BlockingConnection(
    pika.ConnectionParameters(host=momHost))
channel = connection.channel()
channel.queue_declare(queue='loan_requests')

def callback(ch, method, properties, body):
    print(" [x] Received %r" % body)

    response = None
    try:
        msg = json.loads(body.decode("utf-8"))
        loan = adapter.getLoans(msg["amount"], msg["period"])
        response = json.dumps(loan)

        print(" [X] Sent %r" % response)
    except Exception as e: 
        print(e)
        response = 'adapter error, see adapter logs'
    finally:
        channel.basic_publish(exchange='',
                    routing_key=properties.reply_to,
                    properties=pika.BasicProperties(correlation_id = properties.correlation_id),
                    body=response)



channel.basic_consume(queue='loan_requests', on_message_callback=callback, auto_ack=True)
print(' [*] Waiting for messages. To exit press CTRL+C')
channel.start_consuming()
