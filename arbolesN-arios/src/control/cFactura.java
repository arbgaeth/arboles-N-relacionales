/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package control;

import java.io.PrintWriter;
import model.cliente;
import model.factura;
import model.vendedor;

/**
 *
 * @author macbookair
 */
public class cFactura extends CRUD{
    cCliente ctrlc;
    cVendedor ctrlv;
    public cFactura(cCliente cli, cVendedor ven) {
        ctrlc = cli;// Árbol de clientes
        ctrlv = ven;// Árbol de vendedores
        this.read("factura.dat");
    }
    //CRUD (create, insert)
    //CREATE
    private factura create(String fechaFac, String razonSocial, String direccion, String telefono, String nit, int total, vendedor ven, cliente cli, int id){
        return new factura(fechaFac, razonSocial, direccion, telefono, nit, total, ven, cli, id); 
    }
    public boolean insert(String fechaFac, String razonSocial, String direccion, String telefono, String nit, int total, int ven, int cli, int id){
        cliente respc =  (cliente)this.ctrlc.search(this.ctrlc.raiz, cli);
        vendedor respv = (vendedor)this.ctrlv.search(this.ctrlv.raiz, ven);
        if(respc != null && respv != null){
            if(this.insert(this.raiz, this.create(fechaFac, razonSocial, direccion, telefono, nit, total, respv, respc, id)) != null){
                this.save("factura.dat");
                return true;
            }else{
                return false;
            }
        }else{
            return false;
        }
    }
    //READ
    private String inOrden(factura r){
        String resp = "";
        if(r != null){
            // 1. Se mueve a la izquierda
            if(r.getIzq() != null){//puede continuar moviendose a la izq
                resp = this.inOrden((factura)r.getIzq());
            }
            // 2. Consulta la raíz (r)
            resp = resp + r.getId()+","+r.getFechaFac()+","+r.getRazonSocial()+","+r.getDireccion()+","+r.getTelefono()+","+r.getNit()+","+r.getTotal()+","+r.getVen().getId()+","+r.getCli().getId();
            System.out.println(r.getId()+","+r.getVen().getId()+","+r.getCli().getId());
            // 3. Se mueve a la derecha
            if(r.getDer() != null){//puede continuar moviendose a la izq
                resp = resp + this.inOrden((factura)r.getDer());
            }
        }
        return resp;
    }
    private String preOrden(factura r){
        String resp = "";
        if(r != null){
            // 1. Consulta la raíz (r)
            resp = resp + r.getId()+","+r.getFechaFac()+","+r.getRazonSocial()+","+r.getDireccion()+","+r.getTelefono()+","+r.getNit()+","+r.getTotal()+","+r.getVen().getId()+","+r.getCli().getId();
            System.out.println(r.getId()+","+r.getVen().getId()+","+r.getCli().getId());
            // 2. Se mueve a la izquierda
            if(r.getIzq() != null){//puede continuar moviendose a la izq
                resp = resp + this.inOrden((factura)r.getIzq());
            }
            // 3. Se mueve a la derecha
            if(r.getDer() != null){//puede continuar moviendose a la izq
                resp = resp + this.inOrden((factura)r.getDer());
            }
        }
        return resp;
    }
    private String postOrden(factura r){
        String resp = "";
        if(r != null){
            // 1. Se mueve a la izquierda
            if(r.getIzq() != null){//puede continuar moviendose a la izq
                resp = this.inOrden((factura)r.getIzq());
            }
            // 2. Se mueve a la derecha
            if(r.getDer() != null){//puede continuar moviendose a la izq
                resp = resp + this.inOrden((factura)r.getDer());
            }
            // 3. Consulta la raíz (r)
            resp = resp + r.getId()+","+r.getFechaFac()+","+r.getRazonSocial()+","+r.getDireccion()+","+r.getTelefono()+","+r.getNit()+","+r.getTotal()+","+r.getVen().getId()+","+r.getCli().getId();
            System.out.println(r.getId()+","+r.getVen().getId()+","+r.getCli().getId());
        }
        return resp;
    }
    public String showAll(){
        String respuesta = "inOrden\n"+this.inOrden((factura)this.raiz);
        respuesta = respuesta + "\npreOrden\n" + this.preOrden((factura)this.raiz);
        respuesta = respuesta + "\npostOrden\n"+ this.postOrden((factura)this.raiz);
        return respuesta;
    }
    public String select(int id){
        factura r =  (factura)this.search(this.raiz, id);
        if(r != null){//encontró un nodo con el identificador id
            return r.getId()+","+r.getFechaFac()+","+r.getRazonSocial()+","+r.getDireccion()+","+r.getTelefono()+","+r.getNit()+","+r.getTotal()+","+r.getVen().getId()+","+r.getCli().getId();
        }else{//no encontró ninguna coincidencia
            return null;
        }
    }
    //UPDATE
    public boolean update(String fechaFac, String razonSocial, String direccion, String telefono, String nit, int total, int ven, int cli, int id){
        factura p = (factura)this.search(raiz, id);
        if(p != null){//el nodo fue encontrado
            cliente respc =  (cliente)this.ctrlc.search(this.ctrlc.raiz, cli);
            vendedor respv = (vendedor)this.ctrlv.search(this.ctrlv.raiz, ven);
            if(respc != null && respv != null){
                p.setFechaFac(fechaFac);
                p.setRazonSocial(razonSocial);
                p.setDireccion(direccion);
                p.setTelefono(telefono);
                p.setNit(nit);
                p.setTotal(total);
                p.setVen(respv);
                p.setCli(respc);
                this.save("factura.dat");
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
            this.save("factura.dat");
            return true;
        }else{
            return false;
        }
    }
    //PERSISTENCIA
    private void preOrdenWrite(PrintWriter wr, factura r){
        /* 1. */wr.println(r.getId()+","+r.getFechaFac()+","+r.getRazonSocial()+","+r.getDireccion()+","+r.getTelefono()+","+r.getNit()+","+r.getTotal()+","+r.getVen().getId()+","+r.getCli().getId());
        /* 2. */if(r.getIzq() != null){
                    this.preOrdenWrite(wr, (factura)r.getIzq());
                }
        /* 3. */if(r.getDer() != null){
                    this.preOrdenWrite(wr, (factura)r.getDer());
                }
    }
    protected void writting(PrintWriter wr){
        this.preOrdenWrite(wr, (factura)this.raiz);
    }
    
    protected void reading (String cadena){
        this.insert(cadena.split(",")[1], cadena.split(",")[2], cadena.split(",")[3], cadena.split(",")[4], cadena.split(",")[5], Integer.parseInt(cadena.split(",")[6]), Integer.parseInt(cadena.split(",")[7]), Integer.parseInt(cadena.split(",")[8]), Integer.parseInt(cadena.split(",")[0]));
    }
}
