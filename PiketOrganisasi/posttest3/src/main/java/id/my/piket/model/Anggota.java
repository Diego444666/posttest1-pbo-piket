package id.my.piket.model;

/**
 * Anggota - SUPERCLASS / PARENT CLASS
 *
 * Class ini menjadi dasar (parent) dari:
 * - AnggotaBiasa  (subclass)
 * - Pengurus      (subclass)
 * - Koordinator   (subclass)
 *
 * Berisi property dan method yang DIMILIKI SEMUA jenis anggota.
 * Menerapkan Encapsulation (private + public getter/setter) dari Modul 3.
 */
public class Anggota {

    // ===================== PRIVATE PROPERTIES =====================
    // Property umum yang dimiliki SEMUA jenis anggota
    private String idAnggota;
    private String nama;
    private String noHp;
    private boolean aktif;

    // ===================== CONSTRUCTOR =====================
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
            System.out.println("[!] Nama tidak boleh kosong. Nama tidak diubah.");
        } else {
            this.nama = nama.trim();
        }
    }

    public void setNoHp(String noHp) {
        this.noHp = (noHp == null || noHp.trim().isEmpty()) ? "-" : noHp.trim();
    }

    public void setAktif(boolean aktif) {
        this.aktif = aktif;
    }

    // ===================== METHOD =====================
    /**
     * Method ini bisa di-override oleh subclass
     * untuk menampilkan jenis anggota yang berbeda-beda
     */
    public String getJenis() {
        return "Anggota";
    }

    /**
     * Method ini bisa di-override oleh subclass
     * untuk menampilkan info tambahan milik masing-masing subclass
     */
    public String getInfoTambahan() {
        return "-";
    }

    @Override
    public String toString() {
        return String.format("| %-8s | %-20s | %-13s | %-13s | %-8s | %-20s |",
                idAnggota, nama, getJenis(), noHp,
                aktif ? "Aktif" : "Nonaktif", getInfoTambahan());
    }
}