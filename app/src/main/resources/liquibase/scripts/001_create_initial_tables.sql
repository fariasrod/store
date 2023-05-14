CREATE TABLE IF NOT EXISTS tbl_brand
(
    id   INT PRIMARY KEY AUTO_INCREMENT NOT NULL,
    name VARCHAR(255)                   NOT NULL
);

CREATE TABLE IF NOT EXISTS tbl_product
(
    id   INT PRIMARY KEY AUTO_INCREMENT NOT NULL,
    name VARCHAR(255)                   NOT NULL
);

CREATE TABLE IF NOT EXISTS tbl_price
(
    id         INT PRIMARY KEY AUTO_INCREMENT NOT NULL,
    start_date TIMESTAMP                      NOT NULL,
    end_date   TIMESTAMP                      NOT NULL,
    price_list INT                            NOT NULL,
    priority   INT                            NOT NULL,
    price      decimal(10, 2)                 NOT NULL,
    curr       VARCHAR(5)                     NOT NULL,
    brand_id   BIGINT                         NOT NULL,
    product_id BIGINT                         NOT NULL,

    CONSTRAINT FK33x642m9ob6wrt32hiixo0o4e FOREIGN KEY (brand_id) REFERENCES tbl_brand (id),
    CONSTRAINT FKneto5jtcevys4fag4ds9f2sc7 FOREIGN KEY (product_id) REFERENCES tbl_product (id)
);
