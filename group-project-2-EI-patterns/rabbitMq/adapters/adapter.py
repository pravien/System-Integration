import os
import sys
import pika
import json
from nodebank_adapter import NodeBankAdapter
from javabank_adapter import JavaBankAdapter

momHost="localhost";


bankUrl_java="http://localhost:8080/"
adapter_java=JavaBankAdapter(bankUrl_java)

bankUrl_node="http://localhost:3000/"
adapter_node=NodeBankAdapter(bankUrl_node)


connection = pika.BlockingConnection(
    pika.ConnectionParameters(host=momHost))
channel = connection.channel()
channel.queue_declare(queue='loan_requests')

def callback(ch, method, properties, body):
    print(" [x] Received %r" % body)

    response = None
    try:
        msg = json.loads(body.decode("utf-8"))
        loan_java = adapter_java.getLoans(msg["amount"], msg["period"])
        loan_node = adapter_node.getLoans(msg["amount"], msg["period"])
        loans = loan_java+loan_node
        response = json.dumps(loans)

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