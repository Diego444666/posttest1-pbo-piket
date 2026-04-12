package id.my.piket.model;

/**
 * AnggotaBiasa - SUBCLASS dari Anggota
 * Relasi: AnggotaBiasa IS-AN Anggota ✅
 */
public class AnggotaBiasa extends Anggota {

    private String divisi;
    private int tahunMasuk;

    public AnggotaBiasa(String idAnggota, String nama, String noHp,
                        boolean aktif, String divisi, int tahunMasuk) {
        super(idAnggota, nama, noHp, aktif);
        setDivisi(divisi);
        setTahunMasuk(tahunMasuk);
    }

    public String getDivisi()  { return divisi; }
    public int getTahunMasuk() { return tahunMasuk; }

    public void setDivisi(String divisi) {
        this.divisi = (divisi == null || divisi.trim().isEmpty()) ? "Umum" : divisi.trim();
    }
    public void setTahunMasuk(int tahunMasuk) {
        if (tahunMasuk < 2000 || tahunMasuk > 2100) {
            System.out.println("[!] Tahun tidak valid, diset 2024.");
            this.tahunMasuk = 2024;
        } else {
            this.tahunMasuk = tahunMasuk;
        }
    }

    // ============================================================
    // OVERRIDING #1
    // ============================================================
    @Override
    public String getJenis() {
        return "Anggota Biasa";
    }

    // ============================================================
    // OVERRIDING #2
    // ============================================================
    @Override
    public String getInfoTambahan() {
        return divisi + " (" + tahunMasuk + ")";
    }

    // ============================================================
    // OVERRIDING #3 - tampilDetail() versi AnggotaBiasa
    // Menampilkan info umum dari superclass + info khusus divisi
    // ============================================================
    @Override
    public void tampilDetail() {
        // Panggil tampilDetail() milik superclass (Anggota) dulu
        super.tampilDetail();
        // Lalu tambahkan info khusus AnggotaBiasa
        System.out.println("  [Info Khusus Anggota Biasa]");
        System.out.println("  Divisi     : " + divisi);
        System.out.println("  Tahun Masuk: " + tahunMasuk);
        System.out.println("  Masa Keangg: " + (2025 - tahunMasuk) + " tahun");
        System.out.println("+--------------------------------------------+");
    }
}