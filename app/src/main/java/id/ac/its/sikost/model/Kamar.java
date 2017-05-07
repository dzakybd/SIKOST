package id.ac.its.sikost.model;

import java.io.Serializable;

/**
 * Created by Ilham Aulia Majid on 07-May-17.
 */

public class Kamar implements Serializable {

    String nama;
    int kapasitas;
    int terisi;
    int biaya;
    String biayaSatuan;

    public Kamar(String nama, int kapasitas, int terisi, int biaya, String biayaSatuan) {
        this.nama = nama;
        this.kapasitas = kapasitas;
        this.terisi = terisi;
        this.biaya = biaya;
        this.biayaSatuan = biayaSatuan;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public int getKapasitas() {
        return kapasitas;
    }

    public void setKapasitas(int kapasitas) {
        this.kapasitas = kapasitas;
    }

    public int getTerisi() {
        return terisi;
    }

    public void setTerisi(int terisi) {
        this.terisi = terisi;
    }

    public int getBiaya() {
        return biaya;
    }

    public void setBiaya(int biaya) {
        this.biaya = biaya;
    }

    public String getBiayaSatuan() {
        return biayaSatuan;
    }

    public void setBiayaSatuan(String biayaSatuan) {
        this.biayaSatuan = biayaSatuan;
    }
}
