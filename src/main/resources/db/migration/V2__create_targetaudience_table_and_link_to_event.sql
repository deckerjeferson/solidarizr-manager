CREATE TABLE target_audience (
    id INTEGER NOT NULL,
    name VARCHAR(60) NOT NULL,
    PRIMARY KEY(id)
);

create sequence target_audience_id_seq start with 1 increment by 1 no minvalue no maxvalue cache 1;

ALTER TABLE event ADD COLUMN target_audience INTEGER;

ALTER TABLE event ADD FOREIGN KEY(target_audience) REFERENCES target_audience(id);