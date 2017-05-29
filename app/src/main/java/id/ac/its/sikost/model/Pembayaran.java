package id.ac.its.sikost.model;

/**
 * Created by Zaki on 19/05/2017.
 */

public class Pembayaran {
    public String kamar, petugas, tanggal;
    public int nominal;

    public Pembayaran(String kamar, String petugas, String tanggal, int nominal) {
        this.tanggal = tanggal;
        this.kamar = kamar;
        this.petugas = petugas;
        this.nominal = nominal;
    }
}
