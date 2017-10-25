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
class SegmentTree{
    ST_Node raiz;
    double entrada[];
    //ST_Node sTree[];

    public SegmentTree(double[] entrada) {
        this.entrada = entrada;
        if(entrada.length==0){
            raiz=null;
        } else 
             raiz = buildNode(entrada, 0, entrada.length - 1);    
    }
    
     private ST_Node buildNode(double[] nums, int begin, int end) {
        if (begin == end) {
            return new ST_Node(begin, end, null, null,nums[begin],nums[begin],nums[begin]);
        } else {
            int mid = (begin + end) / 2 + 1;
            ST_Node left = buildNode(nums, begin, mid - 1);
            ST_Node right = buildNode(nums, mid, end);
            return new ST_Node(begin, end, left, right,left.sum + right.sum,this.findMax(left.Max,right.Max),this.findMin(left.min,right.min));
        }
    }
    
     private double update(ST_Node node, int i, int val) {
        if (node.isSingle()) {
            node.setSum(val);
        } else {
            ST_Node nodeToUpdate = node.left.contains(i) ? node.left : node.right;
            double withoutNode = node.sum - nodeToUpdate.sum;
            node.setSum(withoutNode + update(nodeToUpdate, i, val));
        }
        return node.sum;
    }
     public double sumRange(int i, int j) {
        if (raiz == null) {
            return 0;
        }
        return sumRange(raiz, i, j);
    }

    private double sumRange(ST_Node node, int i, int j) {
        if (node.outside(i, j)) {
            return 0;
        } else if (node.inside(i, j)) {
            return node.sum;
        } else {
            return sumRange(node.left, i, j) + sumRange(node.right, i, j);
        }
    }
    ST_Node limIzquierdo(int i,ST_Node x){
        if(i==x.begin)
            return x;
        
        if(i<x.end)
            return x.left;
        else 
            return x.right;
      
    }
    ST_Node limDerecho(int j,ST_Node x){
        if(j==x.end)
            return x;
        
        if(j<x.begin)
            return x.left;
        else 
            return x.right;
      
    }
    
     double findMin(int i,int j){
        ST_Node izquierdo= this.limIzquierdo(i, raiz);
        ST_Node derecho= this.limDerecho(j, raiz);
        return findMin(izquierdo.min,derecho.min);
         
     }
     double findMin(double x1, double x2){
         if(x1<x2)
             return x1;
         else
             return x2;
         
     }
     double findMax(double x1, double x2){
         if(x1>x2)
             return x1;
         else
             return x2;
         
     }
     
      
    void preorden(ST_Node x){
        if(x!= null){
            
            System.out.println(x.toString());
            preorden(x.left);
            
            preorden(x.right);
            System.out.println("  d   ");
                       
        }
              
    }
     void postorden(ST_Node x){
        if(x!=null){
            postorden(x.left);
            postorden(x.right);
            System.out.println(x.toString());
        }
    }
    
}
class ST_Node{
    int begin;
    int end;
    ST_Node left;
    ST_Node right;
    double sum;
    double min;
     double Max;
   
    

    public ST_Node(int begin, int end, ST_Node left, ST_Node right, double sum, double Max, double min ) {
        this.begin = begin;
        this.end = end;
        this.left = left;
        this.right = right;
        this.sum = sum;
        this.Max = Max;
        this.min = min;
        
        
    }
    
    public boolean isSingle() {
            return begin == end;
        }

        public boolean contains(int i) {
            return i >= begin && i <= end;
        }

        public boolean inside(int i, int j) {
            return i <= begin && j >= end;
        }

        public boolean outside(int i, int j) {
            return i > end || j < begin;
        }
        
        public void setSum(double sum) {
            this.sum = sum;
        }

    @Override
    public String toString() {
        return "ST_Node{" + "[" + begin+ ", " + end + "] ,sum="+ sum  + ", Max=" + Max + ", min="+ min  + '}';
    }
        
 
}


public class Banco {
    public static void main(String[] args) throws FileNotFoundException{
       
        File f= new File("practica.txt");
        Scanner sc = new Scanner(f);
        while(!sc.next().equals("Banco")){}
        
        int N=sc.nextInt();
        double arreglo[]= new double[N];
        
        
        for (int i = 0; i < N; i++) {
            arreglo[i]=Double.parseDouble(sc.next());
            System.out.print(arreglo[i]+" ");
        }
        
        
        //crear arbol
        System.out.println("");
        SegmentTree cuentas= new SegmentTree(arreglo);
        //cuentas.preorden(cuentas.raiz);
        System.out.println(" "+cuentas.limIzquierdo(3, cuentas.raiz).toString()+" ");
        System.out.println(cuentas.findMin(3, 5)+" ");
        System.out.println("");
       cuentas.postorden(cuentas.raiz);
       // System.out.println());
        int Q= sc.nextInt();
        int i,j,di,ri;
        String cmd;
        for (int k = 0; k < Q; k++) {
            cmd=sc.nextLine();
            if(cmd.equals("depositar")){
                i=sc.nextInt();
                di=sc.nextInt();
                
            }
            if(cmd.equals("retirar")){
                i=sc.nextInt();
                ri=sc.nextInt();
            }
            if(cmd.equals("consultar")){
                i=sc.nextInt();
                j=sc.nextInt();
            }
            
        }
    }
}
