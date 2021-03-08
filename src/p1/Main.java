package p1;

import sun.security.mscapi.CPublicKey;

import java.util.Scanner;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class Main {

    public static void main(String[] args){

        Scanner input = new Scanner( System.in );
        System.out.print( "Unesite broj studenata: " );
        int ukupno_studenata = input.nextInt();

        int sanse_profesor = 50;

        ScheduledExecutorService service_S = Executors.newScheduledThreadPool(ukupno_studenata);

        ExecutorService service_PA = Executors.newFixedThreadPool(2);
        Asistent asistent = new Asistent();
        Profesor profesor = new Profesor();
        //service_PA.submit(new Asistent());
        //service_PA.submit(new Profesor());

        Thread t_tred = new Thread(new Tajmer(service_S,service_PA));
        t_tred.start();

        for (int i = 0; i<ukupno_studenata;i++){
            long p = System.currentTimeMillis();
            long t = (long) (Math.random()*1000);
            boolean profesorPregleda = false;
            if (Math.random()*100 <= sanse_profesor)
                profesorPregleda = true;
            service_S.schedule(new Student(i,p,profesorPregleda,service_PA,asistent,profesor), t, TimeUnit.MILLISECONDS);
        }

        while(true){
            if (service_PA.isShutdown() || service_S.isShutdown()){
                break;
            }
        }


    }
}
