#!/usr/bin/env node

var express = require('express')
var cors = require('cors')
const app = express()
// Technically also a pattern
const loanGateway = require('./src/loanGateway');

const port = 3050
app.use(cors())
app.get('/', async function (req, res) {
    var amount = req.query.amount
    var peri = req.query.periode

    if (amount === undefined || peri === undefined) {
        console.error('invalid client request', amount, peri);
        res.status(422);
        res.send("Amount and periode must be integer");
        return;
    }

    amount = parseInt(amount);
    peri = parseInt(peri);
    
    loanGateway.getLoans(amount, peri).then(function (loans) {
        console.log('success', loans);
        res.send(loans);
    }).catch(function (err) {
        console.error(err);
        res.status(500);
        res.send(err);
    })
})
app.listen(port)
