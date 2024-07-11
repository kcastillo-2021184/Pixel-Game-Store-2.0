/*
   	Programador: Kevin Omar Castillo Calderón
    Carné: 2021184
    Fila #1
    Fecha de creación: 
		17-04-2024
    Fechas de Modificaciones:
		02/07/2024
        09/07/2024
*/

drop database if exists DBPixelGameStore;
create database DBPixelGameStore;

use DBPixelGameStore;

create table Categoria (
    codigoCategoria int auto_increment not null,
    nombreCategoria varchar(30) not null,
    descripcionCategoria varchar(45) not null,
    localizacionCategoria varchar(30) not null,
    primary key (codigoCategoria)
);

create table Distribuidores (
    codigoProveedor int auto_increment not null,
    nombreDistribuidor varchar(60) not null,
    direccionDistribuidor varchar(150) not null,
    codigoPostal varchar(5) not null,
    paginaWeb varchar(50) not null,
    primary key (codigoProveedor)
);

create table Clientes (
    codigoCliente int not null auto_increment,
    NITCliente varchar(10) not null,
    nombresCliente varchar(50) not null,
    apellidosCliente varchar(50) not null,
    direccionCliente varchar(150) not null,
    telefonoCliente varchar(8) not null,
    correoCliente varchar(60) not null,
    primary key (codigoCliente)
);

create table Compras (
    numeroDocumento int not null,
    fechaDocumento date not null,
    descripcion varchar(60) not null,
    totalDocumento decimal(10,2) default 0,
    primary key (numeroDocumento)
);
