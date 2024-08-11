package org.apache.jsp;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;

public final class EmailDistribuidor_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent {

  private static final JspFactory _jspxFactory = JspFactory.getDefaultFactory();

  private static java.util.List<String> _jspx_dependants;

  private org.glassfish.jsp.api.ResourceInjector _jspx_resourceInjector;

  public java.util.List<String> getDependants() {
    return _jspx_dependants;
  }

  public void _jspService(HttpServletRequest request, HttpServletResponse response)
        throws java.io.IOException, ServletException {

    PageContext pageContext = null;
    HttpSession session = null;
    ServletContext application = null;
    ServletConfig config = null;
    JspWriter out = null;
    Object page = this;
    JspWriter _jspx_out = null;
    PageContext _jspx_page_context = null;

    try {
      response.setContentType("text/html;charset=UTF-8");
      pageContext = _jspxFactory.getPageContext(this, request, response,
      			null, true, 8192, true);
      _jspx_page_context = pageContext;
      application = pageContext.getServletContext();
      config = pageContext.getServletConfig();
      session = pageContext.getSession();
      out = pageContext.getOut();
      _jspx_out = out;
      _jspx_resourceInjector = (org.glassfish.jsp.api.ResourceInjector) application.getAttribute("com.sun.appserv.jsp.resource.injector");

      out.write("\r\n");
      out.write("<!DOCTYPE html>\r\n");
      out.write("<html>\r\n");
      out.write("    <head>\r\n");
      out.write("        <meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\">\r\n");
      out.write("        <title>Pagina EmailDistribuidor</title>\r\n");
      out.write("          <link rel=\"stylesheet\" href=\"https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/css/bootstrap.min.css\" integrity=\"sha384-TX8t27EcRE3e/ihU7zmQxVncDAy5uIKz4rEkgIXeMed4M0jlfIDPvg6uqKI2xXr2\" crossorigin=\"anonymous\">\r\n");
      out.write("        \r\n");
      out.write("    </head>\r\n");
      out.write("    <div class=\"d-flex\">\r\n");
      out.write("        <div class=\"card col-sm-4\">\r\n");
      out.write("            <div class=\"card-body\">\r\n");
      out.write("                <form action=\"Controlador?menu=EmailDistribuidor\" method=\"POST\">\r\n");
      out.write("                    <div class=\"form-group\">\r\n");
      out.write("                        <label><Strong>EMAIL DISTRIBUIDOR:</strong></label>\r\n");
      out.write("                        <input type=\"text\" value=\"\" name=\"txtEmailDistribuidor\" class=\"from-control\">\r\n");
      out.write("                    </div>          \r\n");
      out.write("                    \r\n");
      out.write("                    <div class=\"form-group\">\r\n");
      out.write("                        <label><Strong>DESCRIPCION:</strong></label>\r\n");
      out.write("                        <input type=\"text\" value=\"\" name=\"txtDescripcion\" class=\"from-control\">\r\n");
      out.write("                    </div>    \r\n");
      out.write("                    \r\n");
      out.write("                    <div class=\"form-group\">\r\n");
      out.write("                        <label><Strong>HORARIO DE ATENCION:</strong></label>\r\n");
      out.write("                        <input type=\"text\" value=\"\" name=\"txtHorarioDeAtencion\" class=\"from-control\">\r\n");
      out.write("                    </div>    \r\n");
      out.write("                    \r\n");
      out.write("                    <div class=\"form-group\">\r\n");
      out.write("                        <label><Strong>CODIGO DISTRIBUIDOR:</strong></label>\r\n");
      out.write("                        <input type=\"text\" value=\"\" name=\"txtCodigoDistribuidor\" class=\"from-control\">\r\n");
      out.write("                    </div>    \r\n");
      out.write("                    \r\n");
      out.write("                    <input type=\"submit\" name=\"accion\" value=\"Agregar\" class=\"btn btn-info\">\r\n");
      out.write("                    <input type=\"submit\" name=\"accion\" value=\"Actualizar\" class=\"btn btn-success\">\r\n");
      out.write("                </form>\r\n");
      out.write("            </div>\r\n");
      out.write("        </div>\r\n");
      out.write("        \r\n");
      out.write("        <div class=\"col-sm-8\">\r\n");
      out.write("            <table class=\"table table-hover\">\r\n");
      out.write("                <thead>\r\n");
      out.write("                    <tr>\r\n");
      out.write("                        <th>CODIGO</th>\r\n");
      out.write("                        <th>EMAILDISTRIBUIDOR</th>\r\n");
      out.write("                        <th>DESCRIPCION</th>\r\n");
      out.write("                        <th>HORARIODEATENCION</th>\r\n");
      out.write("                        <th>CODIGODISTRIBUIDOR</th>\r\n");
      out.write("                        <th>ACCIONES</th>\r\n");
      out.write("                    </tr>\r\n");
      out.write("                </thead>\r\n");
      out.write("                <tbody>\r\n");
      out.write("                    <c:forEach var=\"emailDistribuidor\" items=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.evaluateExpression("${empleados}", java.lang.String.class, (PageContext)_jspx_page_context, null));
      out.write("\">\r\n");
      out.write("                    <tr>\r\n");
      out.write("                        <td>");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.evaluateExpression("${emailDistribuidor.codigoEmailDistribuidor()}", java.lang.String.class, (PageContext)_jspx_page_context, null));
      out.write("</td>\r\n");
      out.write("                        <td>");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.evaluateExpression("${emailDistribuidor.emailDistribuidor()}", java.lang.String.class, (PageContext)_jspx_page_context, null));
      out.write("</td>\r\n");
      out.write("                        <td>");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.evaluateExpression("${emailDistribuidor.descripcion()}", java.lang.String.class, (PageContext)_jspx_page_context, null));
      out.write("</td>\r\n");
      out.write("                        <td>");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.evaluateExpression("${emailDistribuidor.horarioDeAtencion()}", java.lang.String.class, (PageContext)_jspx_page_context, null));
      out.write("</td>\r\n");
      out.write("                        <td>");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.evaluateExpression("${emailDistribuidor.codigoDistribuidor()}", java.lang.String.class, (PageContext)_jspx_page_context, null));
      out.write("</td>\r\n");
      out.write("                        <td>\r\n");
      out.write("                            <a class=\"btn btn-warning\" href=\"\">Editar</a>\r\n");
      out.write("                            <a class=\"btn btn-danger\" href=\"\">Eliminar</a>\r\n");
      out.write("                        </td>\r\n");
      out.write("                        \r\n");
      out.write("                    </tr>\r\n");
      out.write("                    </c:foreach>\r\n");
      out.write("                </tbody>\r\n");
      out.write("            </table>\r\n");
      out.write("        </div>\r\n");
      out.write("        \r\n");
      out.write("        \r\n");
      out.write("        \r\n");
      out.write("        \r\n");
      out.write("        \r\n");
      out.write("        \r\n");
      out.write("        \r\n");
      out.write("        \r\n");
      out.write("    </div>\r\n");
      out.write("    \r\n");
      out.write("    <body>\r\n");
      out.write("        <script src=\"https://code.jquery.com/jquery-3.5.1.slim.min.js\" integrity=\"sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj\" crossorigin=\"anonymous\"></script>\r\n");
      out.write("        <script src=\"https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js\" integrity=\"sha384-9/reFTGAW83EW2RDu2S0VKaIzap3H66lZH81PoYlFhbGU+6BZp6G7niu735Sk7lN\" crossorigin=\"anonymous\"></script>\r\n");
      out.write("        <script src=\"https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/js/bootstrap.min.js\" integrity=\"sha384-w1Q4orYjBQndcko6MimVbzY0tgp4pWB4lZ7lr30WKz0vr/aWKhXdBNmNb5D92v7s\" crossorigin=\"anonymous\"></script>\r\n");
      out.write("    </body>\r\n");
      out.write("</html>\r\n");
    } catch (Throwable t) {
      if (!(t instanceof SkipPageException)){
        out = _jspx_out;
        if (out != null && out.getBufferSize() != 0)
          out.clearBuffer();
        if (_jspx_page_context != null) _jspx_page_context.handlePageException(t);
        else throw new ServletException(t);
      }
    } finally {
      _jspxFactory.releasePageContext(_jspx_page_context);
    }
  }
}
