import java.util.ArrayList;

class MiRunnable implements Runnable {

    String nombre; // Los objetos de tipo MiRunnable tendrán una propiedad nombre

    // Constructores: Inicializan la propiedad nombre
    MiRunnable() {
        this.nombre = "Anónimo";
    }

    MiRunnable(String nombre) {
        this.nombre = nombre;
    }

    void setThreadNewName(){
        Thread hiloActual = Thread.currentThread();
        String newName = String.format("Thread-%s",this.nombre);
        hiloActual.setName(newName);

    }


    @Override
    public void run() {
        // Este es el método que se ejeutará cuando
        // se invoque al método start del thread.

        try {
            // Tomamos la referencia al thread actual
            Thread hiloActual = Thread.currentThread();
            this.setThreadNewName();
            // E imprimimos información sobre su nombre y algunas propiedades
            System.out.println("Hola Mundo de los threads. Soy " + this.nombre + ":"
                    + "\n\tMi id de thread es " + hiloActual.getId()
                    + "\n\tMi nombre de thread es " + hiloActual.getName()
                    + "\n\tMi prioridad es " + hiloActual.getPriority()
                    + "\n\tMi estado es " + hiloActual.getState() + "\n");
            Thread.sleep(1000);
            System.out.println("Thread: "+ hiloActual.getName() + "está terminado.. Adios");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

public class CasoPractico1 {

    public static void main(String[] args) {
        ArrayList<Thread> MiThreads = new ArrayList<>();
        try {

            for(String nombre: args){
                MiRunnable runnable = new MiRunnable(nombre);
                MiThreads.add(new Thread(runnable));
            }

            for (Thread t:MiThreads){
                t.start();
            }
            for (Thread t:MiThreads){
                t.join();
            }
//            // Creamos algunos objetos de ejemplo
//            MiRunnable runnable1 = new MiRunnable("Jose");
//            MiRunnable runnable2 = new MiRunnable("Joan");
//            MiRunnable runnable3 = new MiRunnable("Vicente");
//
//            // Y los hilos correspondientes
//            Thread hilo1 = new Thread(runnable1);
//            Thread hilo2 = new Thread(runnable2);
//            Thread hilo3 = new Thread(runnable3);
//
//            // Lanzamos los hilos
//            hilo1.start();
//            hilo2.start();
//            hilo3.start();
//
//            // Y los juntamos con el principal cuando acaben cuando acaban
//            hilo1.join();
//            hilo2.join();
//            hilo3.join();

        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("Programa principal empieza a ejecutar...");
    }
}
