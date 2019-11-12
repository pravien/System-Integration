package nodea.bank;

import java.util.Collection;

/**
 * LoanResponse
 */
public class LoanResponse {

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