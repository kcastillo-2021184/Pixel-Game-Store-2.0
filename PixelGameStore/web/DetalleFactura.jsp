<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Detalle Factura Page</title>
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/css/bootstrap.min.css" integrity="sha384-TX8t27EcRE3e/ihU7zmQxVncDAy5uIKz4rEkgIXeMed4M0jlfIDPvg6uqKI2xXr2" crossorigin="anonymous">
    </head>
    <body>
        <div class="d-flex">
            <div class="card col-sm-4">
                <div class="card-body">
                    <!-- Añadir enctype="multipart/form-data" para manejar archivos -->
                    <form action="Controlador?menu=Producto" method="POST" enctype="multipart/form-data">
                        <div class="form-group">
                            <label><strong>Código:</strong></label>
                            <input type="text" value="${producto.getDetalleFactura()}" name="txtCodigoDetalleFactura" class="form-control">
                        </div>
                        <div class="form-group">
                            <label><strong>Precio Unitario:</strong></label>
                            <input type="text" value="${producto.getPrecioUnitario()}" name="txtPrecioUnitario" class="form-control">
                        </div>
                        <div class="form-group">
                            <label><strong>Cantidad:</strong></label>
                            <input type="text" value="${producto.getCantidad()}" name="txtCantidad" class="form-control">
                        </div>
                        <div class="form-group">
                            <label><strong>Número Factura:</strong></label>
                            <input type="text" value="${producto.getNumeroFactura()}" name="txtNumeroFactura" class="form-control">
                        </div>
                        <div class="form-group">
                            <label><strong>Código Producto:</strong></label>
                            <input type="text" value="${producto.getCodigoProducto()}" name="txtCodigoProducto" class="form-control">
                        </div>
                        <input type="submit" name="accion" value="Agregar" class="btn btn-info">
                        <input type="submit" name="accion" value="Actualizar" class="btn btn-success">
                    </form>
                </div>
            </div>
            <div class="col-sm-8">
                <table class="table table-hover">
                    <thead>
                        <tr>
                            <th>CÓDIGO</th>
                            <th>PRECIO UNITARIO</th>
                            <th>CANTIDAD</th>
                            <th>NÚMERO FACTURA</th>
                            <th>CÓDIGO PRODUCTO</th>
                            <th>ACCIONES</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach var="detalleFactura" items="${detalleFacturas}">
                        <tr>
                            <td>${detalleFactura.getCodigoDetalleFactura()}</td>
                            <td>${detalleFactura.getPrecioUnitario()}</td>
                            <td>${detalleFactura.getCantidad()}</td>
                            <td>${detalleFactura.getNumeroFactura()}</td>
                            <td>${detalleFactura.getCodigoProducto()}</td>
                            <td>
                                <a class="btn btn-warning" href="Controlador?menu=DetalleFactura&accion=Editar&codigoDetalleFactura=${detalleFactura.getCodigoDetalleFactura()}">Editar</a>
                                <a class="btn btn-danger" href="Controlador?menu=DetalleFactura&accion=Eliminar&codigoDetalleFactura=${detalleFactura.getCodigoDetalleFactura()}">Eliminar</a>
                            </td>
                        </tr>
                        </c:forEach>
                    </tbody>
                </table>
            </div>
        </div>

        <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js" integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj" crossorigin="anonymous"></script>
        <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js" integrity="sha384-9/reFTGAW83EW2RDu2S0VKaIzap3H66lZH81PoYlFhbGU+6BZp6G7niu735Sk7lN" crossorigin="anonymous"></script>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/js/bootstrap.min.js" integrity="sha384-w1Q4orYjBQndcko6MimVbzY0tgp4pWB4lZ7lr30WKz0vr/aWKhXdBNmNb5D92v7s" crossorigin="anonymous"></script>
    </body>
</html>