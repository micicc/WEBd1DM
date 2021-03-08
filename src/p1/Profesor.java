package p1;

import java.util.concurrent.Callable;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.Semaphore;

public class Profesor implements Callable<Integer> {

    public static Semaphore semaphore = new Semaphore(2);
    public static CyclicBarrier cyclicBarrier = new CyclicBarrier(2);


    @Override
    public Integer call() throws Exception {
        if(Ucionica.getInstance().getStudent_kod_prof_1().getVreme_rada() >= Ucionica.getInstance().getStudent_kod_prof_2().getVreme_rada())
            Thread.sleep((long) Ucionica.getInstance().getStudent_kod_prof_1().getVreme_rada());
        else
            Thread.sleep((long) Ucionica.getInstance().getStudent_kod_prof_2().getVreme_rada());

        Thread.sleep(500);
        int ocena = (int)(Math.random() * 6) + 5;
        Ucionica.brojOcena.getAndIncrement();
        Ucionica.zbirOcena.getAndAdd(ocena);

        return ocena;
    }
}
