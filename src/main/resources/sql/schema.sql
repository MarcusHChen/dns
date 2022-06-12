CREATE TABLE IF NOT EXISTS domains (
    id                     INTEGER  PRIMARY KEY,
    label                   VARCHAR      NOT NULL,
    ipv4                   VARCHAR      NOT NULL,
    parentId                   INTEGER
    );