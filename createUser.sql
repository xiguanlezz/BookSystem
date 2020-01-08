drop table if exists user;
create table user (
	USER_ID varchar(32) not null,
	USER_NAME varchar(20) not null,
	USER_PASSWORD varchar(20) not null,
	USER_SEX varchar(1) not null,
	USER_BIRTHDAY datetime default null,
	USER_IDENTITY_CODE varchar(60) default null,
	USER_EMAIL varchar(60) default null,
	USER_MOBILE varchar(11) default null,
	USER_ADDRESS varchar(200) not null,
	USER_STATUS decimal(6,0) not null,
	primary key(USER_ID)
) default charset=utf8;