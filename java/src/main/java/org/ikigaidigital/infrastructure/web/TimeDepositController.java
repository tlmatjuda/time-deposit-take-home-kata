package org.ikigaidigital.infrastructure.web;

import org.ikigaidigital.application.port.in.GetAllTimeDepositsUseCase;
import org.ikigaidigital.application.port.in.UpdateAllBalancesUseCase;
import org.ikigaidigital.infrastructure.web.dto.TimeDepositResponse;
import org.ikigaidigital.infrastructure.web.mapper.TimeDepositApiMapper;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(path = "/time-deposits", produces = MediaType.APPLICATION_JSON_VALUE)
public class TimeDepositController {

    private final GetAllTimeDepositsUseCase getAllTimeDepositsUseCase;
    private final UpdateAllBalancesUseCase updateAllBalancesUseCase;
    private final TimeDepositApiMapper apiMapper;

    public TimeDepositController(
        GetAllTimeDepositsUseCase getAllTimeDepositsUseCase,
        UpdateAllBalancesUseCase updateAllBalancesUseCase,
        TimeDepositApiMapper apiMapper
    ) {
        this.getAllTimeDepositsUseCase = getAllTimeDepositsUseCase;
        this.updateAllBalancesUseCase = updateAllBalancesUseCase;
        this.apiMapper = apiMapper;
    }

    @GetMapping
    public List<TimeDepositResponse> getAllTimeDeposits() {
        return apiMapper.toResponseList(getAllTimeDepositsUseCase.getAll());
    }

    @PatchMapping("/balances")
    public List<TimeDepositResponse> updateAllBalances() {
        return apiMapper.toResponseList(updateAllBalancesUseCase.updateAllBalances());
    }
}
