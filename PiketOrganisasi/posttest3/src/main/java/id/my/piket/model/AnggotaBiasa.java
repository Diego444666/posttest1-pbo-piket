package id.my.piket.model;

/**
 * AnggotaBiasa - SUBCLASS / CHILD CLASS dari Anggota
 *
 * Menerapkan relasi is-a: AnggotaBiasa IS-AN Anggota ✅
 *
 * Mewarisi semua property dan method dari Anggota,
 * dan menambahkan property khusus:
 * - divisi  : divisi dalam organisasi
 * - tahunMasuk : tahun bergabung
 *
 * Tipe Inheritance: Hierarchical (bersama Pengurus & Koordinator)
 */
public class AnggotaBiasa extends Anggota {

    // Property KHUSUS milik AnggotaBiasa saja
    private String divisi;
    private int tahunMasuk;

    // ===================== CONSTRUCTOR =====================
    public AnggotaBiasa(String idAnggota, String nama, String noHp,
                        boolean aktif, String divisi, int tahunMasuk) {
        // Memanggil constructor SUPERCLASS (Anggota) menggunakan super()
        super(idAnggota, nama, noHp, aktif);
        setDivisi(divisi);
        setTahunMasuk(tahunMasuk);
    }

    // ===================== GETTER & SETTER KHUSUS =====================
    public String getDivisi()    { return divisi; }
    public int getTahunMasuk()   { return tahunMasuk; }

    public void setDivisi(String divisi) {
        if (divisi == null || divisi.trim().isEmpty()) {
            this.divisi = "Umum";
        } else {
            this.divisi = divisi.trim();
        }
    }

    public void setTahunMasuk(int tahunMasuk) {
        if (tahunMasuk < 2000 || tahunMasuk > 2100) {
            System.out.println("[!] Tahun masuk tidak valid. Diset ke 2024.");
            this.tahunMasuk = 2024;
        } else {
            this.tahunMasuk = tahunMasuk;
        }
    }

    // ===================== OVERRIDE METHOD SUPERCLASS =====================
    @Override
    public String getJenis() {
        return "Anggota Biasa";
    }

    @Override
    public String getInfoTambahan() {
        return divisi + " (" + tahunMasuk + ")";
    }
}