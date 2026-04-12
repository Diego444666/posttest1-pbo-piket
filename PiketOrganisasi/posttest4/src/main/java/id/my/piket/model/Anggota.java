package id.my.piket.model;

/**
 * Anggota - SUPERCLASS
 *
 * POLYMORPHISM yang diterapkan di sini:
 *
 * METHOD OVERRIDING (Dynamic Polymorphism) — ada 3 method yang di-override:
 *   1. getJenis()        → tiap subclass kembalikan jenis berbeda
 *   2. getInfoTambahan() → tiap subclass kembalikan info khusus berbeda
 *   3. tampilDetail()    → tiap subclass tampilkan detail yang berbeda [BARU]
 */
public class Anggota {

    // PRIVATE PROPERTIES (Encapsulation dari Posttest 2)
    private String idAnggota;
    private String nama;
    private String noHp;
    private boolean aktif;

    // CONSTRUCTOR
    public Anggota(String idAnggota, String nama, String noHp, boolean aktif) {
        this.idAnggota = idAnggota;
        setNama(nama);
        setNoHp(noHp);
        this.aktif = aktif;
    }

    // GETTER
    public String getIdAnggota() { return idAnggota; }
    public String getNama()      { return nama; }
    public String getNoHp()      { return noHp; }
    public boolean isAktif()     { return aktif; }

    // SETTER WITH VALIDATION
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

    // ============================================================
    // OVERRIDING #1 - getJenis()
    // Di sini bentuk dasarnya, subclass akan override dengan
    // jenis masing-masing: "Anggota Biasa", "Pengurus", "Koordinator"
    // ============================================================
    public String getJenis() {
        return "Anggota";
    }

    // ============================================================
    // OVERRIDING #2 - getInfoTambahan()
    // Di sini bentuk dasarnya, subclass akan override dengan
    // info khusus masing-masing
    // ============================================================
    public String getInfoTambahan() {
        return "-";
    }

    // ============================================================
    // OVERRIDING #3 - tampilDetail() [BARU di Posttest 4]
    // Di sini bentuk dasarnya menampilkan info umum.
    // Setiap subclass akan override untuk tambahkan info khusus mereka.
    // Inilah Dynamic Polymorphism: nama method SAMA, isi BERBEDA!
    // ============================================================
    public void tampilDetail() {
        System.out.println("+--------------------------------------------+");
        System.out.println("|            DETAIL ANGGOTA                  |");
        System.out.println("+--------------------------------------------+");
        System.out.println("  ID        : " + idAnggota);
        System.out.println("  Nama      : " + nama);
        System.out.println("  Jenis     : " + getJenis());
        System.out.println("  No HP     : " + noHp);
        System.out.println("  Status    : " + (aktif ? "Aktif" : "Nonaktif"));
        System.out.println("+--------------------------------------------+");
    }

    @Override
    public String toString() {
        return String.format("| %-8s | %-20s | %-13s | %-13s | %-8s | %-20s |",
                idAnggota, nama, getJenis(), noHp,
                aktif ? "Aktif" : "Nonaktif", getInfoTambahan());
    }
}
