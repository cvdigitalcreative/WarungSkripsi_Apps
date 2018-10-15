package com.digitalcreative.warungskripsi.ModelData;

public class Pembimbing_Model {
    String namaPembimbing;
    String statusPembimbing;
    String tanggal;
    int subbidang;
    int bagian;

    public int getSubbidang() {
        return subbidang;
    }

    public void setSubbidang(int subbidang) {
        this.subbidang = subbidang;
    }

    public int getBagian() {
        return bagian;
    }

    public void setBagian(int bagian) {
        this.bagian = bagian;
    }

    public String getTanggal() {
        return tanggal;
    }

    public void setTanggal(String tanggal) {
        this.tanggal = tanggal;
    }

    public String getNamaPembimbing() {
        return namaPembimbing;
    }

    public void setNamaPembimbing(String namaPembimbing) {
        this.namaPembimbing = namaPembimbing;
    }

    public String getStatusPembimbing() {
        return statusPembimbing;
    }

    public void setStatusPembimbing(String statusPembimbing) {
        this.statusPembimbing = statusPembimbing;
    }
}
