CREATE TABLE IF NOT EXISTS city
 (id NUMBER NOT NULL AUTO_INCREMENT,
 name CHARACTER VARYING(100),
 city_centre_latitude DECIMAL(11,6),
 city_centre_longitude DECIMAL(11,7)
 );

CREATE TABLE IF NOT EXISTS hotel
(
id NUMBER NOT NULL AUTO_INCREMENT,
name CHARACTER VARYING(100),
deleted BOOLEAN,
rating DECIMAL(2,1),
city_id NUMERIC,
address CHARACTER VARYING(100),
latitude DECIMAL(11,6),
longitude DECIMAL(11,7)
 );