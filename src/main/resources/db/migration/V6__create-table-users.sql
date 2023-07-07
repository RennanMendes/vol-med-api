create table users(

    id bigint not null auto_increment,
    user_name varchar(100) not null,
    password varchar(255) not null,
    active tinyint not null,

    primary key(id)

);