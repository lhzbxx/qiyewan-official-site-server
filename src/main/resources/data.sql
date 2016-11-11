LOAD DATA LOCAL INFILE  'classpath:/data/products.vbsv'
INTO TABLE product
FIELDS TERMINATED BY '|'
ENCLOSED BY '"'
LINES TERMINATED BY '\n'
IGNORE 1 LINES
(id, classification_code, classification_name, comment, cover, create_at, heat, info, @var1, @var2, name, process,
 product_state, purchase_number, rate, region_code, serial_id, summary, unit, unit_price, update_at, what_need, what_obtain)
SET is_hot = (@var1 = 'TRUE'), is_instant = (@var2 = 'TRUE');

LOAD DATA LOCAL INFILE  'classpath:/data/faq.csv'
INTO TABLE faq
FIELDS TERMINATED BY ','
OPTIONALLY ENCLOSED BY '"'
ESCAPED BY '"'
LINES TERMINATED BY '\n'
IGNORE 1 LINES
(answer, question, serial_id);

LOAD DATA LOCAL INFILE  'classpath:/data/region.csv'
INTO TABLE region
FIELDS TERMINATED BY ','
OPTIONALLY ENCLOSED BY '"'
ESCAPED BY '"'
LINES TERMINATED BY '\n'
IGNORE 1 LINES
(code, province, city, district);