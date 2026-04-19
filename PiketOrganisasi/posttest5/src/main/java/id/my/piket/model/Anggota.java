package id.my.piket.model;

/**
 * Anggota - ABSTRACT CLASS (SUPERCLASS)
 *
 * Diubah dari class biasa menjadi ABSTRACT CLASS sesuai Modul 6.
 *
 * Aturan yang diterapkan:
 * 1. Kata kunci 'abstract' ditambahkan sebelum 'class'
 * 2. Tidak bisa dibuat objeknya langsung: new Anggota() → ERROR!
 * 3. Memiliki abstract method yang WAJIB diimplementasi subclass
 * 4. Tetap bisa punya method biasa (concrete method)
 *
 * ABSTRACT METHODS (tidak ada isinya, subclass yang isi):
 *   - getJenis()
 *   - getInfoTambahan()
 *   - tampilDetail()
 *
 * CONCRETE METHODS (ada isinya, diwarisi subclass):
 *   - getter & setter
 *   - toString()
 */
public abstract class Anggota {

    // PRIVATE PROPERTIES (Encapsulation dari Posttest 2)
    private String idAnggota;
    private String nama;
    private String noHp;
    private boolean aktif;

    // CONSTRUCTOR — abstract class boleh punya constructor
    public Anggota(String idAnggota, String nama, String noHp, boolean aktif) {
        this.idAnggota = idAnggota;
        setNama(nama);
        setNoHp(noHp);
        this.aktif = aktif;
    }

    // ===================== PUBLIC GETTER =====================
    public String getIdAnggota() { return idAnggota; }
    public String getNama()      { return nama; }
    public String getNoHp()      { return noHp; }
    public boolean isAktif()     { return aktif; }

    // ===================== PUBLIC SETTER WITH VALIDATION =====================
    public void setNama(String nama) {
        if (nama == null || nama.trim().isEmpty()) {
            System.out.println("[!] Nama tidak boleh kosong.");
        } else {
            this.nama = nama.trim();
        }
    }
    public void setNoHp(String noHp) {
        this.noHp = (noHp == null || noHp.trim().isEmpty()) ? "-" : noHp.trim();
    }
    public void setAktif(boolean aktif) { this.aktif = aktif; }

    // =====================================================================
    // ABSTRACT METHODS
    // Tidak ada body (isinya kosong) — subclass WAJIB mengimplementasikan!
    // Jika subclass tidak implement → ERROR saat compile!
    // =====================================================================

    // Abstract method #1
    public abstract String getJenis();

    // Abstract method #2
    public abstract String getInfoTambahan();

    // Abstract method #3
    public abstract void tampilDetail();

    // =====================================================================
    // CONCRETE METHOD (method biasa yang diwarisi subclass)
    // =====================================================================
    @Override
    public String toString() {
        return String.format("| %-8s | %-20s | %-13s | %-13s | %-8s | %-22s |",
                idAnggota, nama, getJenis(), noHp,
                aktif ? "Aktif" : "Nonaktif", getInfoTambahan());
    }
}