SET MODE PostgreSQL;

CREATE TABLE IF NOT EXISTS cardtext (
 id int PRIMARY KEY auto_increment,
 attack INTEGER,
 health INTEGER,
 mana INTEGER,
 classtype VARCHAR,
 name VARCHAR,
 carddetail VARCHAR
);
CREATE TABLE IF NOT EXISTS rarity (
 id int PRIMARY KEY auto_increment,
 name VARCHAR,
 mana INTEGER,
 classtype VARCHAR,
 carddetail VARCHAR,
 typerarity VARCHAR
);