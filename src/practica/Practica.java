/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package practica;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;


/**
 *
 * @author juan sebastian
 */
class BinaryTree<T>{
    class BinaryNode <T>{
        int clave;
        T value;
        BinaryNode izquierdo;
        BinaryNode derecho;

        public BinaryNode(int clave, T value) {
            this.clave = clave;
            this.value = value;
        }
      
       
    }
    BinaryNode raiz;

    public BinaryTree() {
        this.raiz = null;
    }
    
    BinaryNode insertar(int clave, T value, BinaryNode t){
        if(t==null){
           // System.out.println("C");
            return new BinaryNode(clave,value);
        }
        if(clave> t.clave){
            //System.out.println("R");
            t.derecho= insertar(clave,value,t.derecho);
        }
        if(clave< t.clave){
            //System.out.println("L");
            t.izquierdo= insertar(clave,value,t.izquierdo);
        }
         return t;
    }
    
    String recorrido=" ";
    void preorden(BinaryNode x){
        if(x!= null){
            
           // System.out.print(x.value.toString()+" ");
            preorden(x.izquierdo);
            recorrido+="i";
            preorden(x.derecho);
            recorrido+="d";
            
        }
        recorrido="";
        
    }
    
    void postorden(BinaryNode x){
        if(x!=null){
            postorden(x.izquierdo);
            postorden(x.derecho);
           // System.out.print(x.value.toString()+" ");
        }
    }
    
      void enorden(BinaryNode x){
        if(x!=null){
            enorden(x.izquierdo);
            recorrido+="i";
           // System.out.print(x.value.toString()+" , ");
            
            enorden(x.derecho);
            recorrido+="d";
            
        }
    }
      
    BinaryNode lefty(BinaryNode x){
          if(x.izquierdo==null)
            return x;
          return lefty(x.izquierdo);
    }
    
    boolean condicion;
    
    boolean comparar(BinaryNode x1,BinaryNode x2){ //compara si 2 arboles tiene la misma estructura
        if(x1== null && x2== null){
            return true;
            
             
        }else {
             if(x1== null || x2== null){
                
                 return false;
             } else{
               
                condicion=comparar(x1.izquierdo, x2.izquierdo) && comparar(x1.derecho , x2.derecho);
                // System.out.println("  "+x1.clave+"  "+x2.clave+" "+condicion);
                return condicion;
                 
             }
        }
    }
    
    
    /* void remove(int clave, T value){
    raiz= auxRemove(clave,value,raiz);
    }
    BinaryNode auxRemove(int clave, T value,BinaryNode x){
    if(x==null)
    return x;
    if(clave<x.clave){
    
    }
    }
    */
}

public class Practica {

    /**
     * @param args the command line arguments
     * @throws java.io.FileNotFoundException
     */
    static int resistencia [][];
    public static void main(String[] args) throws FileNotFoundException {
        File f= new File ("practica.txt");
        Scanner sc= new Scanner(f);
        
        
        int n =sc.nextInt();
        int k =sc.nextInt();
        int x;
        BinaryTree <Integer>arbol[]= new BinaryTree[n];
        //ingreso de datos
        for (int i = 0; i < n; i++) {
            arbol[i]= new BinaryTree();
            for (int j = 0; j < k; j++) {
                x= sc.nextInt();
              //  System.out.println("->"+x);
                arbol[i].raiz=arbol[i].insertar(x, x, arbol[i].raiz);
                //System.out.println(x);
            }
        }
        
              
        int diferentes= n; // numero de arboles diferentes 
        
        for (int i = 0; i < n; i++) { // compara el arbol i con los arboles j>i 
            for (int j = 0; j < i; j++) {
                if(arbol[i].comparar(arbol[i].raiz,arbol[j].raiz)){// si el arbol i igual a j pasa al siguiete  
                   diferentes--;
                   break;
                }
            }
        }
        System.out.println(diferentes);
        
        
        
    }
  
        
        
    
    
}
