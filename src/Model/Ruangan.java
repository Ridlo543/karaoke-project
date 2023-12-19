package Model;

public class Ruangan {
    private String nama;
    private int kapasitas;
    private int hargaPerJam;

    public Ruangan(String nama, int kapasitas, int hargaPerJam) {
        this.nama = nama;
        this.kapasitas = kapasitas;
        this.hargaPerJam = hargaPerJam;
    }

    public String getNama() {
        return nama;
    }

    public int getKapasitas() {
        return kapasitas;
    }

    public int getHargaPerJam() {
        return hargaPerJam;
    }
    
    

    @Override
    public String toString() {
        return nama + "\t" + kapasitas + " orang\t= " + hargaPerJam + "/jam";
    }
}

