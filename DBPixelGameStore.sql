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

create table CargoEmpleado (
    codigoCargoEmpleado int not null auto_increment,
    nombreCargo varchar(45) not null,
    descripcionCargo varchar(45) not null,
    turno varchar(15) not null,
    primary key (codigoCargoEmpleado)
);

create table TelefonoDistribuidor (
    codigoTelefonoDistribuidor int not null auto_increment,
    numeroPrincipal varchar(8) not null,
    numeroSecundario varchar(8),
    observaciones varchar(45),
    codigoDistribuidor int not null,
    primary key (codigoTelefonoDistribuidor),
    constraint FK_TelefonoDistribuidor_Distribuidores 
        foreign key (codigoDistribuidor) references Distribuidores(codigoProveedor)
);

create table Productos (
    codigoProducto varchar(15) not null,
    descripcionProducto varchar(45) not null,
    precioUnitario decimal(10,2) default 0.00,
    precioDocena decimal(10,2) default 0.00,
    precioMayor decimal(10,2) default 0.00,
    vistaPrevia longblob,
    existencia int default 0,
    codigoCategoria int not null,
    codigoDistribuidor int not null,
    primary key (codigoProducto),
    constraint FK_Productos_Categoria
        foreign key (codigoCategoria)
            references Categoria(codigoCategoria),
    constraint FK_Productos_Distribuidores
        foreign key (codigoDistribuidor)
            references Distribuidores(codigoProveedor)    
);

create table DetalleCompra (
    codigoDetalleCompra int not null auto_increment,
    costoUnitario decimal(10,2) not null,
    cantidad int not null,
    codigoProducto varchar(15) not null,
    numeroDocumento int not null,
    primary key (codigoDetalleCompra),
    constraint FK_DetalleCompra_Productos
        foreign key (codigoProducto)
            references Productos(codigoProducto),
    constraint FK_DetalleCompra_Compras
        foreign key (numeroDocumento)
            references Compras(numeroDocumento)
);

create table EmailDistribuidor (
    codigoEmailDistribuidor int not null auto_increment,
    emailDistribuidor varchar(50) not null,
    descripcion varchar(100) not null,
    horarioDeAtencion varchar(30) not null,
    codigoDistribuidor int not null,
    primary key (codigoEmailDistribuidor),
    constraint FK_EmailDistribuidor_Distribuidores
        foreign key (codigoDistribuidor)
            references Distribuidores(codigoProveedor)
);

create table Empleados (
    codigoEmpleado int not null,
    nombresEmpleado varchar(50) not null,
    apellidosEmpleado varchar(50) not null,
    sueldo decimal(10,2) not null,
    direccionEmpleado varchar(150) not null,
    codigoCargoEmpleado int not null,
    primary key (codigoEmpleado),
    constraint FK_Empleados_CargoEmpleado
        foreign key (codigoCargoEmpleado)
            references CargoEmpleado(codigoCargoEmpleado)
);
