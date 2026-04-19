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

    private static final String[] HARI_VALID   = {"Senin","Selasa","Rabu","Kamis","Jumat","Sabtu","Minggu"};
    private static final String[] STATUS_VALID = {"Terjadwal","Selesai","Absen"};

    public JadwalPiket(String idJadwal, String idAnggota, String namaAnggota,
                       String idLokasi, String namaLokasi, String hari,
                       String jamMulai, String jamSelesai, String status, String keterangan) {
        this.idJadwal = idJadwal; this.idAnggota = idAnggota; this.namaAnggota = namaAnggota;
        this.idLokasi = idLokasi; this.namaLokasi = namaLokasi;
        setHari(hari); setJamMulai(jamMulai); setJamSelesai(jamSelesai);
        setStatus(status); setKeterangan(keterangan);
    }

    public String getIdJadwal()    { return idJadwal; }
    public String getIdAnggota()   { return idAnggota; }
    public String getNamaAnggota() { return namaAnggota; }
    public String getIdLokasi()    { return idLokasi; }
    public String getNamaLokasi()  { return namaLokasi; }
    public String getHari()        { return hari; }
    public String getJamMulai()    { return jamMulai; }
    public String getJamSelesai()  { return jamSelesai; }
    public String getStatus()      { return status; }
    public String getKeterangan()  { return keterangan; }

    public void setIdAnggota(String v)   { this.idAnggota = v; }
    public void setNamaAnggota(String v) { this.namaAnggota = v; }
    public void setIdLokasi(String v)    { this.idLokasi = v; }
    public void setNamaLokasi(String v)  { this.namaLokasi = v; }

    public void setHari(String hari) {
        for (String h : HARI_VALID) { if (h.equalsIgnoreCase(hari)) { this.hari = h; return; } }
        this.hari = "Senin";
    }
    public void setJamMulai(String v)   { this.jamMulai   = (v != null && v.matches("\\d{2}:\\d{2}")) ? v : "07:00"; }
    public void setJamSelesai(String v) { this.jamSelesai  = (v != null && v.matches("\\d{2}:\\d{2}")) ? v : "08:00"; }
    public void setStatus(String status) {
        for (String s : STATUS_VALID) { if (s.equalsIgnoreCase(status)) { this.status = s; return; } }
        this.status = "Terjadwal";
    }
    public void setKeterangan(String v) { this.keterangan = (v == null || v.trim().isEmpty()) ? "-" : v.trim(); }

    @Override
    public String toString() {
        return String.format("| %-8s | %-18s | %-20s | %-7s | %s-%s | %-10s | %-12s |",
                idJadwal, namaAnggota, namaLokasi, hari, jamMulai, jamSelesai, status, keterangan);
    }
}