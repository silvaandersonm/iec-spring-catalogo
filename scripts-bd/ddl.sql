create database catalogo;
create user usr_catalogo@localhost identified by 'usr_catalogo';
grant all privileges on catalogo.* to 'usr_catalogo'@'localhost';

------------------------------------------------
use catalogo;

create table categoria (
  id_categoria int primary key auto_increment not null,
  nm_categoria varchar(30) not null
);

create table musica (
  id_musica     int primary key auto_increment not null,
  ds_titulo     varchar(60) not null,
  nm_autor      varchar(60) not null,
  dt_lancamento date not null,
  ds_letra      text not null,
  id_categoria  int not null
);

ALTER TABLE musica
    ADD CONSTRAINT mus_cat_fk FOREIGN KEY ( id_categoria )
        REFERENCES categoria ( id_categoria );