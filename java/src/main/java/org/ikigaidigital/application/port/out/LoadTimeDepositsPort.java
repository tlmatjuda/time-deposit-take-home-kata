package org.ikigaidigital.application.port.out;

import org.ikigaidigital.TimeDeposit;

import java.util.List;

public interface LoadTimeDepositsPort {
    List<TimeDeposit> loadAll();
}
