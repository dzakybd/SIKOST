package id.ac.its.sikost.model;

/**
 * Created by Zaki on 19/05/2017.
 */

public class Pengeluaran {
    public String judul,petugas;
    public int nominal,bulan,tahun;

    public Pengeluaran(String judul, String petugas, int nominal, int bulan, int tahun) {
        this.judul = judul;
        this.petugas = petugas;
        this.nominal = nominal;
        this.bulan = bulan;
        this.tahun = tahun;
    }
}
