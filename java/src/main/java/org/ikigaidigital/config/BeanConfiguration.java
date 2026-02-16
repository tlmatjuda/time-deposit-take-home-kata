package org.ikigaidigital.config;

import org.ikigaidigital.TimeDepositCalculator;
import org.ikigaidigital.application.port.in.GetAllTimeDepositsUseCase;
import org.ikigaidigital.application.port.in.UpdateAllBalancesUseCase;
import org.ikigaidigital.application.port.out.LoadTimeDepositsPort;
import org.ikigaidigital.application.port.out.SaveTimeDepositsPort;
import org.ikigaidigital.application.service.GetAllTimeDepositsService;
import org.ikigaidigital.application.service.UpdateAllBalancesService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeanConfiguration {

    @Bean
    public TimeDepositCalculator timeDepositCalculator() {
        return new TimeDepositCalculator();
    }

    @Bean
    public GetAllTimeDepositsUseCase getAllTimeDepositsUseCase(LoadTimeDepositsPort loadTimeDepositsPort) {
        return new GetAllTimeDepositsService(loadTimeDepositsPort);
    }

    @Bean
    public UpdateAllBalancesUseCase updateAllBalancesUseCase(
        LoadTimeDepositsPort loadTimeDepositsPort,
        SaveTimeDepositsPort saveTimeDepositsPort,
        TimeDepositCalculator timeDepositCalculator
    ) {
        return new UpdateAllBalancesService(loadTimeDepositsPort, saveTimeDepositsPort, timeDepositCalculator);
    }
}
