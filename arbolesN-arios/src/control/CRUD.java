/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package control;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import model.nodo;
/**
 *
 * @author macbookair
 */
public abstract class CRUD {
    nodo raiz;
    //Create
    protected nodo insert(nodo r, nodo nn){
        if(r != null){//el árbol tiene nodos
            if(nn.getId() < r.getId()){// se ubicará a la izq
                if(r.getIzq() != null){//hay nodos a la izq
                    return this.insert(r.getIzq(), nn);//recursivo
                }else{
                    r.setIzq(nn);
                    return nn;
                }
            }else{//se ubicará a la der
                if(r.getDer() != null){//hay nodos a la der
                    return this.insert(r.getDer(), nn);//recursivo
                }else{//no hay para donde moverse
                    r.setDer(nn);
                    return nn;
                }
            }
        }else{//el árbol está vacío
            this.raiz = nn;
            return this.raiz;
        }
    }
    //Read
    protected nodo search(nodo r, int id){
        if(r != null){
            if(r.getId() != id){
                if(id < r.getId()){//el valor puede estar a la izq
                    if(r.getIzq() != null){//se puede mover a la izq
                        return this.search(r.getIzq(), id);// llamada recursiva
                    }else{//no hay valores hacia la izq
                        return null;
                    }
                }else{//el valor puede estar a la der
                    if(r.getDer() != null){//se puede mover a la der
                        return this.search(r.getDer(), id);//llamada recursiva
                    }else{//no se puede mover para la der
                        return null;
                    }
                }
            }else{
                return r;
            }
        }else{
            return null;
        }
    }
    //DELETE
    public nodo drop(int id){
        if(this.raiz != null){//si el árbol está vacío
            nodo p = this.search(this.raiz, id);
            if(p != null){// el nodo se encuentra dentro del árbol
                if(p != this.raiz){
                    nodo q =  this.getAnt(this.raiz, p);
                    if(p.getIzq() != null && p.getDer() != null){// si tiene las dos rámas
                        this.reLocate(p);
                        if(q.getDer() == p){
                            q.setDer(p.getDer());
                        }else{//////
                            q.setIzq(p.getDer());
                        }
                    }else{
                        if(p.getIzq() != null){//si tiene solo la rama izq
                            if(q.getDer() == p){
                                q.setDer(p.getIzq());
                            }else{//////
                                q.setIzq(p.getIzq());
                            }
                        }else if(p.getDer() != null){//si tiene solo la rama der
                            if(q.getDer() == p){
                                q.setDer(p.getDer());
                            }else{//////
                                q.setIzq(p.getDer());
                            }
                        }else{// si es una hoja
                            if (q.getDer() == p){
                                q.setDer(null);
                            }else{
                                q.setIzq(null);
                            }
                        }
                    }
                }else{
                    if(p.getIzq() != null && p.getDer() != null){// si tiene las dos rámas
                        this.reLocate(p);
                        this.raiz = p.getDer();
                    }else{
                        if(p.getIzq() != null){//si tiene solo la rama izq
                            this.raiz = p.getIzq();
                        }else if(p.getDer() != null){//si tiene solo la rama der
                            this.raiz = p.getDer();
                        }else{// si es una hoja
                            this.raiz = null;
                        }
                    }
                }
                return p;
            }else{
                return null;
            }
        }else{
            return null;
        }
    }
    private nodo getAnt(nodo r, nodo p){
        if(p.getId() < r.getId()){
            if(r.getIzq() != p){
                return this.getAnt(r.getIzq(), p);
            }else{
                return r;
            }
        }else{
            if(r.getDer() != p){
                return this.getAnt(r.getDer(), p);
            }else{
                return r;
            }
        }
    }
    private nodo moveIzq(nodo r){
        if(r.getIzq()!= null){
            return this.moveIzq(r.getIzq());
        }else{
            return r;
        }
    }
    private void reLocate(nodo p){
        nodo der = this.moveIzq(p.getDer());
        der.setIzq(p.getIzq());
    }
    //// guardar en archivo plano
    public void save(String nom){
        this.writeFile(nom);
    }
    protected void writeFile(String file){
        File f;
        f = new File(file);
        //Escritura
        try{
            FileWriter w = new FileWriter(f);
            BufferedWriter bw = new BufferedWriter(w);
            PrintWriter wr = new PrintWriter(bw);
            this.writting(wr);
            wr.close();
            bw.close();
        }catch(IOException e){};
    }
    protected abstract void writting(PrintWriter wr);
    //lectura
    public void read(String nom){
        this.readFile(nom);
    }
    protected void readFile(String file){
        FileReader fr = null;
        try {
            fr = new FileReader(file);
            BufferedReader entrada = new BufferedReader(fr);
            String cadena = entrada.readLine();
            while (cadena != null) {
		      reading(cadena);
		      cadena = entrada.readLine();
            }   
        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        } catch (IOException e) {
            System.out.println(e.getMessage());
        } finally {
            try {
		if (fr != null) {
			fr.close();
                }
            } catch (IOException e) {
                System.out.println(e.getMessage());
            }
        }
    }
    protected abstract void reading (String cadena);
}
