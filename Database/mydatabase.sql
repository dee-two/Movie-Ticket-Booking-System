CREATE DATABASE mydatabase;
USE mydatabase;

CREATE TABLE users(id int(10) NOT NULL AUTO_INCREMENT, username varchar(50), password varchar(50), email varchar(50), mobile varchar(20), PRIMARY KEY(id));
CREATE TABLE movies(movie_id int(10) NOT NULL AUTO_INCREMENT, name varchar(20), duration varchar(20), genre varchar(20), description varchar(300), ratings varchar(5), PRIMARY KEY(movie_id));
CREATE TABLE movie_cast(cast_id int(10) NOT NULL AUTO_INCREMENT, movie_id int(10) references movies(movie_id), cast varchar(200), PRIMARY KEY(cast_id));
CREATE TABLE movie_comments(comment_id int(10) NOT NULL AUTO_INCREMENT, movie_id int(10) references movies(movie_id), comments varchar(300), PRIMARY KEY(comment_id));

INSERT INTO users(username, password, email, mobile) VALUES('abcd', '12345', 'abcd@abcd.com', '0000000000');

INSERT INTO movies(name, duration, genre, description, ratings) VALUES('Avengers', '2hrs, 57mins', 'Action', 'Some Description of the movie XYZXYZ.', '91%');
INSERT INTO movies(name, duration, genre, description, ratings) VALUES('Hulk', '2hrs, 10mins', 'Action', 'Some Description of the movie ABCABCABC.', '83%');
INSERT INTO movies(name, duration, genre, description, ratings) VALUES('Captain America', '1hrs, 37mins', 'Action', 'Some Description of the movie XOXOXOXO.', '86%');

INSERT INTO movie_cast(movie_id, cast) VALUES('1', 'Robert Downey Jr.');
INSERT INTO movie_cast(movie_id, cast) VALUES('1', 'Chris Evans');
INSERT INTO movie_cast(movie_id, cast) VALUES('1', 'Elisabeth Olsen');
INSERT INTO movie_cast(movie_id, cast) VALUES('1', 'Chris Hemsworth');
INSERT INTO movie_cast(movie_id, cast) VALUES('2', 'Lou Ferrigno');
INSERT INTO movie_cast(movie_id, cast) VALUES('2', 'Liv Tyler');
INSERT INTO movie_cast(movie_id, cast) VALUES('3', 'Chris Evans');
INSERT INTO movie_cast(movie_id, cast) VALUES('3', 'Scarlett Johannson');
INSERT INTO movie_cast(movie_id, cast) VALUES('3', 'Hayley Atwell');

INSERT INTO movie_comments(movie_id, comments) VALUES('1', 'Some Comments for Avengers !!!!!');
INSERT INTO movie_comments(movie_id, comments) VALUES('1', 'Comment No. 2 Avengers !!!!!');
INSERT INTO movie_comments(movie_id, comments) VALUES('1', 'Some Comments for Avengers !!!!!');
INSERT INTO movie_comments(movie_id, comments) VALUES('2', 'Comment No. 2 Avengers !!!!!');
INSERT INTO movie_comments(movie_id, comments) VALUES('2', 'QWEHQJWEJQWEQWE Hulk');
INSERT INTO movie_comments(movie_id, comments) VALUES('2', 'O.o HULK');
INSERT INTO movie_comments(movie_id, comments) VALUES('3', 'Some Comments for Captain America');

/*SELECT * FROM movies;
SELECT * FROM movie_cast;
SELECT * FROM movie_comments;

SELECT * from movies WHERE movie_id = movie_id;
SELECT * from movie_cast WHERE movie_id = movie_id;
SELECT * FROM movie_comments WHERE movie_id = movie_id;

DROP TABLE movies;
DROP TABLE movie_cast;
DROP TABLE movie_comments;

CREATE USER 'd2'@'localhost' IDENTIFIED BY '';
GRANT ALL PRIVILEGES ON *.* TO 'd2'@'localhost' WITH GRANT OPTION;
CREATE USER 'd2'@'%' IDENTIFIED BY '';
GRANT ALL PRIVILEGES ON *.* TO 'd2'@'%' WITH GRANT OPTION;*/