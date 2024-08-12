<%-- 
    Document   : Empleado
    Created on : Jul 14, 2024, 8:31:26 PM
    Author     : omary
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Empleados</title>
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
                    <form action="Controlador?menu=Empleados" method="POST" >
                        <div class="form-group">
                            <label><strong> Codigo Empleado</strong></label>
                            <input type="text" value="${Empleados.getCodigoEmpleado()}" name="txtCodigoEmpleado" class="form-control">
                        </div>
                        <div class="form-group">
                            <label><strong>Nombres Empleado</strong></label>
                            <input type="text" value="${Empleados.getNombresEmpleado()}" name="txtNombresEmpleado" class="form-control">
                        </div>
                        <div class="form-group">
                            <label><strong>Apellidos Empleado</strong></label>
                            <input type="text" value="${Empleados.getApellidosEmpleado()}" name="txtApellidosEmpleado" class="form-control">
                        </div>
                        <div class="form-group">
                            <label><strong>Sueldo</strong></label>
                            <input type="text" value="${Empleados.getSueldo()}" name="txtSueldo" class="form-control">
                        </div>
                        <div class="form-group">
                            <label><strong>Direccion Empleado</strong></label>
                            <input type="text" value="${Empleados.getDireccionEmpleado()}" name="txtDireccionEmpleado" class="form-control">
                        </div>
                        <div class="form-group">
                            <label><strong>Codigo Cargo Empleado</strong></label>
                            <input type="text" value="${Empleados.getCodigoCargoEmpleado()}" name="txtCodigoCargoEmpleado" class="form-control">
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
                            <th>NOMBRES</th>
                            <th>APELLIDOS</th>
                            <th>SUELDO</th>
                            <th>DIRECCION</th>
                            <th>CARGO EMPLEADO</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach var="Empleados" items="${empleados}">
                            <tr>
                                <td>${Empleados.getCodigoEmpleado()}</td>
                                <td>${Empleados.getNombresEmpleado()}</td>
                                <td>${Empleados.getApellidosEmpleado()}</td>
                                <td>${Empleados.getSueldo()}</td>
                                <td>${Empleados.getDireccionEmpleado()}</td>
                                <td>${Empleados.getCodigoCargoEmpleado()}</td>
                                <td>
                                    <a class="btn btn-warning" href="Controlador?menu=Empleados&accion=Editar&codigoEmpleado=${Empleados.getCodigoEmpleado()}">Editar</a>
                                    <a class="btn btn-danger" href="Controlador?menu=Empleados&accion=Eliminar&codigoEmpleado=${Empleados.getCodigoEmpleado()}">Eliminar</a>
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