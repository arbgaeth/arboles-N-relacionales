/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 *
 * @author macbookair
 */
public class detalle extends nodo{
    int cantidad;
    int valorParcial;
    factura fac;
    producto pro;

    public detalle() {
    }

    public detalle(int cantidad, int valorParcial, factura fac, producto pro, int id) {
        super(id);
        this.cantidad = cantidad;
        this.valorParcial = valorParcial;
        this.fac = fac;
        this.pro = pro;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public int getValorParcial() {
        return valorParcial;
    }

    public void setValorParcial(int valorParcial) {
        this.valorParcial = valorParcial;
    }

    public factura getFac() {
        return fac;
    }

    public void setFac(factura fac) {
        this.fac = fac;
    }

    public producto getPro() {
        return pro;
    }

    public void setPro(producto pro) {
        this.pro = pro;
    }
    
}
