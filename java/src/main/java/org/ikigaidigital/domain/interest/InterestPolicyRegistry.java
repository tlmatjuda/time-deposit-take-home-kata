package org.ikigaidigital.domain.interest;

import org.ikigaidigital.TimeDeposit;

import java.util.List;

public class InterestPolicyRegistry {
    private final List<InterestPolicy> policies;

    public InterestPolicyRegistry(List<InterestPolicy> policies) {
        this.policies = policies;
    }

    public double calculateInterest(TimeDeposit timeDeposit) {
        for (InterestPolicy policy : policies) {
            if (policy.supports(timeDeposit.getPlanType())) {
                return policy.calculateInterest(timeDeposit);
            }
        }
        return 0;
    }
}
