package controlador;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Blob;
import java.sql.SQLException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
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
import modelo.Distribuidores;
import modelo.DistribuidoresDAO;
import modelo.EmailDistribuidor;
import modelo.EmailDistribuidorDAO;
import modelo.Factura;
import modelo.FacturaDAO;
import modelo.Productos;
import modelo.ProductosDAO;
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
    Compras compras = new Compras();
    ComprasDAO comprasDao = new ComprasDAO();
    int numDocumento;
    Usuario empleado = new Usuario();
    UsuarioDAO empleadoDao = new UsuarioDAO();
    Categoria  categoria = new Categoria();
    CategoriaDAO categoriaDao = new CategoriaDAO();
    int item = 0;
    int cantidad;
    int codCategoria;
    int codCargoEmpleado;
    String descripcion;
    double precio, subTotal, totalPagar = 0.0;
    

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
                    int codCargoEmpleado = Integer.parseInt(request.getParameter("codigoCargoEmpleado"));
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
                    int codigoCargoEmpleado = Integer.parseInt(request.getParameter("codigoCargoEmpleado"));
                    CargoEmpleado ce = cargoempleadoDao.listarCargoEmpleado(codigoCargoEmpleado);
                    request.setAttribute("cargoEmpleado", ce);
                    request.getRequestDispatcher("Controlador?menu=CargoEmpleado&accion=Listar").forward(request, response);
                    break;
                case "Eliminar":
                    int codigoEliminar = Integer.parseInt(request.getParameter("codigoCargoEmpleado"));
                    cargoempleadoDao.eliminar(codigoEliminar);
                    request.getRequestDispatcher("Controlador?menu=CargoEmpleado&accion=Listar").forward(request, response);
                    break;
            }
        }else if (menu.equals("Distribuidores")) {
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
                    List <Factura> listaFacturas = facturaDao.listar();
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
                    factura.setFechaFactura(fechaFactura);
                    factura.setCodigoCliente(codigoCliente);
                    factura.setCodigoEmpleado(codigoEmpleado);
                    facturaDao.agregar(factura);
                     request.getRequestDispatcher("Controlador?menu=Factura&accion=Listar").forward(request, response);
                    break;
                case "Editar":
                    int id = Integer.parseInt(request.getParameter("numeroFactura"));
                    Factura f = facturaDao.listarNumeroFactura(id);
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
                    factura.setFechaFactura(fechaFacturaUpd);
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