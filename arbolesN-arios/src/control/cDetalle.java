/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package control;

import java.io.PrintWriter;
import model.factura;
import model.detalle;
import model.producto;

/**
 *
 * @author macbookair
 */
public class cDetalle extends CRUD{
    cFactura ctrlf;
    cProducto ctrlp;
    public cDetalle(cFactura fac, cProducto pro) {
        ctrlf = fac;// Árbol de facturas
        ctrlp = pro;// Árbol de productoes
        this.read("detalle.dat");
    }
    //CRUD (create, insert)
    //CREATE
    private detalle create(int cantidad, int valorParcial, factura fac, producto pro, int id){
        return new detalle(cantidad, valorParcial, fac, pro, id); 
    }
    public boolean insert(int cantidad, int valorParcial, int fac, int pro, int id){
        factura respf =  (factura)this.ctrlf.search(this.ctrlf.raiz, fac);
        producto respp = (producto)this.ctrlp.search(this.ctrlp.raiz, pro);
        if(respf != null && respp != null){
            if(this.insert(this.raiz, this.create(cantidad, valorParcial, respf, respp, id)) != null){
                this.save("detalle.dat");
                return true;
            }else{
                return false;
            }
        }else{
            return false;
        }
    }
    //READ
    private String inOrden(detalle r){
        String resp = "";
        if(r != null){
            // 1. Se mueve a la izquierda
            if(r.getIzq() != null){//puede continuar moviendose a la izq
                resp = this.inOrden((detalle)r.getIzq());
            }
            // 2. Consulta la raíz (r)
            resp = resp + r.getId()+","+r.getCantidad()+","+r.getValorParcial()+","+r.getFac().getId()+","+r.getPro().getId();
            System.out.println(r.getId()+","+r.getFac().getId()+","+r.getPro().getId());
            // 3. Se mueve a la derecha
            if(r.getDer() != null){//puede continuar moviendose a la izq
                resp = resp + this.inOrden((detalle)r.getDer());
            }
        }
        return resp;
    }
    private String preOrden(detalle r){
        String resp = "";
        if(r != null){
            // 1. Consulta la raíz (r)
            resp = resp + r.getId()+","+r.getCantidad()+","+r.getValorParcial()+","+r.getFac().getId()+","+r.getPro().getId();
            System.out.println(r.getId()+","+r.getFac().getId()+","+r.getPro().getId());
            // 2. Se mueve a la izquierda
            if(r.getIzq() != null){//puede continuar moviendose a la izq
                resp = resp + this.inOrden((detalle)r.getIzq());
            }
            // 3. Se mueve a la derecha
            if(r.getDer() != null){//puede continuar moviendose a la izq
                resp = resp + this.inOrden((detalle)r.getDer());
            }
        }
        return resp;
    }
    private String postOrden(detalle r){
        String resp = "";
        if(r != null){
            // 1. Se mueve a la izquierda
            if(r.getIzq() != null){//puede continuar moviendose a la izq
                resp = this.inOrden((detalle)r.getIzq());
            }
            // 2. Se mueve a la derecha
            if(r.getDer() != null){//puede continuar moviendose a la izq
                resp = resp + this.inOrden((detalle)r.getDer());
            }
            // 3. Consulta la raíz (r)
            resp = resp + r.getId()+","+r.getCantidad()+","+r.getValorParcial()+","+r.getFac().getId()+","+r.getPro().getId();
            System.out.println(r.getId()+","+r.getFac().getId()+","+r.getPro().getId());
        }
        return resp;
    }
    public String showAll(){
        String respuesta = "inOrden\n"+this.inOrden((detalle)this.raiz);
        respuesta = respuesta + "\npreOrden\n" + this.preOrden((detalle)this.raiz);
        respuesta = respuesta + "\npostOrden\n"+ this.postOrden((detalle)this.raiz);
        return respuesta;
    }
    public String select(int id){
        detalle r =  (detalle)this.search(this.raiz, id);
        if(r != null){//encontró un nodo con el identificador id
            return r.getId()+","+r.getCantidad()+","+r.getValorParcial()+","+r.getFac().getId()+","+r.getPro().getId();
        }else{//no encontró ninguna coincidencia
            return null;
        }
    }
    //UPDATE
    public boolean update(int cantidad, int valorParcial, int fac, int pro, int id){
        detalle p = (detalle)this.search(raiz, id);
        if(p != null){//el nodo fue encontrado
            factura respf =  (factura)this.ctrlf.search(this.ctrlf.raiz, fac);
            producto respp = (producto)this.ctrlp.search(this.ctrlp.raiz, pro);
            if(respf != null && respp != null){
                p.setCantidad(cantidad);
                p.setValorParcial(valorParcial);
                p.setFac(respf);
                p.setPro(respp);
                this.save("detalle.dat");
                return true;
            }else{
                return false;
            }
        }else{
            return false;
        }
    }
    //DELETE
    public boolean delete(int id){
        if(this.drop(id) != null){
            this.save("detalle.dat");
            return true;
        }else{
            return false;
        }
    }
    //PERSISTENCIA
    private void preOrdenWrite(PrintWriter wr, detalle r){
        /* 1. */wr.println(r.getId()+","+r.getCantidad()+","+r.getValorParcial()+","+r.getFac().getId()+","+r.getPro().getId());
        /* 2. */if(r.getIzq() != null){
                    this.preOrdenWrite(wr, (detalle)r.getIzq());
                }
        /* 3. */if(r.getDer() != null){
                    this.preOrdenWrite(wr, (detalle)r.getDer());
                }
    }
    protected void writting(PrintWriter wr){
        this.preOrdenWrite(wr, (detalle)this.raiz);
    }
    
    protected void reading (String cadena){
        this.insert(Integer.parseInt(cadena.split(",")[1]), Integer.parseInt(cadena.split(",")[2]), Integer.parseInt(cadena.split(",")[3]), Integer.parseInt(cadena.split(",")[4]), Integer.parseInt(cadena.split(",")[0]));
    }
}
