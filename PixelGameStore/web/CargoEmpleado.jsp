<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Página Cargo Empleado</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/css/bootstrap.min.css" integrity="sha384-xOolHFLEh07PJGoPkLv1IbcEPTNtaed2xpHsD9ESMhqIYd0nLMwNLD69Npy4HI+N" crossorigin="anonymous">
</head>
<body>
    <div class="d-flex">
        <div class="card col-sm-4">
            <div class="card-body">
                <form action="Controlador?menu=CargoEmpleado" method="POST">
                    <div class="form-group">
                        <label><strong>Nombre Cargo Empleado:</strong></label>
                        <input type="text" value="${cargoEmpleado.nombreCargo}" name="txtNombreCargo" class="form-control">
                    </div>
                    <div class="form-group">
                        <label><strong>Descripción Cargo:</strong></label>
                        <input type="text" value="${cargoEmpleado.descripcion}" name="txtDescripcion" class="form-control">
                    </div>
                    <div class="form-group">
                        <label><strong>Turno:</strong></label>
                        <input type="text" value="${cargoEmpleado.turno}" name="txtTurno" class="form-control">
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
                        <th>DESCRIPCIÓN</th>
                        <th>TURNO</th>
                        <th>ACCIONES</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach var="cargoEmpleado" items="${cargoEmpleados}">
                        <tr>
                            <td>${cargoEmpleado.codigoCargoEmpleado}</td>
                            <td>${cargoEmpleado.nombreCargo}</td>
                            <td>${cargoEmpleado.descripcion}</td>
                            <td>${cargoEmpleado.turno}</td>
                            <td>
                                <a class="btn btn-warning" href="Controlador?menu=CargoEmpleado&accion=Editar&codigoCargoEmpleado=${cargoEmpleado.codigoCargoEmpleado}">Editar</a>
                                <a class="btn btn-danger" href="Controlador?menu=CargoEmpleado&accion=Eliminar&codigoCargoEmpleado=${cargoEmpleado.codigoCargoEmpleado}">Eliminar</a>
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
