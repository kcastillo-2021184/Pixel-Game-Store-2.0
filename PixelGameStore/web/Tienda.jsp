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
        .sticky-div {
            position: -webkit-sticky;
            position: sticky;
            top: 0;
            z-index: 1000;
            background: white;
        }
        .main-container {
            display: flex;
        }
        .product-container {
            flex: 2;
            overflow-y: auto;
            height: 100vh;
        }
        .sales-container {
            flex: 1;
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
    </style>
    <script>
        function actualizarCantidad(index, delta) {
            var placeholder = document.getElementById('cantidadProducto-' + index);
            var count = parseInt(placeholder.textContent) || 1;
            count += delta;
            if (count < 1) count = 1;
            placeholder.textContent = count;
        }

        function enviarFormulario(index) {
            var cantidad = document.getElementById('cantidadProducto-' + index).textContent;
            document.getElementById('hiddenCantidadProducto-' + index).value = cantidad;
            document.getElementById('formProducto-' + index).submit();
        }
    </script>
</head>
<body>
    <div class="main-container">
        <div class="product-container">
            <div class="container">
                <div class="row">
                    <c:forEach var="producto" items="${productos}" varStatus="status">
                        <div class="col-md-4 mb-4">
                            <div class="card h-100">
                                <form id="formProducto-${status.index}" action="Controlador" method="post">
                                    <input type="hidden" name="menu" value="Tienda">
                                    <input type="hidden" name="accion" value="Agregar-${status.index}">
                                    <div class="productos">
                                        <center>
                                            <div hidden id="codigoProducto-${status.index}">${producto.getCodigoProducto()}</div>
                                            <br>
                                            <c:if test="${not empty producto.getVistaPreviaBase()}">
                                                <img id="imagenProducto-${status.index}" src="data:image/jpeg;base64,${producto.getVistaPreviaBase()}" alt="Vista Previa" width="200"/>
                                            </c:if>
                                            <c:if test="${empty producto.getVistaPreviaBase()}">
                                                <span>Sin imagen</span>
                                            </c:if>
                                            <br>
                                            <br>
                                            <label class="text-center" id="descripcionProducto-${status.index}"><strong>${producto.getDescripcionProducto()}</strong></label>
                                            <br>
                                            <label class="text-center" id="precioUnitario-${status.index}">Q.${producto.getPrecioUnitario()}0</label>
                                            <div class="container">
                                                <button type="button" class="button" onclick="actualizarCantidad(${status.index}, -1)">-</button>
                                                <div class="placeholder" id="cantidadProducto-${status.index}">1</div>
                                                <button type="button" class="button" onclick="actualizarCantidad(${status.index}, 1)">+</button>
                                            </div>
                                            <input type="hidden" name="codigoProducto" value="${producto.getCodigoProducto()}">
                                            <input type="hidden" name="cantidadProducto" id="hiddenCantidadProducto-${status.index}" value="1">
                                            <br>
                                            <button type="button" class="btn-outline-info" onclick="enviarFormulario(${status.index})">Agregar Producto</button>
                                        </center>
                                    </div>
                                </form>
                            </div>
                        </div>
                        <c:if test="${(status.index + 1) % 3 == 0}">
                            <div class="w-100"></div>
                        </c:if>
                    </c:forEach>
                </div>
            </div>
        </div>

        <div class="sales-container sticky-div">
            <div class="card">
                <br>
                <h2><strong><center>Carrito de compras <img src="img/CarritoPixel.png" width="50"></center></strong></h2>
                <div class="card-body">

                    <table>
                        <thead>
                            <tr>
                                <td><strong>Item</strong></td>
                                <td><strong>Codigo</strong></td>
                                <td><strong>Descripcion</strong></td>
                                <td><strong>Precio</strong></td>
                                <td><strong>Cantidad</strong></td>
                                <td><strong>SubTotal</strong></td>
                                <td><strong>Acciones</strong></td>
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
                                    <td style="vertical-align: middle; height: 100px;"> <!-- Define la altura si es necesario -->
                                        <a class="btn btn-danger" href="Controlador?menu=Tienda&accion=EliminarItem&codigoArticulo=${list.getCodigoArticulo()}" style="margin-left: 10px">Eliminar</a>
                                    </td>

                                </tr>
                            </c:forEach>
                        </tbody>
                    </table>
                </div>
                <div class="card-footer d-flex">
                    <div class="col-sm-6">
                        <a class="btn btn-danger" href="Controlador?menu=FormularioFactura&accion=Listar">Generar Venta</a>
                        <a class="btn btn-danger" href="Controlador?menu=Tienda&accion=Cancelar">Cancelar</a>
                    </div>
                    <div class="col-sm-4 ml-auto"> 
                        <input type="text" name="txtTotal" value="Q.${totalPagar}0" class="form-control">
                    </div>
                </div>

                </div>
            </div>
        </div>
    </div>
    <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js" integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj" crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js" integrity="sha384-9/reFTGAW83EW2RDu2S0VKaIzap3H66lZH81PoYlFhbGU+6BZp6G7niu735Sk7lN" crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/js/bootstrap.min.js" integrity="sha384-w1Q4orYjBQndcko6MimVbzY0tgp4pWB4lZ7lr30WKz0vr/aWKhXdBNmNb5D92v7s" crossorigin="anonymous"></script>
</body>
</html>
