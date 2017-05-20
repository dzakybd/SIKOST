package id.ac.its.sikost.model;

/**
 * Created by Zaki on 19/05/2017.
 */

public class Pengeluaran {
    public String judul,petugas,tanggal, jenis;
    public int nominal;

    public Pengeluaran(String judul, String petugas, String tanggal, int nominal, String jenis) {
        this.judul = judul;
        this.petugas = petugas;
        this.nominal = nominal;
        this.tanggal = tanggal;
        this.jenis = jenis;
    }
}
