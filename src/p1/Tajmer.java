package p1;

import java.util.concurrent.ExecutorService;

public class Tajmer implements Runnable {

    public static long pocetak = 0;
    private final ExecutorService service_S;
    private final ExecutorService service_PA;

    public Tajmer(ExecutorService s_S, ExecutorService s_PA) {
        this.service_S = s_S;
        this.service_PA = s_PA;
    }

    @Override
    public void run() {
        pocetak = System.currentTimeMillis();
        System.out.println("Početak odbrane");
        try {
            Thread.sleep(5000);
            service_S.shutdownNow();
            service_PA.shutdownNow();
            System.out.println("Kraj odbrane");


        }catch (InterruptedException e){
            e.printStackTrace();
        }
    }
}
