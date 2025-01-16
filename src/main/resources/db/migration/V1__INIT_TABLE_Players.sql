CREATE table Players
(
    ID   INTEGER      NOT NULL PRIMARY KEY AUTO_INCREMENT,
    Name VARCHAR(128) NOT NULL CHECK (length(Name) >= 3 AND length(Name) <= 25)
);

