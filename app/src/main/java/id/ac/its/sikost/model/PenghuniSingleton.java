package id.ac.its.sikost.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Ilham Aulia Majid on 07-May-17.
 */

public class PenghuniSingleton {
    private static final PenghuniSingleton ourInstance = new PenghuniSingleton();

    public static PenghuniSingleton getInstance() {
        return ourInstance;
    }

    List<Penghuni> penghunis;

    private PenghuniSingleton() {
        penghunis = new ArrayList<>();
        penghunis.add(new Penghuni("Budi", "24112323212323", "Sby, 21 Mar 2010"));
        penghunis.add(new Penghuni("Budi", "24112323212323", "Sby, 21 Mar 2010"));
        penghunis.add(new Penghuni("Budi", "24112323212323", "Sby, 21 Mar 2010"));
    }

    public List<Penghuni> getPenghunis() {
        return penghunis;
    }

    public void addPenghuni(Penghuni penghuni) {
        penghunis.add(penghuni);
    }
}
