package Model;

import java.util.Date;

public class TransaksiModel {

    private Date tanggalTransaksi;
    private String username;
    private int durasi;
    private int totalHarga;

    public void clear() {
        this.tanggalTransaksi = null;
        this.username = "";
        this.durasi = 0;
        this.totalHarga = 0;
    }

    public Date getTanggalTransaksi() {
        return tanggalTransaksi;
    }

    public void setTanggalTransaksi(Date tanggalTransaksi) {
        this.tanggalTransaksi = tanggalTransaksi;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getDurasi() {
        return durasi;
    }

    public void setDurasi(int durasi) {
        this.durasi = durasi;
    }

    public int getTotalHarga() {
        return totalHarga;
    }

    public void setTotalHarga(int totalHarga) {
        this.totalHarga = totalHarga;
    }
}
