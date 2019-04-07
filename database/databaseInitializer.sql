create database fx_mobile_crawler_db;

use fx_mobile_crawler_db;

create table frequencies (
  frequencyId int auto_increment not null primary key,
  frequencyPeriod varchar(10)
);

create table users (
    userId int auto_increment not null primary key,
    email varchar(100) not null,
    origin varchar(100),
    destination varchar(100),
    frequencyID int,
    confirmed tinyint(1) default 0,
    foreign key (frequencyID) references frequencies(frequencyId)
);

create table flights (
    flightID int auto_increment not null primary key,
    origin varchar(100),
    destination varchar(100),
    flightNumber varchar(30),
    timestamp bigint,
    price double
);

create user 'fxm_user'@'localhost' identified by 'fxm_password';
grant all privileges on fx_mobile_crawler_db.* to 'fxm_user'@'localhost';
flush privileges;

insert into frequencies(frequencyPeriod)
value ('daily');
insert into frequencies(frequencyPeriod)
  value ('weekly');
insert into frequencies(frequencyPeriod)
  value ('monthly');
insert into frequencies(frequencyPeriod)
  value ('uncapped');