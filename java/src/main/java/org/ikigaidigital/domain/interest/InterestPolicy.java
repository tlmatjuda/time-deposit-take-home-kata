package org.ikigaidigital.domain.interest;

import org.ikigaidigital.TimeDeposit;

public interface InterestPolicy {
    boolean supports(String planType);

    double calculateInterest(TimeDeposit timeDeposit);
}
