package nodea.bank;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * LoanRequest
 */
@XmlRootElement
public class LoanRequest implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 1231L;
    private double amount;
    private int durationInMonths;

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public int getDurationInMonths() {
        return durationInMonths;
    }

    public void setDurationInMonths(int durationInMonths) {
        this.durationInMonths = durationInMonths;
    }

    @Override
    public String toString() {
        return String.format("LoanRequest[amount=%f,durationInMonths:%d]", amount, durationInMonths);
    }

    
}