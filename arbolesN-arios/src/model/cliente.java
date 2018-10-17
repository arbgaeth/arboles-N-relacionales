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
public class cliente extends persona{
    String fechaReg;
    int totalCompras;

    public cliente() {
    }

    public cliente(String fechaReg, int totalCompras, String nombre, String direccion, String telefono, String genero, String fechaNac, String email, int id) {
        super(nombre, direccion, telefono, genero, fechaNac, email, id);
        this.fechaReg = fechaReg;
        this.totalCompras = totalCompras;
    }

    public String getFechaReg() {
        return fechaReg;
    }

    public void setFechaReg(String fechaReg) {
        this.fechaReg = fechaReg;
    }

    public int getTotalCompras() {
        return totalCompras;
    }

    public void setTotalCompras(int totalCompras) {
        this.totalCompras = totalCompras;
    }
    
}
