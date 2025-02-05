package raton;

public class bRaton extends Thread{
    private String nombre;
    private int tiempoAlimentacion;

    public bRaton(String nombre, int tiempoAlimentacion){
        this.nombre = nombre;
        this.tiempoAlimentacion = tiempoAlimentacion;
    }

    public void run() {
        try {
            System.out.printf("El raton %s ha comenzado a alimentarse %n", nombre);
            Thread.sleep(tiempoAlimentacion*1000);
            System.out.printf("El raton %s ha terminado de alimentarse%n", nombre);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public static void main(String[] args) {
        bRaton a = new bRaton("a", 1);
        bRaton b = new bRaton("b", 3);
        bRaton c = new bRaton("c", 2);
        bRaton d = new bRaton("d", 1);
        bRaton e = new bRaton("e", 1);

        a.start();
        b.start();
        c.start();
        d.start();
        e.start();

    }
}
