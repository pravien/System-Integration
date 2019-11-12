package nodea.bank.controller;

import java.util.Collection;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import nodea.bank.Loan;
import nodea.bank.LoanEngine;
import nodea.bank.LoanRequest;
import nodea.bank.LoanResponse;

/**
 * BankController
 */
@RestController
public class BankController {

    private static Logger logger = LoggerFactory.getLogger(BankController.class); 
    
    @Autowired
    private LoanEngine loanEngine;

    @RequestMapping(path = "/", method =  RequestMethod.POST, consumes = MediaType.APPLICATION_XML_VALUE, produces = MediaType.APPLICATION_XML_VALUE)
    public @ResponseBody() LoanResponse test(@RequestBody() LoanRequest request) {
        logger.info("Received loan request: " + request.toString());

        Collection<Loan> loans = loanEngine.calculateLoans(request.getAmount(), request.getDurationInMonths());

        return new LoanResponse(loans);
    }
    
}