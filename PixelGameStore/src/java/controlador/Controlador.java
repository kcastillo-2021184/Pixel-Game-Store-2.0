package controlador;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Blob;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import modelo.Articulo;
import modelo.CargoEmpleado;
import modelo.CargoEmpleadoDAO;
import modelo.Categoria;
import modelo.CategoriaDAO;
import modelo.Clientes;
import modelo.ClientesDAO;
import modelo.Compras;
import modelo.ComprasDAO;
import modelo.DetalleCompra;
import modelo.DetalleCompraDAO;
import modelo.DetalleFactura;
import modelo.DetalleFacturaDAO;
import modelo.Distribuidores;
import modelo.DistribuidoresDAO;
import modelo.EmailDistribuidor;
import modelo.EmailDistribuidorDAO;
import modelo.Empleados;
import modelo.EmpleadosDAO;
import modelo.Factura;
import modelo.FacturaDAO;
import modelo.Productos;
import modelo.ProductosDAO;
import modelo.TelefonoDistribuidor;
import modelo.TelefonoDistribuidorDAO;
import modelo.Usuario;
import modelo.UsuarioDAO;

/**
 *
 * @author informatica
 */
@MultipartConfig
public class Controlador extends HttpServlet {
    int codEmpleado;
    int codCliente;
    int codEmailDistribuidor;
    int codDistribuidor;
    String codProducto;
    Productos producto = new Productos();
    ProductosDAO productoDao = new ProductosDAO();
    EmailDistribuidor emailDistribuidores = new EmailDistribuidor();
    EmailDistribuidorDAO emailDistribuidorDao = new EmailDistribuidorDAO();
    Distribuidores distribuidor = new Distribuidores();
    DistribuidoresDAO distribuidoresDao = new DistribuidoresDAO();
    CargoEmpleado cargoempleado = new CargoEmpleado();
    CargoEmpleadoDAO cargoempleadoDao = new CargoEmpleadoDAO();
    Factura factura = new Factura();
    FacturaDAO facturaDao = new FacturaDAO();
    DetalleCompra detalleCompras = new DetalleCompra();
    DetalleCompraDAO detalleCompraDao = new DetalleCompraDAO();
    Clientes clientes = new Clientes();
    ClientesDAO clientesDao = new ClientesDAO();
    int codDetalleCompra;
    TelefonoDistribuidor telefonoDistribuidor = new TelefonoDistribuidor();
    TelefonoDistribuidorDAO telefonoDistribuidorDao = new TelefonoDistribuidorDAO();
    int codTelefonoDistribuidor;
    Empleados empleado = new Empleados();
    Compras compras = new Compras();
    ComprasDAO comprasDao = new ComprasDAO();
    int numDocumento;
//    Usuario empleado = new Usuario();
    EmpleadosDAO empleadoDao = new EmpleadosDAO();
    Categoria  categoria = new Categoria();
    CategoriaDAO categoriaDao = new CategoriaDAO();
    int item = 0;
    int cantidad;
    int codCategoria;
    int codCargoEmpleado;
    String descripcion;
    double precio, subTotal, totalPagar = 0.0;
    
    Clientes cliente = new Clientes();
    ClientesDAO clienteDao = new ClientesDAO();
      
    String descripcionProducto, codigoProducto;
    int cantidadDeProducto;
    List<Articulo> listaArticulos = new ArrayList<>();
    String NITClienteFactura;
    int codigoClienteFactura;
    int numeroDeFactura = 0;
    DetalleFactura detalleFactura = new DetalleFactura();
    DetalleFacturaDAO detalleDao = new DetalleFacturaDAO();
    int numeroDetalleFactura = 0;
    

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String menu = request.getParameter("menu");
        String accion = request.getParameter("accion");

        if (menu.equals("PrincipalClientes")) {
            request.getRequestDispatcher("PrincipalClientes.jsp").forward(request, response);   
        }else if(menu.equals("Principal")){
            request.getRequestDispatcher("Principal.jsp").forward(request, response);
        }else if(menu.equals("Home")){
            request.getRequestDispatcher("Home.jsp").forward(request, response);
        }else if(menu.equals("Empleados")){
            switch(accion){
                case "Listar":
                    List listaEmpleados = empleadoDao.listar();
                    request.setAttribute("empleados", listaEmpleados);
                    break;
                case "Agregar":
                    int codigoEmpleado = Integer.parseInt(request.getParameter("txtCodigoEmpleado"));
                    String nombresEmpleado = request.getParameter("txtNombresEmpleado");
                    String apellidosEmpleado = request.getParameter("txtApellidosEmpleado");
                    Double sueldo = Double.parseDouble(request.getParameter("txtSueldo"));
                    String direccionEmpleado = request.getParameter("txtDireccionEmpleado");
                    int codigoCargoEmpleado = Integer.parseInt(request.getParameter("txtCodigoCargoEmpleado"));
                    empleado.setCodigoEmpleado(codigoEmpleado);
                    empleado.setNombresEmpleado(nombresEmpleado);
                    empleado.setApellidosEmpleado(apellidosEmpleado);
                    empleado.setSueldo(sueldo);
                    empleado.setDireccionEmpleado(direccionEmpleado);
                    empleado.setCodigoCargoEmpleado(codigoCargoEmpleado);
                    empleadoDao.agregar(empleado);
                    request.getRequestDispatcher("Controlador?menu=Empleados&accion=Listar").forward(request, response);
                    break;
                case "Editar":
                    codEmpleado = Integer.parseInt(request.getParameter("codigoEmpleado"));
                    Empleados emp = empleadoDao.listarCodigoEmpleado(codEmpleado);
                    request.setAttribute("Empleados", emp);
                    request.getRequestDispatcher("Controlador?menu=Empleados&accion=Listar").forward(request, response);
                    break;
                case "Actualizar":
                    String nombresEmp = request.getParameter("txtNombresEmpleado");
                    String apellidosEmp = request.getParameter("txtApellidosEmpleado");
                    Double pago = Double.parseDouble(request.getParameter("txtSueldo"));
                    String direccionEmp = request.getParameter("txtDireccionEmpleado");
                    int codCargoEmp = Integer.parseInt(request.getParameter("txtCodigoCargoEmpleado"));
                    empleado.setCodigoEmpleado(codEmpleado); // Establecer el código del empleado
                    empleado.setNombresEmpleado(nombresEmp);
                    empleado.setApellidosEmpleado(apellidosEmp);
                    empleado.setSueldo(pago);
                    empleado.setDireccionEmpleado(direccionEmp);
                    empleado.setCodigoCargoEmpleado(codCargoEmp);
                    empleadoDao.actualizar(empleado);
                    request.getRequestDispatcher("Controlador?menu=Empleados&accion=Listar").forward(request, response);
                    break;
                case "Eliminar":
                    codEmpleado = Integer.parseInt(request.getParameter("codigoEmpleado"));
                    empleadoDao.eliminar(codEmpleado);
                    request.getRequestDispatcher("Controlador?menu=Empleados&accion=Listar").forward(request, response);
                    break; 
            }
            request.getRequestDispatcher("Empleado.jsp").forward(request, response);
        }
        else if(menu.equals("TelefonoDistribuidor")){
            switch(accion){
                case "Listar":
                        List listaTelefonoDistribuidor = telefonoDistribuidorDao.Listar();
                        request.setAttribute("telefonoDistribuidor", listaTelefonoDistribuidor);
                    break;
                case "Agregar":
                        String numeroPrincipal = request.getParameter("txtNumeroPrincipal");
                        String numeroSecundario = request.getParameter("txtNumeroSecundario");
                        String observaciones = request.getParameter("txtObservaciones");
                        int codigoDistribuidor = Integer.parseInt(request.getParameter("txtCodigoDistribuidor"));
                        telefonoDistribuidor.setNumeroPrincipal(numeroPrincipal);
                        telefonoDistribuidor.setNumeroSecundario(numeroSecundario);
                        telefonoDistribuidor.setObservaciones(observaciones);
                        telefonoDistribuidor.setCodigoDistribuidor(codigoDistribuidor);
                        telefonoDistribuidorDao.agregar(telefonoDistribuidor);
                        request.getRequestDispatcher("Controlador?menu=TelefonoDistribuidor&accion=Listar").forward(request, response);
                    break;
                case "Editar":
                        codTelefonoDistribuidor = Integer.parseInt(request.getParameter("codigoTelefonoDistribuidor"));
                        TelefonoDistribuidor tel = telefonoDistribuidorDao.ListarCodigoTelefonoDistribuidor(codTelefonoDistribuidor);
                        request.setAttribute("TelefonoDistribuidor", tel);
                        request.getRequestDispatcher("Controlador?menu=TelefonoDistribuidor&accion=Listar").forward(request, response);
                    break;
                case "Actualizar":
                        int codigoTelefonoDistribuidor = Integer.parseInt(request.getParameter("txtCodigoTelefonoDistribuidor"));
                        String numeroPrincipalTel = request.getParameter("txtNumeroPrincipal");
                        String numeroSecundarioTEl = request.getParameter("txtNumeroSecundario");
                        String observacionesTel = request.getParameter("txtObservaciones");
                        int codigoDistribuidorTe = Integer.parseInt(request.getParameter("txtCodigoDistribuidor"));
                        telefonoDistribuidor.setCodigoTelefonoDistribuidor(codigoTelefonoDistribuidor);
                        telefonoDistribuidor.setNumeroPrincipal(numeroPrincipalTel);
                        telefonoDistribuidor.setNumeroSecundario(numeroSecundarioTEl);
                        telefonoDistribuidor.setObservaciones(observacionesTel);
                        telefonoDistribuidor.setCodigoDistribuidor(codigoDistribuidorTe);
                        telefonoDistribuidorDao.Actualizar(telefonoDistribuidor);
                        request.getRequestDispatcher("Controlador?menu=TelefonoDistribuidor&accion=Listar").forward(request, response);
                    break;
                    case "Eliminar":
                        codTelefonoDistribuidor = Integer.parseInt(request.getParameter("codigoTelefonoDistribuidor"));
                        telefonoDistribuidorDao.Eliminar(codTelefonoDistribuidor);
                        request.getRequestDispatcher("Controlador?menu=TelefonoDistribuidor&accion=Listar").forward(request, response);
                    break;
                }
                request.getRequestDispatcher("TelefonoDistribuidor.jsp").forward(request, response);
            }
        
        else if(menu.equals("Categoria")){
            switch(accion){
                case "Listar":
                    List listaCategoria = categoriaDao.listar();
                    request.setAttribute("categorias", listaCategoria);
                    request.getRequestDispatcher("Categoria.jsp").forward(request, response);
                    break;
                case "Agregar":
                    String nombres = request.getParameter("txtNombreCategoria");
                    String descripcion = request.getParameter("txtDescripcionCategoria");
                    String localizacion = request.getParameter("txtLocalizacionCategoria");
                    categoria.setNombreCategoria(nombres);
                    categoria.setDescripcionCategoria(descripcion);
                    categoria.setLocalizacionCategoria(localizacion);
                    categoriaDao.agregar(categoria);
                    request.getRequestDispatcher("Controlador?menu=Categoria&accion=Listar").forward(request, response);
                    break;
                case "Actualizar":
                    String nomCat = request.getParameter("txtNombreCategoria");
                    String desCat = request.getParameter("txtDescripcionCategoria");
                    String locCat = request.getParameter("txtLocalizacionCategoria");
                    categoria.setNombreCategoria(nomCat);
                    categoria.setDescripcionCategoria(desCat);
                    categoria.setLocalizacionCategoria(locCat);
                    categoria.setCodigoCategoria(codCategoria);
                    categoriaDao.actualizar(categoria);
                    request.getRequestDispatcher("Controlador?menu=Categoria&accion=Listar").forward(request, response);
                    break;
                case "Editar":
                    codCategoria = Integer.parseInt(request.getParameter("codigoCategoria"));;
                    Categoria c = categoriaDao.listarCodigoCategoria(codCategoria);
                    request.setAttribute("categoria", c);
                    request.getRequestDispatcher("Controlador?menu=Categoria&accion=Listar").forward(request, response);
                    break;
                case "Eliminar":
                    codCategoria = Integer.parseInt(request.getParameter("codigoCategoria"));
                    categoriaDao.eliminar(codCategoria);
                    request.getRequestDispatcher("Controlador?menu=Categoria&accion=Listar").forward(request, response);
                    break;
            }
            
                        
                        
        }else if(menu.equals("Compras")){
            switch(accion){
                case "Listar":
                    List listaCompras = comprasDao.Listar();
                    request.setAttribute("compras", listaCompras);
                    break;
                case "Agregar":
                    int numeroDocumento = Integer.parseInt(request.getParameter("txtNumeroDocumento"));
                    String fechaDocumento = request.getParameter("txtFechaDocumento");
                    String descripcion = request.getParameter("txtDescripcion");
                    compras.setNumeroDocumento(numeroDocumento);
                    compras.setFechaDocumento(fechaDocumento);
                    compras.setDescripcion(descripcion);
                    comprasDao.agregar(compras);
                    request.getRequestDispatcher("Controlador?menu=Compras&accion=Listar").forward(request, response);
                    break;
                case "Editar":
                    numDocumento = Integer.parseInt(request.getParameter("numeroDocumento")); // Asegúrate de que este valor esté disponible
                    Compras com = comprasDao.ListarNumeroDocumento(numDocumento);
                    request.setAttribute("Compras", com);
                    request.getRequestDispatcher("Controlador?menu=Compras&accion=Listar").forward(request, response);
                    break;
                case "Actualizar":
                    String FechaDocumento = request.getParameter("txtFechaDocumento");
                    String Descripcion = request.getParameter("txtDescripcion");
                    double Totaldocumento = Double.parseDouble(request.getParameter("txtTotalDocumento")); // Convierte correctamente a double
                    compras.setNumeroDocumento(numDocumento); 
                    compras.setFechaDocumento(FechaDocumento);
                    compras.setDescripcion(Descripcion);
                    compras.setTotalDocumento(Totaldocumento); 
                    comprasDao.actualizar(compras);
                    request.getRequestDispatcher("Controlador?menu=Compras&accion=Listar").forward(request, response);
                    break;
                case "Eliminar":
                    numDocumento = Integer.parseInt(request.getParameter("numeroDocumento"));
                    comprasDao.eliminar(numDocumento);
                    request.getRequestDispatcher("Controlador?menu=Compras&accion=Listar").forward(request, response);
                    break; 
            }
            request.getRequestDispatcher("Compras.jsp").forward(request, response);
            }
        else if (menu.equals("Producto")) {
            switch (accion) {
                case "Listar":
                    List<Productos> listaProductos = productoDao.listar();
                    request.setAttribute("productos", listaProductos);
                    request.getRequestDispatcher("Producto.jsp").forward(request, response);
                    break;
                case "Agregar":
                    String codigoProducto = request.getParameter("txtCodigoProducto");
                    String descripcionProducto = request.getParameter("txtDescripcionProducto");
                    int codigoCategoria = Integer.parseInt(request.getParameter("txtCodigoCategoria"));
                    int codigoDistribuidor = Integer.parseInt(request.getParameter("txtCodigoDistribuidor"));

                    // Obtener la vista previa de la imagen
                    Part archivoVistaPrevia = request.getPart("fileVistaPrevia");
                    InputStream inputStreamVistaPrevia = archivoVistaPrevia.getInputStream();

                    ByteArrayOutputStream outputStreamVistaPrevia = new ByteArrayOutputStream();
                    byte[] bufferer = new byte[104857600];
                    int bytesLeidos;

                    while ((bytesLeidos = inputStreamVistaPrevia.read(bufferer)) != -1) {
                        outputStreamVistaPrevia.write(bufferer, 0, bytesLeidos);
                    }

                    byte[] bytesVistaPrevia = outputStreamVistaPrevia.toByteArray();
                    Blob blobVistaPrevia = null;

                    try {
                        blobVistaPrevia = new javax.sql.rowset.serial.SerialBlob(bytesVistaPrevia);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                    // Asegúrate de cerrar los streams después de usarlos
                    inputStreamVistaPrevia.close();
                    outputStreamVistaPrevia.close();

                    producto.setCodigoProducto(codigoProducto);
                    producto.setDescripcionProducto(descripcionProducto);
                    producto.setCodigoCategoria(codigoCategoria);
                    producto.setCodigoDistribuidor(codigoDistribuidor);
                    try{
                        producto.setVistaPrevia(blobToBytes(blobVistaPrevia));
                    }catch(Exception e){
                        e.printStackTrace();
                    }
                    productoDao.agregar(producto);
                    response.sendRedirect("Controlador?menu=Producto&accion=Listar");
                    break;
                case "Editar":
                    codProducto = request.getParameter("codigoProducto");
                    Productos p = productoDao.listarCodigoProducto(String.valueOf(codProducto));
                    request.setAttribute("producto", p);
                    request.getRequestDispatcher("Controlador?menu=Producto&accion=Listar").forward(request, response);
                    break;
                case "Actualizar":
                    String codigoProductoUpd = request.getParameter("txtCodigoProducto");
                    String descripcionProductoUpd = request.getParameter("txtDescripcionProducto");
                    double precioUnitarioUpd = Double.parseDouble(request.getParameter("txtPrecioUnitario"));
                    double precioDocenaUpd = Double.parseDouble(request.getParameter("txtPrecioDocena"));
                    double precioMayorUpd = Double.parseDouble(request.getParameter("txtPrecioMayor"));
                    int existenciaUpd = Integer.parseInt(request.getParameter("txtExistencia"));
                    int codigoCategoriaUpd = Integer.parseInt(request.getParameter("txtCodigoCategoria"));
                    int codigoDistribuidorUpd = Integer.parseInt(request.getParameter("txtCodigoDistribuidor"));

                    // Obtener la vista previa de la imagen
                    Part archivoVistaPrev = request.getPart("fileVistaPrevia");
                    InputStream inputStreamVistaPrev = archivoVistaPrev.getInputStream();

                    ByteArrayOutputStream outputStreamVistaPrev = new ByteArrayOutputStream();
                    byte[] buffererByte = new byte[104857600];
                    int readedBytes;

                    while ((readedBytes = inputStreamVistaPrev.read(buffererByte)) != -1) {
                        outputStreamVistaPrev.write(buffererByte, 0, readedBytes);
                    }

                    byte[] bytesVistaPrev = outputStreamVistaPrev.toByteArray();
                    Blob blobVistaPrev = null;

                    try {
                        blobVistaPrev = new javax.sql.rowset.serial.SerialBlob(bytesVistaPrev);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                    // Asegúrate de cerrar los streams después de usarlos
                    inputStreamVistaPrev.close();
                    outputStreamVistaPrev.close();

                    producto.setCodigoProducto(codigoProductoUpd);
                    producto.setDescripcionProducto(descripcionProductoUpd);
                    producto.setPrecioUnitario(precioUnitarioUpd);
                    producto.setPrecioDocena(precioDocenaUpd);
                    producto.setPrecioMayor(precioMayorUpd);
                    producto.setExistencia(existenciaUpd);
                    producto.setCodigoCategoria(codigoCategoriaUpd);
                    producto.setCodigoDistribuidor(codigoDistribuidorUpd);
                    try{
                        producto.setVistaPrevia(blobToBytes(blobVistaPrev));
                    }catch(Exception e){
                        e.printStackTrace();
                    }
                    productoDao.actualizar(producto);
                    response.sendRedirect("Controlador?menu=Producto&accion=Listar");
                    break;
                case "Eliminar":
                    codProducto = request.getParameter("codigoProducto");
                    productoDao.eliminar(String.valueOf(codProducto));
                    response.sendRedirect("Controlador?menu=Producto&accion=Listar");
                    break;
            }
        }
        else if(menu.equals("EmailDistribuidor")){
            switch(accion){
                case "Listar":
                    List listaEmailDistribuidores = emailDistribuidorDao.listar();
                    request.setAttribute("emailDistribuidores", listaEmailDistribuidores);
                    request.getRequestDispatcher("EmailDistribuidor.jsp").forward(request, response);
                break;
                case "Agregar":
                    String emailDistribuidor = request.getParameter("txtEmailDistribuidor");
                    String descripcion = request.getParameter("txtDescripcion");
                    String horarioDeAtencion = request.getParameter("txtHorarioDeAtencion");
                    int codigoDistribuidor = Integer.parseInt(request.getParameter("txtCodigoDistribuidor"));
                    emailDistribuidores.setEmailDistribuidor(emailDistribuidor);
                    emailDistribuidores.setDescripcion(descripcion);
                    emailDistribuidores.setHorarioDeAtencion(horarioDeAtencion);
                    emailDistribuidores.setCodigoDistribuidor(codigoDistribuidor);
                    emailDistribuidorDao.agregar(emailDistribuidores);
                    request.getRequestDispatcher("Controlador?menu=EmailDistribuidor&accion=Listar").forward(request, response);
                break;
                case "Editar":
                    codEmailDistribuidor = Integer.parseInt(request.getParameter("codigoEmailDistribuidor"));
                    EmailDistribuidor e = emailDistribuidorDao.listarCodigoEmailDistribuidor(codEmailDistribuidor);
                    request.setAttribute("emailDistribuidor", e);
                    request.getRequestDispatcher("Controlador?menu=EmailDistribuidor&accion=Listar").forward(request, response);
                break;
                case "Actualizar":
                    String emailDistribuidorEMAIL = request.getParameter("txtEmailDistribuidor");
                    String descripcionEMAIL = request.getParameter("txtDescripcion");
                    String horarioDeAtencionEMAIL = request.getParameter("txtHorarioDeAtencion");
                    int codigoDistribuidorEMAIL = Integer.parseInt(request.getParameter("txtCodigoDistribuidor"));
                    emailDistribuidores.setEmailDistribuidor(emailDistribuidorEMAIL);
                    emailDistribuidores.setDescripcion(descripcionEMAIL);
                    emailDistribuidores.setHorarioDeAtencion(horarioDeAtencionEMAIL);
                    emailDistribuidores.setCodigoDistribuidor(codigoDistribuidorEMAIL);
                    emailDistribuidores.setCodigoEmailDistribuidor(codEmailDistribuidor);
                    emailDistribuidorDao.actualizar(emailDistribuidores);
                    request.getRequestDispatcher("Controlador?menu=EmailDistribuidor&accion=Listar").forward(request, response);
                break;
                case "Eliminar":
                    codEmailDistribuidor = Integer.parseInt(request.getParameter("codigoEmailDistribuidor"));
                    emailDistribuidorDao.eliminar(codEmailDistribuidor);
                    request.getRequestDispatcher("Controlador?menu=EmailDistribuidor&accion=Listar").forward(request, response);
                break;
            }
        }else if(menu.equals("CargoEmpleado")) {
            switch (accion) {
                case "Listar":
                    List listaCargoEmpleado = cargoempleadoDao.listar();
                    request.setAttribute("cargoEmpleados", listaCargoEmpleado);
                    request.getRequestDispatcher("CargoEmpleado.jsp").forward(request, response);
                    break;
                case "Agregar":
                    String nombres = request.getParameter("txtNombreCargo");
                    String descripcion = request.getParameter("txtDescripcionCargo");
                    String turno = request.getParameter("txtTurno");
                    cargoempleado.setNombreCargo(nombres);
                    cargoempleado.setDescripcion(descripcion);
                    cargoempleado.setTurno(turno);
                    cargoempleadoDao.agregar(cargoempleado);
                    request.getRequestDispatcher("Controlador?menu=CargoEmpleado&accion=Listar").forward(request, response);
                    break;
                case "Actualizar":
                    String nombreCargo = request.getParameter("txtNombreCargo");
                    String descripcionCargo = request.getParameter("txtDescripcionCargo");
                    String turnoCargo = request.getParameter("txtTurno");
                    cargoempleado.setCodigoCargoEmpleado(codCargoEmpleado);
                    cargoempleado.setNombreCargo(nombreCargo);
                    cargoempleado.setDescripcion(descripcionCargo);
                    cargoempleado.setTurno(turnoCargo);
                    cargoempleadoDao.actualizar(cargoempleado);
                    request.getRequestDispatcher("Controlador?menu=CargoEmpleado&accion=Listar").forward(request, response);
                    break;
                case "Editar":
                    codCargoEmpleado = Integer.parseInt(request.getParameter("codigoCargoEmpleado"));
                    CargoEmpleado ce = cargoempleadoDao.listarCargoEmpleado(codCargoEmpleado);
                    request.setAttribute("cargoEmpleado", ce);
                    request.getRequestDispatcher("Controlador?menu=CargoEmpleado&accion=Listar").forward(request, response);
                    break;
                case "Eliminar":
                    codCargoEmpleado = Integer.parseInt(request.getParameter("codigoCargoEmpleado"));
                    cargoempleadoDao.eliminar(codCargoEmpleado);
                    request.getRequestDispatcher("Controlador?menu=CargoEmpleado&accion=Listar").forward(request, response);
                    break;
            }
        }

        else if (menu.equals("Distribuidores")) {
            switch(accion){
                case "Listar":
                    // Lista todos los distribuidores
                    List<Distribuidores> listaDistribuidores = distribuidoresDao.listar();
                    request.setAttribute("distribuidores", listaDistribuidores);
                    request.getRequestDispatcher("Distribuidores.jsp").forward(request, response);
                    break;

                case "Agregar":
                    // Captura los valores de los campos del formulario
                    String nombreDistribuidor = request.getParameter("txtNombreDistribuidor");
                    String direccionDistribuidor = request.getParameter("txtDireccionDistribuidor");
                    String codigoPostal = request.getParameter("txtCodigoPostal");
                    String paginaWeb = request.getParameter("txtPaginaWeb");

                    // Crea una nueva instancia de Distribuidores y establece los valores
                    distribuidor.setNombreDistribuidor(nombreDistribuidor);
                    distribuidor.setDireccionDistribuidor(direccionDistribuidor);
                    distribuidor.setCodigoPostal(codigoPostal);
                    distribuidor.setPaginaWeb(paginaWeb);

                    // Agrega el nuevo distribuidor a la base de datos
                    distribuidoresDao.agregar(distribuidor);
                    request.getRequestDispatcher("Controlador?menu=Distribuidores&accion=Listar").forward(request, response);
                    break;

                case "Actualizar":
                    // Captura los valores actualizados del formulario
                    String nombreAct = request.getParameter("txtNombreDistribuidor");
                    String direccionAct = request.getParameter("txtDireccionDistribuidor");
                    String codigoPostalAct = request.getParameter("txtCodigoPostal");
                    String paginaWebAct = request.getParameter("txtPaginaWeb");

                    // Establece los nuevos valores en el objeto distribuidor
                    distribuidor.setNombreDistribuidor(nombreAct);
                    distribuidor.setDireccionDistribuidor(direccionAct);
                    distribuidor.setCodigoPostal(codigoPostalAct);
                    distribuidor.setPaginaWeb(paginaWebAct);
                    distribuidor.setCodigoDistribuidor(codDistribuidor);

                    // Actualiza el distribuidor en la base de datos
                    distribuidoresDao.actualizar(distribuidor);
                    request.getRequestDispatcher("Controlador?menu=Distribuidores&accion=Listar").forward(request, response);
                    break;

                case "Editar":
                    // Obtiene el ID del distribuidor a editar
                    codDistribuidor = Integer.parseInt(request.getParameter("codigoDistribuidor"));
                    Distribuidores dist = distribuidoresDao.listarCodigoDistribuidor(codDistribuidor);

                    // Establece el distribuidor como un atributo de la solicitud
                    request.setAttribute("distribuidor", dist);
                    request.getRequestDispatcher("Controlador?menu=Distribuidores&accion=Listar").forward(request, response);
                    break;

                case "Eliminar":
                    // Obtiene el ID del distribuidor a eliminar
                    codDistribuidor = Integer.parseInt(request.getParameter("codigoDistribuidor"));
                    distribuidoresDao.eliminar(codDistribuidor);

                    // Redirige a la lista actualizada de distribuidores
                    request.getRequestDispatcher("Controlador?menu=Distribuidores&accion=Listar").forward(request, response);
                    break;
            }
        }
        else if(menu.equals("DetalleCompra")){
            switch(accion){
                case "Listar":
                    List listaDetalleCompra = detalleCompraDao.listar();
                    request.setAttribute("detalleCompras", listaDetalleCompra);
                    request.getRequestDispatcher("DetalleCompra.jsp").forward(request, response);
                break;
                case "Agregar":
                    Double costoUnitario = Double.parseDouble(request.getParameter("txtCostoUnitario"));
                    int cantidad = Integer.parseInt(request.getParameter("txtCantidad"));
                    String codigoProducto = request.getParameter("txtCodigoProducto");
                    int numeroDocumento = Integer.parseInt(request.getParameter("txtNumeroDocumento"));
                    detalleCompras.setCostoUnitario(costoUnitario);
                    detalleCompras.setCantidad(cantidad);
                    detalleCompras.setCodigoProducto(codigoProducto);
                    detalleCompras.setNumeroDocumento(numeroDocumento);
					detalleCompraDao.agregar(detalleCompras);
                    request.getRequestDispatcher("Controlador?menu=DetalleCompra&accion=Listar").forward(request, response);
                break;
                case "Editar":
                    codDetalleCompra = Integer.parseInt(request.getParameter("codigoDetalleCompra"));
                    DetalleCompra d = detalleCompraDao.listarCodigoDetalleCompra(codDetalleCompra);
                    request.setAttribute("detalleCompra", d);
                    request.getRequestDispatcher("Controlador?menu=DetalleCompra&accion=Listar").forward(request, response);
                break;
                case "Actualizar":
                    double costoDET = Double.parseDouble(request.getParameter("txtCostoUnitario"));
                    int cantidadDET = Integer.parseInt(request.getParameter("txtCantidad"));
                    String codigoProductoDET = request.getParameter("txtCodigoProducto");
                    int numeroDocumentoDET = Integer.parseInt(request.getParameter("txtNumeroDocumento"));
                    detalleCompras.setCostoUnitario(costoDET);
                    detalleCompras.setCantidad(cantidadDET);
                    detalleCompras.setCodigoProducto(codigoProductoDET);
                    detalleCompras.setNumeroDocumento(numeroDocumentoDET);
                    detalleCompras.setCodigoDetalleCompra(codDetalleCompra);
                    detalleCompraDao.actualizar(detalleCompras);
                    request.getRequestDispatcher("Controlador?menu=DetalleCompra&accion=Listar").forward(request, response);
                break;
                case "Eliminar":
                    codDetalleCompra = Integer.parseInt(request.getParameter("codigoDetalleCompra"));
                    detalleCompraDao.eliminar(codDetalleCompra);
                    request.getRequestDispatcher("Controlador?menu=CodigoDetalleCompra&accion=Listar").forward(request, response);
                break;
            }
        }else if(menu.equals("Clientes")){
            switch(accion){
                case "Listar":
                    List listaClientes = clientesDao.Listar();
                    request.setAttribute("clientes", listaClientes);
                    break;
                case "Agregar":
                    String NITCliente = request.getParameter("txtNITCliente");
                    String nombresCliente = request.getParameter("txtNombresCliente");
                    String apellidosCliente = request.getParameter("txtApellidosCliente");
                    String direccionCliente = request.getParameter("txtDireccionCliente");
                    String telefonoCliente = request.getParameter("txtTelefonoCliente");
                    String correoCliente = request.getParameter("txtCorreoCliente");
                    clientes.setNITCliente(NITCliente);
                    clientes.setNombresCliente(nombresCliente);
                    clientes.setApellidosCliente(apellidosCliente);
                    clientes.setDireccionCliente(direccionCliente);
                    clientes.setTelefonoCliente(telefonoCliente);
                    clientes.setCorreoCliente(correoCliente);
                    clientesDao.agregar(clientes);
                    request.getRequestDispatcher("Controlador?menu=Clientes&accion=Listar").forward(request, response);
                    break;
                case "Editar":
                    codCliente = Integer.parseInt(request.getParameter("codigoCliente"));
                    Clientes cli = clientesDao.ListarCodigoCliente(codCliente);
                    request.setAttribute("Clientes", cli);
                    request.getRequestDispatcher("Controlador?menu=Clientes&accion=Listar").forward(request, response);
                    break;
                case "Actualizar":
                    String NITCli = request.getParameter("txtNITCliente");
                    String nombCli = request.getParameter("txtNombresCliente");
                    String apelliCli = request.getParameter("txtApellidosCliente");
                    String direcCli = request.getParameter("txtDireccionCliente");
                    String teleCli = request.getParameter("txtTelefonoCliente");
                    String correoCli = request.getParameter("txtCorreoCliente");
                    clientes.setCodigoCliente(codCliente); // Establecer el código del cliente
                    clientes.setNITCliente(NITCli);
                    clientes.setNombresCliente(nombCli);
                    clientes.setApellidosCliente(apelliCli);
                    clientes.setDireccionCliente(direcCli);
                    clientes.setTelefonoCliente(teleCli);
                    clientes.setCorreoCliente(correoCli);
                    clientesDao.Actualizar(clientes);
                    request.getRequestDispatcher("Controlador?menu=Clientes&accion=Listar").forward(request, response);
                    break;
                case "Eliminar":
                    codCliente = Integer.parseInt(request.getParameter("codigoCliente"));
                    clientesDao.Eliminar(codCliente);
                    request.getRequestDispatcher("Controlador?menu=Clientes&accion=Listar").forward(request, response);
                    break; 
            }
            request.getRequestDispatcher("Clientes.jsp").forward(request, response);
        }
         else if (menu.equals("Factura")) {
            switch (accion) {
                case "Listar":
                    List <Factura> listaFacturas = facturaDao.listarFacturas();
                    request.setAttribute("facturas", listaFacturas);
                    request.getRequestDispatcher("Factura.jsp").forward(request, response);
                    break;
                case "Agregar":
                    int numeroFactura = Integer.parseInt(request.getParameter("txtNumeroFactura"));
                    String estado = request.getParameter("txtEstado");
                    String fechaFactura = request.getParameter("txtFechaFactura");
                    int codigoCliente = Integer.parseInt(request.getParameter("txtCodigoCliente"));
                    int codigoEmpleado = Integer.parseInt(request.getParameter("txtCodigoEmpleado"));
                    factura.setNumeroFactura(numeroFactura);
                    factura.setEstado(estado);
                    try{
                    String dateFormat = "dd/MM/yyyy"; // Por ejemplo, 12/08/2024
                    SimpleDateFormat formatter = new SimpleDateFormat(dateFormat);
                    
                    Date date = formatter.parse(fechaFactura);
                    factura.setFechaFactura(date);
                    }catch(Exception e){
                        e.printStackTrace();
                    }
                    factura.setCodigoCliente(codigoCliente);
                    factura.setCodigoEmpleado(codigoEmpleado);
                    facturaDao.agregar(factura);
                     request.getRequestDispatcher("Controlador?menu=Factura&accion=Listar").forward(request, response);
                    break;
                case "Editar":
                    int id = Integer.parseInt(request.getParameter("numeroFactura"));
                    Factura f = facturaDao.buscar(id);
                    request.setAttribute("factura", f);
                    request.getRequestDispatcher("Controlador?menu=Factura&accion=Listar").forward(request, response);
                    break;
                case "Actualizar":
                    int numFacturaUpd = Integer.parseInt(request.getParameter("txtNumeroFactura"));
                    String estadoUpd = request.getParameter("txtEstado");
                    Double totalFacturaUpd = Double.parseDouble(request.getParameter("txtTotalFactura"));
                    String fechaFacturaUpd = request.getParameter("txtFechaFactura");
                    int codigoClienteUpd = Integer.parseInt(request.getParameter("txtCodigoCliente"));
                    int codigoEmpleadoUpd = Integer.parseInt(request.getParameter("txtCodigoEmpleado"));
                    factura.setEstado(estadoUpd);
                    factura.setTotalFactura(totalFacturaUpd);
                    try{
                    String dateFormat = "dd/MM/yyyy"; // Por ejemplo, 12/08/2024
                    SimpleDateFormat formatter = new SimpleDateFormat(dateFormat);
                    
                    Date dateUpd = formatter.parse(fechaFacturaUpd);
                    factura.setFechaFactura(dateUpd);
                    }catch(Exception e){
                        e.printStackTrace();
                    }
                    factura.setCodigoCliente(codigoClienteUpd);
                    factura.setCodigoEmpleado(codigoEmpleadoUpd);
                     factura.setNumeroFactura(numFacturaUpd);
                    facturaDao.actualizar(factura);
                    request.getRequestDispatcher("Controlador?menu=Factura&accion=Listar").forward(request, response);
                    break;
                case "Eliminar":
                    int idDel = Integer.parseInt(request.getParameter("numeroFactura"));
                    facturaDao.eliminar(idDel);
                    request.getRequestDispatcher("Controlador?menu=Factura&accion=Listar").forward(request, response);
                    break;
            }
        } else if (menu.equals("Tienda")) {
            switch (accion) {
                case "Listar":
                    List<Productos> listaProductos = productoDao.listar();
                    request.setAttribute("productos", listaProductos);
                    request.setAttribute("lista", listaArticulos);
                    request.setAttribute("totalPagar", totalPagar);
                    request.getRequestDispatcher("Tienda.jsp").forward(request, response);
                    break;
                case "EliminarItem":
                    try {
                        int itemToRemove = Integer.parseInt(request.getParameter("codigoArticulo")) - 1; // Ajustar índice

                        // Verificar si el índice está dentro del rango válido
                        if (itemToRemove >= 0 && itemToRemove < listaArticulos.size()) {
                            // Restar el subtotal del artículo eliminado del total a pagar
                            totalPagar -= listaArticulos.get(itemToRemove).getSubTotal();

                            // Eliminar el artículo de la lista
                            listaArticulos.remove(itemToRemove);

                            // Reajustar los índices de los artículos restantes
                            for (int i = 0; i < listaArticulos.size(); i++) {
                                listaArticulos.get(i).setCodigoArticulo(i + 1);
                            }

                            // Actualizar los atributos de la solicitud
                            request.setAttribute("totalPagar", totalPagar);
                            request.setAttribute("lista", listaArticulos);
                        } else {
                            // Manejar el caso cuando el índice no es válido
                            request.setAttribute("error", "Índice fuera de rango.");
                        }

                        // Redirigir a la página de ventas
                        response.sendRedirect("Controlador?menu=Tienda&accion=Listar");

                    } catch (NumberFormatException e) {
                        // Manejar el caso cuando el parámetro no es un número válido
                        request.setAttribute("error", "Número de artículo inválido.");
                        response.sendRedirect("Controlador?menu=Tienda&accion=Listar");
                    }
                    break;


                    
                case "Cancelar":
                    listaArticulos.clear();
                    item = 0;
                    totalPagar = 0.0;
                    request.setAttribute("lista", listaArticulos);
                    request.setAttribute("totalPagar", totalPagar);
                    response.sendRedirect("Controlador?menu=Tienda&accion=Listar");
                    break;
            }
            // Código para agregar un artículo
            // Código para agregar un artículo
            if (accion != null && accion.startsWith("Agregar-")) {
                int index = Integer.parseInt(accion.split("-")[1]);

                // Recuperar los parámetros del formulario
                codigoProducto = request.getParameter("codigoProducto");
                cantidadDeProducto = Integer.parseInt(request.getParameter("cantidadProducto"));

                // Buscar si el artículo ya existe en el carrito
                boolean articuloExiste = false;
                for (Articulo articulo : listaArticulos) {
                    if (articulo.getCodigoProducto().equals(codigoProducto)) {
                        // Actualizar la cantidad y el subtotal si el artículo ya está en el carrito
                        articulo.setCantidad(articulo.getCantidad() + cantidadDeProducto);
                        articulo.setSubTotal(articulo.getPrecioUnitario() * articulo.getCantidad());
                        totalPagar += articulo.getPrecioUnitario() * cantidadDeProducto;
                        articuloExiste = true;
                        break;
                    }
                }

                if (!articuloExiste) {
                    // Crear y configurar el objeto Articulo
                    Productos articuloProducto = productoDao.listarCodigoProducto(codigoProducto);
                    Articulo articulo = new Articulo();

                    // Determinar el siguiente índice disponible
                    int item = 1;
                    boolean found = false;
                    while (!found) {
                        found = true;
                        for (Articulo a : listaArticulos) {
                            if (a.getCodigoArticulo() == item) {
                                found = false;
                                item++;
                                break;
                            }
                        }
                    }

                    articulo.setCodigoArticulo(item);
                    articulo.setCodigoProducto(codigoProducto);
                    articulo.setCantidad(cantidadDeProducto);
                    articulo.setDescripcionProducto(articuloProducto.getDescripcionProducto());
                    articulo.setPrecioUnitario(articuloProducto.getPrecioUnitario());
                    articulo.setSubTotal(articulo.getPrecioUnitario() * articulo.getCantidad());

                    // Agregar el artículo a la lista y actualizar el total
                    listaArticulos.add(articulo);
                    totalPagar += articuloProducto.getPrecioUnitario() * articulo.getCantidad();
                }

                // Configurar atributos para la página JSP
                request.setAttribute("totalPagar", totalPagar);
                request.setAttribute("lista", listaArticulos);

                // Redirigir a la página de ventas
                response.sendRedirect("Controlador?menu=Tienda&accion=Listar");
            }


        }else if(menu.equals("FormularioFactura")){
            switch (accion) {
                case "Listar":
                    List<Productos> listaProductos = productoDao.listar();
                    request.setAttribute("productos", listaProductos);
                    request.setAttribute("lista", listaArticulos);
                    request.setAttribute("totalPagar", totalPagar);
                    request.getRequestDispatcher("FormularioFactura.jsp").forward(request, response);
                    break;
                case "FinalizarCompraCF":
                    codigoClienteFactura = 1;
                    response.sendRedirect("Controlador?menu=FinalizarCompra&accion=CrearFacturaCF");
                    break;
                case "FinalizarCompraNIT":
                    codigoClienteFactura =cliente.getCodigoCliente();
                    response.sendRedirect("Controlador?menu=FinalizarCompra&accion=CrearFacturaNIT");
                    break;
                case "ValidarNIT":
                // Obtener el NIT ingresado por el usuario
                String nitIngresado = request.getParameter("txtNIT");

                // Verificar si el NIT existe en la base de datos
                 cliente = clienteDao.buscarPorNIT(nitIngresado);

                if (cliente == null) {
                    // Si el NIT no existe, enviar un mensaje de error y permanecer en la página actual
                    request.setAttribute("errorNIT", "El NIT ingresado no existe. Por favor, ingrese un NIT válido.");
                    request.getRequestDispatcher("Controlador?menu=FormularioFactura&accion=Listar").forward(request, response);
                } else {
                    // Si el NIT existe, redirigir a la página de finalizar compra
                    request.setAttribute("cliente", cliente);
                    request.getRequestDispatcher("Controlador?menu=FormularioFactura&accion=FinalizarCompraNIT&txtNIT="+nitIngresado).forward(request, response);
                }
                break;
            }
        }else if(menu.equals("FinalizarCompra")){
            switch(accion){
                case "Listar":
                    LocalDate localDate = LocalDate.now();
        
                    Date sqlDate = java.sql.Date.valueOf(localDate);
                    factura.setFechaFactura(sqlDate);
                    cliente = clienteDao.buscar(factura.getCodigoCliente());
                    request.setAttribute("cliente", cliente);
                    request.setAttribute("lista", listaArticulos);
                    request.setAttribute("factura", factura);
                    request.setAttribute("totalPagar", totalPagar);
                    
                    
                    
                    request.getRequestDispatcher("FinalizarCompra.jsp").forward(request, response);
                    break;
                case "CrearFacturaCF":
                    numeroDeFactura = numeroDeFactura + 1;
                    LocalDate fechaHoy = LocalDate.now();

                    // Convertir la fecha a formato YYMMDD
                    String fechaCorta = fechaHoy.toString().substring(2, 4) + 
                                        fechaHoy.toString().substring(5, 7) + 
                                        fechaHoy.toString().substring(8, 10);

                    String textoNumeroFactura = String.valueOf(numeroDeFactura);
                    if(numeroDeFactura < 10){
                        textoNumeroFactura = "00" + textoNumeroFactura;
                    }else if(numeroDeFactura >= 10 && numeroDeFactura < 100){
                        textoNumeroFactura = "0" + textoNumeroFactura;
                    }

                    String nuevoCodigoFactura = fechaCorta + textoNumeroFactura;
                    int numeroFactura = Integer.parseInt(nuevoCodigoFactura);
                    factura.setNumeroFactura(numeroFactura);
                    factura.setEstado("A cancelar");
                    factura.setCodigoCliente(1); // Cliente CF
                    factura.setCodigoEmpleado(1); // Ajusta según sea necesario
                    // Insertar la factura y verificar que se haya insertado correctamente
                    facturaDao.agregar(factura);
                    for (Articulo articulo : listaArticulos) {
                        detalleFactura = new DetalleFactura();
                        detalleFactura.setCantidad(articulo.getCantidad());
                        detalleFactura.setCodigoProducto(articulo.getCodigoProducto());
                        detalleFactura.setNumeroFactura(factura.getNumeroFactura());
                        detalleDao.agregar(detalleFactura);
                    }
                    //factura = facturaDao.buscar(numeroFactura);
                    response.sendRedirect("Controlador?menu=FinalizarCompra&accion=Listar");
                    
                    break;

                case "CrearFacturaNIT":
                    codigoClienteFactura = cliente.getCodigoCliente();
                    numeroDeFactura = numeroDeFactura + 1;
                    LocalDate fechaHoyNIT = LocalDate.now();

                    // Convertir la fecha a formato YYMMDD
                    String fechaCortaNIT = fechaHoyNIT.toString().substring(2, 4) + 
                                           fechaHoyNIT.toString().substring(5, 7) + 
                                           fechaHoyNIT.toString().substring(8, 10);

                    String textoNumeroFacturaNIT = String.valueOf(numeroDeFactura);
                    if(numeroDeFactura < 10){
                        textoNumeroFacturaNIT = "00" + textoNumeroFacturaNIT;
                    }else if(numeroDeFactura >= 10 && numeroDeFactura < 100){
                        textoNumeroFacturaNIT = "0" + textoNumeroFacturaNIT;
                    }

                    String nuevoCodigoFacturaNIT = fechaCortaNIT + textoNumeroFacturaNIT;
                    int numeroFacturaNIT = Integer.parseInt(nuevoCodigoFacturaNIT);
                    factura.setNumeroFactura(numeroFacturaNIT);
                    factura.setEstado("A cancelar");
                    factura.setCodigoCliente(codigoClienteFactura);
                    factura.setCodigoEmpleado(1); // Ajusta según sea necesario

                    // Insertar la factura y verificar que se haya insertado correctamente
                    facturaDao.agregar(factura);
                    for (Articulo articulo : listaArticulos) {
                        detalleFactura = new DetalleFactura();
                        detalleFactura.setCantidad(articulo.getCantidad());
                        detalleFactura.setCodigoProducto(articulo.getCodigoProducto());
                        detalleFactura.setNumeroFactura(factura.getNumeroFactura());
                        detalleDao.agregar(detalleFactura);
                    }
                    //factura = facturaDao.buscar(numeroFacturaNIT);
                    response.sendRedirect("Controlador?menu=FinalizarCompra&accion=Listar");
                    break;
                case "VolverATienda":
                    List<Productos> listaProductos = productoDao.listar();
                    item = 0;
                    totalPagar = 0.0;
                    listaArticulos.clear();
                    request.setAttribute("productos", listaProductos);
                    request.setAttribute("lista", listaArticulos);
                    request.setAttribute("totalPagar", totalPagar);
                    request.getRequestDispatcher("Tienda.jsp").forward(request, response);
                    break;
            }
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }
    
    public static byte[] blobToBytes(Blob blob) throws SQLException, IOException {
        if (blob == null) {
            return new byte[0];
        }
        
        try (InputStream inputStream = blob.getBinaryStream()) {
            long blobLength = blob.length();
            byte[] bytes = new byte[(int) blobLength];
            int bytesRead = 0;
            int read;
            while ((read = inputStream.read(bytes, bytesRead, bytes.length - bytesRead)) != -1) {
                bytesRead += read;
            }
            return bytes;
        }
    }
}