package uas;

import java.time.LocalDate;

public class OpenTrip {

    private int id;
    private String namaTrip;
    private String destinasi;
    private LocalDate tanggalBerangkat;
    private int kuotaPeserta;
    private double harga;
    private String deskripsi;

    public OpenTrip(int id, String namaTrip, String destinasi, LocalDate tanggalBerangkat, int kuotaPeserta, double harga, String deskripsi) {
        this.id = id;
        this.namaTrip = namaTrip;
        this.destinasi = destinasi;
        this.tanggalBerangkat = tanggalBerangkat;
        this.kuotaPeserta = kuotaPeserta;
        this.harga = harga;
        this.deskripsi = deskripsi;
    }

    public int getId() {
        return id;
    }

    public String getNamaTrip() {
        return namaTrip;
    }

    public void setNamaTrip(String namaTrip) {
        this.namaTrip = namaTrip;
    }

    public String getDestinasi() {
        return destinasi;
    }

    public void setDestinasi(String destinasi) {
        this.destinasi = destinasi;
    }

    public LocalDate getTanggalBerangkat() {
        return tanggalBerangkat;
    }

    public void setTanggalBerangkat(LocalDate tanggalBerangkat) {
        this.tanggalBerangkat = tanggalBerangkat;
    }

    public int getKuotaPeserta() {
        return kuotaPeserta;
    }

    public void setKuotaPeserta(int kuotaPeserta) {
        this.kuotaPeserta = kuotaPeserta;
    }

    public double getHarga() {
        return harga;
    }

    public void setHarga(double harga) {
        this.harga = harga;
    }

    public String getDeskripsi() {
        return deskripsi;
    }

    public void setDeskripsi(String deskripsi) {
        this.deskripsi = deskripsi;
    }
}
