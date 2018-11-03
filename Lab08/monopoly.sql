-- This SQL scrpit wsa built by Daniel Ackuaku it is a Database for the game monopoly.
--
--@author dma2@students.calvin.edu
--@version Fall, 2018
--@date 26th October, 2018.

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
INSERT INTO Game VALUES (1, '2018-06-27 08:00:00');
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

INSERT INTO PlayerProperty VALUES (1, 1, 1, 1000);
INSERT INTO PlayerProperty VALUES (2, 1, 1, 500);
INSERT INTO PlayerProperty VALUES (3, 1, 1, 200);
INSERT INTO PlayerProperty VALUES (4, 2, 2, 450);
INSERT INTO PlayerProperty VALUES (5, 2, 2, 200);
INSERT INTO PlayerProperty VALUES (6, 2, 2, 450);
INSERT INTO PlayerProperty VALUES (7, 3, 3, 200);
INSERT INTO PlayerProperty VALUES (8, 3, 3, 450);
INSERT INTO PlayerProperty VALUES (9, 3, 3, 650);

INSERT INTO GameProperty VALUES (1, 1, 1, 'house');
INSERT INTO GameProperty VALUES (2, 1, 2, 'hotel');
INSERT INTO GameProperty VALUES (3, 1, 3, 'hotel');
INSERT INTO GameProperty VALUES (4, 2, 1, 'parliament');
INSERT INTO GameProperty VALUES (5, 2, 2, 'airbnb');
INSERT INTO GameProperty VALUES (6, 2, 3, 'hotel');
INSERT INTO GameProperty VALUES (7, 3, 1, 'house');
INSERT INTO GameProperty VALUES (8, 3, 2, 'hotel');
INSERT INTO GameProperty VALUES (9, 3, 3, 'park');

-- Exercise 8.1
-- a) Retrieve a list of all the games, ordered by date with the most recent game coming first.
SELECT *
  FROM Game
  ORDER BY time DESC;


-- b) Retrieve all the games that occurred in the past week.
SELECT *
  FROM Game
  WHERE time > '2018-06-19 24:00:00';


-- c) Retrieve a list of players who have (non-NULL) names.
SELECT *
  FROM Player
  WHERE name IS NOT NULL;


-- d) Retrieve a list of IDs for players who have some game score larger than 2000.
SELECT *
  FROM PlayerGame
  WHERE score > 2000;


-- e) Retrieve a list of players who have GMail accounts.
SELECT *
  FROM Player
  WHERE emailAddress LIKE '%gmail.edu%';




-- Exercise 8.2
-- a) Retrieve all “The King”’s game scores in decreasing order.
SELECT score
  FROM Player, PlayerGame
  WHERE Player.ID = PlayerGame.playerID
    AND Player.name = 'The King'
    ORDER BY score DESC;


-- b) Retrieve the name of the winner of the game played on 2006-06-28 13:20:00.
SELECT MAX(score)
  FROM Player, Game, PlayerGame
  WHERE Player.ID = PlayerGame.playerID
  AND PlayerGame.gameID = Game.ID
  AND Game.time = '2006-06-28 13:20:00';


-- c) So what does that P1.ID < P2.ID clause do in the last example query?
-- It makes sure that the player id's of the two compared entries are not the same, ensuring that we are have 2 different players with the same name.


-- d) The query that joined the Player table to itself seems rather contrived.
-- Can you think of a realistic situation in which you’d want to join a table to itself?

-- You would want to join a table to itself if you need to access a record more than once. In our example, we could join a table to itself to find all games which were played
-- in a series of 2 or more.
