create table if not exists DRONE
(
    ID               bigint       auto_increment,
    SERIAL_NUMBER    varchar(100) not null,
    MODEL            varchar(255) not null,
    WEIGHT_LIMIT     INTEGER      not null,
    BATTERY_CAPACITY INTEGER      not null,
    STATE            varchar(255) not null
);

create table if not exists MEDICATION
(
    id       bigint       auto_increment,
    NAME     varchar(100) not null,
    WEIGHT   INTEGER      not null,
    CODE     varchar(255) not null,
    IMAGE    MEDIUMBLOB   not null,
    DRONE_ID bigint       not null,
    constraint drone_medication_fk
        foreign key (DRONE_ID) references DRONE (ID)
);
