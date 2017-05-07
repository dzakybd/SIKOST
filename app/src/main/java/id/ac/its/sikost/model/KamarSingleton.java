package id.ac.its.sikost.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Ilham Aulia Majid on 07-May-17.
 */

public class KamarSingleton {
    private static final KamarSingleton ourInstance = new KamarSingleton();

    public static KamarSingleton getInstance() {
        return ourInstance;
    }

    List<Kamar> kamars;

    private KamarSingleton() {
        kamars = new ArrayList<>();
        kamars.add(new Kamar("101",2,1,500000,"bulan"));
        kamars.add(new Kamar("102",2,2,500000,"bulan"));
        kamars.add(new Kamar("103",1,0,350000,"bulan"));
        kamars.add(new Kamar("104",1,0,350000,"bulan"));
    }

    public List<Kamar> getKamars() {
        return kamars;
    }

    public void addKamar(Kamar kamar){
        kamars.add(kamar);
    }
}
