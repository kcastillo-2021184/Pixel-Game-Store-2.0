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
    descripcionCategoria varchar(100) not null,
    localizacionCategoria varchar(70) not null,
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

create table Factura (
    numeroFactura int not null,
    estado varchar(50) not null,
    totalFactura decimal(10,2) default 0.00,
    fechaFactura date not null,
    codigoCliente int not null,
    codigoEmpleado int not null,
    primary key (numeroFactura),
    constraint FK_Factura_Clientes
        foreign key (codigoCliente)
            references Clientes(codigoCliente),
    constraint FK_Factura_Empleados
        foreign key (codigoEmpleado)
            references Empleados(codigoEmpleado)
);

create table DetalleFactura (
    codigoDetalleFactura int not null auto_increment,
    precioUnitario decimal(10,2) default 0.00,
    cantidad int not null,
    numeroFactura int not null,
    codigoProducto varchar(15) not null,
    primary key (codigoDetalleFactura),
    constraint FK_DetalleFactura_Factura foreign key (numeroFactura)
        references Factura (numeroFactura),
    constraint FK_DetalleFactura_Productos foreign key (codigoProducto)
        references Productos (codigoProducto)
);

create table TipoUsuario (
    codigoTipoUsuario int not null auto_increment,
    tipoUsuario varchar(20) not null,
    primary key (codigoTipoUsuario)
);

create table Usuario (
    codigoUsuario int not null auto_increment,
    nombreUsuario varchar(100) not null,
    apellidoUsuario varchar(100) not null,
    usuarioLogin varchar(50) not null,
    contrasena varchar(50) not null,
    codigoTipoUsuario int not null,
    primary key (codigoUsuario),
    constraint FK_Usuario_TipoUsuario foreign key (codigoTipoUsuario)
        references TipoUsuario(codigoTipoUsuario)
);

insert into TipoUsuario(tipoUsuario) values('Administrador');
insert into TipoUsuario(tipoUsuario) values('Empleado');
insert into TipoUsuario(tipoUsuario) values('Cliente');

insert into Usuario(nombreUsuario, apellidoUsuario, usuarioLogin, contrasena, codigoTipoUsuario) 
    values ('Admin', 'admin', 'admin2024', 'admin123' , '1');
insert into Usuario(nombreUsuario, apellidoUsuario, usuarioLogin, contrasena, codigoTipoUsuario) 
    values ('Pedro', 'Armas', 'parmas', 'admin123', '1');
insert into Usuario(nombreUsuario, apellidoUsuario, usuarioLogin, contrasena, codigoTipoUsuario) 
    values ('José', 'Aceituno', 'jaceituno-2020037', 2020037, '2');
insert into Usuario(nombreUsuario, apellidoUsuario, usuarioLogin, contrasena, codigoTipoUsuario) 
    values ('Julio', 'Alvarado', 'jalvarado-2021353', 2021353, '2');
insert into Usuario(nombreUsuario, apellidoUsuario, usuarioLogin, contrasena, codigoTipoUsuario) 
    values ('Jose', 'Arrecis', 'jarrecis-2020444', 2020444, '2');
insert into Usuario(nombreUsuario, apellidoUsuario, usuarioLogin, contrasena, codigoTipoUsuario) 
    values ('José', 'Aceituno', 'jaceituno-2020037', 2020037, '2');
insert into Usuario(nombreUsuario, apellidoUsuario, usuarioLogin, contrasena, codigoTipoUsuario) 
    values ('Juan', 'Barrera', 'jbarrera-2020316', 2020316, '2');
insert into Usuario(nombreUsuario, apellidoUsuario, usuarioLogin, contrasena, codigoTipoUsuario) 
    values ('Christopher', 'Barrera', 'cbarrera-2020306', 2020306, '2');
insert into Usuario(nombreUsuario, apellidoUsuario, usuarioLogin, contrasena, codigoTipoUsuario) 
    values ('Miguel', 'Bautista', 'mbautista-2020375', 2020375, '2');
insert into Usuario(nombreUsuario, apellidoUsuario, usuarioLogin, contrasena, codigoTipoUsuario) 
    values ('Alexander', 'Borja', 'aborja-2020413', 2020413, '2');
insert into Usuario(nombreUsuario, apellidoUsuario, usuarioLogin, contrasena, codigoTipoUsuario) 
    values ('Diego', 'Caal', 'dcaal-2020531', 2020531, '2');
insert into Usuario(nombreUsuario, apellidoUsuario, usuarioLogin, contrasena, codigoTipoUsuario) 
    values ('Jorge', 'Castellanos', 'jcastellanos-2020387', 2020387, '2');
insert into Usuario(nombreUsuario, apellidoUsuario, usuarioLogin, contrasena, codigoTipoUsuario) 
    values ('Diego', 'Caal', 'dcaal-2020531', 2020531, '2');
insert into Usuario(nombreUsuario, apellidoUsuario, usuarioLogin, contrasena, codigoTipoUsuario) 
    values ('Jorge', 'Castellanos', 'jcastellanos-2020387', 2020387, '2');
insert into Usuario(nombreUsuario, apellidoUsuario, usuarioLogin, contrasena, codigoTipoUsuario) 
    values ('Kevin', 'Castillo', 'kcastillo-2021184', 2021184, '2');
insert into Usuario(nombreUsuario, apellidoUsuario, usuarioLogin, contrasena, codigoTipoUsuario) 
    values ('Hettson', 'Ceballos', 'hceballos-2020415', 2020415, '2');
insert into Usuario(nombreUsuario, apellidoUsuario, usuarioLogin, contrasena, codigoTipoUsuario) 
    values ('Andrés', 'Coloma', 'acoloma-2023009', 2020009, '2');

insert into Categoria(nombreCategoria, descripcionCategoria, localizacionCategoria) values ('ARPG', 'Combate en tiempo real con personalización y progresión de personajes.', 'Pasillo 1');

insert into Distribuidores(nombreDistribuidor, direccionDistribuidor, codigoPostal, paginaWeb) values ('Solutek B2B', 'FFJ8+MFQ, 3ra. Avenida, Villa Canales', '01072', 'https://guatemala.solutekla.com/');
insert into Distribuidores(nombreDistribuidor, direccionDistribuidor, codigoPostal, paginaWeb) values ('Agencias Way', '18 Calle 5-59 Zona 11 Colonia Mariscal, Ciudad de Guatemala', '01011', 'https://agenciaswayonline.com/');

select * from Productos;
select * from Categoria;
select * from Distribuidores;