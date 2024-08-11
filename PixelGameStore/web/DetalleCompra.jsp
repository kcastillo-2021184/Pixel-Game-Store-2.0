<%-- 
    Document   : empleado
    Created on : 10/07/2024, 08:26:36 AM
    Author     : informatica
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Pagina Detalle Compra</title>
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/css/bootstrap.min.css" integrity="sha384-TX8t27EcRE3e/ihU7zmQxVncDAy5uIKz4rEkgIXeMed4M0jlfIDPvg6uqKI2xXr2" crossorigin="anonymous">
    </head>
     <body>
   <div class="d-flex">
        <div class="card col-sm-4">
            <div class="card-body">
                <form action="Controlador?menu=DetalleCompra"	 method="post">
                    <div class="form-group">
                        <label><strong>COSTO UNITARIO:</strong></label>
                        <input type="text" value="${detalleCompra.getCostoUnitario()}" name="txtCostoUnitario" class="form-control">
                    </div>
                    
                    <div class="form-group">
                        <label><strong>CANTIDAD:</strong></label>
                        <input type="text" value="${detalleCompra.getCantidad()}" name="txtCantidad" class="form-control">
                    </div>
                    
                    <div class="form-group">
                        <label><strong>CODIGO PRODUCTO:</strong></label>
                        <input type="text" value="${detalleCompra.getCodigoProducto()}" name="txtCodigoProducto" class="form-control">
                    </div>
                    
                    <div class="form-group">
                        <label><strong>NUMERO DOCUMENTO:</strong></label>
                        <input type="text" value="${detalleCompra.getNumeroDocumento()}" name="txtNumeroDocumento" class="form-control">
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
                          <th>CODIGO DETALLE COMPRA</th>
                          <th>COSTO UNITARIO</th>
                          <th>CANTIDAD</th>
                          <th>CODIGO PRODUCTO</th>
                          <th>NUMERO DOCUMENTO</th>
                          <th>ACCIONES</th>
                        </tr>
                    </thead>
                  <tbody>
                        <c:forEach var="detalleCompra" items="${detalleCompras}">
                            <tr>
                                <td>${detalleCompra.getCodigoDetalleCompra()}</td>
                                <td>${detalleCompra.getCostoUnitario()}</td>
                                <td>${detalleCompra.getCantidad()}</td>
                                <td>${detalleCompra.getCodigoProducto()}</td>
                                <td>${detalleCompra.getNumeroDocumento()}</td>
                                
                                <td>
                                
                                <a class="btn btn-warning" href="Controlador?menu=DetalleCompra&accion=Editar&codigoDetalleCompra=${detalleCompra.getCodigoDetalleCompra()}">Editar</a>
                                <a class="btn btn-danger" href="Controlador?menu=DetalleCompra&accion=Eliminar&codigoDetalleCompra=${detalleCompra.getCodigoDetalleCompra()}">Eliminar</a>
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
