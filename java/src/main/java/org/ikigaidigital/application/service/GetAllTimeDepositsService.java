package org.ikigaidigital.application.service;

import org.ikigaidigital.TimeDeposit;
import org.ikigaidigital.application.port.in.GetAllTimeDepositsUseCase;
import org.ikigaidigital.application.port.out.LoadTimeDepositsPort;

import java.util.List;

public class GetAllTimeDepositsService implements GetAllTimeDepositsUseCase {
    private final LoadTimeDepositsPort loadTimeDepositsPort;

    public GetAllTimeDepositsService(LoadTimeDepositsPort loadTimeDepositsPort) {
        this.loadTimeDepositsPort = loadTimeDepositsPort;
    }

    @Override
    public List<TimeDeposit> getAll() {
        return loadTimeDepositsPort.loadAll();
    }
}
