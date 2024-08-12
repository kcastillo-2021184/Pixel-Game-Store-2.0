<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
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

        function validateNIT() {
            var nitInput = document.getElementById('txtNIT');
            var finalizarButton = document.getElementById('btnFinalizarNIT');
            if (nitInput.value.trim() === '') {
                finalizarButton.disabled = true;
            } else {
                finalizarButton.disabled = false;
            }
        }

        window.onload = function() {
            checkTotal(); // Check total on page load

            // Add event listener to NIT input
            var nitInput = document.getElementById('txtNIT');
            nitInput.addEventListener('input', validateNIT);

            // Initial validation in case there is already some value in the input
            validateNIT();
        };
    </script>
</head>
<body>
    <div class="main-container">
        <div class="sales-container">
            <div class="card">
                <br>
                <h2><strong><center>Carrito de compras <img src="img/CarritoPixel.png" width="50"></center></strong></h2>
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
                        <input type="text" name="txtTotal" value="Q.${totalPagar}0" class="form-control">
                    </div>
                </div>
            </div>

            <!-- Buttons and content for CF and NIT -->
            <div class="button-container d-flex justify-content-center">
                <button class="btn-custom cf" data-target="#cf-content">
                    CF (Compra menor a Q.2500.00)
                </button>
                <button class="btn-custom nit" onclick="toggleContent(this)" data-target="#nit-content">
                    NIT
                </button>
            </div>

            <!-- Content for CF -->
            <div id="cf-content" class="content">
                <form action="Controlador?menu=FinalizarCompra&accion=CrearFacturaCF" method="post">
                    <input type="hidden" name="cf" value="1">
                    <button type="submit" name="accion" value="FinalizarCompraCF" class="btn btn-success">Finalizar Compra</button>
                </form>
            </div>

            <!-- Content for NIT -->
            <div id="nit-content" class="content">
                <form action="Controlador?menu=FormularioFactura&accion=ValidarNIT" method="post">
                    <label for="NITCliente">Ingrese su NIT:</label>
                    <input type="text" id="txtNIT" name="txtNIT" required>
                    <button type="submit"  name="accion" value ="ValidarNIT" class="btn btn-primary">Validar NIT</button>

                    <!-- Mostrar mensaje de error si el NIT no existe -->
                    <c:if test="${not empty errorNIT}">
                        <div class="alert alert-danger">${errorNIT}</div>
                    </c:if>
                </form>
            </div>
        </div>
    </div>
    <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js" integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj" crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js" integrity="sha384-9/reFTGAW83EW2RDu2S0VKaIzap3H66lZH81PoYlFhbGU+6BZp6G7niu735Sk7lN" crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/js/bootstrap.min.js" integrity="sha384-w1Q4orYjBQndcko6MimVbzY0tgp4pWB4lZ7lr30WKz0vr/aWKhXdBNmNb5D92v7s" crossorigin="anonymous"></script>
</body>
</html>
