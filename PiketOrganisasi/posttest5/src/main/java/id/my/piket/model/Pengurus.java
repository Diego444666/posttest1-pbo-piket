package id.my.piket.model;

/**
 * Pengurus - SUBCLASS dari Anggota + implements IPiketable
 *
 * 1. extends Anggota       → mewarisi abstract class
 * 2. implements IPiketable → memenuhi kontrak interface
 */
public class Pengurus extends Anggota implements IPiketable {

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

    // =====================================================================
    // IMPLEMENTASI dari ABSTRACT CLASS Anggota
    // =====================================================================

    @Override
    public String getJenis() {
        return "Pengurus";
    }

    @Override
    public String getInfoTambahan() {
        return jabatan + " | " + periode;
    }

    @Override
    public void tampilDetail() {
        System.out.println("+--------------------------------------------+");
        System.out.println("|            DETAIL PENGURUS                 |");
        System.out.println("+--------------------------------------------+");
        System.out.println("  ID       : " + getIdAnggota());
        System.out.println("  Nama     : " + getNama());
        System.out.println("  Jenis    : " + getJenis());
        System.out.println("  No HP    : " + getNoHp());
        System.out.println("  Status   : " + (isAktif() ? "Aktif" : "Nonaktif"));
        System.out.println("  Jabatan  : " + jabatan);
        System.out.println("  Periode  : " + periode);
        System.out.println("+--------------------------------------------+");
    }

    // =====================================================================
    // IMPLEMENTASI dari INTERFACE IPiketable
    // =====================================================================

    @Override
    public String getDeskripsiTugas() {
        return "Sebagai " + jabatan + ", bertugas mengawasi dan mengkoordinasi " +
                "seluruh kegiatan piket kebersihan pada periode " + periode + ".";
    }

    @Override
    public void getLaporanAktivitas() {
        System.out.println("+--------------------------------------------+");
        System.out.println("|        LAPORAN AKTIVITAS PENGURUS          |");
        System.out.println("+--------------------------------------------+");
        System.out.println("  Nama        : " + getNama());
        System.out.println("  Jabatan     : " + jabatan);
        System.out.println("  Periode     : " + periode);
        System.out.println("  Tugas Utama : " + getDeskripsiTugas());
        System.out.println("+--------------------------------------------+");
    }
}