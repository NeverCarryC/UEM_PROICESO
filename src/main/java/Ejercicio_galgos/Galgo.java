package Ejercicio_galgos;

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class Galgo extends Thread{
    private String nombre;
    private int tiempoCarrera;
    private Random random = new Random();
    private static ArrayList<Galgo> orden = new ArrayList<Galgo>();

    public Galgo(String nombre) {
        this.tiempoCarrera = (random.nextInt(5) + 1 ) * 1000;
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
            Galgo g = orden.get(i);
            System.out.println(i + ": " + g.nombre  +"----Tiempo: "  + g.tiempoCarrera);
        }
    }

    public static void main(String[] args) {
        System.out.println("Cuantos galgos correrán?");
        Scanner sc = new Scanner(System.in);
        int num = sc.nextInt();
        sc.nextLine();
        ArrayList<Galgo> galgos = new ArrayList<Galgo>();

        for (int i=0; i<num; i++){
            System.out.println("Nombre de galgo " + i + "?");
            galgos.add(new Galgo(sc.next()));
        }

        for (Galgo g:galgos){
            g.start();
        }

        for (Galgo g:galgos){
            try {
                g.join();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        mostrarResultado();

    }

}

