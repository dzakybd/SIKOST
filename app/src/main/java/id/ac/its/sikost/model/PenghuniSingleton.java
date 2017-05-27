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
        penghunis.add(new Penghuni("Budi", "44512373242323", "Demak, 18 Maret 1997", "089650724560"));
        penghunis.add(new Penghuni("Andi", "74012623292323", "Solo, 20 April 1996", "085737486339"));
        penghunis.add(new Penghuni("Candra", "24112323212323", "Bali, 3 Juni 1995", "082174516112"));
    }

    public List<Penghuni> getPenghunis() {
        return penghunis;
    }

    public void addPenghuni(Penghuni penghuni) {
        penghunis.add(penghuni);
    }
}
