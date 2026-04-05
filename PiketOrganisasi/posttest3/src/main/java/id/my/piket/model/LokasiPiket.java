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

    public String getIdLokasi()       { return idLokasi; }
    public String getNamaLokasi()     { return namaLokasi; }
    public String getDeskripsi()      { return deskripsi; }
    public String getLantai()         { return lantai; }
    public int getKapasitasOrang()    { return kapasitasOrang; }

    public void setNamaLokasi(String namaLokasi) {
        if (namaLokasi == null || namaLokasi.trim().isEmpty()) {
            System.out.println("[!] Nama lokasi tidak boleh kosong.");
        } else {
            this.namaLokasi = namaLokasi.trim();
        }
    }

    public void setDeskripsi(String deskripsi) {
        this.deskripsi = (deskripsi == null || deskripsi.trim().isEmpty()) ? "-" : deskripsi.trim();
    }

    public void setLantai(String lantai) {
        this.lantai = (lantai == null || lantai.trim().isEmpty()) ? "Lantai 1" : lantai.trim();
    }

    public void setKapasitasOrang(int kapasitasOrang) {
        if (kapasitasOrang <= 0) {
            System.out.println("[!] Kapasitas harus lebih dari 0. Diset ke 1.");
            this.kapasitasOrang = 1;
        } else {
            this.kapasitasOrang = kapasitasOrang;
        }
    }

    @Override
    public String toString() {
        return String.format("| %-8s | %-20s | %-25s | %-9s | %d orang |",
                idLokasi, namaLokasi, deskripsi, lantai, kapasitasOrang);
    }
}