INSERT INTO time_deposits (id, plan_type, days, balance) VALUES
    (1, 'basic', 90, 1000.00),
    (2, 'student', 200, 2000.00),
    (3, 'premium', 60, 3000.00)
ON CONFLICT (id) DO NOTHING;

INSERT INTO withdrawals (id, time_deposit_id, amount, date) VALUES
    (1, 1, 50.00, DATE '2026-01-15'),
    (2, 2, 100.00, DATE '2026-01-20'),
    (3, 3, 150.00, DATE '2026-01-25')
ON CONFLICT (id) DO NOTHING;
