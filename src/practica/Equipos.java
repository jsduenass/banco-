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
 class DisjointSet {
    int rango[] ;
    int A[];
    int R[];
    
    public DisjointSet(int n){
        A= new int [n];
        R= new int [n+1];
        rango= new int [n];
        
        for (int i = 0; i < n; i++) {
            A[i]=i;
            R[i]=n;
        }
    }
    int representante(int x){
        if(A[x]==x)
            return x;
        else 
            return representante(A[x]);
    }
   
    
    int merge(int ax,int ay){//une dos representantes
        if(ax==A.length)
           return ay;
        if(ay==A.length)
            return ax;
        if(rango[ax]<rango[ay]){
            A[ax]=ay;
            return  A[ax];
        }else 
            A[ay]=ax;
            if(rango[ax]==rango[ay])
                rango[ax]++;
            return  A[ay];  
    }
    
    boolean alianza(int x,int y){
        int ax=this.representante(x);
        int ay=this.representante(y);
        this.print(x, y);
        return ax==ay&&R[ax]==R[ay];
    }
    boolean rival(int x,int y){
        int ax=this.representante(x);
        int ay=this.representante(y);
        this.print(x, y);
        return ax==R[ay]&&ay==R[ax];
    }
    boolean formarAlianza(int x, int y){
        int ax=this.representante(x);
        int ay= this.representante(y);
        this.print(x, y);
        if(ax!=R[ay]&&ay!=R[ax]){
           // System.out.println("formada");
           int nuevoRepresentante=merge(ax,ay); 
           int rival=this.merge(R[ax],R[ay]);
           if(rival<A.length)
              R[rival]=nuevoRepresentante;
           R[nuevoRepresentante]=rival;
          this.print(x,y);
           return true;
         }
        return false;
    }
    boolean formarRival(int x, int y){
        int ax=this.representante(x);
        int ay=this.representante(y);
        this.print(x, y);
        if(ax!=ay){
           // System.out.println("formada");
            int representante_x=merge(ax,R[ay]);
            int representante_y=merge(ay,R[ax]);
            R[representante_x]=representante_y;
            R[representante_y]=representante_x;
            this.print(x,y);
            return true;
        } else
        return false;
    }

 
    public void print(int x, int y) {
       // int ax=this.representante(x);
        //int ay=this.representante(y);
       // System.out.println(x+" A:"+ax+" R:"+R[ax]+"   "+y+" A:"+ay+" R:"+R[ay]);
      
    }
    
    
}

public class Equipos {
    public static void main(String[] args) throws FileNotFoundException{
         File f= new File("practica.txt");
        Scanner sc = new Scanner(f);
        while(!sc.nextLine().equals("Equipos")){}
        int m = sc.nextInt();
        int i,j;
        DisjointSet s= new DisjointSet(m);
        
        String cmd;
        while(sc.hasNext()){
            cmd=sc.next();
          //  s.iterar();
            if(cmd.equals("FormarAlianza")){
                i=sc.nextInt();
                j=sc.nextInt();
               //System.out.println("formar alianza  "+ i+" "+j);
                if(!s.formarAlianza(i, j))
                    System.out.println("ERROR");
                
            }if(cmd.equals("FormarRival")){
                i=sc.nextInt();
                j=sc.nextInt();
               // System.out.println("formar rivalidad  "+ i+" "+j);
                if(!s.formarRival(i, j))
                    System.out.println("ERROR");
                
            }if(cmd.equals("Alianza")){
                i=sc.nextInt();
                j=sc.nextInt();
              // System.out.println( i+" "+j+" son aliados?");
                System.out.println(s.alianza(i, j)?"SI":"NO");
                
            }if(cmd.equals("Rival")){
                i=sc.nextInt();
                j=sc.nextInt();
                //System.out.println( i+" "+j+" son rivales?");
                
                System.out.println(s.rival(i, j)?"SI":"NO");
            }
        }
        
    }
}
