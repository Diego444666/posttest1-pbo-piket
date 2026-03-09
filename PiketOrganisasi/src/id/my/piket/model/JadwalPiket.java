package id.my.piket.model;

public class JadwalPiket {
    private String idJadwal;
    private String idAnggota;
    private String namaAnggota;
    private String idLokasi;
    private String namaLokasi;
    private String hari;
    private String jamMulai;
    private String jamSelesai;
    private String status;
    private String keterangan;

    // Constructor
    public JadwalPiket(String idJadwal, String idAnggota, String namaAnggota,
                       String idLokasi, String namaLokasi, String hari,
                       String jamMulai, String jamSelesai, String status, String keterangan) {
        this.idJadwal = idJadwal;
        this.idAnggota = idAnggota;
        this.namaAnggota = namaAnggota;
        this.idLokasi = idLokasi;
        this.namaLokasi = namaLokasi;
        this.hari = hari;
        this.jamMulai = jamMulai;
        this.jamSelesai = jamSelesai;
        this.status = status;
        this.keterangan = keterangan;
    }

    // Getter
    public String getIdJadwal() { return idJadwal; }
    public String getIdAnggota() { return idAnggota; }
    public String getNamaAnggota() { return namaAnggota; }
    public String getIdLokasi() { return idLokasi; }
    public String getNamaLokasi() { return namaLokasi; }
    public String getHari() { return hari; }
    public String getJamMulai() { return jamMulai; }
    public String getJamSelesai() { return jamSelesai; }
    public String getStatus() { return status; }
    public String getKeterangan() { return keterangan; }

    // Setter
    public void setIdAnggota(String idAnggota) { this.idAnggota = idAnggota; }
    public void setNamaAnggota(String namaAnggota) { this.namaAnggota = namaAnggota; }
    public void setIdLokasi(String idLokasi) { this.idLokasi = idLokasi; }
    public void setNamaLokasi(String namaLokasi) { this.namaLokasi = namaLokasi; }
    public void setHari(String hari) { this.hari = hari; }
    public void setJamMulai(String jamMulai) { this.jamMulai = jamMulai; }
    public void setJamSelesai(String jamSelesai) { this.jamSelesai = jamSelesai; }
    public void setStatus(String status) { this.status = status; }
    public void setKeterangan(String keterangan) { this.keterangan = keterangan; }

    @Override
    public String toString() {
        return String.format("| %-8s | %-18s | %-20s | %-7s | %s-%s | %-10s | %-12s |",
                idJadwal, namaAnggota, namaLokasi, hari, jamMulai, jamSelesai, status, keterangan);
    }
}