package org.ikigaidigital;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

public class TimeDepositCalculatorTest {

    private final TimeDepositCalculator calculator = new TimeDepositCalculator();

    @ParameterizedTest(name = "{0}")
    @MethodSource("updateBalanceCases")
    void shouldUpdateBalanceAsExpected(
        String scenario,
        String planType,
        double initialBalance,
        int days,
        double expectedBalance
    ) {
        TimeDeposit deposit = new TimeDeposit(1, planType, initialBalance, days);

        calculator.updateBalance(List.of(deposit));

        assertThat(deposit.getBalance()).isEqualTo(expectedBalance);
    }

    private static Stream<Arguments> updateBalanceCases() {
        return Stream.of(
            Arguments.of("No interest in first 30 days", "basic", 1000.00, 30, 1000.00),
            Arguments.of("No student interest after 365 days", "student", 2000.00, 366, 2000.00),
            Arguments.of("No premium interest at or below day 45", "premium", 3000.00, 45, 3000.00),
            Arguments.of("Basic interest after day 30", "basic", 1200.00, 31, 1201.00),
            Arguments.of("Preserve current rounding behavior", "basic", 1002.00, 31, 1002.83)
        );
    }
}
