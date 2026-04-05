package id.my.piket.model;

/**
 * Koordinator - SUBCLASS / CHILD CLASS dari Anggota
 *
 * Menerapkan relasi is-a: Koordinator IS-AN Anggota ✅
 *
 * Mewarisi semua property dan method dari Anggota,
 * dan menambahkan property khusus:
 * - divisiKoordinasi : area/divisi yang dikoordinasi
 * - jumlahAnggota    : jumlah anggota yang diawasi
 *
 * Tipe Inheritance: Hierarchical (bersama AnggotaBiasa & Pengurus)
 *
 * ★ POIN PLUS: Subclass ke-3 (lebih dari 2 subclass)
 */
public class Koordinator extends Anggota {

    // Property KHUSUS milik Koordinator saja
    private String divisiKoordinasi;
    private int jumlahAnggota;

    // ===================== CONSTRUCTOR =====================
    public Koordinator(String idAnggota, String nama, String noHp,
                       boolean aktif, String divisiKoordinasi, int jumlahAnggota) {
        // Memanggil constructor SUPERCLASS (Anggota) menggunakan super()
        super(idAnggota, nama, noHp, aktif);
        setDivisiKoordinasi(divisiKoordinasi);
        setJumlahAnggota(jumlahAnggota);
    }

    // ===================== GETTER & SETTER KHUSUS =====================
    public String getDivisiKoordinasi() { return divisiKoordinasi; }
    public int getJumlahAnggota()       { return jumlahAnggota; }

    public void setDivisiKoordinasi(String divisiKoordinasi) {
        if (divisiKoordinasi == null || divisiKoordinasi.trim().isEmpty()) {
            this.divisiKoordinasi = "Umum";
        } else {
            this.divisiKoordinasi = divisiKoordinasi.trim();
        }
    }

    public void setJumlahAnggota(int jumlahAnggota) {
        if (jumlahAnggota < 0) {
            System.out.println("[!] Jumlah anggota tidak boleh negatif. Diset ke 0.");
            this.jumlahAnggota = 0;
        } else {
            this.jumlahAnggota = jumlahAnggota;
        }
    }

    // ===================== OVERRIDE METHOD SUPERCLASS =====================
    @Override
    public String getJenis() {
        return "Koordinator";
    }

    @Override
    public String getInfoTambahan() {
        return "Koordinasi: " + divisiKoordinasi + " (" + jumlahAnggota + " org)";
    }
}