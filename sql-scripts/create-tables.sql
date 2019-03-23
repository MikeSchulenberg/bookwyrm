USE bookwyrm;

SET FOREIGN_KEY_CHECKS = 0;

DROP TABLE IF EXISTS book;

CREATE TABLE book(
	id INT AUTO_INCREMENT PRIMARY KEY,
    title VARCHAR(150) NOT NULL,
    genre VARCHAR(100) NOT NULL,
    series_name VARCHAR(100),
    series_number INT
);

INSERT INTO book(title, genre)
VALUES ("The Deed of Paksenarrion", "Fantasy"),
	   ("The Left Hand of Darkness", "Science Fiction"),
       ("Catseye", "Science Fiction"),
       ("The Complete Arrows Trilogy", "Fantasy");

INSERT INTO book(title, genre, series_name, series_number)
VALUES ("Moon Called", "Urban Fantasy", "Mercy Thompson", 1),
	   ("The Elvenbane", "Fantasy", "Halfblood Chronicles", 1),
       ("Heris Serrano", "Science Fiction", "The Serrano Legacy", 1);

DROP TABLE IF EXISTS author;

CREATE TABLE author(
	id INT AUTO_INCREMENT PRIMARY KEY,
    first_name VARCHAR(50) NOT NULL,
    middle_name VARCHAR(50),
    last_name VARCHAR(50) NOT NULL
);

INSERT INTO author(first_name, last_name)
VALUES ("Elizabeth", "Moon"),
	   ("Patricia", "Briggs"),
       ("Andre", "Norton"),
       ("Mercedes", "Lackey");
       
INSERT INTO author(first_name, middle_name, last_name)
VALUES ("Ursula", "K.", "Le Guin");
       
DROP TABLE IF EXISTS book_author;

CREATE TABLE book_author(
	book_id INT NOT NULL,
    author_id INT NOT NULL,
    
    PRIMARY KEY (book_id, author_id),
    FOREIGN KEY (book_id) REFERENCES book(id)
    ON DELETE CASCADE ON UPDATE CASCADE,
    FOREIGN KEY (author_id) REFERENCES author(id)
    ON DELETE CASCADE ON UPDATE CASCADE
);

INSERT INTO book_author(book_id, author_id)
VALUES (1, 1),
	   (2, 5),
       (3, 3),
       (4, 4),
       (5, 2),
       (6, 3),
       (6, 4),
       (7, 1);
       
SET FOREIGN_KEY_CHECKS = 1;