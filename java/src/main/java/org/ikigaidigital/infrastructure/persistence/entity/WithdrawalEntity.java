package org.ikigaidigital.infrastructure.persistence.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "withdrawals")
public class WithdrawalEntity {

    @Id
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "time_deposit_id", nullable = false)
    private TimeDepositEntity timeDeposit;

    @Column(name = "amount", nullable = false, precision = 19, scale = 2)
    private BigDecimal amount;

    @Column(name = "date", nullable = false)
    private LocalDate date;

    protected WithdrawalEntity() {
    }

    public WithdrawalEntity(Long id, TimeDepositEntity timeDeposit, BigDecimal amount, LocalDate date) {
        this.id = id;
        this.timeDeposit = timeDeposit;
        this.amount = amount;
        this.date = date;
    }

    public Long getId() {
        return id;
    }

    public TimeDepositEntity getTimeDeposit() {
        return timeDeposit;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public LocalDate getDate() {
        return date;
    }
}
