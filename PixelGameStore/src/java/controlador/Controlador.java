/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import modelo.Categoria;
import modelo.CategoriaDAO;
import modelo.Usuario;
import modelo.UsuarioDAO;

/**
 *
 * @author informatica
 */
public class Controlador extends HttpServlet {
    Usuario empleado = new Usuario();
    UsuarioDAO empleadoDao = new UsuarioDAO();
    Categoria  categoria = new Categoria();
    CategoriaDAO categoriaDao = new CategoriaDAO();
    int codCategoria;

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String menu = request.getParameter("menu");
        String accion = request.getParameter("accion");
        if (menu.equals("Principal")){
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
                        
                        
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
