package org.ikigaidigital.application.service;

import org.ikigaidigital.TimeDeposit;
import org.ikigaidigital.TimeDepositCalculator;
import org.ikigaidigital.application.port.in.UpdateAllBalancesUseCase;
import org.ikigaidigital.application.port.out.LoadTimeDepositsPort;
import org.ikigaidigital.application.port.out.SaveTimeDepositsPort;

import java.util.List;

public class UpdateAllBalancesService implements UpdateAllBalancesUseCase {
    private final LoadTimeDepositsPort loadTimeDepositsPort;
    private final SaveTimeDepositsPort saveTimeDepositsPort;
    private final TimeDepositCalculator timeDepositCalculator;

    public UpdateAllBalancesService(
        LoadTimeDepositsPort loadTimeDepositsPort,
        SaveTimeDepositsPort saveTimeDepositsPort,
        TimeDepositCalculator timeDepositCalculator
    ) {
        this.loadTimeDepositsPort = loadTimeDepositsPort;
        this.saveTimeDepositsPort = saveTimeDepositsPort;
        this.timeDepositCalculator = timeDepositCalculator;
    }

    @Override
    public List<TimeDeposit> updateAllBalances() {
        List<TimeDeposit> timeDeposits = loadTimeDepositsPort.loadAll();
        timeDepositCalculator.updateBalance(timeDeposits);
        saveTimeDepositsPort.saveAll(timeDeposits);
        return timeDeposits;
    }
}
