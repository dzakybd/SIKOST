package id.ac.its.sikost.model;

/**
 * Created by Zaki on 19/05/2017.
 */

public class Pembayaran {
    public String kamar,pembayar,petugas;
    public int nominal,bulan,tahun;

    public Pembayaran(String kamar, String pembayar, String petugas, int nominal, int bulan, int tahun) {
        this.kamar = kamar;
        this.pembayar = pembayar;
        this.petugas = petugas;
        this.nominal = nominal;
        this.bulan = bulan;
        this.tahun = tahun;
    }
}
