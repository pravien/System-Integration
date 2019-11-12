package nodea.bank;

/**
 * Loan
 */
public class Loan {

    private double amount;
    private double interest;
    private int durationInMonths;

    public Loan(double amount, double interest, int durationInMonths) {
        this.amount = amount;
        this.interest = interest;
        this.durationInMonths = durationInMonths;
    }

    public Loan() {
        
    }
    
    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public double getInterest() {
        return interest;
    }

    public void setInterest(double interest) {
        this.interest = interest;
    }

    public int getDurationInMonths() {
        return durationInMonths;
    }

    public void setDurationInMonths(int durationInMonths) {
        this.durationInMonths = durationInMonths;
    }
    

}