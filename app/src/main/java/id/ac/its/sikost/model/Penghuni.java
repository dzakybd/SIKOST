package id.ac.its.sikost.model;

import java.io.Serializable;

/**
 * Created by Ilham Aulia Majid on 07-May-17.
 */

public class Penghuni implements Serializable {

    String nama;
    String ktp;
    String ttl;
    String nohp;

    public Penghuni(String nama, String ktp, String ttl, String nohp) {
        this.nama = nama;
        this.ktp = ktp;
        this.ttl = ttl;
        this.nohp=nohp;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getKtp() {
        return ktp;
    }

    public void setKtp(String ktp) {
        this.ktp = ktp;
    }

    public String getTtl() {
        return ttl;
    }

    public void setTtl(String ttl) {
        this.ttl = ttl;
    }

    public String getNohp() {
        return nohp;
    }

    public void setNohp(String nohp) {
        this.nohp = nohp;
    }
}
