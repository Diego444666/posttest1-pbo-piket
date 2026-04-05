package id.my.piket.model;

/**
 * Pengurus - SUBCLASS / CHILD CLASS dari Anggota
 *
 * Menerapkan relasi is-a: Pengurus IS-AN Anggota ✅
 *
 * Mewarisi semua property dan method dari Anggota,
 * dan menambahkan property khusus:
 * - jabatan : jabatan dalam organisasi (Ketua, Sekretaris, dll)
 * - periode : masa menjabat
 *
 * Tipe Inheritance: Hierarchical (bersama AnggotaBiasa & Koordinator)
 */
public class Pengurus extends Anggota {

    // Property KHUSUS milik Pengurus saja
    private String jabatan;
    private String periode;

    // ===================== CONSTRUCTOR =====================
    public Pengurus(String idAnggota, String nama, String noHp,
                    boolean aktif, String jabatan, String periode) {
        // Memanggil constructor SUPERCLASS (Anggota) menggunakan super()
        super(idAnggota, nama, noHp, aktif);
        setJabatan(jabatan);
        setPeriode(periode);
    }

    // ===================== GETTER & SETTER KHUSUS =====================
    public String getJabatan() { return jabatan; }
    public String getPeriode() { return periode; }

    public void setJabatan(String jabatan) {
        if (jabatan == null || jabatan.trim().isEmpty()) {
            this.jabatan = "Anggota";
        } else {
            this.jabatan = jabatan.trim();
        }
    }

    public void setPeriode(String periode) {
        if (periode == null || periode.trim().isEmpty()) {
            this.periode = "2024/2025";
        } else {
            this.periode = periode.trim();
        }
    }

    // ===================== OVERRIDE METHOD SUPERCLASS =====================
    @Override
    public String getJenis() {
        return "Pengurus";
    }

    @Override
    public String getInfoTambahan() {
        return jabatan + " | " + periode;
    }
}