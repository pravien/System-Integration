const env = process.env;
const amqp = require('amqplib/callback_api');

const mqHost = env.MQ_HOST || 'amqp://localhost';


function encodeMessage(amount, period) {
  return JSON.stringify({ amount: amount, period: period });
}


function generateUuid() {
  return Math.random().toString() +
      Math.random().toString() +
      Math.random().toString();
}

function getLoans(amount, periodInDays) {
  var correlationId = generateUuid();

  return new Promise((resolv, reject) => {
    // TODO cleanup callbacks
    amqp.connect(mqHost, function (error0, connection) {
      if (error0) return reject(error0);

      connection.createChannel(function (error1, channel) {
          if (error1) return reject(error1);

          channel.assertQueue('', {
              exclusive: true
          }, function (error2, q) {
              if (error2) return reject(error2);
              
              channel.consume(q.queue, function (msg) {
                  if (msg.properties.correlationId === correlationId) {
                      console.log('Received message %s', msg.content.toString());
                      resolv(msg.content.toString()); // TODO deserialize

                      // TODO
                      setTimeout(function () {
                          connection.close();
                      }, 500);
                  }
              }, {
                  noAck: true
              });

              let msg = encodeMessage(amount, periodInDays);
              console.log('Publish to loan_requests queue message: ', msg);
              channel.sendToQueue('loan_requests',
                  Buffer.from(msg, 'utf8'), {
                  correlationId: correlationId,
                  replyTo: q.queue
              });
          });
      });
  })
  });
}

module.exports = {
  getLoans,
}