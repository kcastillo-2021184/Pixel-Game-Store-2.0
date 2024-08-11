<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Clientes</title>
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/css/bootstrap.min.css" integrity="sha384-TX8t27EcRE3e/ihU7zmQxVncDAy5uIKz4rEkgIXeMed4M0jlfIDPvg6uqKI2xXr2" crossorigin="anonymous">
    </head>
    <body>
        <div class="d-flex">
            <div class="card col-sm-4"> 
                <div class="card-body">
                    <form action="Controlador?menu=Clientes" method="POST" >
                        <div class="form-group">
                            <label><strong> Codigo Cliente</strong></label>
                            <input type="text" value="${Clientes.getCodigoCliente()}" name="txtCodigoCliente" class="form-control">
                        </div>
                        <div class="form-group">
                            <label><strong>NITCliente</strong></label>
                            <input type="text" value="${Clientes.getNITCliente()}" name="txtNITCliente" class="form-control">
                        </div>
                        <div class="form-group">
                            <label><strong>Nombres Cliente</strong></label>
                            <input type="text" value="${Clientes.getNombresCliente()}" name="txtNombresCliente" class="form-control">
                        </div>
                        <div class="form-group">
                            <label><strong>Apellidos Cliente</strong></label>
                            <input type="text" value="${Clientes.getApellidosCliente()}" name="txtApellidosCliente" class="form-control">
                        </div>
                        <div class="form-group">
                            <label><strong>Direccion Cliente</strong></label>
                            <input type="text" value="${Clientes.getDireccionCliente()}" name="txtDireccionCliente" class="form-control">
                        </div>
                        <div class="form-group">
                            <label><strong>Telefono Cliente</strong></label>
                            <input type="text" value="${Clientes.getTelefonoCliente()}" name="txtTelefonoCliente" class="form-control">
                        </div>
                        <div class="form-group">
                            <label><strong>Correo Cliente</strong></label>
                            <input type="text" value="${Clientes.getCorreoCliente()}" name="txtCorreoCliente" class="form-control">
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
                            <th>NIT</th>
                            <th>NOMBRES</th>
                            <th>APELLIDOS</th>
                            <th>DIRECCION</th>
                            <th>TELEFONO</th>
                            <th>CORREO</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach var="Clientes" items="${clientes}">
                            <tr>
                                <td>${Clientes.getCodigoCliente()}</td>
                                <td>${Clientes.getNITCliente()}</td>
                                <td>${Clientes.getNombresCliente()}</td>
                                <td>${Clientes.getApellidosCliente()}</td>
                                <td>${Clientes.getDireccionCliente()}</td>
                                <td>${Clientes.getTelefonoCliente()}</td>
                                <td>${Clientes.getCorreoCliente()}</td>
                                <td>
                                    <a class="btn btn-warning" href="Controlador?menu=Clientes&accion=Editar&codigoCliente=${Clientes.getCodigoCliente()}">Editar</a>
                                    <a class="btn btn-danger" href="Controlador?menu=Clientes&accion=Eliminar&codigoCliente=${Clientes.getCodigoCliente()}">Eliminar</a>
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
