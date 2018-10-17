package com.digitalcreative.warungskripsi.ModelData;

public class JadwalKonsultan {
    private int jadwal_akhir;
    private int jadwal_awal;
    private String tanggal;

    public JadwalKonsultan() {
    }

    public JadwalKonsultan(int jadwal_akhir, int jadwal_awal, String tanggal) {
        this.jadwal_akhir = jadwal_akhir;
        this.jadwal_awal = jadwal_awal;
        this.tanggal = tanggal;
    }

    public int getJadwal_akhir() {
        return jadwal_akhir;
    }

    public void setJadwal_akhir(int jadwal_akhir) {
        this.jadwal_akhir = jadwal_akhir;
    }

    public int getJadwal_awal() {
        return jadwal_awal;
    }

    public void setJadwal_awal(int jadwal_awal) {
        this.jadwal_awal = jadwal_awal;
    }

    public String getTanggal() {
        return tanggal;
    }

    public void setTanggal(String tanggal) {
        this.tanggal = tanggal;
    }
}
