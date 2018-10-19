-- This SQL scrpit wsa built by Daniel ACkuaku it is a Database for the game monopoly.
--
--@author dma2@students.calvin.edu
--@version Fall, 2018
--@date 19th October, 2018.

-- Drop all the previious versions of the tables if they exist, in reverse order of foreign keys.
DROP TABLE IF EXISTS GameProperty CASCADE;
DROP TABLE IF EXISTS PlayerProperty CASCADE;
DROP TABLE IF EXISTS PlayerGame CASCADE;
DROP TABLE IF EXISTS Game CASCADE;
DROP TABLE IF EXISTS Player CASCADE;

 -- Create game  schema
 CREATE TABLE Game (
   ID integer PRIMARY KEY,
   time timestamp
 );

 CREATE TABLE Player(
   ID integer PRIMARY KEY,
   emailAddress varchar(25),
   name varchar(50)
 );

CREATE TABLE PlayerGame (
  gameID integer REFERENCES Game(ID),
  playerID integer REFERENCES Player(ID),
  score integer,
  location integer
);

CREATE TABLE PlayerProperty(
  ID integer PRIMARY KEY,
  gameID integer REFERENCES Game(ID),
  playerID integer REFERENCES Player(ID),
  price integer
);

CREATE TABLE GameProperty(
  ID integer PRIMARY KEY,
  playerID integer REFERENCES Player(ID),
  gameID integer REFERENCES Game(ID),
  propertyType varchar(25)
);

-- Allow users to select data from the tables.
GRANT SELECT ON Game TO PUBLIC;
GRANT SELECT ON Player TO PUBLIC;
GRANT SELECT ON PlayerGame TO PUBLIC;
GRANT SELECT ON PlayerProperty TO  PUBLIC;
GRANT SELECT ON GameProperty TO PUBLIC;

-- Adding Sample data.
INSERT INTO Game VALUES (1, '2006-06-27 08:00:00');
INSERT INTO Game VALUES (2, '2006-06-28 13:20:00');
INSERT INTO Game VALUES (3, '2006-06-29 18:41:00');

INSERT INTO Player(ID, emailAddress) VALUES (1, 'me@calvin.edu');
INSERT INTO Player VALUES (2, 'king@gmail.edu', 'The King');
INSERT INTO Player VALUES (3, 'dog@gmail.edu', 'Dogbreath');

INSERT INTO PlayerGame VALUES (1, 1, 0.00);
INSERT INTO PlayerGame VALUES (1, 2, 0.00);
INSERT INTO PlayerGame VALUES (1, 3, 2350.00);
INSERT INTO PlayerGame VALUES (2, 1, 1000.00);
INSERT INTO PlayerGame VALUES (2, 2, 0.00);
INSERT INTO PlayerGame VALUES (2, 3, 500.00);
INSERT INTO PlayerGame VALUES (3, 2, 0.00);
INSERT INTO PlayerGame VALUES (3, 3, 5500.00);
