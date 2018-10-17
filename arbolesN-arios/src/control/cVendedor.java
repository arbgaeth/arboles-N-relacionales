/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package control;

import java.io.PrintWriter;
import model.vendedor;

/**
 *
 * @author macbookair
 */
public class cVendedor extends CRUD{
    public cVendedor() {
        this.read("vendedor.dat");
    }
    //CRUD (create, insert)
    //CREATE
    private vendedor create(String tipoContrato, String fechaIni, int salario, String nombre, String direccion, String telefono, String genero, String fechaNac, String email, int id){
        return new vendedor(tipoContrato, fechaIni, salario, nombre, direccion, telefono, genero, fechaNac, email, id); 
    }
    public boolean insert(String tipoContrato, String fechaIni, int salario, String nombre, String direccion, String telefono, String genero, String fechaNac, String email, int id){
        if(this.insert(this.raiz, this.create(tipoContrato, fechaIni, salario, nombre, direccion, telefono, genero, fechaNac, email, id)) != null){
            this.save("vendedor.dat");
            return true;
        }else{
            return false;
        }
    }
    //READ
    private String inOrden(vendedor r){
        String resp = "";
        if(r != null){
            // 1. Se mueve a la izquierda
            if(r.getIzq() != null){//puede continuar moviendose a la izq
                resp = this.inOrden((vendedor)r.getIzq());
            }
            // 2. Consulta la raíz (r)
            resp = resp + r.getId()+","+r.getTipoContrato()+","+r.getFechaIni()+","+r.getSalario()+","+r.getNombre()+","+r.getDireccion()+","+r.getTelefono()+","+r.getGenero()+","+r.getFechaNac()+","+r.getEmail();
            System.out.println(r.getId()+","+r.getTipoContrato()+","+r.getFechaIni()+","+r.getSalario()+","+r.getNombre()+","+r.getDireccion()+","+r.getTelefono()+","+r.getGenero()+","+r.getFechaNac()+","+r.getEmail());
            // 3. Se mueve a la derecha
            if(r.getDer() != null){//puede continuar moviendose a la izq
                resp = resp + this.inOrden((vendedor)r.getDer());
            }
        }
        return resp;
    }
    private String preOrden(vendedor r){
        String resp = "";
        if(r != null){
            // 1. Consulta la raíz (r)
            resp = resp + r.getId()+","+r.getTipoContrato()+","+r.getFechaIni()+","+r.getSalario()+","+r.getNombre()+","+r.getDireccion()+","+r.getTelefono()+","+r.getGenero()+","+r.getFechaNac()+","+r.getEmail();
            System.out.println(r.getId()+","+r.getTipoContrato()+","+r.getFechaIni()+","+r.getSalario()+","+r.getNombre()+","+r.getDireccion()+","+r.getTelefono()+","+r.getGenero()+","+r.getFechaNac()+","+r.getEmail());
            // 2. Se mueve a la izquierda
            if(r.getIzq() != null){//puede continuar moviendose a la izq
                resp = resp + this.inOrden((vendedor)r.getIzq());
            }
            // 3. Se mueve a la derecha
            if(r.getDer() != null){//puede continuar moviendose a la izq
                resp = resp + this.inOrden((vendedor)r.getDer());
            }
        }
        return resp;
    }
    private String postOrden(vendedor r){
        String resp = "";
        if(r != null){
            // 1. Se mueve a la izquierda
            if(r.getIzq() != null){//puede continuar moviendose a la izq
                resp = this.inOrden((vendedor)r.getIzq());
            }
            // 2. Se mueve a la derecha
            if(r.getDer() != null){//puede continuar moviendose a la izq
                resp = resp + this.inOrden((vendedor)r.getDer());
            }
            // 3. Consulta la raíz (r)
            resp = resp + r.getId()+","+r.getTipoContrato()+","+r.getFechaIni()+","+r.getSalario()+","+r.getNombre()+","+r.getDireccion()+","+r.getTelefono()+","+r.getGenero()+","+r.getFechaNac()+","+r.getEmail();
            System.out.println(r.getId()+","+r.getTipoContrato()+","+r.getFechaIni()+","+r.getSalario()+","+r.getNombre()+","+r.getDireccion()+","+r.getTelefono()+","+r.getGenero()+","+r.getFechaNac()+","+r.getEmail());
        }
        return resp;
    }
    public String showAll(){
        String respuesta = "inOrden\n"+this.inOrden((vendedor)this.raiz);
        respuesta = respuesta + "\npreOrden\n" + this.preOrden((vendedor)this.raiz);
        respuesta = respuesta + "\npostOrden\n"+ this.postOrden((vendedor)this.raiz);
        return respuesta;
    }
    public String select(int id){
        vendedor r =  (vendedor)this.search(this.raiz, id);
        if(r != null){//encontró un nodo con el identificador id
            return r.getId()+","+r.getTipoContrato()+","+r.getFechaIni()+","+r.getSalario()+","+r.getNombre()+","+r.getDireccion()+","+r.getTelefono()+","+r.getGenero()+","+r.getFechaNac()+","+r.getEmail();
        }else{//no encontró ninguna coincidencia
            return null;
        }
    }
    //UPDATE
    public boolean update(String tipoContrato, String fechaIni, int salario, String nombre, String direccion, String telefono, String genero, String fechaNac, String email, int id){
        vendedor p = (vendedor)this.search(raiz, id);
        if(p != null){//el nodo fue encontrado
            p.setTipoContrato(tipoContrato);
            p.setFechaIni(fechaIni);
            p.setSalario(salario);
            p.setNombre(nombre);
            p.setDireccion(direccion);
            p.setTelefono(telefono);
            p.setGenero(genero);
            p.setFechaNac(fechaNac);
            p.setEmail(email);
            this.save("vendedor.dat");
            return true;
        }else{
            return false;
        }
    }
    //DELETE
    public boolean delete(int id){
        if(this.drop(id) != null){
            this.save("vendedor.dat");
            return true;
        }else{
            return false;
        }
    }
    //PERSISTENCIA
    private void preOrdenWrite(PrintWriter wr, vendedor r){
        /* 1. */wr.println(r.getId()+","+r.getTipoContrato()+","+r.getFechaIni()+","+r.getSalario()+","+r.getNombre()+","+r.getDireccion()+","+r.getTelefono()+","+r.getGenero()+","+r.getFechaNac()+","+r.getEmail());
        /* 2. */if(r.getIzq() != null){
                    this.preOrdenWrite(wr, (vendedor)r.getIzq());
                }
        /* 3. */if(r.getDer() != null){
                    this.preOrdenWrite(wr, (vendedor)r.getDer());
                }
    }
    protected void writting(PrintWriter wr){
        this.preOrdenWrite(wr, (vendedor)this.raiz);
    }
    
    protected void reading (String cadena){
        this.insert(cadena.split(",")[1], cadena.split(",")[2], Integer.parseInt(cadena.split(",")[3]), cadena.split(",")[4], cadena.split(",")[5], cadena.split(",")[6], cadena.split(",")[7], cadena.split(",")[8], cadena.split(",")[9],Integer.parseInt(cadena.split(",")[0]));
    }
}
