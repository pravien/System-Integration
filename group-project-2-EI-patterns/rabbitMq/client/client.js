#!/usr/bin/env node

var amqp = require('amqplib/callback_api');
var express = require('express')
var cors = require('cors')
const app = express()

const port = 3050
app.use(cors())
app.get('/', async function(req,res){
    var amount = req.query.amount
    var peri = req.query.periode
    amqp.connect('amqp://localhost', function(error0, connection) {
        if (error0) {
            throw error0;
        }
        connection.createChannel(function(error1, channel) {
            if (error1) {
                throw error1;
            }
            channel.assertQueue('', {
                exclusive: true
            }, function(error2, q) {
                if (error2) {
                    throw error2;
                }
                var correlationId = generateUuid();
                var num = parseInt(amount);
                let temp = ','+peri
    
                console.log(' [x] Requesting fib(%d)', num);
    
                channel.consume(q.queue, function(msg) {
                    if (msg.properties.correlationId === correlationId) {
                        console.log(' [.] Got %s', msg.content.toString());
                        update(msg)
                        setTimeout(function() {
                            connection.close();
                        }, 500);
                    }
                }, {
                    noAck: true
                });
    
                channel.sendToQueue('rpc_queue',
                    Buffer.from(num.toString()+temp), {
                        correlationId: correlationId,
                        replyTo: q.queue
                    });
            });
        });
    }).then((result) => {
        console.log(result)
    });
}) 
app.listen(port)


async function requestLoans(amount, peri){
    return await amqp.connect('amqp://localhost', function(error0, connection) {
        if (error0) {
            throw error0;
        }
        connection.createChannel(function(error1, channel) {
            if (error1) {
                throw error1;
            }
            channel.assertQueue('', {
                exclusive: true
            }, function(error2, q) {
                if (error2) {
                    throw error2;
                }
                var correlationId = generateUuid();
                var num = parseInt(amount);
                let temp = ','+peri
    
                console.log(' [x] Requesting fib(%d)', num);
    
                channel.consume(q.queue, function(msg) {
                    if (msg.properties.correlationId === correlationId) {
                        console.log(' [.] Got %s', msg.content.toString());
                        setTimeout(function() {
                            connection.close();
                        }, 500);
                    }
                }, {
                    noAck: true
                });
    
                channel.sendToQueue('rpc_queue',
                    Buffer.from(num.toString()+temp), {
                        correlationId: correlationId,
                        replyTo: q.queue
                    });
            });
        });
    });
}
function update(){
    result = msg.content.toString();
}
function generateUuid() {
    return Math.random().toString() +
        Math.random().toString() +
        Math.random().toString();
}