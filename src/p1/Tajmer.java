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
            int brojOcena = Ucionica.brojOcena.get();
            int zbirOcena = Ucionica.zbirOcena.get();
            System.out.println("Broj ocena: " + brojOcena);
            double prosek = zbirOcena * 1.0 / brojOcena;
            System.out.println("Prosek ocena " + prosek);



        }catch (InterruptedException e){
            e.printStackTrace();
        }
    }
}
