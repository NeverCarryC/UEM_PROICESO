import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.Semaphore;

// PROCESO:
//1. 若干辆tren，进入estacion
//2. 进入estacion的tren，再选择via离开
public class EstacionGestor extends Thread{

    private static final int MAX_TRENS = 8;
    private static final int NUM_VIAS = 6;
    private static Semaphore  estacion = new Semaphore(MAX_TRENS);
    private static Semaphore vias = new Semaphore(NUM_VIAS);

    Random random = new Random();
    private ArrayList<Tren> trens = new ArrayList<Tren>();

    EstacionGestor(int num_tren){
        for (int i = 0; i < num_tren; i++) {
            Tren tren = new Tren(i, random.nextBoolean());
            this.trens.add(tren);
        }
    }

    public void run(){
        Random random = new Random();
        while(!trens.isEmpty()){
            Tren tren = this.trens.remove(0);
            try {
                estacion.acquire();
                System.out.println("火车" + tren.getId() + "进站");

                // 选择站台
                vias.acquire();
                int via = random.nextInt(NUM_VIAS); // 随机选择一个站台
                System.out.println("火车" + tren.getId() + "使用站台" + via);

                // 模拟在站台停留
                Thread.sleep(1000);

                // 出站
                vias.release();
                estacion.release();
                System.out.println("火车" + tren.getId() + "出站");
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public static void main(String[] args) {
        EstacionGestor eg = new EstacionGestor(5);

        System.out.println("wait");
        eg.start();
    }
}
