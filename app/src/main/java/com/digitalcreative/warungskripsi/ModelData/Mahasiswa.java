package com.digitalcreative.warungskripsi.ModelData;

import com.google.firebase.database.Exclude;

public class Mahasiswa {
    String email;
    String nama;
    String alamat;

    @Exclude
    String uid;

    public Mahasiswa() {
    }

    public Mahasiswa(String email, String nama, String alamat, String uid) {
        this.email = email;
        this.nama = nama;
        this.alamat = alamat;
        this.uid = uid;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getAlamat() {
        return alamat;
    }

    public void setAlamat(String alamat) {
        this.alamat = alamat;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }
}
