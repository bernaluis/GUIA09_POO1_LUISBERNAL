/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sv.udb.modelo;

import java.util.Date;

/**
 *
 * @author vergo_000
 */
public class Bodega {
    private int codigo;
    private Piezas codigo_pieza;
    private Proveedores codigo_prov;
    private int cantidad;
    private Date fecha;

    public Bodega(int codigo, Piezas codigo_pieza, Proveedores codigo_prov, int cantidad, Date fecha) {
        this.codigo = codigo;
        this.codigo_pieza = codigo_pieza;
        this.codigo_prov = codigo_prov;
        this.cantidad = cantidad;
        this.fecha = fecha;
    }

    public Bodega() {
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public Piezas getCodigo_pieza() {
        return codigo_pieza;
    }

    public void setCodigo_pieza(Piezas codigo_pieza) {
        this.codigo_pieza = codigo_pieza;
    }

    public Proveedores getCodigo_prov() {
        return codigo_prov;
    }

    public void setCodigo_prov(Proveedores codigo_prov) {
        this.codigo_prov = codigo_prov;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    @Override
    public String toString() {
        return  codigo_pieza.getNombre();
    }
}
