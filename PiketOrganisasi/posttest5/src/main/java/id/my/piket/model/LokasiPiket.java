package id.my.piket.model;

public class LokasiPiket {
    private String idLokasi;
    private String namaLokasi;
    private String deskripsi;
    private String lantai;
    private int kapasitasOrang;

    public LokasiPiket(String idLokasi, String namaLokasi, String deskripsi,
                       String lantai, int kapasitasOrang) {
        this.idLokasi = idLokasi;
        setNamaLokasi(namaLokasi);
        setDeskripsi(deskripsi);
        setLantai(lantai);
        setKapasitasOrang(kapasitasOrang);
    }

    public String getIdLokasi()    { return idLokasi; }
    public String getNamaLokasi()  { return namaLokasi; }
    public String getDeskripsi()   { return deskripsi; }
    public String getLantai()      { return lantai; }
    public int getKapasitasOrang() { return kapasitasOrang; }

    public void setNamaLokasi(String v) {
        if (v == null || v.trim().isEmpty()) System.out.println("[!] Nama lokasi tidak boleh kosong.");
        else this.namaLokasi = v.trim();
    }
    public void setDeskripsi(String v) { this.deskripsi = (v == null || v.trim().isEmpty()) ? "-" : v.trim(); }
    public void setLantai(String v)    { this.lantai    = (v == null || v.trim().isEmpty()) ? "Lantai 1" : v.trim(); }
    public void setKapasitasOrang(int v) {
        if (v <= 0) { System.out.println("[!] Kapasitas harus > 0. Diset 1."); this.kapasitasOrang = 1; }
        else this.kapasitasOrang = v;
    }

    @Override
    public String toString() {
        return String.format("| %-8s | %-20s | %-25s | %-9s | %d orang |",
                idLokasi, namaLokasi, deskripsi, lantai, kapasitasOrang);
    }
}