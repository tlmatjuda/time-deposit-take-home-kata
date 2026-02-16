package org.ikigaidigital.application.service;

import org.ikigaidigital.TimeDeposit;
import org.ikigaidigital.application.port.out.LoadTimeDepositsPort;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class GetAllTimeDepositsServiceTest {

    @Test
    void shouldReturnAllDepositsFromLoadPort() {
        List<TimeDeposit> expected = List.of(new TimeDeposit(1, "basic", 1000.00, 31));
        LoadTimeDepositsPort loadPort = () -> expected;
        GetAllTimeDepositsService service = new GetAllTimeDepositsService(loadPort);

        List<TimeDeposit> actual = service.getAll();

        assertThat(actual).isSameAs(expected);
    }
}
