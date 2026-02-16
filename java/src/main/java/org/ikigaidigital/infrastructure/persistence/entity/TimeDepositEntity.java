package org.ikigaidigital.infrastructure.persistence.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "time_deposits")
public class TimeDepositEntity {

    @Id
    private Long id;

    @Column(name = "plan_type", nullable = false)
    private String planType;

    @Column(name = "days", nullable = false)
    private Integer days;

    @Column(name = "balance", nullable = false, precision = 19, scale = 2)
    private BigDecimal balance;

    @OneToMany(mappedBy = "timeDeposit")
    private List<WithdrawalEntity> withdrawals = new ArrayList<>();

    protected TimeDepositEntity() {
    }

    public TimeDepositEntity(Long id, String planType, Integer days, BigDecimal balance) {
        this.id = id;
        this.planType = planType;
        this.days = days;
        this.balance = balance;
    }

    public Long getId() {
        return id;
    }

    public String getPlanType() {
        return planType;
    }

    public Integer getDays() {
        return days;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public List<WithdrawalEntity> getWithdrawals() {
        return withdrawals;
    }
}
