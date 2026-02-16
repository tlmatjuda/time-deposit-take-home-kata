package org.ikigaidigital.infrastructure.persistence.mapper;

import org.ikigaidigital.TimeDeposit;
import org.ikigaidigital.infrastructure.persistence.entity.TimeDepositEntity;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.List;

@Component
public class TimeDepositPersistenceMapper {

    public TimeDeposit toDomain(TimeDepositEntity entity) {
        return new TimeDeposit(
            Math.toIntExact(entity.getId()),
            entity.getPlanType(),
            entity.getBalance().doubleValue(),
            entity.getDays()
        );
    }

    public TimeDepositEntity toEntity(TimeDeposit domain) {
        return new TimeDepositEntity(
            (long) domain.getId(),
            domain.getPlanType(),
            domain.getDays(),
            BigDecimal.valueOf(domain.getBalance())
        );
    }

    public List<TimeDeposit> toDomainList(List<TimeDepositEntity> entities) {
        return entities.stream().map(this::toDomain).toList();
    }

    public List<TimeDepositEntity> toEntityList(List<TimeDeposit> domains) {
        return domains.stream().map(this::toEntity).toList();
    }
}
