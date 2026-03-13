package id.my.piket.model;

/**
 * Class JadwalPiket - menerapkan konsep ENCAPSULATION
 *
 * ACCESS MODIFIER:
 * - private : semua properti
 * - public  : getter & setter dengan validasi
 */
public class JadwalPiket {

    // PRIVATE PROPERTIES
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

    private static final String[] HARI_VALID = {
            "Senin", "Selasa", "Rabu", "Kamis", "Jumat", "Sabtu", "Minggu"
    };
    private static final String[] STATUS_VALID = {
            "Terjadwal", "Selesai", "Absen"
    };

    // CONSTRUCTOR
    public JadwalPiket(String idJadwal, String idAnggota, String namaAnggota,
                       String idLokasi, String namaLokasi, String hari,
                       String jamMulai, String jamSelesai, String status, String keterangan) {
        this.idJadwal = idJadwal;
        this.idAnggota = idAnggota;
        this.namaAnggota = namaAnggota;
        this.idLokasi = idLokasi;
        this.namaLokasi = namaLokasi;
        setHari(hari);
        setJamMulai(jamMulai);
        setJamSelesai(jamSelesai);
        setStatus(status);
        setKeterangan(keterangan);
    }

    // PUBLIC GETTER
    public String getIdJadwal()     { return idJadwal; }
    public String getIdAnggota()    { return idAnggota; }
    public String getNamaAnggota()  { return namaAnggota; }
    public String getIdLokasi()     { return idLokasi; }
    public String getNamaLokasi()   { return namaLokasi; }
    public String getHari()         { return hari; }
    public String getJamMulai()     { return jamMulai; }
    public String getJamSelesai()   { return jamSelesai; }
    public String getStatus()       { return status; }
    public String getKeterangan()   { return keterangan; }

    // PUBLIC SETTER WITH VALIDATION
    public void setIdAnggota(String idAnggota)     { this.idAnggota = idAnggota; }
    public void setNamaAnggota(String namaAnggota) { this.namaAnggota = namaAnggota; }
    public void setIdLokasi(String idLokasi)       { this.idLokasi = idLokasi; }
    public void setNamaLokasi(String namaLokasi)   { this.namaLokasi = namaLokasi; }

    public void setHari(String hari) {
        // Validasi: hari harus salah satu dari 7 hari
        for (String h : HARI_VALID) {
            if (h.equalsIgnoreCase(hari)) {
                this.hari = h;
                return;
            }
        }
        System.out.println("[!] Hari tidak valid. Diset ke Senin.");
        this.hari = "Senin";
    }

    public void setJamMulai(String jamMulai) {
        // Validasi: format jam harus HH:MM
        if (jamMulai != null && jamMulai.matches("\\d{2}:\\d{2}")) {
            this.jamMulai = jamMulai;
        } else {
            System.out.println("[!] Format jam mulai tidak valid (HH:MM). Diset ke 07:00.");
            this.jamMulai = "07:00";
        }
    }

    public void setJamSelesai(String jamSelesai) {
        if (jamSelesai != null && jamSelesai.matches("\\d{2}:\\d{2}")) {
            this.jamSelesai = jamSelesai;
        } else {
            System.out.println("[!] Format jam selesai tidak valid (HH:MM). Diset ke 08:00.");
            this.jamSelesai = "08:00";
        }
    }

    public void setStatus(String status) {
        // Validasi: status harus salah satu dari 3 pilihan
        for (String s : STATUS_VALID) {
            if (s.equalsIgnoreCase(status)) {
                this.status = s;
                return;
            }
        }
        System.out.println("[!] Status tidak valid. Diset ke Terjadwal.");
        this.status = "Terjadwal";
    }

    public void setKeterangan(String keterangan) {
        this.keterangan = (keterangan == null || keterangan.trim().isEmpty()) ? "-" : keterangan.trim();
    }

    @Override
    public String toString() {
        return String.format("| %-8s | %-18s | %-20s | %-7s | %s-%s | %-10s | %-12s |",
                idJadwal, namaAnggota, namaLokasi, hari, jamMulai, jamSelesai, status, keterangan);
    }
}