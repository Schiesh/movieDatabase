CREATE TABLE movies (
	mid integer,
    mname CHAR(100),
    score integer,
    PRIMARY KEY (mid));
CREATE TABLE movieCast (
	mid integer,
    cid integer,
    cname CHAR (50),
    PRIMARY KEY (mid, cid),
    FOREIGN KEY (mid) REFERENCES movies(mid));

SELECT mname, score
FROM movies NATURAL JOIN movieCast
WHERE cname LIKE "%Samuel L. Jacksion%"
ORDER BY score DESC;

SELECT mid, mname, score
FROM movies
WHERE mname LIKE "%star%";

SELECT cname, AVG(score) average
FROM movies NATURAL JOIN movieCast 
GROUP BY cname
ORDER BY average DESC
LIMIT 10;

SELECT e.cname, c.cname, COUNT(e.mid) casted_together
FROM movieCast e JOIN movieCast c
ON (e.mid = c.mid)
WHERE e.cname <> c.cname
GROUP BY e.cname, c.cname
ORDER BY casted_together DESC
LIMIT 2;

SELECT mname, cid, cname
FROM movies NATURAL JOIN movieCast
WHERE mname LIKE "%World War Z%"
ORDER BY cname ASC;


