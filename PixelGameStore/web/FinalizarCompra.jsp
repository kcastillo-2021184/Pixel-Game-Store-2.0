<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<html lang="en">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Pagina Ventas</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/css/bootstrap.min.css" integrity="sha384-TX8t27EcRE3e/ihU7zmQxVncDAy5uIKz4rEkgIXeMed4M0jlfIDPvg6uqKI2xXr2" crossorigin="anonymous">
    <style>
        .preview-img {
            cursor: pointer;
            max-width: 100px;
            max-height: 100px;
            display: block;
        }
        .hidden {
            display: none;
        }
        .container {
            display: flex;
            align-items: center;
            justify-content: center;
        }
        .button {
            padding: 10px 20px;
            font-size: 13px;
            margin: 0 10px;
            cursor: pointer;
        }
        .placeholder {
            padding: 10px 20px;
            font-size: 13px;
            border: 1px solid #000;
            height: 40px;
            text-align: center;
        }
        .main-container {
            display: flex;
            flex-direction: column;
            align-items: center; /* Centra el contenedor horizontalmente */
        }
        .sales-container {
            width: 100%;
            max-width: 1200px; /* Ajusta el ancho del contenedor según sea necesario */
            margin: 20px auto; /* Centra el contenedor horizontalmente */
            padding: 20px; /* Añade espacio alrededor del contenido */
        }
        .oculto {
            display: none;
        }
        .d-flex {
            display: flex;
        }
        .align-items-center {
            align-items: center; /* Centra verticalmente */
        }
        .justify-content-center {
            justify-content: center; /* Centra horizontalmente, opcional */
        }
        .btn-custom {
            margin: 5px;
            padding: 10px 20px;
            font-size: 14px;
            border: none;
            cursor: pointer;
            color: white;
        }
        .btn-custom.cf {
            background-color: red;
        }
        .btn-custom.nit {
            background-color: red;
        }
        .btn-custom.active {
            background-color: green;
        }
        .btn-custom.disabled {
            background-color: gray;
            cursor: not-allowed;
        }
        .content {
            display: none;
        }
        .content.active {
            display: block;
        }
        .card-header {
            display: flex;
            justify-content: space-between;
            padding: 10px;
        }

        .left-align {
            text-align: left;
        }

        .right-align {
            text-align: right;
        }

        @media print {
            /* Esconde elementos no deseados en la impresión */
            @page {
                margin: 0; /* Elimina márgenes */
            }
            .parte1, .parte2, .oculto, .hidden {
                display: none;
            }
            .card-header, .card-footer {
                display: block; /* Asegura que los elementos necesarios se muestren */
            }
        }
    </style>
    <script>
        function toggleContent(button) {
            // Remove 'active' class from all buttons
            var buttons = document.querySelectorAll('.btn-custom');
            buttons.forEach(function(btn) {
                btn.classList.remove('active');
            });

            // Hide all contents
            var contents = document.querySelectorAll('.content');
            contents.forEach(function(content) {
                content.classList.remove('active');
            });

            // Add 'active' class to clicked button and associated content
            button.classList.add('active');
            var target = button.getAttribute('data-target');
            document.querySelector(target).classList.add('active');
        }

        function checkTotal() {
            var totalPagar = parseFloat('${totalPagar}');
            var cfButton = document.querySelector('.btn-custom.cf');
            if (totalPagar > 2499.99) {
                cfButton.classList.add('disabled');
                cfButton.onclick = function(event) {
                    event.preventDefault(); // Prevent any action when button is clicked
                };
            } else {
                cfButton.classList.remove('disabled');
                cfButton.onclick = function() {
                    toggleContent(cfButton);
                };
            }
        }

        function printAndShowBackButton() {
            // Show the "Volver a la tienda" button
            document.getElementById('volverTienda').classList.remove('oculto');
            
            // Print the document
            window.print();
        }

        window.onload = function() {
            checkTotal(); // Check total on page load
        };
    </script>
</head>
<body>
   <div class="main-container">
        <div class="sales-container">
            <div class="card parte1">
                <br>
                <h2><strong><center><img src="img/MarioLuigiDancing.gif" width="100">¡Gracias por comprar en Pixel-Game-Store! <img src="img/SonicThumbsUp.gif" width="100"></center></strong></h2>
                <h3><strong><center>Te dejamos tu comprobante de compra para que recojas tu pedido en nuestras sucursales.</center></strong></h3>
                <br>
            </div>
            <br>
            <div class="card">
                <!-- Flex container for alignment -->
                <div class="card-header">
                    <!-- Left aligned content -->
                    <div class="left-align">
                        <label><strong>Fecha de emisión:</strong> ${factura.getFechaFactura()}</label>
                        <br>
                        <label><strong>Cliente:</strong> ${cliente.getNombresCliente()} ${cliente.getApellidosCliente()}</label>
                        <br>
                        <label><strong>NIT:</strong> ${cliente.getNITCliente()} </label>
                        <br>
                        <label><strong>Comprobante emitido por:</strong> Página Web de Pixel-Game-Store</label>
                    </div>

                    <!-- Right aligned content -->
                    <div class="right-align">
                        <h3><strong>Pixel-Game-Store <img src="img/LogoPixel.png" width="50"></strong></h3>
                        <label><strong>Número de Factura:</strong> ${factura.getNumeroFactura()}</label>
                    </div>
                </div>
                
                <div class="card-body">
                    <table class="table">
                        <thead>
                            <tr>
                                <th><strong>Item</strong></th>
                                <th><strong>Codigo</strong></th>
                                <th><strong>Descripcion</strong></th>
                                <th><strong>Precio</strong></th>
                                <th><strong>Cantidad</strong></th>
                                <th><strong>SubTotal</strong></th>
                            </tr>
                        </thead>
                        <tbody>
                            <c:forEach var="list" items="${lista}">
                                <tr>
                                    <td>${list.getCodigoArticulo()}</td>
                                    <td>${list.getCodigoProducto()}</td>
                                    <td>${list.getDescripcionProducto()}</td>
                                    <td>Q.${list.getPrecioUnitario()}0</td>
                                    <td>${list.getCantidad()}</td>
                                    <td>Q.${list.getSubTotal()}0</td>
                                </tr>
                            </c:forEach>
                        </tbody>
                    </table>
                </div>
                <div class="card-footer d-flex">
                    <div class="col-sm-6">
                        <label><strong>Total a pagar:</strong></label>
                    </div>
                    <div class="col-sm-4 ml-auto"> 
                        <input type="text" name="txtTotal" value="Q.${totalPagar}0" class="form-control" readonly>
                    </div>
                </div>
            </div>
                    
            <div class="button-container d-flex justify-content-center">
                <div class="parte2">
                    <br>
                    <a class="btn btn-primary" href="#" onclick="printAndShowBackButton()">Imprimir el comprobante</a>
                    <a id="volverTienda" class="btn btn-success oculto" href="Controlador?menu=FinalizarCompra&accion=VolverATienda">Volver a la tienda</a>
                </div>
            </div>
        </div>
    </div>
    <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js" integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj" crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js" integrity="sha384-9/reFTGAW83EW2RDu2S0VKaIzap3H66lZH81PoYlF9S3Xt0BQ91CgtF4zEhuB5VXM" crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/js/bootstrap.min.js" integrity="sha384-rbsA0F59w7axnE0v7VjxD9dWHTqF1bg5E48jYAtTni1I+YwAM0xH8gUK5KfUzX7H" crossorigin="anonymous"></script>
    <audio id="myAudio" src="audio/CompraRealizada.mp3"></audio>

    <script>
        window.onload = function() {
            var audio = document.getElementById("myAudio");
            audio.volume = 1.0; 
            audio.play();
        };
    </script>
</body>
</html>
