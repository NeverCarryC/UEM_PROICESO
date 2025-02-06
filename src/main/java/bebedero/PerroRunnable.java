package bebedero;

import java.util.Random;
import java.util.concurrent.Semaphore;

public class PerroRunnable implements Runnable{
    private int id;
    private int aguaBebido;

    private static int aguaTotal = 0;
    final  private  static  Object ob = new Object();
    private static Semaphore capacidadBebedero = new Semaphore(3);

    public PerroRunnable(int id){
        this.id = id;
        Random random = new Random();
        this.aguaBebido = random.nextInt(9);
    }

    @Override
    public void run() {
        try {
            capacidadBebedero.acquire();
            System.out.println("Perro " + this.id + "entra en al bebedero.");

            Thread.sleep(this.aguaBebido);
            System.out.println("Perro bebiendo " + this.aguaBebido + " tiempo " + this.id + "sale del bebedero ");
            synchronized (ob){
                aguaTotal += this.aguaBebido;
            }

        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } finally {
            capacidadBebedero.release();
        }
        // System.out.println("");
    }

}
