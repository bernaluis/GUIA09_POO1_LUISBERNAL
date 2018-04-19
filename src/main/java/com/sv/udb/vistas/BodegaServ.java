/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sv.udb.vistas;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.sv.udb.controlador.Bodega_Ctrl;
import com.sv.udb.modelo.Bodega;
import java.util.Date;
/**
 *
 * @author vergo_000
 */
@WebServlet(name = "BodegaServ", urlPatterns = {"/BodegaServ"})
public class BodegaServ extends HttpServlet {

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
         boolean esValido = request.getMethod().equals("POST");
        String mens = "";
        boolean resp=false;
        if (!esValido) {
            response.sendRedirect(request.getContextPath() + "/index.jsp");
        } else {
            String CRUD = request.getParameter("btonBodega");
            Date fecha=new Date();
            if (CRUD.equals("Guardar")) {
                 
                if (new Bodega_Ctrl().guar(Integer.parseInt(request.getParameter("selPiezas")), Integer.parseInt(request.getParameter("selProveedores")), Integer.parseInt(request.getParameter("cant")), fecha)) {
                    mens = "Datos guardados";
                    resp=true;
                } else {
                    mens = "Error al guardar";
                    resp=false;
                }
            } else if (CRUD.equals("Modificar")) {
                if (new Bodega_Ctrl().modi( Integer.parseInt(request.getParameter("selPiezas")), Integer.parseInt(request.getParameter("selProveedores")), Integer.parseInt(request.getParameter("cant")), fecha,Integer.parseInt(request.getParameter("codi").trim()))) {
                    mens = "Datos Modificados";
                    resp=true;
                } else {
                    mens = "Error al modificar";
                    resp=false;
                }
            } else if (CRUD.equals("Eliminar")) {
                if (new Bodega_Ctrl().del(Integer.parseInt(request.getParameter("codi")))) {
                    mens = "Datos Eliminados";
                    resp=true;
                } else {
                    mens = "Error al eliminados";
                    resp=false;
                }
            } else if (CRUD.equals("Consultar")) {
                int codi = Integer.parseInt(request.getParameter("codiBRadi") == null ? "-1"
                        : request.getParameter("codiBRadi"));
                Bodega obje = new Bodega_Ctrl().consTodoE(codi);
                if (obje != null) {
                    request.setAttribute("codi", obje.getCodigo());
                    request.setAttribute("selPiezas", obje.getCodigo_pieza().getCodigo());
                    request.setAttribute("selPiezas", obje.getCodigo_pieza().getNombre());
                    request.setAttribute("selProveedores", obje.getCodigo_prov().getCodigo());
                    request.setAttribute("selProveedores", obje.getCodigo_prov().getNombre());
                    request.setAttribute("cant", obje.getCantidad());
                    request.setAttribute("fecha", obje.getFecha());
                    mens = "Información consultada";
                    resp=true;
                    request.setAttribute("estaModi", "true"); //Esta modificando
                } else {
                    mens = "Error al consultar";
                    resp=false;
                }
            }
            request.setAttribute("mensAler", mens);
            request.getRequestDispatcher("/index.jsp").forward(request, response);
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
