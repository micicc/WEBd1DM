package p1;

import java.util.concurrent.Callable;
import java.util.concurrent.Semaphore;

public class Asistent implements Callable<Integer> {

    public static Semaphore semaphore = new Semaphore(1);


    public Integer call() throws Exception {

        Thread.sleep((long) Ucionica.getInstance().getStudent_kod_asistenta().getVreme_rada());
        int ocena = (int)(Math.random() * 6) + 5;
        Ucionica.brojOcena.getAndIncrement();
        Ucionica.zbirOcena.getAndAdd(ocena);

        return ocena;
    }


}
