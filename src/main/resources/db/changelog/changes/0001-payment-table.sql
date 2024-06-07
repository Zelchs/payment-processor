--liquibase formatted sql

--changeset raivis:1

create table payment
(
    id           UUID default primary key,
    amount       decimal      not null,
    debtorIban   varchar(255) not null,
    creationTime timestamp    not null
)