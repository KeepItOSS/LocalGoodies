CREATE TABLE BUSINESS (
    id INTEGER PRIMARY KEY,
    name VARCHAR(30),
    phone_number VARCHAR(12),
    email VARCHAR(50),
    created_at TIMESTAMPTZ NOT NULL DEFAULT now(),
    changed_at TIMESTAMPTZ NOT NULL DEFAULT now(),
    active BOOLEAN DEFAULT FALSE,
    description TEXT
);

INSERT INTO BUSINESS (id, name, phone_number, email, created_at, changed_at, active, description)
VALUES
(1, 'ABC Company', '123-456-7890', 'abc@example.com', now(), now(), TRUE, 'This is a description for ABC Company'),
(2, 'XYZ Corporation', '987-654-3210', 'xyz@example.com', now(), now(), TRUE, 'Description for XYZ Corporation'),
(3, 'Smith & Sons', '555-123-4567', 'smith@example.com', now(), now(), FALSE, 'Smith & Sons - Providing quality services since 1990'),
(4, 'Johnson Ltd.', '444-555-6666', 'johnson@example.com', now(), now(), TRUE, 'Johnson Ltd. - A leader in the industry'),
(5, 'Miller Enterprises', '777-888-9999', 'miller@example.com', now(), now(), FALSE, 'Description for Miller Enterprises'),
(6, 'Anderson Co.', '333-222-1111', 'anderson@example.com', now(), now(), TRUE, 'Anderson Co. - Your trusted partner for all business needs'),
(7, 'Wilson Group', '999-888-7777', 'wilson@example.com', now(), now(), TRUE, 'Wilson Group - Innovation at its core'),
(8, 'Brown Industries', '666-555-4444', 'brown@example.com', now(), now(), FALSE, 'Brown Industries - Delivering excellence for over a decade'),
(9, 'Taylor Corp.', '222-333-4444', 'taylor@example.com', now(), now(), TRUE, 'Description for Taylor Corp.'),
(10, 'Clark Enterprises', '888-999-0000', 'clark@example.com', now(), now(), TRUE, 'Clark Enterprises - Building a better future'),
(11, 'Adams & Co.', '111-222-3333', 'adams@example.com', now(), now(), FALSE, 'Adams & Co. - Where quality meets commitment'),
(12, 'Robinson Ltd.', '444-333-2222', 'robinson@example.com', now(), now(), TRUE, 'Robinson Ltd. - Your solution provider'),
(13, 'Harris Holdings', '777-666-5555', 'harris@example.com', now(), now(), FALSE, 'Harris Holdings - Driving growth through partnerships'),
(14, 'Martinez Group', '999-888-7777', 'martinez@example.com', now(), now(), TRUE, 'Description for Martinez Group'),
(15, 'Garcia Corporation', '333-222-1111', 'garcia@example.com', now(), now(), TRUE, 'Garcia Corporation - Empowering businesses worldwide'),
(16, 'Lee & Sons', '555-444-3333', 'lee@example.com', now(), now(), FALSE, 'Lee & Sons - Crafting excellence since 1985'),
(17, 'Wright Enterprises', '888-777-6666', 'wright@example.com', now(), now(), TRUE, 'Wright Enterprises - Transforming industries'),
(18, 'Scott Ltd.', '222-111-0000', 'scott@example.com', now(), now(), FALSE, 'Scott Ltd. - Innovating for a sustainable future'),
(19, 'King Co.', '111-222-3333', 'king@example.com', now(), now(), TRUE, 'King Co. - Your success is our priority'),
(20, 'Green Group', '444-555-6666', 'green@example.com', now(), now(), TRUE, 'Description for Green Group'),
(21, 'White Enterprises', '777-888-9999', 'white@example.com', now(), now(), FALSE, 'White Enterprises - Striving for excellence');
