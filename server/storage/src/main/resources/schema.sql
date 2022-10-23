CREATE TABLE items (
    id   INTEGER      NOT NULL AUTO_INCREMENT,
    brandname VARCHAR(128) NOT NULL,
    size VARCHAR(128) NOT NULL,
    quantity INTEGER NOT NULL,
    price REAL NOT NULL,
    PRIMARY KEY (id)
);
