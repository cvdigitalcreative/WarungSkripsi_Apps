package com.digitalcreative.warungskripsi.ModelData;

public class InformasiKonsultan {
    String nama;
    String nama_bank;
    String no_hp;
    int no_rekening;
    String subbidang;
    String bagian;

    public InformasiKonsultan() {
    }

    public InformasiKonsultan(String nama, String nama_bank, String no_hp, int no_rekening, String subbidang, String bagian) {
        this.nama = nama;
        this.nama_bank = nama_bank;
        this.no_hp = no_hp;
        this.no_rekening = no_rekening;
        this.subbidang = subbidang;
        this.bagian = bagian;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getNama_bank() {
        return nama_bank;
    }

    public void setNama_bank(String nama_bank) {
        this.nama_bank = nama_bank;
    }

    public String getNo_hp() {
        return no_hp;
    }

    public void setNo_hp(String no_hp) {
        this.no_hp = no_hp;
    }

    public int getNo_rekening() {
        return no_rekening;
    }

    public void setNo_rekening(int no_rekening) {
        this.no_rekening = no_rekening;
    }

    public String getSubbidang() {
        return subbidang;
    }

    public void setSubbidang(String subbidang) {
        this.subbidang = subbidang;
    }

    public String getBagian() {
        return bagian;
    }

    public void setBagian(String bagian) {
        this.bagian = bagian;
    }
}
