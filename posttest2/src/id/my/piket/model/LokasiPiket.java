package id.my.piket.model;

/**
 * Class LokasiPiket - menerapkan konsep ENCAPSULATION
 *
 * ACCESS MODIFIER:
 * - private : semua properti
 * - public  : getter & setter dengan validasi
 */
public class LokasiPiket {

    // PRIVATE PROPERTIES
    private String idLokasi;
    private String namaLokasi;
    private String deskripsi;
    private String lantai;
    private int kapasitasOrang;

    // CONSTRUCTOR
    public LokasiPiket(String idLokasi, String namaLokasi, String deskripsi,
                       String lantai, int kapasitasOrang) {
        this.idLokasi = idLokasi;
        setNamaLokasi(namaLokasi);
        setDeskripsi(deskripsi);
        setLantai(lantai);
        setKapasitasOrang(kapasitasOrang);
    }

    // PUBLIC GETTER
    public String getIdLokasi()       { return idLokasi; }
    public String getNamaLokasi()     { return namaLokasi; }
    public String getDeskripsi()      { return deskripsi; }
    public String getLantai()         { return lantai; }
    public int getKapasitasOrang()    { return kapasitasOrang; }

    // PUBLIC SETTER WITH VALIDATION
    public void setNamaLokasi(String namaLokasi) {
        if (namaLokasi == null || namaLokasi.trim().isEmpty()) {
            System.out.println("[!] Nama lokasi tidak boleh kosong. Nama tidak diubah.");
        } else {
            this.namaLokasi = namaLokasi.trim();
        }
    }

    public void setDeskripsi(String deskripsi) {
        // Deskripsi boleh kosong, diberi nilai default
        this.deskripsi = (deskripsi == null || deskripsi.trim().isEmpty()) ? "-" : deskripsi.trim();
    }

    public void setLantai(String lantai) {
        if (lantai == null || lantai.trim().isEmpty()) {
            this.lantai = "Lantai 1"; // Default
        } else {
            this.lantai = lantai.trim();
        }
    }

    public void setKapasitasOrang(int kapasitasOrang) {
        // Validasi: kapasitas harus lebih dari 0
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