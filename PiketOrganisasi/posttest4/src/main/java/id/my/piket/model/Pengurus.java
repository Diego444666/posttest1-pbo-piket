package id.my.piket.model;

/**
 * Pengurus - SUBCLASS dari Anggota
 * Relasi: Pengurus IS-AN Anggota ✅
 */
public class Pengurus extends Anggota {

    private String jabatan;
    private String periode;

    public Pengurus(String idAnggota, String nama, String noHp,
                    boolean aktif, String jabatan, String periode) {
        super(idAnggota, nama, noHp, aktif);
        setJabatan(jabatan);
        setPeriode(periode);
    }

    public String getJabatan() { return jabatan; }
    public String getPeriode() { return periode; }

    public void setJabatan(String jabatan) {
        this.jabatan = (jabatan == null || jabatan.trim().isEmpty()) ? "Anggota" : jabatan.trim();
    }
    public void setPeriode(String periode) {
        this.periode = (periode == null || periode.trim().isEmpty()) ? "2024/2025" : periode.trim();
    }

    // ============================================================
    // OVERRIDING #1
    // ============================================================
    @Override
    public String getJenis() {
        return "Pengurus";
    }

    // ============================================================
    // OVERRIDING #2
    // ============================================================
    @Override
    public String getInfoTambahan() {
        return jabatan + " | " + periode;
    }

    // ============================================================
    // OVERRIDING #3 - tampilDetail() versi Pengurus
    // Berbeda dengan AnggotaBiasa, Pengurus tampilkan jabatan & periode
    // ============================================================
    @Override
    public void tampilDetail() {
        super.tampilDetail();
        System.out.println("  [Info Khusus Pengurus]");
        System.out.println("  Jabatan    : " + jabatan);
        System.out.println("  Periode    : " + periode);
        System.out.println("+--------------------------------------------+");
    }
}