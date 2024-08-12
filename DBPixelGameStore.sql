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
    paginaWeb varchar(200) not null,
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
    fechaDocumento varchar(15) not null,
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
    codigoProducto varchar(20) not null,
    descripcionProducto varchar(200) not null,
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
    values ('Kevin', 'Castillo', 'kcastillo-2021184', 2021184, '1');
insert into Usuario(nombreUsuario, apellidoUsuario, usuarioLogin, contrasena, codigoTipoUsuario) 
    values ('Hettson', 'Ceballos', 'hceballos-2020415', 2020415, '2');
insert into Usuario(nombreUsuario, apellidoUsuario, usuarioLogin, contrasena, codigoTipoUsuario) 
    values ('Andrés', 'Coloma', 'acoloma-2023009', 2020009, '2');
insert into Usuario(nombreUsuario, apellidoUsuario, usuarioLogin, contrasena, codigoTipoUsuario) 
    values ('Cliente', 'CLIENTE', 'cliente2024', 'cliente123', '3');
 
insert into Categoria(nombreCategoria, descripcionCategoria, localizacionCategoria) values ('a','a','a'); 
 
-- DISTRIBUIDORES
insert into Distribuidores(nombreDistribuidor, direccionDistribuidor, codigoPostal, paginaWeb) values ('Solutek B2B', 'FFJ8+MFQ, 3ra. Avenida, Villa Canales', '01072', 'https://guatemala.solutekla.com/');
insert into Distribuidores(nombreDistribuidor, direccionDistribuidor, codigoPostal, paginaWeb) values ('Agencias Way', '18 Calle 5-59 Zona 11 Colonia Mariscal, Ciudad de Guatemala', '01011', 'https://agenciaswayonline.com/');
insert into Distribuidores(nombreDistribuidor, direccionDistribuidor, codigoPostal, paginaWeb) values ('Distribuidora de Productos Informáticos','3a. Avenida 12-16, zona 10, Cuidad de Guatemala, Guatemala','01010','https://dpi.gt/');
insert into Distribuidores(nombreDistribuidor, direccionDistribuidor, codigoPostal, paginaWeb) values ('Game Over','Centro comercial Pradera Concepción, Local 14, Zona 12, Ciudad de Guatemala, Guatemala','01012','https://gameover.gt/');
insert into Distribuidores(nombreDistribuidor, direccionDistribuidor, codigoPostal, paginaWeb) values ('Microplay','Centro comercial Metronorte, Local 5, Zona 18, Ciudad de Guatemala, Guatemala','01018','https://microplaygt.com/');
insert into Distribuidores(nombreDistribuidor, direccionDistribuidor, codigoPostal, paginaWeb) values ('Best Buy','Centro comercial Miraflores, Nivel 1, Zona 11, Ciudad De Guatemala, Guatemala','0.011','https://bestbuy.com.gt/');
insert into Distribuidores(nombreDistribuidor, direccionDistribuidor, codigoPostal, paginaWeb) values ('Simán','Centro comercial los Próceres, Local 201, Zona 10, Ciudad de Guatemala, Guatemala','01010','https://siman.com.gt/');
 
-- PRODUCTOS
insert into Productos(codigoProducto, descripcionProducto, codigoCategoria, codigoDistribuidor) values('NATEOYE-NSW', 'NieR Automata: The End of YoRHa Edition - Nintendo Switch',  1, 1);
 
-- ---------------------------------JUEGOS Y CONSOLAS XBOX---------------------------------
 
insert into Productos(codigoProducto, descripcionProducto, codigoCategoria, codigoDistribuidor) values('XP-1234', 'Halo Infinite - Xbox Series X|S', 1, 1);
insert into Productos(codigoProducto, descripcionProducto, codigoCategoria, codigoDistribuidor) values('GX-9876', 'Forza Horizon 5 - Xbox Series X|S', 1, 1);
insert into Productos(codigoProducto, descripcionProducto, codigoCategoria, codigoDistribuidor) values('ST-4567', 'Starfield - Xbox Series X|S', 1, 1);
insert into Productos(codigoProducto, descripcionProducto, codigoCategoria, codigoDistribuidor) values('RF-2345', 'Redfall - Xbox Series X|S', 1, 1);
insert into Productos(codigoProducto, descripcionProducto, codigoCategoria, codigoDistribuidor) values('FB-7654', 'Fable - Xbox Series X|S', 1, 1);
insert into Productos(codigoProducto, descripcionProducto, codigoCategoria, codigoDistribuidor) values('GE-3456', 'Gears 5 - Xbox Series X|S', 1, 1);
insert into Productos(codigoProducto, descripcionProducto, codigoCategoria, codigoDistribuidor) values('GW-5678', 'Gears of War 2 - Xbox 360', 1, 1);
insert into Productos(codigoProducto, descripcionProducto, codigoCategoria, codigoDistribuidor) values('HC-8901', 'Halo: Combat Evolved - Xbox Original', 1, 1);
insert into Productos(codigoProducto, descripcionProducto, codigoCategoria, codigoDistribuidor) values('FH-2345', 'Forza Horizon 4 - Xbox One', 1, 1);
insert into Productos(codigoProducto, descripcionProducto, codigoCategoria, codigoDistribuidor) values('FM-4567', 'Forza Motorsport 4 - Xbox 360', 1, 1);
insert into Productos(codigoProducto, descripcionProducto, codigoCategoria, codigoDistribuidor) values('SK-6789', 'Skrym - Xbox 360', 1, 1);
insert into Productos(codigoProducto, descripcionProducto, codigoCategoria, codigoDistribuidor) values('CR-9012', 'Conker: Live & Reloaded - Xbox Original', 1, 1);
insert into Productos(codigoProducto, descripcionProducto, codigoCategoria, codigoDistribuidor) values('NG-3456', 'Ninja Garden - Xbox Original', 1, 1);
insert into Productos(codigoProducto, descripcionProducto, codigoCategoria, codigoDistribuidor) values('MC-5678', 'Minecraft - Xbox One', 1, 1);
insert into Productos(codigoProducto, descripcionProducto, codigoCategoria, codigoDistribuidor) values('SO-7890', 'Sea of Thieves - Xbox One', 1, 1);
insert into Productos(codigoProducto, descripcionProducto, codigoCategoria, codigoDistribuidor) values('H3-1234', 'Halo 3 - Xbox 360', 1, 1);
insert into Productos(codigoProducto, descripcionProducto, codigoCategoria, codigoDistribuidor) values('ME-4567', 'Mass Effect 2 - Xbox 360', 1, 1);
insert into Productos(codigoProducto, descripcionProducto, codigoCategoria, codigoDistribuidor) values('JE-6789', 'Jade Empire - Xbox Original', 1, 1);
 
 
-- ---------------------------------ACCESORIOS---------------------------------
 
insert into Productos(codigoProducto, descripcionProducto, codigoCategoria, codigoDistribuidor) values('XBOXELITE-NSW', 'Xbox Elite Wireless Controller Series 2', 1, 1);
insert into Productos(codigoProducto, descripcionProducto, codigoCategoria, codigoDistribuidor) values('XBOXCARBON-NSW', 'Xbox Wireless Controller – Carbon Black', 1, 1);
insert into Productos(codigoProducto, descripcionProducto, codigoCategoria, codigoDistribuidor) values('XBOXSHOCK-NSW', 'Xbox Wireless Controller – Shock Blue', 1, 1);
insert into Productos(codigoProducto, descripcionProducto, codigoCategoria, codigoDistribuidor) values('XBOXCHARGE-NSW', 'Xbox Play & Charge Kit', 1, 1);
insert into Productos(codigoProducto, descripcionProducto, codigoCategoria, codigoDistribuidor) values('POWERACHARGE-NSW', 'PowerA Dual Charging Station for Xbox Series X|S', 1, 1);
insert into Productos(codigoProducto, descripcionProducto, codigoCategoria, codigoDistribuidor) values('XBOXHEADSET-NSW', 'Xbox Wireless Headset', 1, 1);
insert into Productos(codigoProducto, descripcionProducto, codigoCategoria, codigoDistribuidor) values('STEELSERIES-NSW', 'SteelSeries Arctis 9X', 1, 1);
insert into Productos(codigoProducto, descripcionProducto, codigoCategoria, codigoDistribuidor) values('HORIVERTICAL-NSW', 'Hori Vertical Stand for Xbox Series X', 1, 1);
insert into Productos(codigoProducto, descripcionProducto, codigoCategoria, codigoDistribuidor) values('POWERASTAND-NSW', 'PowerA Console Stand for Xbox Series X', 1, 1);
insert into Productos(codigoProducto, descripcionProducto, codigoCategoria, codigoDistribuidor) values('HORICOOLING-NSW', 'Hori Xbox Series X Cooling Stand', 1, 1);
insert into Productos(codigoProducto, descripcionProducto, codigoCategoria, codigoDistribuidor) values('HORIPROTECTOR-NSW', 'Hori Duraflexi Protector for Xbox Series X', 1, 1);
insert into Productos(codigoProducto, descripcionProducto, codigoCategoria, codigoDistribuidor) values('OIVOVERTICAL-NSW', 'OIVO Vertical Stand with Cooling Fan for Xbox Series X', 1, 1);
insert into Productos(codigoProducto, descripcionProducto, codigoCategoria, codigoDistribuidor) values('KOOKEKCOOLING-NSW', 'Kootek Xbox Series X Cooling Stand', 1, 1);

-- DISTRIBUIDORES

insert into Distribuidores(nombreDistribuidor, direccionDistribuidor, codigoPostal, paginaWeb) values ('Solutek B2B', 'FFJ8+MFQ, 3ra. Avenida, Villa Canales', '01072', 'https://guatemala.solutekla.com/');

insert into Distribuidores(nombreDistribuidor, direccionDistribuidor, codigoPostal, paginaWeb) values ('Agencias Way', '18 Calle 5-59 Zona 11 Colonia Mariscal, Ciudad de Guatemala', '01011', 'https://agenciaswayonline.com/');

insert into Distribuidores(nombreDistribuidor, direccionDistribuidor, codigoPostal, paginaWeb) values ('Pacifiko', 'Ofibodega 605, Centro de Negocios, Calz. la Paz 0 Av. 23-13 Z.17, Cdad. de Guatemala', '01010', 'https://www.pacifiko.com/');


insert into Distribuidores(nombreDistribuidor, direccionDistribuidor, codigoPostal, paginaWeb) values ('Intelaf', 'Las Plazas, Cdad. de Guatemala', '01012', 'https://www.intelaf.com/?utm_source=GOOGLE&utm_medium=SEARCH&utm_id=SEARCH&gclid=EAIaIQobChMI_KD58JbjhwMVxZ9aBR2DQS0mEAAYASAAEgKBiPD_BwE');

insert into Distribuidores(nombreDistribuidor, direccionDistribuidor, codigoPostal, paginaWeb) values ('Walmart', 'Calzada Roosevelt 26-95, Cdad. de Guatemala', '01010', 'https://www.walmart.com.gt/');


insert into Distribuidores(nombreDistribuidor, direccionDistribuidor, codigoPostal, paginaWeb) values ('Gangas Videogames', '2ndo nivel a la par de Claro, Centro Comercial Tikal Futura, nivel, Cdad. de Guatemala', '01011', 'https://gangasvideogames.com/');
 
-- JUEGOS

INSERT INTO Productos(codigoProducto, descripcionProducto, codigoCategoria, codigoDistribuidor) VALUES ('REDISE-HRR', 'Resident Evil 4 Remake (PS5 y PS4)', 1, 1);

INSERT INTO	Productos(codigoProducto, descripcionProducto, codigoCategoria, codigoDistribuidor) VALUES ('TLOUFI-PII', 'The Last of Us Par II (PS5 y PS4)', 1, 1);

INSERT INTO Productos(codigoProducto, descripcionProducto, codigoCategoria, codigoDistribuidor) VALUES ('SPDIR-MVL', 'Marvel\'s Spider-Man 2 (PS5 y PS4)', 1, 1);

INSERT INTO Productos(codigoProducto, descripcionProducto, codigoCategoria, codigoDistribuidor) VALUES ('HDRIV-DEV', 'Helldivers (PS5)', 1, 1);

INSERT INTO Productos(codigoProducto, descripcionProducto, codigoCategoria, codigoDistribuidor) VALUES ('REDITW-HOW', 'Resident Evil 2 Remake (PS5 y PS4)', 1, 1);

INSERT INTO Productos(codigoProducto, descripcionProducto, codigoCategoria, codigoDistribuidor) VALUES ('RERKE-UGH', 'Resident Evil 3 Remake (PS5 y PS4)', 1, 1);

INSERT INTO Productos(codigoProducto, descripcionProducto, codigoCategoria, codigoDistribuidor) VALUES ('GDOWA-RAG', 'God of War Ragnarök (PS5 y PS4)', 1, 1);

INSERT INTO Productos(codigoProducto, descripcionProducto, codigoCategoria, codigoDistribuidor) VALUES ('RDMTON-ART', 'Red Redemption 2 (PS4)', 1, 1);

INSERT INTO Productos(codigoProducto, descripcionProducto, codigoCategoria, codigoDistribuidor) VALUES ('RSERO-KYO', 'Rise of Ronin (PS5)', 1, 1);

INSERT INTO Productos(codigoProducto, descripcionProducto, codigoCategoria, codigoDistribuidor) VALUES ('CDMWA-KIA', 'Call Of Duty: Modern Warfare 2 (PS5)', 1, 1);

INSERT INTO Productos(codigoProducto, descripcionProducto, codigoCategoria, codigoDistribuidor) VALUES ('TLOUPI-PIJ', 'The Last of Us Part I (PS5)', 1, 1);

INSERT INTO Productos(codigoProducto, descripcionProducto, codigoCategoria, codigoDistribuidor) VALUES ('CLLSTO-OHG', 'The Callisto Protocol (PS5)', 1, 1);

INSERT INTO Productos(codigoProducto, descripcionProducto, codigoCategoria, codigoDistribuidor) VALUES ('GOSHT-PPE', 'Ghost Of Tsushima (PS5 y PS4)', 1, 1);

INSERT INTO Productos(codigoProducto, descripcionProducto, codigoCategoria, codigoDistribuidor) VALUES ('GOTAL-MTF', 'Grand Theft Auto Online (PS5)', 1, 1);

INSERT INTO Productos(codigoProducto, descripcionProducto, codigoCategoria, codigoDistribuidor) VALUES ('EAFCW-ESF', 'EA SPORTS FC™ 24 Edición Estándar (PS5 y PS4)', 1, 1);

INSERT INTO Productos(codigoProducto, descripcionProducto, codigoCategoria, codigoDistribuidor) VALUES ('MKASK-WHY', 'Mortal Kombat 1 (PS5)', 1, 1);

INSERT INTO Productos(codigoProducto, descripcionProducto, codigoCategoria, codigoDistribuidor) VALUES ('MESMER-MEL', 'Elden Ring (PS5 y PS4)', 1, 1);

INSERT INTO Productos(codigoProducto, descripcionProducto, codigoCategoria, codigoDistribuidor) VALUES ('DINGLIG-JPR', 'Dying Light (PS4)', 1, 1);

INSERT INTO Productos(codigoProducto, descripcionProducto, codigoCategoria, codigoDistribuidor) VALUES ('KOKVC-GOR', 'Knack (PS4)', 1, 1);

INSERT INTO Productos(codigoProducto, descripcionProducto, codigoCategoria, codigoDistribuidor) VALUES ('EVELI-BAK', 'Resident Evil 7 (PS4)', 1, 1);

INSERT INTO Productos(codigoProducto, descripcionProducto, codigoCategoria, codigoDistribuidor) VALUES ('HOLWKI-VPA', 'Hollow Knight (PS4)', 1, 1);

INSERT INTO Productos(codigoProducto, descripcionProducto, codigoCategoria, codigoDistribuidor) VALUES ('DMETER-WAO', 'Doom Eternal (PS5 y PS4)', 1, 1);

INSERT INTO Productos(codigoProducto, descripcionProducto, codigoCategoria, codigoDistribuidor) VALUES ('ACCOR-MOR', 'Assassin\'s Creed Origins (PS4)', 1, 1);

INSERT INTO Productos(codigoProducto, descripcionProducto, codigoCategoria, codigoDistribuidor) VALUES ('AVALHA-THO', 'Assassin\'s Creed Valhalla (PS4)', 1, 1);

INSERT INTO Productos(codigoProducto, descripcionProducto, codigoCategoria, codigoDistribuidor) VALUES ('BARKM-JOK', 'Batman Return To Arkham (PS4)', 1, 1);

INSERT INTO Productos(codigoProducto, descripcionProducto, codigoCategoria, codigoDistribuidor) VALUES ('BORDR-OHH', 'Borderlands 3 (PS4)', 1, 1);

INSERT INTO Productos(codigoProducto, descripcionProducto, codigoCategoria, codigoDistribuidor) VALUES ('CDBLKS-CWW', 'Call of Duty: Black Ops Cold War', 1, 1);

INSERT INTO Productos(codigoProducto, descripcionProducto, codigoCategoria, codigoDistribuidor) VALUES ('DEIMAN-HUM', 'Detroit Become Human (PS4)', 1, 1);

INSERT INTO Productos(codigoProducto, descripcionProducto, codigoCategoria, codigoDistribuidor)  VALUES ('HDEAS-DEM', 'Hades (PS4)', 1, 1);

INSERT INTO Productos(codigoProducto, descripcionProducto, codigoCategoria, codigoDistribuidor)  VALUES ('GALOYP-CJI', 'Grand Theft Auto The Trilogy (PS5 y PS4)', 1, 1);

 

-- CONSOLAS

INSERT INTO Productos(codigoProducto, descripcionProducto, codigoCategoria, codigoDistribuidor)  VALUES ('PSDT-PSE', 'Consola PlayStation 5 Edición Estándar Digital', 1, 1);

INSERT INTO Productos(codigoProducto, descripcionProducto, codigoCategoria, codigoDistribuidor)  VALUES ('PSDE-PSD', 'Consola PlayStation 5 Edición Estándar con Disco', 1, 1);

INSERT INTO Productos(codigoProducto, descripcionProducto, codigoCategoria, codigoDistribuidor)  VALUES ('PSSL-PSJ', 'Consola Sony Playstation 5 Slim Edición Digital Bundle + Juego Returnal Y Ratchet And Clank', 1, 1);

INSERT INTO Productos(codigoProducto, descripcionProducto, codigoCategoria, codigoDistribuidor)  VALUES ('PSSJ-PSL', 'Consola Playstation 5 Slim Disco + Juego Call Of Duty Modern Warfare III', 1, 1);

INSERT INTO Productos(codigoProducto, descripcionProducto, codigoCategoria, codigoDistribuidor)  VALUES ('PSBL-LPS', 'Consola Playstation 5 Slim Edición Digital Color Blanco', 1, 1);

INSERT INTO Productos(codigoProducto, descripcionProducto, codigoCategoria, codigoDistribuidor)  VALUES ('PSYP-SRP', 'PlayStation 4 Pro', 1, 1);
 
-- ACCESORIOS

INSERT INTO Productos(codigoProducto, descripcionProducto, codigoCategoria, codigoDistribuidor)  VALUES ('DUASEN-BLN', 'Control Inalámbrico DualSense Playstation PS5, Color Blanco', 1, 1);

INSERT INTO Productos(codigoProducto, descripcionProducto, codigoCategoria, codigoDistribuidor)  VALUES ('DASENL-CLT', 'Control Inalámbrico Dual Sense Para PS5, Color Celeste, Sony Precio', 1, 1);

INSERT INTO Productos(codigoProducto, descripcionProducto, codigoCategoria, codigoDistribuidor) VALUES ('DSFGA-WHT', 'Control Inalámbrico DualSense Playstation PS5, Color Blanco', 1, 1);

INSERT INTO Productos(codigoProducto, descripcionProducto, codigoCategoria, codigoDistribuidor) VALUES ('DARLT-LBL', 'Control Inalámbrico Dual Sense Para PS5, Color Celeste', 1, 1);

INSERT INTO Productos(codigoProducto, descripcionProducto, codigoCategoria, codigoDistribuidor) VALUES ('DADSL-BLU', 'Control Inalámbrico Dualsense Playstation PS5, Color Azul Cobal', 1, 1);

INSERT INTO Productos(codigoProducto, descripcionProducto, codigoCategoria, codigoDistribuidor) VALUES ('DADFL-BLK', 'Control Inalámbrico Dual Sense Para PS5, Negro Noche', 1, 1);

INSERT INTO Productos(codigoProducto, descripcionProducto, codigoCategoria, codigoDistribuidor) VALUES ('DOGDA-RED', 'Control Sony PS5 Dualsense Inalámbrico Rojo Cósmico', 1, 1);

INSERT INTO Productos(codigoProducto, descripcionProducto, codigoCategoria, codigoDistribuidor) VALUES ('DAJDF-EDGE', 'Control Sony PS5 DualSense Edge Inalámbrico', 1, 1);

INSERT INTO Productos(codigoProducto, descripcionProducto, codigoCategoria, codigoDistribuidor) VALUES ('PLSED-EDD', 'Auriculares inalámbricos PlayStation PULSE 3D', 1, 1);

INSERT INTO Productos(codigoProducto, descripcionProducto, codigoCategoria, codigoDistribuidor) VALUES ('CAMIM-EHD', 'Cámara Oficial Para PS5 HD Lentes Duales 1080P', 1, 1);

INSERT INTO Productos(codigoProducto, descripcionProducto, codigoCategoria, codigoDistribuidor) VALUES ('PTRAL-PSU', 'Playstation Portal Reproductor Remoto Para Playstation 5', 1, 1);

INSERT INTO Productos(codigoProducto, descripcionProducto, codigoCategoria, codigoDistribuidor) VALUES ('RMOTE-WHT', 'Mando a distancia para PlayStation Media, color blanco', 1, 1);

INSERT INTO Productos(codigoProducto, descripcionProducto, codigoCategoria, codigoDistribuidor) VALUES ('CONTPS-MID', 'CONTROL PS4 AZUL MEDIANOCHE', 1, 1);

INSERT INTO Productos(codigoProducto, descripcionProducto, codigoCategoria, codigoDistribuidor) VALUES ('CONETY-WHT', 'CONTROL PS4 BLANCO', 1, 1);

INSERT INTO Productos(codigoProducto, descripcionProducto, codigoCategoria, codigoDistribuidor) VALUES ('CONUIR-RED', 'CONTROL PS4 ROJO', 1, 1);

INSERT INTO Productos(codigoProducto, descripcionProducto, codigoCategoria, codigoDistribuidor) VALUES ('COMAFR-BLK', 'Control PS4 negro', 1, 1);

INSERT INTO Productos(codigoProducto, descripcionProducto, codigoCategoria, codigoDistribuidor) VALUES ('CVERER-REF', 'Cubierta para Consola PS5 Edición Estándar (Volcanic Red)', 1, 1);

INSERT INTO Productos(codigoProducto, descripcionProducto, codigoCategoria, codigoDistribuidor) VALUES ('CVERSD-SIL', 'Cubierta para Consola PS5 Edición Estándar (Sterling Silver)', 1, 1);

INSERT INTO Productos(codigoProducto, descripcionProducto, codigoCategoria, codigoDistribuidor) VALUES ('COVSLM-BLU', 'Cubierta para Consola PS5 Slim (Cobalt Blue)', 1, 1);

INSERT INTO Productos(codigoProducto, descripcionProducto, codigoCategoria, codigoDistribuidor) VALUES ('COMPSA-BLK', 'Cubierta para Consola PS5 Slim (Negro)', 1, 1);

INSERT INTO Productos(codigoProducto, descripcionProducto, codigoCategoria, codigoDistribuidor) VALUES ('WHEEAL-PSW', 'Timón Hori Racing Wheel Apex para PS5 y PS4', 1, 1);

-- Inserts para los distribuidores
insert into Distribuidores(nombreDistribuidor, direccionDistribuidor, codigoPostal, paginaWeb) values ('Aqui todos somos gamers', 'Avenida Las Américas 7-04 Zona 3', '01003', 'https://www.aquitodossomosgamers.com/');
Insert into Distribuidores (nombreDistribuidor, direccionDistribuidor, codigoPostal, paginaWeb) values ('Click GT', 'Centro comercial Peri Roosevelt zona 7 local 40', '01007', 'https://www.click.gt/');
Insert into Distribuidores (nombreDistribuidor, direccionDistribuidor, codigoPostal, paginaWeb) values ('Rech', 'Local S6 en el sótano, Plaza San Cristobal', '01057', 'https://www.rech.com.gt/');
Insert into Distribuidores (nombreDistribuidor, direccionDistribuidor, codigoPostal, paginaWeb) values ('Kemik', '7A Avenida 2-21, Cdad. de Guatemala', '01004', 'https://www.kemik.gt/tienda-en-linea/gaming');
Insert into Distribuidores (nombreDistribuidor, direccionDistribuidor, codigoPostal, paginaWeb) values ('Max', 'Calzada Roosevelt 25-50 Zona 7, Centro Comercial Peri Roosevelt, local 1-25.', '01007', 'https://www.max.com.gt/');
 
-- Inserts para los productos
-- Para 'Aqui todos somos gamers'
insert into Productos (codigoProducto, descripcionProducto, codigoCategoria, codigoDistribuidor) values ('SGCOS', 'Silla Gaming Cougar Outrider S', '1', '1');
insert into Productos (codigoProducto, descripcionProducto, codigoCategoria, codigoDistribuidor) values ('TGMAKC400', 'Teclado Gaming Mecánico Alámbrico Checkpoint Naruto KX-400', '1', '1');
insert into Productos (codigoProducto, descripcionProducto, codigoCategoria, codigoDistribuidor) values ('TGMCCHRGB', 'Teclado Gaming Mecánico Alámbrico Cougar Core Híbrido RGB Negro', '1', '1');
insert into Productos (codigoProducto, descripcionProducto, codigoCategoria, codigoDistribuidor) values ('MGINLOLOGG305', 'Mouse Gaming Inalámbrico Óptico Logitech G305 LIGHTSPEED', '1', '1');
insert into Productos (codigoProducto, descripcionProducto, codigoCategoria, codigoDistribuidor) values ('AHSTLOGZONE100', 'Audífonos tipo Headset Logitech ZONE VIBE 100', '1', '1');
 
-- Para 'Click GT'
insert into Productos (codigoProducto, descripcionProducto, codigoCategoria, codigoDistribuidor) values('AIPTMRZ', 'AUDIFONO INTRAUDITIVO PARA TRASMISION MORAY NASA RZ12-04450100-R3U1', '1', '2');
insert into Productos (codigoProducto, descripcionProducto, codigoCategoria, codigoDistribuidor) values('MUYGX', 'MICROFONO USB YETI GX BLUE SOUND NEGRO', '1', '2');
insert into Productos (codigoProducto, descripcionProducto, codigoCategoria, codigoDistribuidor) values('PCDF', 'PALANCA DE CAMBIOS DRIVING FORCE PARA TIMON Y PEDALES G923-G29-G920', '1', '2');
insert into Productos (codigoProducto, descripcionProducto, codigoCategoria, codigoDistribuidor) values('MSM', 'MICROFONO SEIREN MINI - MERCURY', '1', '2');
insert into Productos (codigoProducto, descripcionProducto, codigoCategoria, codigoDistribuidor) values('BSV2', 'BASE STATION V2 CHROMA', '1', '2');
 
-- Para 'Rech'
insert into Productos (codigoProducto, descripcionProducto, codigoCategoria, codigoDistribuidor) values('HSGXPPRECW', 'Headset Gaming XPG PRECOG S White 3.5mm', '1', '3');
insert into Productos (codigoProducto, descripcionProducto, codigoCategoria, codigoDistribuidor) values('CAMWEBREDGW800', 'Camara Web Redragon GW800 1080p USB', '1', '3');
insert into Productos (codigoProducto, descripcionProducto, codigoCategoria, codigoDistribuidor) values('BOCXTXTS110', 'Bocinas Xtech XTS-110 negro', '1', '3');
insert into Productos (codigoProducto, descripcionProducto, codigoCategoria, codigoDistribuidor) values('HUBUSB4P', 'Hub USB Adaptable de 4 Puertos 3.0 XTC-390', '1', '3');
insert into Productos (codigoProducto, descripcionProducto, codigoCategoria, codigoDistribuidor) values('MICROLOGBLUESN', 'Microfono Logitech Blue Snowball Ice Black', '1', '3');
 
-- Para 'Kemik'
insert into Productos (codigoProducto, descripcionProducto, codigoCategoria, codigoDistribuidor) values('LOGPROXTKL', 'Logitech Pro X TKL Teclado Gaming Inalámbrico 2.4GHz en Inglés Negro', '1', '4');
insert into Productos (codigoProducto, descripcionProducto, codigoCategoria, codigoDistribuidor) values('XMG27IFHD', 'Xiaomi G27i Monitor Gaming 27" Full HD, HDMI y DisplayPort a 165Hz Negro', '1', '4');
insert into Productos (codigoProducto, descripcionProducto, codigoCategoria, codigoDistribuidor) values('ASUTUF31.5', 'Asus Monitor Curvo TUF Gaming 31.5" HDMI y DP QHD 165Hz Negro', '1', '4');
insert into Productos (codigoProducto, descripcionProducto, codigoCategoria, codigoDistribuidor) values('RZGIGANTUSV2', 'Razer Mousepad Gaming Gigantus V2 XXL', '1', '4');
insert into Productos (codigoProducto, descripcionProducto, codigoCategoria, codigoDistribuidor) values('XTA130LAPSUP', 'XTECH XTA-130 Soporte Plegable para Laptops hasta 17" - Negro', '1', '4');
 
-- Para 'Max'
insert into Productos (codigoProducto, descripcionProducto, codigoCategoria, codigoDistribuidor) values('AUSONYINZONEB', 'Audífonos Sony INZONE Buds Inalámbricos con Sonido Espacial (Negro)', '1', '5');
insert into Productos (codigoProducto, descripcionProducto, codigoCategoria, codigoDistribuidor) values('MOCDELL460BCMJ', 'Mochila Dell 460BCMJ Pro Slim Compacta para Laptops de 15"', '1', '5');
insert into Productos (codigoProducto, descripcionProducto, codigoCategoria, codigoDistribuidor) values('SGPCH201', 'Silla Gaming Primus PCH-201 Thronos 200S (Negro)', '1', '5');
insert into Productos (codigoProducto, descripcionProducto, codigoCategoria, codigoDistribuidor) values('VENTILADORMAX', 'Ventilador Maxell CA-LC-9 Gaming Cooler para Laptops', '1', '5');
insert into Productos (codigoProducto, descripcionProducto, codigoCategoria, codigoDistribuidor) values('CTRZRKIIV2', 'Control Razer Kishi V2 Gaming para iPhone con Ajuste Universal (Negro)', '1', '5');