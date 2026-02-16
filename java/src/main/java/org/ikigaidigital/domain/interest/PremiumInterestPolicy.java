package org.ikigaidigital.domain.interest;

import org.ikigaidigital.TimeDeposit;

public class PremiumInterestPolicy implements InterestPolicy {
    private static final String PLAN_TYPE = "premium";
    private static final int INTEREST_START_DAY = 45;
    private static final double RATE = 0.05;

    @Override
    public boolean supports(String planType) {
        return PLAN_TYPE.equals(planType);
    }

    @Override
    public double calculateInterest(TimeDeposit timeDeposit) {
        if (timeDeposit.getDays() > INTEREST_START_DAY) {
            return timeDeposit.getBalance() * RATE / 12;
        }
        return 0;
    }
}
