package org.ikigaidigital.infrastructure.persistence.adapter;

import org.ikigaidigital.TimeDeposit;
import org.ikigaidigital.application.port.out.LoadTimeDepositsPort;
import org.ikigaidigital.application.port.out.SaveTimeDepositsPort;
import org.ikigaidigital.infrastructure.persistence.mapper.TimeDepositPersistenceMapper;
import org.ikigaidigital.infrastructure.persistence.repository.SpringDataTimeDepositRepository;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class TimeDepositPersistenceAdapter implements LoadTimeDepositsPort, SaveTimeDepositsPort {

    private final SpringDataTimeDepositRepository timeDepositRepository;
    private final TimeDepositPersistenceMapper mapper;

    public TimeDepositPersistenceAdapter(
        SpringDataTimeDepositRepository timeDepositRepository,
        TimeDepositPersistenceMapper mapper
    ) {
        this.timeDepositRepository = timeDepositRepository;
        this.mapper = mapper;
    }

    @Override
    public List<TimeDeposit> loadAll() {
        return mapper.toDomainList(timeDepositRepository.findAll(Sort.by(Sort.Direction.ASC, "id")));
    }

    @Override
    public void saveAll(List<TimeDeposit> timeDeposits) {
        timeDepositRepository.saveAll(mapper.toEntityList(timeDeposits));
    }
}
