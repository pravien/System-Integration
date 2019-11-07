const express = require('express')
const app = express()
const port = 3000
const paraChecker = require('parameterCheck')
app.use(paraChecker)
const name = 'Nordea'
function calculateInterest(month){
    if (month < 12){
        return 5.00;
    }
    else if (month < 16){
        return 4.5;
    }
    else{
        return 3.5;
    }
}

app.get('/', paraChecker, function (req,res){
    var month = req.query.month;
    var amount = req.query.amount;

    let ses = calculateInterest(month);
    const jsonO = {
        'name' : name,
        'month' : month,
        'amount' : amount,
        'interest' : ses
    }
    res.send(jsonO)

})

app.listen(port)