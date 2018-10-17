package com.digitalcreative.warungskripsi.ModelData;

public class Transaksi {
    String id_konsultasi;
    String id_mahasiswa;
    String tanggal;
    String sesi;

    public Transaksi() {
    }

    public Transaksi(String id_konsultasi, String id_mahasiswa, String tanggal, String sesi) {
        this.id_konsultasi = id_konsultasi;
        this.id_mahasiswa = id_mahasiswa;
        this.tanggal = tanggal;
        this.sesi = sesi;
    }

    public String getId_konsultasi() {
        return id_konsultasi;
    }

    public void setId_konsultasi(String id_konsultasi) {
        this.id_konsultasi = id_konsultasi;
    }

    public String getId_mahasiswa() {
        return id_mahasiswa;
    }

    public void setId_mahasiswa(String id_mahasiswa) {
        this.id_mahasiswa = id_mahasiswa;
    }

    public String getTanggal() {
        return tanggal;
    }

    public void setTanggal(String tanggal) {
        this.tanggal = tanggal;
    }

    public String getSesi() {
        return sesi;
    }

    public void setSesi(String sesi) {
        this.sesi = sesi;
    }
}
