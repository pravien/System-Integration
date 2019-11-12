import requests
import xml.etree.ElementTree as ET

class JavaBankAdapter:

    def __init__(self, bankUrl):
        self.bankUrl = bankUrl


    def getLoans(self, amount, duration):
        headers = { 'Accept': 'application/xml', 'Content-Type': 'application/xml'}
        r = requests.post(self.bankUrl, data=self.encodeRequest(amount, duration), headers=headers)
        if r.status_code == 200:
            print(r.text)
            return self.decodeResponse(r.text)
        else:
            raise Exception('Unknown response status' + str(r.status_code) + ' content: ' + r.text)

    def encodeRequest(self, amount, duration):
        return "<LoanRequest><amount>{0}</amount><durationInMonths>{1}</durationInMonths></LoanRequest>".format(amount, duration)

    def decodeResponse(self, respStr):
        loans = []
        loanrequest = ET.fromstring(respStr)

        for loan in loanrequest.iter('loans'):
            l = {}
            for param in list(loan):
                val=param.text
                if param.tag == 'amount' or param.tag == 'interest':
                    val = float(val)
                elif param.tag == 'durationInMonths':
                    val = int(val)
                l[param.tag] = val
            loans.append(l)
        
        return loans
        
