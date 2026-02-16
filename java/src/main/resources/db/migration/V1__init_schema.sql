CREATE TABLE IF NOT EXISTS time_deposits (
    id BIGINT PRIMARY KEY,
    plan_type VARCHAR(50) NOT NULL,
    days INTEGER NOT NULL,
    balance NUMERIC(19, 2) NOT NULL
);

CREATE TABLE IF NOT EXISTS withdrawals (
    id BIGINT PRIMARY KEY,
    time_deposit_id BIGINT NOT NULL,
    amount NUMERIC(19, 2) NOT NULL,
    date DATE NOT NULL,
    CONSTRAINT fk_withdrawals_time_deposit
        FOREIGN KEY (time_deposit_id)
        REFERENCES time_deposits(id)
);

CREATE INDEX IF NOT EXISTS idx_withdrawals_time_deposit_id
    ON withdrawals(time_deposit_id);
