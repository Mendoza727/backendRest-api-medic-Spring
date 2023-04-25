create table medicos(
            id bigint not null auto_increment,
            nombre varchar(50) not null,
            apellido varchar(50) not null,
            email varchar(80) not null unique,
            documento varchar(50) not null unique,
            especialidad varchar(50) not null,
            calle varchar(60) not null,
            distrito varchar(60) not null,
            complemento varchar(100),
            numero varchar(60),
            ciudad varchar(100) not null,


            primary key(id)

);