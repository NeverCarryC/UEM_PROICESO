package raton;

import java.util.Random;

public class Raton4 extends Thread {
    private String nombre;
    private static int tiempoAlimentarse;
    private Random random = new Random();

    public Raton4(String nombre){
        this.nombre = nombre;
    }

    public void comer(){
        try {
            System.out.println("Rl raton " + nombre + " ha comenzado a alimentarse");
            // generar un numero aleatorio entre 10 y 100(inclusive)
            int randonMilisegundos = 10 + random.nextInt(91);
            Thread.sleep(randonMilisegundos * 1000);
            tiempoAlimentarse += randonMilisegundos;

            System.out.println("El raton " + nombre + " ha terminado de alimentarse y ha tardado " + randonMilisegundos);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
    public void run(){
        this.comer();
    }

    public static void main(String[] args) {
        Raton4 a = new Raton4("a");
        Raton4 b = new Raton4("b");
        Raton4 c = new Raton4("c");
        Raton4 d = new Raton4("d");

        a.start();
        b.start();
        c.start();
        d.start();

        try {

            a.join();
            b.join();
            c.join();
            d.join();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        System.out.println("Tiempo total es: " + tiempoAlimentarse );
    }

}
