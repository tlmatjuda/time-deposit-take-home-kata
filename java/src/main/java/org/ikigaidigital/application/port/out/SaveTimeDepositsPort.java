package org.ikigaidigital.application.port.out;

import org.ikigaidigital.TimeDeposit;

import java.util.List;

public interface SaveTimeDepositsPort {
    void saveAll(List<TimeDeposit> timeDeposits);
}
