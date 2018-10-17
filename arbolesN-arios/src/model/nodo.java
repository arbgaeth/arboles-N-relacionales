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
public class nodo {
    
    //Atributos
    nodo der, izq;// punteros
    int id;// Primary key (Llave primaria)
    // Métodos
    //Cosntructor
    //Sobrecargados
    public nodo(){}
    
    public nodo(int id){
        this.id = id;
    }
    //Getter & Setter
    public void setId(int id){
        this.id = id;
    }
    public int getId(){
        return this.id;
    }
    public void setDer(nodo r){
        this.der = r;
    }
    public nodo getDer(){
        return this.der;
    }
    public void setIzq(nodo l){
        this.izq = l;
    }
    public nodo getIzq(){
        return this.izq;
    }
}
