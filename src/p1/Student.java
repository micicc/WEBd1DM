package p1;

import java.util.Random;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;

public class Student implements Runnable{

    private final String ime;
    private final long vreme_dolaska;
    private final double vreme_rada;
    private final boolean profesor;
    private final ExecutorService service_PA;
    private int ocena;
    private final Asistent asistent;

    public Student(int id, Long vreme_dolaska, boolean prof, ExecutorService service_PA, Asistent a) {
        this.ime = "Student" + id;
        this.vreme_dolaska = vreme_dolaska;
        this.vreme_rada = (500 + Math.random() * (500));
        this.profesor=prof;
        this.service_PA=service_PA;
        this.asistent=a;

    }


    @Override
    public void run() {
        System.out.println(ime + " aktivan" + profesor);
        if(!profesor) {

            try {
                Asistent.semaphore.acquire();
                Ucionica.getInstance().setStudent_kod_asistenta(this);
            } catch (InterruptedException e) {

            }
            Future<Integer> future = service_PA.submit(asistent);

            try {
                this.ocena=future.get();
                Asistent.semaphore.release();
                kraj();
            } catch (InterruptedException | ExecutionException e) {

            }
        }else {

        }


    }

    private void kraj(){
        String s;
        if(this.profesor)
            s="profesor";
        else
            s="asistent";
        System.out.println("Thread: "+this.ime+" Arrival: "+ this.vreme_dolaska+" Prof: "+s
                +" TTC: " + vreme_rada+":"+Tajmer.pocetak+" Score: "+this.ocena);
    }

    public synchronized double getVreme_rada() {
        return vreme_rada;
    }
}
