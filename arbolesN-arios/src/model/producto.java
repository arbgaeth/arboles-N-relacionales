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
public class producto extends nodo{
    String serie;
    String marca;
    String modelo;
    String descripcion;
    int valor;

    public producto() {
    }

    public producto(String serie, String marca, String modelo, String descripcion, int valor, int id) {
        super(id);
        this.serie = serie;
        this.marca = marca;
        this.modelo = modelo;
        this.descripcion = descripcion;
        this.valor = valor;
    }

    public String getSerie() {
        return serie;
    }

    public void setSerie(String serie) {
        this.serie = serie;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripciom) {
        this.descripcion = descripcion;
    }

    public int getValor() {
        return valor;
    }

    public void setValor(int valor) {
        this.valor = valor;
    }
    
}
