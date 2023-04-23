MERGE INTO role (id, role)
    KEY (id)
    VALUES (1, 'USER');

MERGE INTO role (id, role)
    KEY (id)
    VALUES (2, 'ADMIN');


MERGE INTO Genre (id, name) KEY (id) VALUES (1, 'Action');
MERGE INTO Genre (id, name) KEY (id) VALUES (2, 'Adventure');
MERGE INTO Genre (id, name) KEY (id) VALUES (3, 'Animals');
MERGE INTO Genre (id, name) KEY (id) VALUES (4, 'Animation');
MERGE INTO Genre (id, name) KEY (id) VALUES (5, 'Biography');
MERGE INTO Genre (id, name) KEY (id) VALUES (6, 'Comedy');
MERGE INTO Genre (id, name) KEY (id) VALUES (7, 'Cooking');
MERGE INTO Genre (id, name) KEY (id) VALUES (8, 'Dance');
MERGE INTO Genre (id, name) KEY (id) VALUES (9, 'Documentary');
MERGE INTO Genre (id, name) KEY (id) VALUES (10, 'Drama');
MERGE INTO Genre (id, name) KEY (id) VALUES (11, 'Education');
MERGE INTO Genre (id, name) KEY (id) VALUES (12, 'Entertainment');
MERGE INTO Genre (id, name) KEY (id) VALUES (13, 'Family');
MERGE INTO Genre (id, name) KEY (id) VALUES (14, 'Fantasy');
MERGE INTO Genre (id, name) KEY (id) VALUES (15, 'History');
MERGE INTO Genre (id, name) KEY (id) VALUES (16, 'Horror');
MERGE INTO Genre (id, name) KEY (id) VALUES (17, 'Independent');
MERGE INTO Genre (id, name) KEY (id) VALUES (18, 'International');
MERGE INTO Genre (id, name) KEY (id) VALUES (19, 'Kids');
MERGE INTO Genre (id, name) KEY (id) VALUES (20, 'Kids & Family');
MERGE INTO Genre (id, name) KEY (id) VALUES (21, 'Medical');
MERGE INTO Genre (id, name) KEY (id) VALUES (22, 'Military/War');
MERGE INTO Genre (id, name) KEY (id) VALUES (23, 'Music');
MERGE INTO Genre (id, name) KEY (id) VALUES (24, 'Musical');
MERGE INTO Genre (id, name) KEY (id) VALUES (25, 'Mystery/Crime');
MERGE INTO Genre (id, name) KEY (id) VALUES (26, 'Nature');
MERGE INTO Genre (id, name) KEY (id) VALUES (27, 'Paranormal');
MERGE INTO Genre (id, name) KEY (id) VALUES (28, 'Politics');
MERGE INTO Genre (id, name) KEY (id) VALUES (29, 'Racing');
MERGE INTO Genre (id, name) KEY (id) VALUES (30, 'Romance');
MERGE INTO Genre (id, name) KEY (id) VALUES (31, 'Sci-Fi/Horror');
MERGE INTO Genre (id, name) KEY (id) VALUES (32, 'Science');
MERGE INTO Genre (id, name) KEY (id) VALUES (33, 'Science Fiction');
MERGE INTO Genre (id, name) KEY (id) VALUES (34, 'Science/Nature');



MERGE INTO Item (id, name, description, type, duration, series_Count, release_Year, image) KEY (id) VALUES (1, 'I Dream in Another Language', 'Description of I Dream in Another Language', 0, 120, 1, 2017, '/img/covers/cover.jpg');
MERGE INTO Item (id, name, description, type, duration, series_Count, release_Year, image) KEY (id) VALUES (2, 'Benched', 'Description of Benched', 0, 90, 1, 2018, '/img/covers/cover3.jpg');
MERGE INTO Item (id, name, description, type, duration, series_Count, release_Year, image) KEY (id) VALUES (3, 'Whitney', 'Description of Whitney', 0, 110, 3, 2018, '/img/covers/cover2.jpg');
MERGE INTO Item (id, name, description, type, duration, series_Count, release_Year, image) KEY (id) VALUES (4, 'Blindspotting', 'Description of Blindspotting', 0, 95, 1, 2018, '/img/covers/cover6.jpg');
MERGE INTO Item (id, name, description, type, duration, series_Count, release_Year, image) KEY (id) VALUES (5, 'I Dream in Another Language', 'Description of I Dream in Another Language', 0, 1, 1, 2017, '/img/covers/cover4.jpg');
MERGE INTO Item (id, name, description, type, duration, series_Count, release_Year, image) KEY (id) VALUES (6, 'Benched', 'Description of Benched', 0, 90, 1, 2018, '/img/covers/cover5.jpg');

DROP TABLE IF EXISTS Item_Genres;

CREATE TABLE Item_Genres (
 ITEM_ID BIGINT NOT NULL,
 GENRES_ID BIGINT NOT NULL,
 PRIMARY KEY (ITEM_ID, GENRES_ID),
 FOREIGN KEY (ITEM_ID) REFERENCES Item (id),
 FOREIGN KEY (GENRES_ID) REFERENCES Genre (id)
);


MERGE INTO Item_Genres (GENRES_ID, ITEM_ID) VALUES (1, 1);
MERGE INTO Item_Genres (GENRES_ID, ITEM_ID) VALUES (25, 1);

MERGE INTO Item_Genres (GENRES_ID, ITEM_ID) VALUES (6, 2);

MERGE INTO Item_Genres (GENRES_ID, ITEM_ID) VALUES (30, 3);
MERGE INTO Item_Genres (GENRES_ID, ITEM_ID) VALUES (10, 3);
MERGE INTO Item_Genres (GENRES_ID, ITEM_ID) VALUES (23, 3);

MERGE INTO Item_Genres (GENRES_ID, ITEM_ID) VALUES (6, 4);
MERGE INTO Item_Genres (GENRES_ID, ITEM_ID) VALUES (10, 4);

MERGE INTO Item_Genres (GENRES_ID, ITEM_ID) VALUES (1, 5);
MERGE INTO Item_Genres (GENRES_ID, ITEM_ID) VALUES (25, 5);

MERGE INTO Item_Genres (GENRES_ID, ITEM_ID) VALUES (6, 6);

