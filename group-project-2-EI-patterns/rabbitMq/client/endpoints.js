async function fetchLoans() {
    var amount = document.getElementById('amount').value
    var periode = document.getElementById('periode').value
    url = "http://localhost:3050/?amount=" + amount + "&periode=" + periode
    console.log(url)
        fetch(url).then(response => 
            response.json().then(data => (
                makeTable(data)
            )
        ));

}

function makeTable(list) {
    console.log(list)
    for(i = 0; i < list.length; i++){
        var elm = list[i]
        document.getElementById("demo").innerHTML += 
        "amount : "+elm.amount+
        " | interest : "+elm.interest+
        " | durationInMonths : "+elm.durationInMonths+
        "</br>"
    }
}