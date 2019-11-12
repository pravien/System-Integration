async function fetchLoans(){
    var amount = document.getElementsByName('amount').value
    var periode = document.getElementsByName('periode').value
    url = "http://localhost:3050/?amount="+amount+"&periode="+periode
    var result = await fetch(url)
    console.log(await result.json())
}