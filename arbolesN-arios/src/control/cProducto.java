/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package control;

import java.io.PrintWriter;
import model.producto;

/**
 *
 * @author macbookair
 */
public class cProducto extends CRUD{
    public cProducto() {
        this.read("producto.dat");
    }
    //CRUD (create, insert)
    //CREATE
    private producto create(String serie, String marca, String modelo, String descripcion, int valor, int id){
        return new producto(serie, marca, modelo, descripcion, valor, id); 
    }
    public boolean insert(String serie, String marca, String modelo, String descripcion, int valor, int id){
        if(this.insert(this.raiz, this.create(serie, marca, modelo, descripcion, valor, id)) != null){
            this.save("producto.dat");
            return true;
        }else{
            return false;
        }
    }
    //READ
    private String inOrden(producto r){
        String resp = "";
        if(r != null){
            // 1. Se mueve a la izquierda
            if(r.getIzq() != null){//puede continuar moviendose a la izq
                resp = this.inOrden((producto)r.getIzq());
            }
            // 2. Consulta la raíz (r)
            resp = resp + r.getId()+","+r.getSerie()+","+r.getMarca()+","+r.getModelo()+","+r.getDescripcion()+","+r.getValor();
            System.out.println(r.getId()+","+r.getSerie()+","+r.getMarca()+","+r.getModelo()+","+r.getDescripcion()+","+r.getValor());
            // 3. Se mueve a la derecha
            if(r.getDer() != null){//puede continuar moviendose a la izq
                resp = resp + this.inOrden((producto)r.getDer());
            }
        }
        return resp;
    }
    private String preOrden(producto r){
        String resp = "";
        if(r != null){
            // 1. Consulta la raíz (r)
            resp = resp + r.getId()+","+r.getSerie()+","+r.getMarca()+","+r.getModelo()+","+r.getDescripcion()+","+r.getValor();
            System.out.println(r.getId()+","+r.getSerie()+","+r.getMarca()+","+r.getModelo()+","+r.getDescripcion()+","+r.getValor());
            // 2. Se mueve a la izquierda
            if(r.getIzq() != null){//puede continuar moviendose a la izq
                resp = resp + this.inOrden((producto)r.getIzq());
            }
            // 3. Se mueve a la derecha
            if(r.getDer() != null){//puede continuar moviendose a la izq
                resp = resp + this.inOrden((producto)r.getDer());
            }
        }
        return resp;
    }
    private String postOrden(producto r){
        String resp = "";
        if(r != null){
            // 1. Se mueve a la izquierda
            if(r.getIzq() != null){//puede continuar moviendose a la izq
                resp = this.inOrden((producto)r.getIzq());
            }
            // 2. Se mueve a la derecha
            if(r.getDer() != null){//puede continuar moviendose a la izq
                resp = resp + this.inOrden((producto)r.getDer());
            }
            // 3. Consulta la raíz (r)
            resp = resp + r.getId()+","+r.getSerie()+","+r.getMarca()+","+r.getModelo()+","+r.getDescripcion()+","+r.getValor();
            System.out.println(r.getId()+","+r.getSerie()+","+r.getMarca()+","+r.getModelo()+","+r.getDescripcion()+","+r.getValor());
        }
        return resp;
    }
    public String showAll(){
        String respuesta = "inOrden\n"+this.inOrden((producto)this.raiz);
        respuesta = respuesta + "\npreOrden\n" + this.preOrden((producto)this.raiz);
        respuesta = respuesta + "\npostOrden\n"+ this.postOrden((producto)this.raiz);
        return respuesta;
    }
    public String select(int id){
        producto r =  (producto)this.search(this.raiz, id);
        if(r != null){//encontró un nodo con el identificador id
            return r.getId()+","+r.getSerie()+","+r.getMarca()+","+r.getModelo()+","+r.getDescripcion()+","+r.getValor();
        }else{//no encontró ninguna coincidencia
            return null;
        }
    }
    //UPDATE
    public boolean update(String serie, String marca, String modelo, String descripcion, int valor, int id){
        producto p = (producto)this.search(raiz, id);
        if(p != null){//el nodo fue encontrado
            p.setSerie(serie);
            p.setMarca(marca);
            p.setModelo(modelo);
            p.setDescripcion(descripcion);
            p.setValor(valor);
            this.save("producto.dat");
            return true;
        }else{
            return false;
        }
    }
    //DELETE
    public boolean delete(int id){
        if(this.drop(id) != null){
            this.save("producto.dat");
            return true;
        }else{
            return false;
        }
    }
    //PERSISTENCIA
    private void preOrdenWrite(PrintWriter wr, producto r){
        /* 1. */wr.println(r.getId()+","+r.getSerie()+","+r.getMarca()+","+r.getModelo()+","+r.getDescripcion()+","+r.getValor());
        /* 2. */if(r.getIzq() != null){
                    this.preOrdenWrite(wr, (producto)r.getIzq());
                }
        /* 3. */if(r.getDer() != null){
                    this.preOrdenWrite(wr, (producto)r.getDer());
                }
    }
    protected void writting(PrintWriter wr){
        this.preOrdenWrite(wr, (producto)this.raiz);
    }
    
    protected void reading (String cadena){
        this.insert(cadena.split(",")[1], cadena.split(",")[2], cadena.split(",")[3], cadena.split(",")[4], Integer.parseInt(cadena.split(",")[5]), Integer.parseInt(cadena.split(",")[0]));
    }
}
