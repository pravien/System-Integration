package nodea.bank;

import java.time.Instant;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;

import org.springframework.stereotype.Component;

/**
 * LoanEngine
 */
@Component
public class LoanEngine {

    private static final int BASE_INTEREST = 4;

    public Collection<Loan> calculateLoans(double amount, int durationInMonths) {
        Collection<Loan> loans = new ArrayList<>();


        if (durationInMonths > 3) {
            loans.add(new Loan(amount, BASE_INTEREST + 1, durationInMonths + 3));
        }
        loans.add(new Loan(amount, BASE_INTEREST, durationInMonths));

        loans.add(new Loan(amount, BASE_INTEREST - 1, durationInMonths / 2));

        Calendar c = Calendar.getInstance();
        c.setTime(new Date());
        int dayOfWeek = c.get(Calendar.DAY_OF_WEEK);
        if (dayOfWeek == 3) {
            loans.add(new Loan(amount, BASE_INTEREST / 2, durationInMonths));
        }
        
        return loans;
    }
}