package org.ikigaidigital.infrastructure.web.dto;

import java.math.BigDecimal;
import java.time.LocalDate;

public record WithdrawalResponse(
    Long id,
    BigDecimal amount,
    LocalDate date
) {
}
