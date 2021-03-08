package p1;

import java.util.concurrent.Callable;
import java.util.concurrent.Semaphore;

public class Asistent implements Callable<Integer> {

    public static Semaphore semaphore = new Semaphore(1);
    public static Student s;

    public Integer call() throws Exception {

        Thread.sleep((long) s.getVreme_rada());
        int ocena = (int) ((Math.random()*5)%10+5);
        return ocena;


    }

    public static void setS(Student s) {
        Asistent.s = s;
    }
}
