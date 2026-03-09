package id.my.piket.model;

public class LokasiPiket {
    private String idLokasi;
    private String namaLokasi;
    private String deskripsi;
    private String lantai;
    private int kapasitasOrang;

    // Constructor
    public LokasiPiket(String idLokasi, String namaLokasi, String deskripsi, String lantai, int kapasitasOrang) {
        this.idLokasi = idLokasi;
        this.namaLokasi = namaLokasi;
        this.deskripsi = deskripsi;
        this.lantai = lantai;
        this.kapasitasOrang = kapasitasOrang;
    }

    // Getter
    public String getIdLokasi() { return idLokasi; }
    public String getNamaLokasi() { return namaLokasi; }
    public String getDeskripsi() { return deskripsi; }
    public String getLantai() { return lantai; }
    public int getKapasitasOrang() { return kapasitasOrang; }

    // Setter
    public void setNamaLokasi(String namaLokasi) { this.namaLokasi = namaLokasi; }
    public void setDeskripsi(String deskripsi) { this.deskripsi = deskripsi; }
    public void setLantai(String lantai) { this.lantai = lantai; }
    public void setKapasitasOrang(int kapasitasOrang) { this.kapasitasOrang = kapasitasOrang; }

    @Override
    public String toString() {
        return String.format("| %-8s | %-20s | %-25s | %-9s | %d orang |",
                idLokasi, namaLokasi, deskripsi, lantai, kapasitasOrang);
    }
}