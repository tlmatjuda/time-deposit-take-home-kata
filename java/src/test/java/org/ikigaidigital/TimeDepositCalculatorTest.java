package org.ikigaidigital;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class TimeDepositCalculatorTest {

    private final TimeDepositCalculator calculator = new TimeDepositCalculator();

    @Test
    void shouldNotApplyInterestForFirst30Days() {
        TimeDeposit deposit = new TimeDeposit(1, "basic", 1000.00, 30);

        calculator.updateBalance(List.of(deposit));

        assertThat(deposit.getBalance()).isEqualTo(1000.00);
    }

    @Test
    void shouldNotApplyStudentInterestAfter365Days() {
        TimeDeposit deposit = new TimeDeposit(2, "student", 2000.00, 366);

        calculator.updateBalance(List.of(deposit));

        assertThat(deposit.getBalance()).isEqualTo(2000.00);
    }

    @Test
    void shouldNotApplyPremiumInterestAtOrBelow45Days() {
        TimeDeposit deposit = new TimeDeposit(3, "premium", 3000.00, 45);

        calculator.updateBalance(List.of(deposit));

        assertThat(deposit.getBalance()).isEqualTo(3000.00);
    }

    @Test
    void shouldApplyBasicInterestAfter30Days() {
        TimeDeposit deposit = new TimeDeposit(4, "basic", 1200.00, 31);

        calculator.updateBalance(List.of(deposit));

        assertThat(deposit.getBalance()).isEqualTo(1201.00);
    }

    @Test
    void shouldRoundInterestHalfUpToTwoDecimalsBeforeAddingToBalance() {
        TimeDeposit deposit = new TimeDeposit(5, "basic", 1002.00, 31);

        calculator.updateBalance(List.of(deposit));

        assertThat(deposit.getBalance()).isEqualTo(1002.83);
    }
}
