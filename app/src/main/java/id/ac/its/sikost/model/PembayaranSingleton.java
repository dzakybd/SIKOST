package id.ac.its.sikost.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Zaki on 19/05/2017.
 */

public class PembayaranSingleton {
    private static final PembayaranSingleton ourInstance = new PembayaranSingleton();

    public static PembayaranSingleton getInstance() {
        return ourInstance;
    }

    List<Pembayaran> pembayarans;

    private PembayaranSingleton() {
        pembayarans = new ArrayList<>();
        pembayarans.add(new Pembayaran("101", "Budi", "Admin","2 Mei 2017", 100000,1,2017));
    }

    public List<Pembayaran> getPembayarans() {
        return pembayarans;
    }

    public void addPembayaran(Pembayaran pembayaran) {
        pembayarans.add(pembayaran);
    }
}