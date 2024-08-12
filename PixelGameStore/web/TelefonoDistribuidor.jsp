<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Teléfonos de Distribuidor</title>
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/css/bootstrap.min.css" integrity="sha384-TX8t27EcRE3e/ihU7zmQxVncDAy5uIKz4rEkgIXeMed4M0jlfIDPvg6uqKI2xXr2" crossorigin="anonymous">
        <style>
        body {
            background-color: #1a1a2e;
            font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
            color: #ffffff;
        }
        .container {
            background-color: #16213e;
            border-radius: 15px;
            box-shadow: 0 0 20px rgba(233, 69, 96, 0.2);
            padding: 20px;
            margin-top: 20px;
        }
        .card {
            border: none;
            box-shadow: 0 4px 8px rgba(0,0,0,0.2);
            background-color: #0f3460;
        }
        .card-body {
            background-color: #0f3460;
        }
        .form-control {
            background-color: #16213e;
            border: 1px solid #ffffff;
            color: #ffffff;
        }
        .form-control:focus {
            border-color: #ffffff;
            box-shadow: 0 0 0 0.2rem rgba(255, 255, 255, 0.25);
            background-color: #16213e;
            color: #ffffff;
        }
        .btn {
            border-radius: 5px;
            transition: all 0.3s;
        }
        .btn-info {
            background-color: #4d9de0;
            border-color: #4d9de0;
        }
        .btn-success {
            background-color: #3ec1d3;
            border-color: #3ec1d3;
        }
        .btn-warning {
            background-color: #f9b208;
            border-color: #f9b208;
        }
        .btn-danger {
            background-color: #e94560;
            border-color: #e94560;
        }
        .btn:hover {
            transform: translateY(-2px);
            box-shadow: 0 5px 15px rgba(233, 69, 96, 0.3);
        }
        .table {
            background-color: #16213e;
            border-radius: 8px;
            overflow: hidden;
            color: #ffffff;
        }
        .table thead th {
            background-color: #0f3460;
            color: #ffffff;
            border: none;
        }
        .table-hover tbody tr:hover {
            background-color: #1a1a2e;
        }
        h4 {
            color: #3ec1d3;
            text-shadow: 2px 2px 4px rgba(0,0,0,0.3);
        }
        label {
            color: #4d9de0;
        }
    </style>
    </head>
    <body>
        <div class="d-flex">
            <div class="card col-sm-4"> 
                <div class="card-body">
                    <form action="Controlador?menu=TelefonoDistribuidor" method="POST" >
                        <div class="form-group">
                            <label><strong>Código Teléfono Distribuidor</strong></label>
                            <input type="text" value="${TelefonoDistribuidor.getCodigoTelefonoDistribuidor()}" name="txtCodigoTelefonoDistribuidor" class="form-control">
                        </div>
                        <div class="form-group">
                            <label><strong>Número Principal</strong></label>
                            <input type="text" value="${TelefonoDistribuidor.getNumeroPrincipal()}" name="txtNumeroPrincipal" class="form-control">
                        </div>
                        <div class="form-group">
                            <label><strong>Número Secundario</strong></label>
                            <input type="text" value="${TelefonoDistribuidor.getNumeroSecundario()}" name="txtNumeroSecundario" class="form-control">
                        </div>
                        <div class="form-group">
                            <label><strong>Observaciones</strong></label>
                            <input type="text" value="${TelefonoDistribuidor.getObservaciones()}" name="txtObservaciones" class="form-control">
                        </div>
                        <div class="form-group">
                            <label><strong>Código Distribuidor</strong></label>
                            <input type="text" value="${TelefonoDistribuidor.getCodigoDistribuidor()}" name="txtCodigoDistribuidor" class="form-control">
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
                            <th>NÚMERO PRINCIPAL</th>
                            <th>NÚMERO SECUNDARIO</th>
                            <th>OBSERVACIONES</th>
                            <th>CÓDIGO DISTRIBUIDOR</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach var="TelefonoDistribuidor" items="${telefonoDistribuidor}">
                            <tr>
                                <td>${TelefonoDistribuidor.getCodigoTelefonoDistribuidor()}</td>
                                <td>${TelefonoDistribuidor.getNumeroPrincipal()}</td>
                                <td>${TelefonoDistribuidor.getNumeroSecundario()}</td>
                                <td>${TelefonoDistribuidor.getObservaciones()}</td>
                                <td>${TelefonoDistribuidor.getCodigoDistribuidor()}</td>
                                <td>
                                    <a class="btn btn-warning" href="Controlador?menu=TelefonoDistribuidor&accion=Editar&codigoTelefonoDistribuidor=${TelefonoDistribuidor.getCodigoTelefonoDistribuidor()}">Editar</a>
                                    <a class="btn btn-danger" href="Controlador?menu=TelefonoDistribuidor&accion=Eliminar&codigoTelefonoDistribuidor=${TelefonoDistribuidor.getCodigoTelefonoDistribuidor()}">Eliminar</a>
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
