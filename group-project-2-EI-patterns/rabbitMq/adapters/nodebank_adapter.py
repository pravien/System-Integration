import requests 

class NodeBankAdapter:

    def __init__(self, bankUrl):
        self.bankUrl = bankUrl

    def getLoans(self, amount, duration):
        r = requests.get(self.bankUrl+"?amount=" + str(amount) + "&month=" + str(duration))
        if r.status_code == 200:
            return [r.json()]
        else:
            raise Exception('Unknown response status' + str(r.status_code) + ' content: ' + r.text)