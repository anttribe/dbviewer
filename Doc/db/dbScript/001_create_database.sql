CREATE DATABASE `db_dbviewer` DEFAULT CHARACTER SET utf8 COLLATE utf8_general_ci;
grant all privileges on `db_dbviewer`.* to `admin`@'%';
flush privileges;