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
public class Banco2 {

    public static void main(String[] args) throws FileNotFoundException {

        File f = new File("practica.txt");
        Scanner sc = new Scanner(f);
        while (!sc.nextLine().equals("Banco")) {
        }

        int N = sc.nextInt();
        double arreglo[] = new double[N];

        for (int i = 0; i < N; i++) {
            arreglo[i] = Double.parseDouble(sc.next());
        }

        //crear arbol
        SegTree tree = new SegTree(arreglo, 0, N -1);

        int Q = sc.nextInt();
        int i, j;
        double di, ri;
        String cmd;
        
        for (int k = 0; k < Q; k++) {
            cmd = sc.next();

            if (cmd.equals("depositar")) {

                i = sc.nextInt();
                di = Double.parseDouble(sc.next());
                tree.actualizar(i - 1, di);

            }
            if (cmd.equals("retirar")) {
                i = sc.nextInt();
                ri = Double.parseDouble(sc.next());
                tree.actualizar(i - 1, -ri);

            }
            if (cmd.equals("consultar")) {
                i = sc.nextInt();
                j = sc.nextInt();
                Data d = tree.query(i - 1, j - 1);
                double mn = d.mn;
                double mx = d.mx;
                double promedio = d.sum / (double) (j + 1 - i);
                //System.out.println(mn+" "+mx+" "+d.sum);
                System.out.println(redondear(mn) + " " + redondear(mx) + " " + redondear(promedio));
                // System.out.println(tree.toString());

            }

        }

    }

    static String redondear1(double x) {
        long y = (long) (x * 100.0);
        long ans = y / 10;
        if (y % 10 >= 5) {
            ans++;
        }
        return (ans / 10) + "." + (ans % 10);
    }

    static double redondear(double x) {
        long y = (long) (x * 100.0);
        int cast = (int) y;
        int ans = cast / 10;
        
        if(cast % 10 <= 4) {
            return ((double) (ans)) / 10; 
        }
        return (((double) (ans + 1)) / 10);
    }

}

class Data {

    double mn;
    double mx;
    double sum;

    public Data(double x) {
        mn = mx = sum = x;

    }

    void setAll(double x) {
        mn = mx = sum = x;
    }

    public void setMn(double mn) {
        this.mn = mn;
    }

    public void setMx(double mx) {
        this.mx = mx;
    }

    public void setSum(double sum) {
        this.sum = sum;
    }
}

class SegTree {

    int from;
    int to;
    SegTree izq;
    SegTree der;
    Data valor;
    
    public SegTree(double[] arreglo, int from, int to) {
        this.from = from;
        this.to = to;
        if (from == to) {
            valor = new Data(arreglo[from]);
            izq = null;
            der = null;
            return;
        }
        int mid = (from + to) / 2;
        izq = new SegTree(arreglo, from, mid);
        der = new SegTree(arreglo, mid + 1, to);
        valor = mergeData(izq.valor, der.valor);
    }
    
    public Data getValor() {
        return valor;
    }

    public void setValor(Data valor) {
        this.valor = valor;
    }

    Data mergeData(Data a, Data b) {
        Data rta = new Data(0);
        rta.setSum(a.sum + b.sum);
        rta.setMn(a.mn <= b.mn ? a.mn : b.mn);
        rta.setMx(a.mx >= b.mx ? a.mx : b.mx);
        return rta;
    }

    Data query(int f, int t) {
        if (from == f && to == t) {
            return getValor();
        } else if (t <= izq.to) {
            return izq.query(f, t);
        } else if (f >= der.from) {
            return der.query(f, t);
        } else {
            return mergeData(izq.query(f, izq.to), der.query(der.from, t));
        }

    }

    Data actualizar(int i, double x) {
        if (from == i && to == i) {
            valor.setAll(x + valor.mn);
            return valor;
        } else if (i <= izq.to) {
            return valor = mergeData(izq.actualizar(i, x), der.valor);

        } else {
            return valor = mergeData(izq.valor, der.actualizar(i, x));
        }
    }
}