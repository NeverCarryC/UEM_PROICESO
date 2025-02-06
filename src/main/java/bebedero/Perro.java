package bebedero;

import java.util.Random;
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

}
