package id.my.piket.model;

/**
 * AnggotaBiasa - SUBCLASS dari Anggota + implements IPiketable
 *
 * Melakukan DUA hal sekaligus (sesuai Modul 6):
 * 1. extends Anggota    → mewarisi abstract class
 * 2. implements IPiketable → memenuhi kontrak interface
 *
 * Wajib mengimplementasikan:
 * - Dari abstract class Anggota : getJenis(), getInfoTambahan(), tampilDetail()
 * - Dari interface IPiketable   : getDeskripsiTugas(), getLaporanAktivitas()
 */
public class AnggotaBiasa extends Anggota implements IPiketable {

    private String divisi;
    private int tahunMasuk;

    // CONSTRUCTOR — memanggil super() ke abstract class Anggota
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

    // =====================================================================
    // IMPLEMENTASI dari ABSTRACT CLASS Anggota
    // =====================================================================

    @Override
    public String getJenis() {
        return "Anggota Biasa";
    }

    @Override
    public String getInfoTambahan() {
        return divisi + " (" + tahunMasuk + ")";
    }

    @Override
    public void tampilDetail() {
        System.out.println("+--------------------------------------------+");
        System.out.println("|          DETAIL ANGGOTA BIASA              |");
        System.out.println("+--------------------------------------------+");
        System.out.println("  ID          : " + getIdAnggota());
        System.out.println("  Nama        : " + getNama());
        System.out.println("  Jenis       : " + getJenis());
        System.out.println("  No HP       : " + getNoHp());
        System.out.println("  Status      : " + (isAktif() ? "Aktif" : "Nonaktif"));
        System.out.println("  Divisi      : " + divisi);
        System.out.println("  Tahun Masuk : " + tahunMasuk);
        System.out.println("  Masa Keangg : " + (2025 - tahunMasuk) + " tahun");
        System.out.println("+--------------------------------------------+");
    }

    // =====================================================================
    // IMPLEMENTASI dari INTERFACE IPiketable
    // =====================================================================

    @Override
    public String getDeskripsiTugas() {
        return "Melaksanakan piket kebersihan di area divisi " + divisi +
                " sesuai jadwal yang telah ditetapkan.";
    }

    @Override
    public void getLaporanAktivitas() {
        System.out.println("+--------------------------------------------+");
        System.out.println("|      LAPORAN AKTIVITAS ANGGOTA BIASA       |");
        System.out.println("+--------------------------------------------+");
        System.out.println("  Nama           : " + getNama());
        System.out.println("  Divisi         : " + divisi);
        System.out.println("  Tugas Utama    : " + getDeskripsiTugas());
        System.out.println("  Tahun Bergabung: " + tahunMasuk);
        System.out.println("  Lama Bergabung : " + (2025 - tahunMasuk) + " tahun");
        System.out.println("+--------------------------------------------+");
    }
}