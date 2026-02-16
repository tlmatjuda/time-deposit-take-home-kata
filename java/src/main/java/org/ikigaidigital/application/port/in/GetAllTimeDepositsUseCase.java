package org.ikigaidigital.application.port.in;

import org.ikigaidigital.TimeDeposit;

import java.util.List;

public interface GetAllTimeDepositsUseCase {
    List<TimeDeposit> getAll();
}
