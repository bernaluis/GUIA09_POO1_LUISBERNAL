<%-- 
    Document   : index
    Created on : Apr 18, 2018, 12:37:26 PM
    Author     : vergo_000
--%>
<%@page import="com.sv.udb.modelo.Bodega"%>
<%@page import="com.sv.udb.modelo.Piezas"%>
<%@page import="com.sv.udb.modelo.Proveedores"%>
<%@page import="com.sv.udb.controlador.Bodega_Ctrl"%>
<%@page import="com.sv.udb.controlador.Piezas_Ctrl"%>
<%@page import="com.sv.udb.controlador.Proveedores_Ctrl"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Mantenimiento de Piezas</title>
        <link rel='stylesheet' href='webjars/bootstrap/3.2.0/css/bootstrap.min.css'>
        <script type="text/javascript" src="webjars/jquery/2.1.1/jquery.min.js"></script>
        <script type="text/javascript" src="webjars/bootstrap/3.2.0/js/bootstrap.min.js"></script>
    </head>
    <body>
        <%
            boolean estaModi = Boolean.parseBoolean((String)request.getAttribute("estaModi"));
            String nombBton = estaModi ? "Nuevo" : "Guardar"; // Para el texto del botón
            String clssEditBton = estaModi ? "" : "display: none"; //Pra ocultar botones
        %>
        <div class="container">
            <div class="alert alert-success">
                <strong>Indicaciones:</strong> Usando Bootstrap con la Guía 09.
            </div>
            <div class="row">
                <div class="col-md-5">
                    <div class="panel panel-primary">
                        <div class="panel-heading">Formulario</div>
                        <div class="panel-body">
                            <div class="alert alert-success">
                                ${mensAler}
                            </div>
                            <form method="POST" action="BodegaServ" name="Demo">
                                <input type="hidden" name="codi" id="codi" value="${codi}"/>
                                 <div class="form-group">
                                  <label for="selPiezas">Pieza:</label>
                                  <select class="form-control" id="selPiezas" name="selPiezas">
                                    <%
                                        for(Piezas temp : new Piezas_Ctrl().consTodo())
                                        {
                                    %>

                                      <option value="<%=temp.getCodigo()%>"><%= temp.getNombre()%></option>
                                    <%
                                        }
                                    %>

                                  </select>
                                </div>
                                <div class="form-group">
                                  <label for="selProveedores">Proveedores:</label>
                                  <select class="form-control" id="selProveedores" name="selProveedores">
                                    <%
                                        for(Proveedores temp : new Proveedores_Ctrl().consTodo())
                                        {
                                    %>

                                      <option value="<%=temp.getCodigo()%>"><%= temp.getNombre()%></option>
                                    <%
                                        }
                                    %>

                                  </select>
                                </div>
                                    <div class="form-group">
                                            <label for="cant">Cantidad:</label>
                                            <input type="text" class="form-control" name="cant" id="cant" value="${cant}"/>
                                        </div>
                                        <div class="form-group">
                                            <label for="fecha">Fecha</label>
                                            <input type="text" class="form-control" name="fecha" id="fecha" value="${fecha}"/>
                                        </div>
                                <input type="submit" class="btn btn-default" name="btonBodega" value="<%=nombBton%>"/>
                                <input type="submit" class="btn btn-primary" style="<%=clssEditBton%>" name="btonBodega" value="Modificar"/>
                                <input type="submit" class="btn btn-danger" style="<%=clssEditBton%>" name="btonBodega" value="Eliminar"/>
                            </form>
                        </div>
                    </div>
                </div>
                <div class="col-md-7">
                    <div class="panel panel-primary">
                        <div class="panel-heading">La Tabla</div>
                        <div class="panel-body">
                            <form method="POST" action="BodegaServ" name="Tabl">
                                <table class="table table-bordered">
                                    <tr>
                                        <th>Cons</th>
                                        <th>Pieza</th>
                                        <th>Proveedor</th>
                                        <th>Fecha</th>
                                    </tr>
                                    <%
                                        for(Bodega temp : new Bodega_Ctrl().consTodo())
                                        {
                                    %>
                                        <tr>
                                            <td><input type="radio" name="codiBRadi" value="<%= temp.getCodigo()%>"/></td>
                                            <td><%= temp.getCodigo_pieza().getNombre()%></td>
                                            <td><%= temp.getCodigo_prov().getNombre()%></td>
                                            <td><%= temp.getFecha()%></td>
                                        </tr>
                                    <%
                                        }
                                    %>
                                </table>
                                <input type="submit" class="btn btn-success" name="btonBodega" value="Consultar"/>
                            </form>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </body>
</html>
