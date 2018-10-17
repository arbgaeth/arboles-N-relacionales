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
public class factura extends nodo{
    String fechaFac;
    String razonSocial;
    String direccion;
    String telefono;
    String nit;
    int total;
    vendedor ven;
    cliente cli;

    public factura() {
    }

    public factura(String fechaFac, String razonSocial, String direccion, String telefono, String nit, int total, vendedor ven, cliente cli, int id) {
        super(id);
        this.fechaFac = fechaFac;
        this.razonSocial = razonSocial;
        this.direccion = direccion;
        this.telefono = telefono;
        this.nit = nit;
        this.total = total;
        this.ven = ven;
        this.cli = cli;
    }

    public String getFechaFac() {
        return fechaFac;
    }

    public void setFechaFac(String fechaFac) {
        this.fechaFac = fechaFac;
    }

    public String getRazonSocial() {
        return razonSocial;
    }

    public void setRazonSocial(String razonSocial) {
        this.razonSocial = razonSocial;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getNit() {
        return nit;
    }

    public void setNit(String nit) {
        this.nit = nit;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public vendedor getVen() {
        return ven;
    }

    public void setVen(vendedor ven) {
        this.ven = ven;
    }

    public cliente getCli() {
        return cli;
    }

    public void setCli(cliente cli) {
        this.cli = cli;
    }
    
    
}
