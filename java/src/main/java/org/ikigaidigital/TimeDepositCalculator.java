package org.ikigaidigital;

import org.ikigaidigital.domain.interest.BasicInterestPolicy;
import org.ikigaidigital.domain.interest.InterestPolicyRegistry;
import org.ikigaidigital.domain.interest.PremiumInterestPolicy;
import org.ikigaidigital.domain.interest.StudentInterestPolicy;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

public class TimeDepositCalculator {
    private static final int GLOBAL_INTEREST_START_DAY = 30;
    private final InterestPolicyRegistry interestPolicyRegistry;

    public TimeDepositCalculator() {
        this.interestPolicyRegistry = new InterestPolicyRegistry(
            List.of(
                new StudentInterestPolicy(),
                new PremiumInterestPolicy(),
                new BasicInterestPolicy()
            )
        );
    }

    public void updateBalance(List<TimeDeposit> xs) {
        for (TimeDeposit timeDeposit : xs) {
            double interest = calculateInterest(timeDeposit);
            double roundedInterest = new BigDecimal(interest).setScale(2, RoundingMode.HALF_UP).doubleValue();
            timeDeposit.setBalance(timeDeposit.getBalance() + roundedInterest);
        }
    }

    private double calculateInterest(TimeDeposit timeDeposit) {
        if (timeDeposit.getDays() <= GLOBAL_INTEREST_START_DAY) {
            return 0;
        }
        return interestPolicyRegistry.calculateInterest(timeDeposit);
    }
}
