package id.ac.its.sikost.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Zaki on 19/05/2017.
 */

public class PengeluaranSingleton {
    private static final PengeluaranSingleton ourInstance = new PengeluaranSingleton();

    public static PengeluaranSingleton getInstance() {
        return ourInstance;
    }

    List<Pengeluaran> pengeluarans;

    private PengeluaranSingleton() {
        pengeluarans = new ArrayList<>();
        pengeluarans.add(new Pengeluaran("Cuci toilet", "Admin", "20-05-2017", 100000));
    }

    public List<Pengeluaran> getPengeluarans() {
        return pengeluarans;
    }

    public void addPengeluaran(Pengeluaran pengeluaran) {
        pengeluarans.add(pengeluaran);
    }
}
