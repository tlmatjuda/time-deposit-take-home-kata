package org.ikigaidigital.domain.interest;

import org.ikigaidigital.TimeDeposit;

public class BasicInterestPolicy implements InterestPolicy {
    private static final String PLAN_TYPE = "basic";
    private static final double RATE = 0.01;

    @Override
    public boolean supports(String planType) {
        return PLAN_TYPE.equals(planType);
    }

    @Override
    public double calculateInterest(TimeDeposit timeDeposit) {
        return timeDeposit.getBalance() * RATE / 12;
    }
}
