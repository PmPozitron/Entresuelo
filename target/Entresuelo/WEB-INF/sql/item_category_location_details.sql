USE entresuelo;

CREATE TABLE location (
    id int(11) NOT NULL AUTO_INCREMENT,
    name varchar(100) NOT NULL,
    description varchar(500) NOT NULL,
    PRIMARY KEY (id)
);

CREATE TABLE category (
    id int(11) NOT NULL AUTO_INCREMENT,
    name varchar(100) NOT NULL,
    description varchar(500) NOT NULL,
    PRIMARY KEY (id)
);

CREATE TABLE item (
    id int(11) NOT NULL AUTO_INCREMENT,
    name varchar(100) NOT NULL,
    description varchar(500) NOT NULL,
    location_id int(11) NOT NULL,
    PRIMARY KEY (id),
    CONSTRAINT fk_location_id FOREIGN KEY (location_id) REFERENCES location (id)
);

CREATE TABLE categories_details (
    item_id int(11) NOT NULL,
    category_id int(11) NOT NULL,
    PRIMARY KEY (item_id, category_id),
    CONSTRAINT fk_item_id FOREIGN KEY (item_id) REFERENCES item (id),
    CONSTRAINT fk_category_id FOREIGN KEY (category_id) REFERENCES category (id)
);

CREATE TABLE inventory_details (
    item_holder_id int(11) NOT NULL,
    stored_item_id int(11) NOT NULL,    
    PRIMARY KEY (item_holder_id, stored_item_id),
    CONSTRAINT fk_item_holder_id FOREIGN KEY (item_holder_id) REFERENCES item (id),
    CONSTRAINT fk_stored_item_id FOREIGN KEY (stored_item_id) REFERENCES item (id)      
);
