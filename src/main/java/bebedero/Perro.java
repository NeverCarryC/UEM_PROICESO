package bebedero;

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;
import java.util.concurrent.Semaphore;

public class Perro extends Thread{
    private int id;
    private int aguaBebido;
    public static int aguaTotal = 0;
    private static Semaphore capacidadBebedero = new Semaphore(3);
    private static final Object lock = new Object();

    public Perro(int id){
        this.id = id;
        Random random = new Random();
        this.aguaBebido = random.nextInt(5000) + 1000;
    }

    public void run(){

        try {
            capacidadBebedero.acquire();
            System.out.println("Perro " + this.id + " entra en bebedero");
            System.out.println("Perro "+ this.id + " Esta bebiendo...");
            Thread.sleep(this.aguaBebido);

            System.out.println("Perro "+ this.id + " sale del bebedero y bebe " + this.aguaBebido + "agua");

            synchronized (lock){
                aguaTotal += this.aguaBebido;
            }

        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } finally {
            capacidadBebedero.release();
        }
    }

    public static void main(String[] args) {
        System.out.println("Cuantos perros?");
        Scanner sc = new Scanner(System.in);
        int num = sc.nextInt();
        sc.nextLine();


        ArrayList<Perro> perros = new ArrayList<Perro>();
        for(int i=0;i<num;i++){
            perros.add(new Perro(i));
        }

        for (Perro p :perros){
            p.start();
        }
        for (Perro p :perros){
            try {
                p.join();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }

        System.out.println("Agura toal: " + aguaTotal);


    }

}
