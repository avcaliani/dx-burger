-- -----------------------
--  INGREDIENTS
-- -----------------------
INSERT INTO ingredient (id, name, price) VALUES (1, 'Alface', 0.4);
INSERT INTO ingredient (id, name, price) VALUES (2, 'Bacon', 2.0);
INSERT INTO ingredient (id, name, price) VALUES (3, 'Hambúrguer de Carne', 3.0);
INSERT INTO ingredient (id, name, price) VALUES (4, 'Ovo', 0.8);
INSERT INTO ingredient (id, name, price) VALUES (5, 'Queijo', 1.5);

-- -----------------------
--  Burgers
-- -----------------------
INSERT INTO burger (id, name) VALUES (1, 'X-Bacon');
INSERT INTO burger (id, name) VALUES (2, 'X-Burger');
INSERT INTO burger (id, name) VALUES (3, 'X-Egg');
INSERT INTO burger (id, name) VALUES (4, 'X-Egg Bacon');

-- -----------------------
-- Burger Ingredient
-- -----------------------
INSERT INTO burger_ingredient (id, quantity, burger_id, ingredient_id) VALUES (1, 1, 1, 2); -- Bacon
INSERT INTO burger_ingredient (id, quantity, burger_id, ingredient_id) VALUES (2, 1, 1, 3); -- Hambúrguer
INSERT INTO burger_ingredient (id, quantity, burger_id, ingredient_id) VALUES (3, 1, 1, 5); -- Queijo

INSERT INTO burger_ingredient (id, quantity, burger_id, ingredient_id) VALUES (4, 1, 2, 3); -- Hambúrguer
INSERT INTO burger_ingredient (id, quantity, burger_id, ingredient_id) VALUES (5, 1, 2, 5); -- Queijo

INSERT INTO burger_ingredient (id, quantity, burger_id, ingredient_id) VALUES (6, 1, 3, 4); -- Ovo
INSERT INTO burger_ingredient (id, quantity, burger_id, ingredient_id) VALUES (7, 1, 3, 3); -- Hambúrguer
INSERT INTO burger_ingredient (id, quantity, burger_id, ingredient_id) VALUES (8, 1, 3, 5); -- Queijo

INSERT INTO burger_ingredient (id, quantity, burger_id, ingredient_id) VALUES (9, 1, 4, 4); -- Ovo
INSERT INTO burger_ingredient (id, quantity, burger_id, ingredient_id) VALUES (10, 1, 4, 2); -- Bacon
INSERT INTO burger_ingredient (id, quantity, burger_id, ingredient_id) VALUES (11, 1, 4, 3); -- Hambúrguer
INSERT INTO burger_ingredient (id, quantity, burger_id, ingredient_id) VALUES (12, 1, 4, 5); -- Queijo

-- -----------------------
-- Promotion
-- -----------------------
INSERT INTO promotion (id, active, description, percent) VALUES (
	1,
	true,
	'Light: Se o lanche tem alface e não tem bacon, ganha 10% de desconto.',
	0.1
);
INSERT INTO promotion_ingredient_present (promotion_id, ingredient_id) VALUES (
	1, 1 -- Alface
);
INSERT INTO promotion_ingredient_not_present (promotion_id, ingredient_id) VALUES (
	1, 2 -- Bacon
);

INSERT INTO promotion (id, active, description, divider, ingredient_id) VALUES (
	2,
	true,
	'Muita Carne: A cada 3 porções de carne o cliente só paga 2. Se o lanche tiver 6 porções, o cliente pagará 4. Assim por diante...',
	3,
	3 -- Hambúrguer
);

INSERT INTO promotion (id, active, description, divider, ingredient_id) VALUES (
	3,
	true,
	'Muito Queijo: A cada 3 porções de queijo o cliente só paga 2. Se o lanche tiver 6 porções, o cliente pagará 4. Assim por diante...',
	3,
	5 -- Queijo
);