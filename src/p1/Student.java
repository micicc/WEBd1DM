package p1;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;

public class Student implements Runnable{

    private final String ime;
    private final long vreme_dolaska;
    private final double vreme_rada;
    private final boolean profesorpregleda;
    private final ExecutorService service_PA;
    private int ocena;
    private final Asistent asistent;
    private final Profesor profesor;

    public Student(int id, Long vreme_dolaska, boolean prof, ExecutorService service_PA, Asistent a, Profesor p) {
        this.ime = "Student" + id;
        this.vreme_dolaska = vreme_dolaska;
        this.vreme_rada = (500 + Math.random() * (500));
        this.profesorpregleda =prof;
        this.service_PA=service_PA;
        this.asistent=a;
        this.profesor=p;
    }

    @Override
    public void run() {
        System.out.println(ime + " aktivan" + profesorpregleda);
        if(!profesorpregleda) {

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
            try {
                Profesor.semaphore.acquire();
                Profesor.cyclicBarrier.await();
                if(Ucionica.getInstance().getStudent_kod_prof_2()==null){
                    Ucionica.getInstance().setStudent_kod_prof_2(this);
                }else {
                    Ucionica.getInstance().setStudent_kod_prof_1(this);
                }
                Ucionica.getInstance().setStudent_kod_prof_1(this);
            } catch (InterruptedException | BrokenBarrierException e) {

            }
            Future<Integer> future = service_PA.submit(profesor);

            try {
                this.ocena=future.get();
                if(Ucionica.getInstance().getStudent_kod_prof_2()==this){
                    Ucionica.getInstance().setStudent_kod_prof_2(null);
                }else {
                    Ucionica.getInstance().setStudent_kod_prof_1(null);
                }
                Profesor.semaphore.release();
                kraj();
            } catch (InterruptedException | ExecutionException e) {

            }
        }
    }

    private void kraj(){
        String s;
        if(this.profesorpregleda)
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
