/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sv.udb.controlador;

import com.sv.udb.modelo.Bodega;
import com.sv.udb.modelo.Piezas;
import com.sv.udb.modelo.Proveedores;
import com.sv.udb.recursos.Conexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author vergo_000
 */
public class Bodega_Ctrl {
    private final Connection conn;

    public Bodega_Ctrl() {
        this.conn = new Conexion().getCon();
    }
    public List<Bodega> consTodo()
    {
       List<Bodega> resp = new ArrayList<>();
        try
        {
            PreparedStatement cmd = this.conn.prepareStatement("SELECT b.codi_bode,d.*,p.*,b.cant,b.fech_comp from bodega b inner join proveedores p inner join piezas d on d.codi_piez=b.codi_piez and p.codi_prov=b.codi_prov");
           ResultSet rs = cmd.executeQuery();
            while(rs.next())
            {
                resp.add(new Bodega(rs.getInt(1), (new Piezas(rs.getInt(2),rs.getString(3),rs.getString(4),rs.getString(5))),(new Proveedores(rs.getInt(6),rs.getString(7),rs.getString(8),rs.getString(9))),rs.getInt(10),rs.getDate(11))); // <----- Hay que llenar con los objetos
            }
        }
        catch(SQLException ex)
        {
            System.err.println("Error al consultar la bodega: " + ex.getMessage());
        }
        finally
        {
            try
            {
                if(this.conn != null)
                {
                    if(!this.conn.isClosed())
                    {
                        this.conn.close();
                    }
                }
            }
            catch(SQLException ex)
            {
                System.err.println("Error al cerrar la conexión");
            }
        }
        return resp;
    }
     public Bodega consTodoE(int codigo)
    {
      Bodega resp = null;
        try
        {
            PreparedStatement cmd = this.conn.prepareStatement("SELECT b.codi_bode,d.*,p.*,b.cant,b.fech_comp from bodega b inner join proveedores p inner join piezas d on d.codi_piez=b.codi_piez and p.codi_prov=b.codi_prov and b.codi_bode=?");
           cmd.setInt(1, codigo);
            ResultSet rs = cmd.executeQuery();
            while(rs.next())
            {
                resp=new Bodega(rs.getInt(1), (new Piezas(rs.getInt(2),rs.getString(3),rs.getString(4),rs.getString(5))),(new Proveedores(rs.getInt(6),rs.getString(7),rs.getString(8),rs.getString(9))),rs.getInt(10),rs.getDate(11)); // <----- Hay que llenar con los objetos
            }
        }
        catch(SQLException ex)
        {
            System.err.println("Error al consultar la bodega: " + ex.getMessage());
        }
        finally
        {
            try
            {
                if(this.conn != null)
                {
                    if(!this.conn.isClosed())
                    {
                        this.conn.close();
                    }
                }
            }
            catch(SQLException ex)
            {
                System.err.println("Error al cerrar la conexión");
            }
        }
        return resp;
    }
    public boolean del(int codi)
    {
        boolean resp = false;
        try
        {
            PreparedStatement cmd = this.conn.prepareStatement("delete  from bodega WHERE codi_bode=?");
            cmd.setInt(1, codi);
            cmd.executeUpdate();
            resp=true;
        }
        catch (SQLException ex)
        {
            System.err.println("Error al eliminar datos de bodega: " + ex.getMessage());
        }
        finally
        {
            try
            {
                if(this.conn != null)
                {
                    if(!this.conn.isClosed())
                    {
                        this.conn.close();
                    }
                }
            }
            catch(SQLException ex)
            {
                System.err.println("Error al cerrar la conexión");
            }
        }
        return resp;
    }
    public boolean guar(int codipi, int codip, int cantidad,String fecha)
    {
        boolean resp = false;
        try
        {
            PreparedStatement cmd = this.conn.prepareStatement("INSERT INTO bodega VALUES(NULL,?,?,?,?)");
            cmd.setInt(1, codipi);
            cmd.setInt(2, codip);
            cmd.setInt(3, cantidad);
            cmd.setString(4,  new SimpleDateFormat("yyyy-MM-dd").format(fecha));
            cmd.executeUpdate();
            resp=true;
        }
        catch (SQLException ex)
        {
            System.err.println("Error al guardar en la bodega: " + ex.getMessage());
        }
        finally
        {
            try
            {
                if(this.conn != null)
                {
                    if(!this.conn.isClosed())
                    {
                        this.conn.close();
                    }
                }
            }
            catch(SQLException ex)
            {
                System.err.println("Error al cerrar la conexión");
            }
        }
        return resp;
    }
     public boolean modi(int codipi, int codip, int cantidad, String fecha,int codigo)
    {
        boolean resp = false;
        try
        {
            PreparedStatement cmd = this.conn.prepareStatement("UPDATE `bodega` SET `codi_piez`=?,`codi_prov`=?,`cant`=?,`fech_comp`=? WHERE codi_bode=?");
            cmd.setInt(1, codipi);
            cmd.setInt(2, codip);
            cmd.setInt(3, cantidad);
            cmd.setString(4,  new SimpleDateFormat("yyyy-MM-dd").format(fecha));
            cmd.setInt(5, codigo);
            cmd.executeUpdate();
            resp=true;
        }
        catch (SQLException ex)
        {
            System.err.println("Error al guardar en la bodega: " + ex.getMessage());
        }
        finally
        {
            try
            {
                if(this.conn != null)
                {
                    if(!this.conn.isClosed())
                    {
                        this.conn.close();
                    }
                }
            }
            catch(SQLException ex)
            {
                System.err.println("Error al cerrar la conexión");
            }
        }
        return resp;
    }
}
