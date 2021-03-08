package p1;

import java.util.concurrent.atomic.AtomicInteger;

public class Ucionica {

    private static Ucionica instance;
    static volatile AtomicInteger brojOcena = new AtomicInteger(0);
    static volatile AtomicInteger zbirOcena = new AtomicInteger(0);

    private Student student_kod_asistenta;
    private Student student_kod_prof_1;
    private Student student_kod_prof_2;

    public static Ucionica getInstance(){
        if(instance == null){
            instance = new Ucionica();
        }
        return instance;
    }


    private Ucionica(){
        this.student_kod_asistenta = null;
        this.student_kod_prof_1 = null;
        this.student_kod_prof_2 = null;
    }

    public synchronized Student getStudent_kod_asistenta() {
        return student_kod_asistenta;
    }

    public synchronized void setStudent_kod_asistenta(Student student_kod_asistenta) {
        this.student_kod_asistenta = student_kod_asistenta;
    }

    public synchronized Student getStudent_kod_prof_1() {
        return student_kod_prof_1;
    }

    public synchronized Student getStudent_kod_prof_2() {
        return student_kod_prof_2;
    }

    public synchronized  void setStudent_kod_prof_1(Student student_kod_prof_1) {
        this.student_kod_prof_1 = student_kod_prof_1;
    }

    public synchronized void setStudent_kod_prof_2(Student student_kod_prof_2) {
        this.student_kod_prof_2 = student_kod_prof_2;
    }
}

