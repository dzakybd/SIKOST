package id.ac.its.sikost.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Ilham Aulia Majid on 20-May-17.
 */

class JenisPengeluaranSingleton {

    private static final JenisPengeluaranSingleton ourInstance = new JenisPengeluaranSingleton();

    static JenisPengeluaranSingleton getInstance() {
        return ourInstance;
    }

    List<String> jenisPengeluarans;

    private JenisPengeluaranSingleton() {
        jenisPengeluarans = new ArrayList<>();
        jenisPengeluarans.add("Biaya bulanan");
        jenisPengeluarans.add("Pemeliharaan");
        jenisPengeluarans.add("Peralatan");
        jenisPengeluarans.add("Reparasi");
    }

    public List<String> getJenisPengeluarans() {
        return jenisPengeluarans;
    }

    public void addJenisPengeluaran(String jenisPengeluaran){
        jenisPengeluarans.add(jenisPengeluaran);
    }
}
