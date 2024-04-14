CREATE TABLE BUSINESS (
    id INTEGER PRIMARY KEY,
    name VARCHAR(30)
    phone_number VARCHAR(12),
    email VARCHAR(50),
    created_at TIMESTAMPTZ NOT NULL,
    changed_at TIMESTAMPTZ NOT NULL,
    active BOOLEAN DEFAULT FALSE,
);

INSERT INTO BUSINESS (id, name, phone_number, email, created_at, changed_at, active)
VALUES
(1, 'ABC Company', '123-456-7890', 'abc@example.com', now(), now(), TRUE),
(2, 'XYZ Corporation', '987-654-3210', 'xyz@example.com', now(), now(), TRUE),
(3, 'Smith & Sons', '555-123-4567', 'smith@example.com', now(), now(), FALSE),
(4, 'Johnson Ltd.', '444-555-6666', 'johnson@example.com', now(), now(), TRUE),
(5, 'Miller Enterprises', '777-888-9999', 'miller@example.com', now(), now(), FALSE),
(6, 'Anderson Co.', '333-222-1111', 'anderson@example.com', now(), now(), TRUE),
(7, 'Wilson Group', '999-888-7777', 'wilson@example.com', now(), now(), TRUE),
(8, 'Brown Industries', '666-555-4444', 'brown@example.com', now(), now(), FALSE),
(9, 'Taylor Corp.', '222-333-4444', 'taylor@example.com', now(), now(), TRUE),
(10, 'Clark Enterprises', '888-999-0000', 'clark@example.com', now(), now(), TRUE),
(11, 'Adams & Co.', '111-222-3333', 'adams@example.com', now(), now(), FALSE),
(12, 'Robinson Ltd.', '444-333-2222', 'robinson@example.com', now(), now(), TRUE),
(13, 'Harris Holdings', '777-666-5555', 'harris@example.com', now(), now(), FALSE),
(14, 'Martinez Group', '999-888-7777', 'martinez@example.com', now(), now(), TRUE),
(15, 'Garcia Corporation', '333-222-1111', 'garcia@example.com', now(), now(), TRUE),
(16, 'Lee & Sons', '555-444-3333', 'lee@example.com', now(), now(), FALSE),
(17, 'Wright Enterprises', '888-777-6666', 'wright@example.com', now(), now(), TRUE),
(18, 'Scott Ltd.', '222-111-0000', 'scott@example.com', now(), now(), FALSE),
(19, 'King Co.', '111-222-3333', 'king@example.com', now(), now(), TRUE),
(20, 'Green Group', '444-555-6666', 'green@example.com', now(), now(), TRUE);
