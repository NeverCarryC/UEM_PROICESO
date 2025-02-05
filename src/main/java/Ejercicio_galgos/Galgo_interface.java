package Ejercicio_galgos;

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class Galgo_interface implements Runnable{

    private String nombre;
    private int tiempoCarrera;
    private Random random = new Random();
    private static ArrayList<Galgo_interface> orden = new ArrayList<Galgo_interface>();

    public Galgo_interface(String nombre) {
        this.tiempoCarrera = (1+random.nextInt(5)) * 1000;
        this.nombre = nombre;

    }

    public void run(){
        System.out.println("galgo " + this.nombre + " empieza a correr.");
        try {
            Thread.sleep(this.tiempoCarrera);
            System.out.println("galgo " + this.nombre + " está terminado.");
            synchronized (orden){

                orden.add(this);
            }
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public static void mostrarResultado(){
        for (int i = 0; i < orden.size(); i++) {
            Galgo_interface g = orden.get(i);
            System.out.println(i + ": " + g.nombre  +"----Tiempo: "  + g.tiempoCarrera);
        }
    }

    public static void main(String[] args) {
        System.out.println("Cuantos galgos correrán?");
        Scanner sc = new Scanner(System.in);
        int num = sc.nextInt();

        ArrayList<Thread> threads = new ArrayList<Thread>();

        for (int i=0; i<num; i++){
            System.out.println("Nombre de galgo " + i + "?");
            Thread mythread = new Thread(new Galgo_interface(sc.next()));
            threads.add(mythread);
        }

        for (Thread t:threads){
            t.start();
        }

        for (Thread t:threads){
            try {
                t.join();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        mostrarResultado();


    }
}
