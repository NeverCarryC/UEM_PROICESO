package simplied;

import java.util.Random;
import java.util.concurrent.Semaphore;

public class Tren extends Thread{
    private int id;
    boolean escarga;


    private static final int MAX_TRENS = 3;
    private static final int NUM_VIAS = 2;
    private static final Semaphore estacion = new Semaphore(MAX_TRENS);
    private static final Semaphore vias = new Semaphore(NUM_VIAS);
    Random random = new Random();


    private static int cargoEntradaContador = 0;
    private static int cargoSalidaContador = 0;
    private static int pasajeroEntradaContador = 0;
    private static int pasajeroSalidaContador = 0;
    public void run(){
        try {
            estacion.acquire();
            System.out.println("火车" + this.getTrenId() + "进站");
            if(this.isEscarga()){
                cargoEntradaContador++;
            }else {
                pasajeroEntradaContador ++;
            }
            // 选择站台
            vias.acquire();
            int via = random.nextInt(NUM_VIAS); // 随机选择一个站台
            System.out.println("火车" + this.getTrenId() + "使用站台" + via);

            // 模拟在站台停留
            Thread.sleep(1000);

            // 出站
            vias.release();
            estacion.release();
            if(this.isEscarga()){
                cargoSalidaContador++;
            }else {
                pasajeroSalidaContador += 1;
            }
            System.out.println("火车" + this.getTrenId() + "出站");
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }


    }

    public static void main(String[] args) {
        // 也可以用for循环，询问用户数量，然后用随机值创建不同类型的tren
        // 示例代码
//        for (int i = 1; i <= clientes; i++) {
//            Thread hilo = new supermecadoJoin(i);
//            hilos.add(hilo);
//            hilo.start(); // Iniciar el hilo
//        }
//
//        // Opcional: Esperar a que todos los hilos terminen
//        for (Thread hilo : hilos) {
//            try {
//                hilo.join();
//            } catch (InterruptedException e) {
//                System.out.println("Error al esperar al hilo.");
//            }
//        }
        Tren tren1 = new Tren(1,true);
        Tren tren2 = new Tren(2,true);
        Tren tren3 = new Tren(3,false);
        Tren tren4 = new Tren(4,true);
        Tren tren5 = new Tren(5,false);

        tren1.start();
        tren2.start();
        tren3.start();
        tren4.start();
        tren5.start();

        try {
            tren1.join();
            tren2.join();
            tren3.join();
            tren4.join();
            tren5.join();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        System.out.println("cargoEntradaContador: " + getCargoEntradaContador());
        System.out.println("cargoSalidaContador: " + getCargoSalidaContador());
        System.out.println("pasajeroEntradaContador: " + getPasajeroEntradaContador());
        System.out.println("pasajeroSalidaContador: " + getPasajeroSalidaContador());

    }

    public Tren(int id, boolean escarga) {
        this.id = id;
        this.escarga = escarga;
    }


    public int getTrenId() {
        return id;
    }

    public boolean isEscarga() {
        return escarga;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setEscarga(boolean escarga) {
        this.escarga = escarga;
    }
    public static int getCargoEntradaContador() {
        return cargoEntradaContador;
    }

    public static int getCargoSalidaContador() {
        return cargoSalidaContador;
    }

    public static int getPasajeroEntradaContador() {
        return pasajeroEntradaContador;
    }

    public static int getPasajeroSalidaContador() {
        return pasajeroSalidaContador;
    }
}
