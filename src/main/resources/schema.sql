create table if not exists DRONE
(
    SERIAL_NUMBER    varchar(100) not null
        constraint drone_pk
            primary key,
    MODEL            varchar(255) not null,
    WEIGHT_LIMIT     INTEGER      not null,
    BATTERY_CAPACITY INTEGER      not null,
    STATE            varchar(255) not null,
);

create table if not exists MEDICATION
(
    ID                  bigint auto_increment,
    NAME                varchar(255) not null,
    WEIGHT              INTEGER      not null,
    CODE                varchar(255) not null,
    IMAGE_DATA          MEDIUMBLOB,
    IMAGE_NAME          varchar(255),
    IMAGE_EXTENSION     varchar(50),
    DRONE_SERIAL_NUMBER varchar(100) not null,
    constraint drone_serial_number_fk
        foreign key (DRONE_SERIAL_NUMBER) references DRONE (SERIAL_NUMBER)
);
