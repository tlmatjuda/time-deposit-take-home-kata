package org.ikigaidigital.infrastructure.persistence.repository;

import org.ikigaidigital.infrastructure.persistence.entity.TimeDepositEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SpringDataTimeDepositRepository extends JpaRepository<TimeDepositEntity, Long> {
}
