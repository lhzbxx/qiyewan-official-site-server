LOAD DATA LOCAL INFILE 'classpath:/data/products.csv'
INTO TABLE product
FIELDS TERMINATED BY ','
OPTIONALLY ENCLOSED BY '"'
ENCLOSED BY '"'
LINES TERMINATED BY '\n'
IGNORE 1 LINES
(classification_code, classification_name, comment, cover, create_at, heat, @var1, @var2, name, process,
 product_state, purchase_number, rate, region_code, serial_id, summary, t_what_need, t_what_obtain,
 unit, unit_price, update_at, what_need, what_obtain, version)
SET is_hot = (@var1 = '1'), is_instant = (@var2 = '1');

LOAD DATA LOCAL INFILE 'classpath:/data/faq.csv'
INTO TABLE faq
FIELDS TERMINATED BY ','
OPTIONALLY ENCLOSED BY '"'
ESCAPED BY '"'
LINES TERMINATED BY '\r\n'
IGNORE 1 LINES
(answer, question, serial_id, id);

LOAD DATA LOCAL INFILE 'classpath:/data/region.csv'
INTO TABLE region
FIELDS TERMINATED BY ','
OPTIONALLY ENCLOSED BY '"'
ESCAPED BY '"'
LINES TERMINATED BY '\n'
IGNORE 1 LINES
(city_code, province, city, district, id);

LOAD DATA LOCAL INFILE 'classpath:/data/users.csv'
INTO TABLE user
FIELDS TERMINATED BY ','
OPTIONALLY ENCLOSED BY '"'
ESCAPED BY '"'
LINES TERMINATED BY '\n'
(id, phone, uuid, nickname, create_at, update_at);

LOAD DATA LOCAL INFILE 'classpath:/data/user_auth.csv'
INTO TABLE user_auth
FIELDS TERMINATED BY ','
OPTIONALLY ENCLOSED BY '"'
ESCAPED BY '"'
LINES TERMINATED BY '\n'
(identifier, credential, user_id, id);

LOAD DATA LOCAL INFILE 'classpath:/data/company.csv'
INTO TABLE company
FIELDS TERMINATED BY ','
OPTIONALLY ENCLOSED BY '"'
ESCAPED BY '"'
LINES TERMINATED BY '\n'
(user_id, create_at, update_at);

LOAD DATA LOCAL INFILE 'classpath:/data/admin.csv'
INTO TABLE admin
FIELDS TERMINATED BY ','
OPTIONALLY ENCLOSED BY '"'
ESCAPED BY '"'
LINES TERMINATED BY '\n'
IGNORE 1 LINES
(id, account, password, nickname, role, create_at, update_at);

# 更新产品的评论个数：
UPDATE product SET purchase_number = (SELECT count(product_serial_id) FROM review T WHERE T.product_serial_id = product.serial_id GROUP BY product_serial_id);
UPDATE product SET purchase_number = 0 WHERE purchase_number IS NULL;

# 更新图片规则：
UPDATE product SET cover = CONCAT('product-', SUBSTR(serial_id, 4), '-cover.jpg'),
 what_need = CONCAT('product-', SUBSTR(serial_id, 4), '-what-need.png'),
 what_obtain = CONCAT('product-', SUBSTR(serial_id, 4), '-what-obtain.png');

# 更新购物车中的外键。
# UPDATE cart SET product_serial_id = serial_id;

# 更新周期性产品的单位。
UPDATE product SET unit = '月' WHERE is_instant = 0;