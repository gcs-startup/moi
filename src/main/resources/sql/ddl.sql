DROP TABLE IF EXISTS member;
DROP TABLE IF EXISTS room;
DROP TABLE IF EXISTS item;
DROP TABLE IF EXISTS money;
DROP TABLE IF EXISTS invest;

create table member (
    id bigint not null auto_increment,
    token varchar(255),
    name varchar(50),
    role varchar(20),
    created_at datetime(6),
    created_by varchar(50),
    modified_at datetime(6),
    modified_by varchar(50),
    primary key (id),
    unique key (token)
)
;

create table room (
    id bigint not null auto_increment,
    title varchar(255),
    created_at datetime(6),
    created_by varchar(50),
    modified_at datetime(6),
    modified_by varchar(50),
    primary key (id)
)
;

create table item (
    id bigint not null auto_increment,
    title varchar(255),
    amount bigint,
    is_deleted boolean,
    room_id bigint,
    owner_member_id bigint,
    created_at datetime(6),
    created_by varchar(50),
    modified_at datetime(6),
    modified_by varchar(50),
    primary key (id)
)
;

create table money (
    id bigint not null auto_increment,
    room_id bigint,
    member_id bigint,
    amount bigint,
    created_at datetime(6),
    created_by varchar(50),
    modified_at datetime(6),
    modified_by varchar(50),
    primary key (id)
)
;

create table invest (
    id bigint not null auto_increment,
    item_id bigint,
    member_id bigint,
    amount bigint,
    created_at datetime(6),
    created_by varchar(50),
    modified_at datetime(6),
    modified_by varchar(50),
    primary key (id)
)
;