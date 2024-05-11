drop database awbdtestdatabase;
create database awbdTestDatabase;
use awbdTestDatabase;

create table if not exists user(
    id int not null primary key auto_increment,
    last_name varchar(100) not null,
    first_name varchar(100) not null,
    email varchar(100) not null unique,
    phone_number varchar(15) not null,
    password varchar(600) not null
);

create table if not exists author(
     id int not null primary key auto_increment,
     last_name varchar(100) not null,
     first_name varchar(100) not null
);

create table if not exists biography(
    id int not null primary key auto_increment,
    biography varchar(1000) not null,
    birth_place varchar(100) not null,
    author_id int not null unique,
    foreign key (author_id) references author(id)
);

create table if not exists genre(
    id int not null primary key auto_increment,
    name varchar(100) not null,
    description varchar(300) not null
);

create table if not exists publisher(
    id int not null primary key auto_increment,
    name varchar(100) not null,
    location varchar(300) not null,
    website varchar(300) not null
);

create table if not exists book(
    id int not null primary key auto_increment,
    title varchar(100) not null,
    author_id int not null,
    foreign key (author_id) references author(id),
    genre_id int not null,
    foreign key (genre_id) references genre(id),
    publisher_id int not null,
    foreign key (publisher_id) references publisher(id)
);

create table if not exists rental(
    id int not null primary key auto_increment,
    book_id int not null,
    foreign key (book_id) references book(id) on delete cascade,
    user_id int not null,
    foreign key (user_id) references user(id) on delete cascade,
    renting_date date not null,
    due_date date not null,
    CONSTRAINT unique_rental_combination UNIQUE (book_id, user_id, renting_date)
);