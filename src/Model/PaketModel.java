package Model;

import java.util.ArrayList;
import java.util.List;

public class PaketModel {
    private List<Ruangan> ruanganList;

    public List<Ruangan> getRuanganList() {
        return ruanganList;
    }

    public PaketModel() {
        // Inisialisasi ruanganList dengan daftar ruangan
        ruanganList = new ArrayList<>();
        ruanganList.add(new Ruangan("Small", 5, 50000));
        ruanganList.add(new Ruangan("Medium", 8, 60000));
        ruanganList.add(new Ruangan("Large", 10, 80000));
        ruanganList.add(new Ruangan("Deluxe", 14, 100000));
        ruanganList.add(new Ruangan("VVIP", 14, 200000));
    }

    // Metode untuk menambahkan ruangan ke dalam PaketModel
    public void addRuangan(String nama, int kapasitas, int hargaPerJam) {
        Ruangan ruangan = new Ruangan(nama, kapasitas, hargaPerJam);
        ruanganList.add(ruangan);
    }

    // Metode untuk mendapatkan ruangan berdasarkan indeks
    public Ruangan getRuangan(int index) {
        if (index >= 0 && index < ruanganList.size()) {
            return ruanganList.get(index);
        } else {
            return null;
        }
    }

    
}
