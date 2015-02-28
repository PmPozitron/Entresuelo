DROP DATABASE IF EXISTS entresuelo;

CREATE DATABASE entresuelo CHARACTER SET utf8 COLLATE utf8_general_ci;

CREATE USER spring IDENTIFIED BY 'security';
GRANT ALL PRIVILEGES ON entresuelo TO spring;
