/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package practica;

import java.util.*;
import java.io.*;

/**
 *
 * @author juan sebastian
 */
class BinaryHeap< T extends Comparable< ? super T>> {

    int DEFAULT_CAPACITY = 10;
    T key[];
    int currentSize;

    public BinaryHeap(int N) {
        key = (T[]) new Comparable[N + 1];
        currentSize = 0;
    }

    public BinaryHeap() {
        key = (T[]) new Comparable[DEFAULT_CAPACITY + 1];
        currentSize = 0;
    }

    void insert(T x) {
        int hole = ++currentSize;
        //key[0]=x;
        for (key[0] = x; x.compareTo(key[hole / 2]) < 0; hole /= 2) {
            key[hole] = key[hole / 2];
        }
        key[hole] = x;
        key[0] = null;
    }

    T deleteMin() {
        if (currentSize < 1) {
            return null;
        } else {
            T myItem = key[1];
            key[1] = key[currentSize--];
            percolateDown(1);

            return myItem;
        }
    }

    void percolateDown(int hole) {
        int hijo;
        T tmp = key[hole];
        for (; 2 * hole <= currentSize; hole = hijo) {
            hijo = 2 * hole;
            if (hijo != currentSize && key[hijo + 1].compareTo(key[hijo]) < 0) {
                hijo++;
            }
            if (key[hijo].compareTo(tmp) < 0) {
                key[hole] = key[hijo];
            } else {
                break;
            }

        }
        key[hole] = tmp;
    }

}

class Solicitud implements Comparable {

    long documento;
    int codigo;
    String motivo;
    int creditos;
    double promedio;
    int turno;

    public Solicitud(Long documento, Integer codigo, String motivo, int creditos, Double promedio, int turno) {
        this.documento = documento;
        this.codigo = codigo;
        this.motivo = motivo;
        this.creditos = creditos;
        this.promedio = promedio;
        this.turno = turno;
    }

    @Override

    public int compareTo(Object o) {
        Solicitud s = (Solicitud) o;
        if (s.promedio == this.promedio) {
            if (s.creditos == this.creditos) {
                if( this.turno == s.turno ) {
                    return 0;
                } else if (this.turno > s.turno) {
                    return 1;
                } else {
                    return -1;
                }
            } else {
                if (this.creditos > s.creditos) {
                    return -1;
                } else {
                    return 1;
                }
            }
        } else {
            if (this.promedio > s.promedio) {
                return -1;
            } else {
                return 1;
            }
        }
    }

    @Override
    public String toString() {
        return documento + "," + motivo + "," + codigo;
    }

}

class SistemaDeSobrecupo<Solicitud> extends BinaryHeap {

    public SistemaDeSobrecupo() {
        super(200000);
    }

    void insert(Solicitud x) {
        super.insert((Comparable) x); //To change body of generated methods, choose Tools | Templates.
        //System.out.println("insercion exitosa");
    }

    public String toString() {
        String sout = "";
        for (int i = 1; i < currentSize + 1; i++) {
            sout += " " + key[i].toString();

        }

        return sout;
    }

    public String atender() {
        Solicitud x = (Solicitud) this.deleteMin();
        if (x == null) {
            return "Todos tienen cupo";
        } else {
            return x.toString();
        }
    }

}

public class Sobrecupos {

    public static void main(String[] args) throws FileNotFoundException {

        File f = new File("practica.txt");
        Scanner sc = new Scanner(f);
        while (!sc.next().equals("Sobrecupos")) {
        }

        int turno = 0;
        SistemaDeSobrecupo sdSobrecupo = new SistemaDeSobrecupo();
        Solicitud solicitud;

        while (sc.hasNextLine()) {
            String cmd = sc.nextLine();
            //System.out.println(cmd);

            if (cmd.equals("solicitud")) {
                Long documento = Long.parseLong(sc.nextLine());
                Integer codigo = Integer.parseInt(sc.nextLine());
                String motivo = sc.nextLine();
                int creditos = sc.nextInt();
                sc.nextLine();
                Double promedio = Double.parseDouble(sc.nextLine());
                Double PAPPI = promedio * 1000.0 / creditos;
                turno++;

                // Crear solicitud y agregarla al Heap
                solicitud = new Solicitud(documento, codigo, motivo, creditos, PAPPI, turno);
                sdSobrecupo.insert(solicitud);

            } else if (cmd.equals("atender")) {

                System.out.println(sdSobrecupo.atender());
            }
        }

    }

}
