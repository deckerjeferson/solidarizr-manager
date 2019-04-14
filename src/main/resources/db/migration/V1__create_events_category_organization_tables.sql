CREATE TABLE category (
    id INTEGER NOT NULL,
    name VARCHAR(60) NOT NULL,
    PRIMARY KEY(id)
);

create sequence category_id_seq start with 1 increment by 1 no minvalue no maxvalue cache 1;

CREATE TABLE organization (
    id INTEGER NOT NULL,
    name VARCHAR(60) NOT NULL,
    email VARCHAR(120),
    PRIMARY KEY(id)
);

create sequence organization_id_seq start with 1 increment by 1 no minvalue no maxvalue cache 1;

CREATE TABLE event (
    id INTEGER NOT NULL,
    name VARCHAR(60) NOT NULL,
    description VARCHAR(500) NOT NULL,
    category INTEGER,
    creator INTEGER,
    PRIMARY KEY(id),
    FOREIGN KEY(category) REFERENCES category(id),
    FOREIGN KEY(creator) REFERENCES organization(id)
);

create sequence event_id_seq start with 1 increment by 1 no minvalue no maxvalue cache 1;