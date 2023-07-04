create table patients(

    id bigint not null auto_increment,
    name varchar(100) not null,
    email varchar(100) not null,
    phone varchar(20) not null,
    cpf varchar(14) not null,
    street_address varchar(100) not null,
    neighborhood varchar(100) not null,
    postal_code varchar(9) not null,
    city varchar(100) not null,
    state char(2) not null,
    number varchar(20),
    complement varchar(100),

    primary key(id)

);