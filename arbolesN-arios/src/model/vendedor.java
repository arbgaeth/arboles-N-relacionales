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
public class vendedor extends persona{
    String tipoContrato;
    String fechaIni;
    int salario;

    public vendedor() {
    }

    public vendedor(String tipoContrato, String fechaIni, int salario, String nombre, String direccion, String telefono, String genero, String fechaNac, String email, int id) {
        super(nombre, direccion, telefono, genero, fechaNac, email, id);
        this.tipoContrato = tipoContrato;
        this.fechaIni = fechaIni;
        this.salario = salario;
    }

    public String getTipoContrato() {
        return tipoContrato;
    }

    public void setTipoContrato(String tipoContrato) {
        this.tipoContrato = tipoContrato;
    }

    public String getFechaIni() {
        return fechaIni;
    }

    public void setFechaIni(String fechaIni) {
        this.fechaIni = fechaIni;
    }

    public int getSalario() {
        return salario;
    }

    public void setSalario(int salario) {
        this.salario = salario;
    }
    
}
