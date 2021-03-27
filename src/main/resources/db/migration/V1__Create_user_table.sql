create table user (
    id int not null AUTO INCREMENT ,
    role_id not null,
    name varchar(100) not null unique,
    password varchar(100) not null,
    email_id varchar(100) not null,
    address varchar(100) not null,
    contact_number varchar(100) not null,
    account_enable BOOLEAN ,
    primary key(id)
 );