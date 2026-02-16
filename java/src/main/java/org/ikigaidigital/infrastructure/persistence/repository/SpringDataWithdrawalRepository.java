package org.ikigaidigital.infrastructure.persistence.repository;

import org.ikigaidigital.infrastructure.persistence.entity.WithdrawalEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SpringDataWithdrawalRepository extends JpaRepository<WithdrawalEntity, Long> {
}
