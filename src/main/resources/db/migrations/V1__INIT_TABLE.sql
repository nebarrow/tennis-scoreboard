CREATE table Players (
    ID INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT,
    Name VARCHAR(128) NOT NULL UNIQUE CHECK ( length(Name) >= 3 AND length(Name) <= 25 )
);

