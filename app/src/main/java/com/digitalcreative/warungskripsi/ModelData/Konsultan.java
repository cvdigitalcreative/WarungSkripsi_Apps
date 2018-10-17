package com.digitalcreative.warungskripsi.ModelData;

public class Konsultan {
    private String id_konsultan;
    private InformasiKonsultan informasi;
    private JadwalKonsultan jadwal;

    public Konsultan() {
    }

    public Konsultan(String id_konsultan, InformasiKonsultan informasi, JadwalKonsultan jadwal) {
        this.id_konsultan = id_konsultan;
        this.informasi = informasi;
        this.jadwal = jadwal;
    }

    public String getId_konsultan() {
        return id_konsultan;
    }

    public void setId_konsultan(String id_konsultan) {
        this.id_konsultan = id_konsultan;
    }

    public InformasiKonsultan getInformasi() {
        return informasi;
    }

    public void setInformasi(InformasiKonsultan informasi) {
        this.informasi = informasi;
    }

    public JadwalKonsultan getJadwal() {
        return jadwal;
    }

    public void setJadwal(JadwalKonsultan jadwal) {
        this.jadwal = jadwal;
    }
}
