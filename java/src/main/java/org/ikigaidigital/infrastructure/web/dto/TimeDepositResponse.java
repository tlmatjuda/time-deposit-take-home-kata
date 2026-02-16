package org.ikigaidigital.infrastructure.web.dto;

import java.util.List;

public record TimeDepositResponse(
    Integer id,
    String planType,
    Double balance,
    Integer days,
    List<WithdrawalResponse> withdrawals
) {
}
