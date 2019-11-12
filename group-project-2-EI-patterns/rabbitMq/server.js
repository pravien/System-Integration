#!/usr/bin/env node

var amqp = require('amqplib/callback_api');

amqp.connect('amqp://localhost', function(error0, connection) {
    if (error0) {
        throw error0;
    }
    connection.createChannel(function(error1, channel) {
        if (error1) {
            throw error1;
        }
        var queue = 'rpc_queue';

        channel.assertQueue(queue, {
            durable: false
        });
        channel.prefetch(1);
        console.log(' [x] Awaiting RPC requests');
        channel.consume(queue, function reply(msg) {
            console.log(msg+"message")
            msg_ = msg.content.toString()
            var temp = msg_.split(',')
            let message = temp[1]
            let number = temp[0]
            var n = parseInt(number.toString());
            console.log(` [.] fib(${number}),message ${message}`, );

            var r = fibonacci(n);

            channel.sendToQueue(msg.properties.replyTo,
                Buffer.from(r.toString()+message), {
                    correlationId: msg.properties.correlationId
                });

            channel.ack(msg);
        });
    });
});

function fibonacci(n) {
    if (n === 0 || n === 1)
        return n;
    else
        return fibonacci(n - 1) + fibonacci(n - 2);
}