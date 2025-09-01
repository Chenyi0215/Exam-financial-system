
INSERT INTO users (user_id, user_name, email, account) VALUES ('A123456789', '王明', 'wang@email.com', '111999666');
INSERT INTO users (user_id, user_name, email, account) VALUES ('B987654321', '陳華', 'chen@email.com', '333888777');

INSERT INTO product (product_name, price, fee_rate) VALUES ('高科技基金', 5000.00, 0.0150);
INSERT INTO product (product_name, price, fee_rate) VALUES ('全球債券', 2000.00, 0.0100);
INSERT INTO product (product_name, price, fee_rate) VALUES ('新興市場股票', 3500.00, 0.0200);


INSERT INTO like_list (user_id, product_no, order_name, account, total_fee, total_amount) VALUES ('A123456789', 1, 10, '111999666', 750.00, 50750.00);
INSERT INTO like_list (user_id, product_no, order_name, account, total_fee, total_amount) VALUES ('B987654321', 2, 5, '333888777', 100.00, 10100.00);
INSERT INTO like_list (user_id, product_no, order_name, account, total_fee, total_amount) VALUES ('B987654321', 3, 8, '333888777', 560.00, 28560.00);