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
    int rango2[];
    int A[];
    int R[];
    
    public DisjointSet(int n){
        rango= new int [n];
        rango2= new int [n];
        A= new int [n];
        R= new int [n+1];
        for (int i = 0; i < 10; i++) {
            A[i]=i;
            R[i]=n+1;
        }
    }
    int alianza(int x){
        if(A[x]==x)
            return x;
        else 
            return alianza(A[x]);
    }
    int rival(int x){
        
        if(R[x]==R.length)
            return R.length;
        else 
            return rival(R[x]);
    }
    boolean alianza(int i,int j){
        System.out.println(i+" A:"+alianza(i)+"   "+j+" A:"+alianza(j));
        return alianza(i)==alianza(j);
    }
    boolean rival(int i,int j){
         System.out.println(i+" R:"+rival(i)+"   "+j+" R:"+rival(j));
         if(rival(i)==R.length||rival(j)==R.length)
             return false;
        else
        return rival(i)==rival(j);
    }
    boolean formarAlianza(int x, int y){
        int ax=this.alianza(x);
        int ay= this.alianza(y);
        int rx=this.rival(x);
        int ry=this.rival(y);
        System.out.println(x+" A:"+ax+" R:"+rx+"    "+y+" A:"+ay+" R:"+ry);
        if(ax!=ry &&ay!=rx){
            System.out.println("formada");
            if(rango[ax]<rango[ay]){
                A[ax]=ay;
            }else{
                A[ay]=ax;
                if(rango[ax]==rango[ay])
                    rango[ay]++;
            }
            return true;
        }
        return false;        
    }
    
    boolean formarRival(int x, int y){
        int ax=this.alianza(x);
        int ay= this.alianza(y);
        int rx=this.rival(x);
        int ry=this.rival(y);
        System.out.println(x+" A:"+ax+" R:"+rx+"    "+y+" A:"+ay+" R:"+ry);
        if(ax!=ry &&ay!=rx){
            System.out.println("formada");
            if(rango2[ax]<rango2[ay]){
                R[ax]=ay;
            }else{
                R[ay]=ax;
                if(rango2[ax]==rango2[ay])
                    rango2[ay]++;
            }
            return true;
        }
        return false;
    }
    void iterar(){
        for (int i = 0; i < this.A.length; i++) {
            if(A[i]==i){
                System.out.print(i+" ");
            }
        }
        System.out.println("");
        for (int i = 0; i < this.A.length; i++) {
            if(R[i]==-1){
                System.out.print(i+" ");
            }
        }
        System.out.println("");
    }
    

}
   class ConjuntosDisyuntos {
    int[] conjunto;
    int[] rango;

    public ConjuntosDisyuntos(int tam) {

      rango = new int[tam];
      conjunto = new int[tam];
        // Inicializa cada elemento en su propio conjunto
      for (int x = 0; x < tam; x++)
        conjunto[x] = x;
    }
      // Retorna el conjunto del elemento x
      // Profundidad promedio de la recursiÃ³n no mayor a 5
    int conjuntoDe(int x) {
    
      if (conjunto[x] == x)
        return x;
      return conjunto[x] = conjuntoDe(conjunto[x]);
    }
      // Une los dos conjuntos Cx, Cy a los que pertenecen x, y respectivamente.
      // El conjunto de mayor rango absorve al de menor rango.
      // Si tienen igual rango, cualquier conjunto puede absorver. En esta
      // implementacio'n escogemos Cy absorve Cx
    void unir(int x, int y) {
    
      int cx = conjuntoDe(x);
      int cy = conjuntoDe(y);
      
      if (rango[cx] > rango[cy])
        conjunto[cy] = cx;
      else {
        conjunto[cx] = cy;
        
        if (rango[cx] == rango[cy])
          rango[cy]++;
      }
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
               System.out.println("formar alianza  "+ i+" "+j);
                if(!s.formarAlianza(i, j))
                    System.out.println("ERROR");
                
            }if(cmd.equals("FormarRival")){
                i=sc.nextInt();
                j=sc.nextInt();
                 System.out.println("formar rivalidad  "+ i+" "+j);
                if(!s.formarRival(i, j))
                    System.out.println("ERROR");
                
            }if(cmd.equals("Alianza")){
                i=sc.nextInt();
                j=sc.nextInt();
                 System.out.println( i+" "+j+" son aliados?");
                 System.out.println(s.alianza(i, j)?"SI":"NO");
                
            }if(cmd.equals("Rival")){
                i=sc.nextInt();
                j=sc.nextInt();
                System.out.println( i+" "+j+" son rivales?");
                
                System.out.println(s.rival(i, j)?"SI":"NO");
            }
        }
        
    }
}
