package org.ikigaidigital.domain.interest;

import org.ikigaidigital.TimeDeposit;

public class StudentInterestPolicy implements InterestPolicy {
    private static final String PLAN_TYPE = "student";
    private static final int INTEREST_END_DAY = 366;
    private static final double RATE = 0.03;

    @Override
    public boolean supports(String planType) {
        return PLAN_TYPE.equals(planType);
    }

    @Override
    public double calculateInterest(TimeDeposit timeDeposit) {
        if (timeDeposit.getDays() < INTEREST_END_DAY) {
            return timeDeposit.getBalance() * RATE / 12;
        }
        return 0;
    }
}
