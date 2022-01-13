DROP TABLE IF EXISTS stockDataDB.usstocks;

CREATE TABLE stockDataDB.usstocks (
symbol VARCHAR(5) NOT NULL UNIQUE,
closePrice FLOAT NOT NULL,
highestPrice FLOAT NOT NULL,
lowestPrice FLOAT NOT NULL,
openPrice FLOAT NOT NULL
);

INSERT INTO stockDataDB.usstocks(symbol, closePrice, lowestPrice, highestPrice, openPrice) VALUES('FPT', 96.5, 96.5, 96.5, 95.6);