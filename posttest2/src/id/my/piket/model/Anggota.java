package id.my.piket.model;

/**
 * Class Anggota - menerapkan konsep ENCAPSULATION
 *
 * ACCESS MODIFIER yang digunakan:
 * - private  : semua properti → tidak bisa diakses langsung dari luar
 * - public   : getter & setter → cara satu-satunya untuk akses data dari luar
 * - protected: method getRingkasan() → bisa diakses dari package yang sama
 */
public class Anggota {

    // =========================================================
    // PRIVATE PROPERTIES
    // Tidak bisa diakses langsung dari luar class ini.
    // Contoh yang TIDAK BOLEH: anggota.nama = "Budi"; ← ERROR!
    // =========================================================
    private String idAnggota;
    private String nama;
    private String divisi;
    private String noHp;
    private boolean aktif;

    // =========================================================
    // CONSTRUCTOR
    // =========================================================
    public Anggota(String idAnggota, String nama, String divisi, String noHp, boolean aktif) {
        this.idAnggota = idAnggota;
        // Pakai setter agar validasi berjalan sejak awal
        setNama(nama);
        setDivisi(divisi);
        setNoHp(noHp);
        this.aktif = aktif;
    }

    // =========================================================
    // PUBLIC GETTER - untuk MEMBACA data dari luar
    // =========================================================
    public String getIdAnggota() { return idAnggota; }
    public String getNama()      { return nama; }
    public String getDivisi()    { return divisi; }
    public String getNoHp()      { return noHp; }
    public boolean isAktif()     { return aktif; }

    // =========================================================
    // PUBLIC SETTER WITH VALIDATION - untuk MENGUBAH data dari luar
    // Inilah inti Encapsulation: data tidak bisa diubah sembarangan!
    // =========================================================

    public void setNama(String nama) {
        if (nama == null || nama.trim().isEmpty()) {
            // Validasi: nama tidak boleh kosong
            System.out.println("[!] Nama tidak boleh kosong. Nama tidak diubah.");
        } else {
            this.nama = nama.trim();
        }
    }

    public void setDivisi(String divisi) {
        if (divisi == null || divisi.trim().isEmpty()) {
            System.out.println("[!] Divisi tidak boleh kosong. Divisi tidak diubah.");
        } else {
            this.divisi = divisi.trim();
        }
    }

    public void setNoHp(String noHp) {
        if (noHp == null || noHp.trim().isEmpty()) {
            this.noHp = "-"; // Nilai default jika kosong
        } else {
            this.noHp = noHp.trim();
        }
    }

    public void setAktif(boolean aktif) {
        this.aktif = aktif;
    }

    // =========================================================
    // PROTECTED METHOD
    // Hanya bisa diakses dari:
    // 1. Class Anggota sendiri
    // 2. Class lain dalam package id.my.piket.model
    // 3. Subclass dari Anggota di package manapun
    // =========================================================
    protected String getRingkasan() {
        return "[" + idAnggota + "] " + nama + " (" + divisi + ")";
    }

    @Override
    public String toString() {
        return String.format("| %-8s | %-20s | %-15s | %-13s | %-8s |",
                idAnggota, nama, divisi, noHp, aktif ? "Aktif" : "Nonaktif");
    }
}