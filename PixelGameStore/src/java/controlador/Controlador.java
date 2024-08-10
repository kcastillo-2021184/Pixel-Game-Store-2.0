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
import modelo.Categoria;
import modelo.CategoriaDAO;
import modelo.EmailDistribuidor;
import modelo.EmailDistribuidorDAO;
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
    String codProducto;
    Productos producto = new Productos();
    ProductosDAO productoDao = new ProductosDAO();
    EmailDistribuidor emailDistribuidores = new EmailDistribuidor();
    EmailDistribuidorDAO emailDistribuidorDao = new EmailDistribuidorDAO();
    
    Usuario empleado = new Usuario();
    UsuarioDAO empleadoDao = new UsuarioDAO();
    Categoria  categoria = new Categoria();
    CategoriaDAO categoriaDao = new CategoriaDAO();
    int item = 0;
    int cantidad;
    int codCategoria;
    String descripcion;
    double precio, subTotal, totalPagar = 0.0;
    

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String menu = request.getParameter("menu");
        String accion = request.getParameter("accion");

        if (menu.equals("Principal")) {
            request.getRequestDispatcher("Principal.jsp").forward(request, response);   
        }else if(menu.equals("Categoria")){
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
            
                        
                        
        }else if (menu.equals("Producto")) {
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
                    codEmailDistribuidor = Integer.parseInt(request.getParameter("codEmailDistribuidor"));
                    empleadoDao.eliminar(codEmailDistribuidor);
                    request.getRequestDispatcher("Controlador?menu=EmailDistribuidor&accion=Listar").forward(request, response);
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