DROP TABLE IF EXISTS USER;

create table user(
	id int not null primary key auto_increment,
    user_name varchar_ignorecase not null,
    password varchar_ignorecase not null,
    roles varchar_ignorecase not null,
    active boolean
);