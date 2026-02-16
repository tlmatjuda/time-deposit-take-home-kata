package org.ikigaidigital.application.port.in;

import org.ikigaidigital.TimeDeposit;

import java.util.List;

public interface UpdateAllBalancesUseCase {
    List<TimeDeposit> updateAllBalances();
}
