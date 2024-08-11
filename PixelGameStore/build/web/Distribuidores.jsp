<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Página Distribuidores</title>
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/css/bootstrap.min.css" integrity="sha384-TX8t27EcRE3e/ihU7zmQxVncDAy5uIKz4rEkgIXeMed4M0jlfIDPvg6uqKI2xXr2" crossorigin="anonymous"> 
    </head>
    <body>
        <div class="d-flex">
            <div class="card col-sm-4">
                <div class="card-body">
                    <form action="Controlador?menu=Distribuidores" method="POST"> 
                        <div class="form-group">
                            <label><strong>Nombre Distribuidor</strong></label>
                            <input type="text" value="${distribuidor.getNombreDistribuidor()}" name="txtNombreDistribuidor" class="form-control"> 
                        </div>
                        <div class="form-group">
                            <label><strong>Dirección</strong></label>
                            <input type="text" value="${distribuidor.getDireccionDistribuidor()}" name="txtDireccionDistribuidor" class="form-control"> 
                        </div>
                        <div class="form-group">
                            <label><strong>Código Postal</strong></label>
                            <input type="text" value="${distribuidor.getCodigoPostal()}" name="txtCodigoPostal" class="form-control"> 
                        </div>
                        <div class="form-group">
                            <label><strong>Página Web</strong></label>
                            <input type="text" value="${distribuidor.getPaginaWeb()}" name="txtPaginaWeb" class="form-control"> 
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
                            <th>NOMBRE</th>
                            <th>DIRECCIÓN</th>
                            <th>CÓDIGO POSTAL</th>
                            <th>PÁGINA WEB</th>
                            <th>ACCIÓN</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach var="distribuidor" items="${distribuidores}">
                            <tr>
                                <td>${distribuidor.getCodigoDistribuidor()}</td>
                                <td>${distribuidor.getNombreDistribuidor()}</td>
                                <td>${distribuidor.getDireccionDistribuidor()}</td>
                                <td>${distribuidor.getCodigoPostal()}</td>
                                <td>${distribuidor.getPaginaWeb()}</td>
                                <td>
                                    <a class="btn btn-warning" href="Controlador?menu=Distribuidores&accion=Editar&codigoDistribuidor=${distribuidor.getCodigoDistribuidor()}">Editar</a>
                                    <a class="btn btn-danger" href="Controlador?menu=Distribuidores&accion=Eliminar&codigoDistribuidor=${distribuidor.getCodigoDistribuidor()}">Eliminar</a>
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