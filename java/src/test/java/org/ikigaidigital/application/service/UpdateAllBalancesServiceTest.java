package org.ikigaidigital.application.service;

import org.ikigaidigital.TimeDeposit;
import org.ikigaidigital.TimeDepositCalculator;
import org.ikigaidigital.application.port.out.LoadTimeDepositsPort;
import org.ikigaidigital.application.port.out.SaveTimeDepositsPort;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class UpdateAllBalancesServiceTest {

    @Test
    void shouldUpdateAndPersistAllDeposits() {
        List<TimeDeposit> loaded = new ArrayList<>();
        loaded.add(new TimeDeposit(1, "basic", 1200.00, 31));

        LoadTimeDepositsPort loadPort = () -> loaded;
        CapturingSavePort savePort = new CapturingSavePort();

        UpdateAllBalancesService service = new UpdateAllBalancesService(
            loadPort,
            savePort,
            new TimeDepositCalculator()
        );

        List<TimeDeposit> updated = service.updateAllBalances();

        assertThat(updated).isSameAs(loaded);
        assertThat(updated.get(0).getBalance()).isEqualTo(1201.00);
        assertThat(savePort.saved).isSameAs(loaded);
    }

    private static class CapturingSavePort implements SaveTimeDepositsPort {
        private List<TimeDeposit> saved;

        @Override
        public void saveAll(List<TimeDeposit> timeDeposits) {
            this.saved = timeDeposits;
        }
    }
}
