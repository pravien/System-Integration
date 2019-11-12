package nodea.bank;

import java.util.Collection;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;

/**
 * LoanResponse
 */
public class LoanResponse {


    @JacksonXmlElementWrapper(useWrapping = false)
    private Collection<Loan> loans;

    public Collection<Loan> getLoans() {
        return loans;
    }

    public void setLoans(Collection<Loan> loans) {
        this.loans = loans;
    }

    public LoanResponse(Collection<Loan> loans) {
        this.loans = loans;
    }

    
}