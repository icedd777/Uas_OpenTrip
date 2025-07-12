package uas;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class OpenTripController {

    private List<OpenTrip> daftarTrip = new ArrayList<>();
    private int nextId = 1;

    public void tambahTrip(String namaTrip, String destinasi, LocalDate tanggalBerangkat, int kuotaPeserta, double harga, String deskripsi) {
        OpenTrip trip = new OpenTrip(nextId++, namaTrip, destinasi, tanggalBerangkat, kuotaPeserta, harga, deskripsi);
        daftarTrip.add(trip);
    }

    public List<OpenTrip> getAll() {
        return daftarTrip;
    }

    public OpenTrip cariById(int id) {
        return daftarTrip.stream()
                         .filter(trip -> trip.getId() == id)
                         .findFirst()
                         .orElse(null);
    }

    public boolean updateTrip(int id, String namaTripBaru, String destinasiBaru, LocalDate tanggalBaru, int kuotaBaru, double hargaBaru, String deskripsiBaru) {
        OpenTrip trip = cariById(id);
        if (trip != null) {
            trip.setNamaTrip(namaTripBaru);
            trip.setDestinasi(destinasiBaru);
            trip.setTanggalBerangkat(tanggalBaru);
            trip.setKuotaPeserta(kuotaBaru);
            trip.setHarga(hargaBaru);
            trip.setDeskripsi(deskripsiBaru);
            return true;
        }
        return false;
    }

    public boolean hapusTrip(int id) {
        OpenTrip trip = cariById(id);
        return trip != null && daftarTrip.remove(trip);
    }
}
