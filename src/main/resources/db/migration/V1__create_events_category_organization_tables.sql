CREATE TABLE category (
    id INTEGER NOT NULL,
    name VARCHAR(60) NOT NULL,
    PRIMARY KEY(id)
);

CREATE TABLE organization (
    id INTEGER NOT NULL,
    name VARCHAR(60) NOT NULL,
    email VARCHAR(120),
    PRIMARY KEY(id)
);

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