package org.ikigaidigital.infrastructure.web.mapper;

import org.ikigaidigital.TimeDeposit;
import org.ikigaidigital.infrastructure.web.dto.TimeDepositResponse;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class TimeDepositApiMapper {

    public TimeDepositResponse toResponse(TimeDeposit timeDeposit) {
        return new TimeDepositResponse(
            timeDeposit.getId(),
            timeDeposit.getPlanType(),
            timeDeposit.getBalance(),
            timeDeposit.getDays(),
            List.of()
        );
    }

    public List<TimeDepositResponse> toResponseList(List<TimeDeposit> timeDeposits) {
        return timeDeposits.stream().map(this::toResponse).toList();
    }
}
