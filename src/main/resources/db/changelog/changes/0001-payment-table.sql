--liquibase formatted sql

--changeset raivis:1

create table payment
(
    id            UUID default random_uuid() primary key,
    amount        decimal      not null,
    debtor_iban   varchar(255) not null,
    debtor_location varchar(255),
    creation_time timestamp    not null
);