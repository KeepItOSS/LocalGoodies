CREATE SEQUENCE business_id_seq
INCREMENT BY 51
START WITH 1
NO MINVALUE
NO MAXVALUE
CACHE 1;

CREATE TABLE BUSINESS (
    id INTEGER PRIMARY KEY DEFAULT nextval('business_id_seq'),
    name VARCHAR(50),
    phone_number VARCHAR(12),
    email VARCHAR(50),
    created_at TIMESTAMPTZ NOT NULL DEFAULT now(),
    changed_at TIMESTAMPTZ NOT NULL DEFAULT now(),
    active BOOLEAN DEFAULT FALSE,
    description TEXT,
    type VARCHAR(20)
);

INSERT INTO BUSINESS (id, name, phone_number, email, created_at, changed_at, active, description, type)
VALUES
(DEFAULT, 'Tasty Bites', '123-456-7890', 'tastybites@example.com', DEFAULT, DEFAULT, TRUE, 'Delicious cuisine served with love', 'RESTAURANT'),
(DEFAULT, 'Spice Garden', '987-654-3210', 'spicegarden@example.com', DEFAULT, DEFAULT, TRUE, 'Authentic flavors from around the world', 'RESTAURANT'),
(DEFAULT, 'Burger Haven', '555-123-4567', 'burgerhaven@example.com', DEFAULT, DEFAULT, FALSE, 'Home of the juiciest burgers in town', 'RESTAURANT'),
(DEFAULT, 'Pizza Palace', '444-555-6666', 'pizzapalace@example.com', DEFAULT, DEFAULT, TRUE, 'A slice of heaven in every bite', 'RESTAURANT'),
(DEFAULT, 'Pasta Paradise', '777-888-9999', 'pastaparadise@example.com', DEFAULT, DEFAULT, FALSE, 'Savor the taste of Italy', 'RESTAURANT'),
(DEFAULT, 'Sushi Central', '333-222-1111', 'sushicentral@example.com', DEFAULT, DEFAULT, TRUE, 'Fresh sushi made with the finest ingredients', 'RESTAURANT'),
(DEFAULT, 'Steakhouse Supreme', '999-888-7777', 'steakhousesupreme@example.com', DEFAULT, DEFAULT, TRUE, 'Indulge in premium cuts of meat', 'RESTAURANT'),
(DEFAULT, 'Seafood Sensation', '666-555-4444', 'seafoodsensation@example.com', DEFAULT, DEFAULT, FALSE, 'Bringing the ocean to your table', 'RESTAURANT'),
(DEFAULT, 'Veggie Delight', '222-333-4444', 'veggiedelight@example.com', DEFAULT, DEFAULT, TRUE, 'Healthy and delicious vegetarian options', 'RESTAURANT'),
(DEFAULT, 'Diner Dash', '888-999-0000', 'dinerdash@example.com', DEFAULT, DEFAULT, TRUE, 'Classic diner fare with a modern twist', 'RESTAURANT'),
(DEFAULT, 'Café Cheers', '111-222-3333', 'cafecheers@example.com', DEFAULT, DEFAULT, FALSE, 'Where every cup is a celebration', 'RESTAURANT'),
(DEFAULT, 'Bakery Bliss', '444-333-2222', 'bakerybliss@example.com', DEFAULT, DEFAULT, TRUE, 'Satisfy your sweet tooth with our fresh pastries', 'RESTAURANT'),
(DEFAULT, 'BBQ Junction', '777-666-5555', 'bbqjunction@example.com', DEFAULT, DEFAULT, FALSE, 'Smoky flavors that will tantalize your taste buds', 'RESTAURANT'),
(DEFAULT, 'Mexican Fiesta', '999-888-7777', 'mexicanfiesta@example.com', DEFAULT, DEFAULT, TRUE, 'Spice up your life with our authentic Mexican dishes', 'RESTAURANT'),
(DEFAULT, 'Noodle Nirvana', '333-222-1111', 'noodlenirvana@example.com', DEFAULT, DEFAULT, TRUE, 'From ramen to lo mein, weve got your noodle fix', 'RESTAURANT'),
(DEFAULT, 'Taco Time', '555-444-3333', 'tacotime@example.com', DEFAULT, DEFAULT, FALSE, 'Every day is taco Tuesday at our place', 'RESTAURANT'),
(DEFAULT, 'Bistro Breeze', '888-777-6666', 'bistrobreeze@example.com', DEFAULT, DEFAULT, TRUE, 'Elegant dining in a casual atmosphere', 'RESTAURANT'),
(DEFAULT, 'Indian Spice', '222-111-0000', 'indianspice@example.com', DEFAULT, DEFAULT, FALSE, 'Experience the vibrant flavors of India', 'RESTAURANT'),
(DEFAULT, 'Greek Taverna', '111-222-3333', 'greektaverna@example.com', DEFAULT, DEFAULT, TRUE, 'Transport your taste buds to the Mediterranean', 'RESTAURANT'),
(DEFAULT, 'Artisan Workshop', '123-456-7890', 'artisanworkshop@example.com', DEFAULT, DEFAULT, TRUE, 'Handcrafted creations made with passion', 'HANDMADE'),
(DEFAULT, 'Repair Masters', '987-654-3210', 'repairmasters@example.com', DEFAULT, DEFAULT, TRUE, 'Your one-stop shop for repairs', 'REPAIR'),
(DEFAULT, 'Creative Crafts', '555-123-4567', 'creativecrafts@example.com', DEFAULT, DEFAULT, FALSE, 'Unlock your imagination with our handmade products', 'HANDMADE'),
(DEFAULT, 'Cuisine Crafters', '444-555-6666', 'cuisinecrafters@example.com', DEFAULT, DEFAULT, TRUE, 'Crafting culinary delights from scratch', 'RESTAURANT'),
(DEFAULT, 'Fix It Fast', '777-888-9999', 'fixitfast@example.com', DEFAULT, DEFAULT, FALSE, 'Well get your broken items back in working order in no time', 'REPAIR'),
(DEFAULT, 'Homemade Heaven', '333-222-1111', 'homemadeheaven@example.com', DEFAULT, DEFAULT, TRUE, 'Bringing the taste of home to your doorstep', 'HANDMADE'),
(DEFAULT, 'Elegant Eats', '999-888-7777', 'eleganteats@example.com', DEFAULT, DEFAULT, TRUE, 'Gourmet dining experience for every occasion', 'RESTAURANT'),
(DEFAULT, 'Crafty Creations', '666-555-4444', 'craftycreations@example.com', DEFAULT, DEFAULT, FALSE, 'Handmade treasures for every taste', 'HANDMADE'),
(DEFAULT, 'Quick Fix Repairs', '222-333-4444', 'quickfixrepairs@example.com', DEFAULT, DEFAULT, TRUE, 'Fast and reliable repair services', 'REPAIR'),
(DEFAULT, 'Artisanal Delights', '888-999-0000', 'artisanaldelights@example.com', DEFAULT, DEFAULT, TRUE, 'Experience the artisanal difference', 'HANDMADE'),
(DEFAULT, 'Savor & Salvage', '111-222-3333', 'savorandsalvage@example.com', DEFAULT, DEFAULT, FALSE, 'Transforming salvage into something to savor', 'REPAIR'),
(DEFAULT, 'Handcrafted Happiness', '444-333-2222', 'handcraftedhappiness@example.com', DEFAULT, DEFAULT, TRUE, 'Each piece crafted with care and joy', 'HANDMADE');