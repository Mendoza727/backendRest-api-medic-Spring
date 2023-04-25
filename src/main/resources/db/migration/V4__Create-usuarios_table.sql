create table usuarios(
            id bigint not null auto_increment,
            usuario varchar(50) not null,
            clave varchar(300) not null,

            primary key(id)

);