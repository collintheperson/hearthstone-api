SET MODE PostgreSQL;

CREATE TABLE IF NOT EXISTS text (
 id int PRIMARY KEY auto_increment,
 name VARCHAR,
 mana INTEGER,
 attack INTEGER,
 classtype VARCHAR,
 health INTEGER,
 carddetail VARCHAR
);
CREATE TABLE IF NOT EXISTS text (
 id int PRIMARY KEY auto_increment,
 name VARCHAR,
 mana INTEGER,
 classtype VARCHAR,
 carddetail VARCHAR
);