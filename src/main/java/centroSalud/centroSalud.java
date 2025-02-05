package centroSalud;

import java.util.Random;
import java.util.Scanner;
import java.util.concurrent.Semaphore;

public class centroSalud extends Thread{
    private static Semaphore centroSalud = new Semaphore(2);
    protected int identificador = 0;
    private static Scanner sc = new Scanner(System.in);
    private Random random = new Random();
    public centroSalud(int id){
        this.identificador = id;
    }

    public void run(){
        try {
            centroSalud.acquire();
            System.out.println("Patiente " + this.identificador  + "entra al centro de salud...");
            int tiempo = (1 + random.nextInt(8)) * 200;
            Thread.sleep(tiempo);
            centroSalud.release();
            System.out.println("Patiente " + this.identificador  + "sale del centro de salud... " +  "Tiempo: " + tiempo);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public static void main(String[] args) {
        int pacientes = 0;
        System.out.println("Cuantos pacientes tienencita en el dia");
        int num = sc.nextInt();
        sc.nextLine();

        for (int i=0; i<num;i++){
            new centroSalud(i).start();
        }
    }
}
