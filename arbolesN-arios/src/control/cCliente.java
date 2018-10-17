/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package control;
import java.io.PrintWriter;
import model.cliente;

/**
 *
 * @author macbookair
 */
public class cCliente extends CRUD{
    public cCliente() {
        this.read("cliente.dat");
    }
    //CRUD (create, insert)
    //CREATE
    private cliente create(String fechaReg, int totalCompras, String nombre, String direccion, String telefono, String genero, String fechaNac, String email, int id){
        return new cliente(fechaReg, totalCompras, nombre, direccion, telefono, genero, fechaNac, email, id); 
    }
    public boolean insert(String fechaReg, int totalCompras, String nombre, String direccion, String telefono, String genero, String fechaNac, String email, int id){
        if(this.insert(this.raiz, this.create(fechaReg, totalCompras, nombre, direccion, telefono, genero, fechaNac, email, id)) != null){
            this.save("cliente.dat");
            return true;
        }else{
            return false;
        }
    }
    //READ
    private String inOrden(cliente r){
        String resp = "";
        if(r != null){
            // 1. Se mueve a la izquierda
            if(r.getIzq() != null){//puede continuar moviendose a la izq
                resp = this.inOrden((cliente)r.getIzq());
            }
            // 2. Consulta la raíz (r)
            resp = resp + r.getId()+","+r.getFechaReg()+","+r.getTotalCompras()+","+r.getNombre()+","+r.getDireccion()+","+r.getTelefono()+","+r.getGenero()+","+r.getFechaNac()+","+r.getEmail();
            System.out.println(r.getId()+","+r.getFechaReg()+","+r.getTotalCompras()+","+r.getNombre()+","+r.getDireccion()+","+r.getTelefono()+","+r.getGenero()+","+r.getFechaNac()+","+r.getEmail());
            // 3. Se mueve a la derecha
            if(r.getDer() != null){//puede continuar moviendose a la izq
                resp = resp + this.inOrden((cliente)r.getDer());
            }
        }
        return resp;
    }
    private String preOrden(cliente r){
        String resp = "";
        if(r != null){
            // 1. Consulta la raíz (r)
            resp = resp + r.getId()+","+r.getFechaReg()+","+r.getTotalCompras()+","+r.getNombre()+","+r.getDireccion()+","+r.getTelefono()+","+r.getGenero()+","+r.getFechaNac()+","+r.getEmail();
            System.out.println(r.getId()+","+r.getFechaReg()+","+r.getTotalCompras()+","+r.getNombre()+","+r.getDireccion()+","+r.getTelefono()+","+r.getGenero()+","+r.getFechaNac()+","+r.getEmail());
            // 2. Se mueve a la izquierda
            if(r.getIzq() != null){//puede continuar moviendose a la izq
                resp = resp + this.inOrden((cliente)r.getIzq());
            }
            // 3. Se mueve a la derecha
            if(r.getDer() != null){//puede continuar moviendose a la izq
                resp = resp + this.inOrden((cliente)r.getDer());
            }
        }
        return resp;
    }
    private String postOrden(cliente r){
        String resp = "";
        if(r != null){
            // 1. Se mueve a la izquierda
            if(r.getIzq() != null){//puede continuar moviendose a la izq
                resp = this.inOrden((cliente)r.getIzq());
            }
            // 2. Se mueve a la derecha
            if(r.getDer() != null){//puede continuar moviendose a la izq
                resp = resp + this.inOrden((cliente)r.getDer());
            }
            // 3. Consulta la raíz (r)
            resp = resp + r.getId()+","+r.getFechaReg()+","+r.getTotalCompras()+","+r.getNombre()+","+r.getDireccion()+","+r.getTelefono()+","+r.getGenero()+","+r.getFechaNac()+","+r.getEmail();
            System.out.println(r.getId()+","+r.getFechaReg()+","+r.getTotalCompras()+","+r.getNombre()+","+r.getDireccion()+","+r.getTelefono()+","+r.getGenero()+","+r.getFechaNac()+","+r.getEmail());
        }
        return resp;
    }
    public String showAll(){
        String respuesta = "inOrden\n"+this.inOrden((cliente)this.raiz);
        respuesta = respuesta + "\npreOrden\n" + this.preOrden((cliente)this.raiz);
        respuesta = respuesta + "\npostOrden\n"+ this.postOrden((cliente)this.raiz);
        return respuesta;
    }
    public String select(int id){
        cliente r =  (cliente)this.search(this.raiz, id);
        if(r != null){//encontró un nodo con el identificador id
            return r.getId()+","+r.getFechaReg()+","+r.getTotalCompras()+","+r.getNombre()+","+r.getDireccion()+","+r.getTelefono()+","+r.getGenero()+","+r.getFechaNac()+","+r.getEmail();
        }else{//no encontró ninguna coincidencia
            return null;
        }
    }
    //UPDATE
    public boolean update(String fechaReg, int totalCompras, String nombre, String direccion, String telefono, String genero, String fechaNac, String email, int id){
        cliente p = (cliente)this.search(raiz, id);
        if(p != null){//el nodo fue encontrado
            p.setFechaReg(fechaReg);
            p.setTotalCompras(totalCompras);
            p.setNombre(nombre);
            p.setDireccion(direccion);
            p.setTelefono(telefono);
            p.setGenero(genero);
            p.setFechaNac(fechaNac);
            p.setEmail(email);
            this.save("cliente.dat");
            return true;
        }else{
            return false;
        }
    }
    //DELETE
    public boolean delete(int id){
        if(this.drop(id) != null){
            this.save("cliente.dat");
            return true;
        }else{
            return false;
        }
    }
    //PERSISTENCIA
    private void preOrdenWrite(PrintWriter wr, cliente r){
        /* 1. */wr.println(r.getId()+","+r.getFechaReg()+","+r.getTotalCompras()+","+r.getNombre()+","+r.getDireccion()+","+r.getTelefono()+","+r.getGenero()+","+r.getFechaNac()+","+r.getEmail());
        /* 2. */if(r.getIzq() != null){
                    this.preOrdenWrite(wr, (cliente)r.getIzq());
                }
        /* 3. */if(r.getDer() != null){
                    this.preOrdenWrite(wr, (cliente)r.getDer());
                }
    }
    protected void writting(PrintWriter wr){
        this.preOrdenWrite(wr, (cliente)this.raiz);
    }
    
    protected void reading (String cadena){
        this.insert(cadena.split(",")[1], Integer.parseInt(cadena.split(",")[2]), cadena.split(",")[3], cadena.split(",")[4], cadena.split(",")[5], cadena.split(",")[6], cadena.split(",")[7], cadena.split(",")[8], Integer.parseInt(cadena.split(",")[0]));
    }
}
