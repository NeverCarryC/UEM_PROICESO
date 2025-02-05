import java.util.Stack;

class ObjetoCompartido {
    private Stack<Integer> valores;
    private int tam_max;

    public ObjetoCompartido(int tam_max){
        valores=new Stack<Integer>();
        this.tam_max=tam_max;
    }

    public synchronized int get() {

        // Mientras no tengamos datos disponibles esperamos,
        // esto es, cuando la pila esté vacía.
        while (valores.empty()) {
            try {
                wait();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }

        // Cuando se despierte, notificamos a todos
        // los productores de la disponibilidad y
        // devolvemos el valor del tope de la pila,
        // mediante un pop.
        int valorRetorno=valores.pop();
        notifyAll();
        return valorRetorno;

    }

    public synchronized void set(int val) {

        // Mientras la pila esté llena, esperamos
        while (valores.size()>=tam_max) {
            try {
                wait();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
        // Cuando se despierte, añadimos el nuevo valor
        // a la pila y notificamos a todos
        // los consumidores de la disponibilidad.

        valores.push(val);
        notifyAll();
    }
}

class Productor implements Runnable {
    // Referencia a un objeto compartido
    ObjetoCompartido compartido;

    Productor(ObjetoCompartido compartido) {
        this.compartido = compartido;
    }

    @Override
    public void run() {
        for (int y = 0; y < 15; y++) {
            System.out.println("El productor produce: " + y);
            this.compartido.set(y);
            try {
                Thread.currentThread().sleep(40);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }
}

class Consumidor implements Runnable {
    // Referencia a un objeto compartido
    private ObjetoCompartido compartido;

    Consumidor(ObjetoCompartido compartido) {
        this.compartido = compartido;
    }

    @Override
    public void run() {
        for (int y = 0; y < 15; y++) {
            System.out.println("El consumidor consume: " + this.compartido.get());
            try {
                Thread.currentThread().sleep(100);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }
}

public class ModeloProductorConsumidorPila {
    public static void main(String[] args) {
        // Establecemos el tamaño de la pila a 3
        ObjetoCompartido compartido = new ObjetoCompartido(3);
        Thread p = new Thread(new Productor(compartido));
        Thread c = new Thread(new Consumidor(compartido));
        p.start();
        c.start();

    }
}
