/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author juan sebastian
 */

class BinaryIntHeap{
    int key[];
    int currentSize;

    public BinaryIntHeap(int N) {
        this.key = new int[N+1];
        currentSize=0;
    }
    
    void insert(int x){
        int hole= ++currentSize;
        key[0]=x;
        while(key[hole/2]<x){
            key[hole]=key[hole/2];
            hole/=2;
        }
        key[hole]=x;
        key[0]=0;
    }
    void percolateDown(int hole){
        int hijo;
        int tmp= key[hole];
        while(2*hole<=currentSize){
            hijo=2*hole;
            if(hijo!=currentSize &&key[hijo+1]>key[hijo])
                hijo++;
            if(key[hijo]>tmp)
                key[hole]=key[hijo];
            else 
                break;
            hole=hijo;
        }
        key[hole]=tmp;                
        
    }
    int deleteMin(){
        int myItem=key[1];
        key[1]=key[currentSize--];
        percolateDown(1);
        return myItem;
    }
    public String toString(){
         String sout=""; 
        for (int i = 1; i < currentSize+1; i++) {
         sout+=" "+key[i]; 
         //if((i+1)%2==0)sout+="  ";
        }
       
        return sout;
    }
    
    /*
        while(!sc.next().equals("Sobrecupos")){
        
        }
        
        int n=sc.nextInt();
        BinaryHeap estructura= new BinaryHeap(10*n);
        for (int i = 0; i < n; i++) {
        estructura.insert(sc.nextInt());
        }
        System.out.println(estructura.toString());
        estructura.insert(22);
        System.out.println(estructura.toString());
        estructura.insert(39);
        System.out.println(estructura.toString());
        for (int i = 0; i < 9; i++) {
        System.out.println(estructura.deleteMin()+",    "+estructura.toString());
        }
        */
}