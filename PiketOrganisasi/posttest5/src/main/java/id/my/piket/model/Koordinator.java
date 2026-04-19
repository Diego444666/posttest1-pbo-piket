package id.my.piket.model;

/**
 * Koordinator - SUBCLASS dari Anggota + implements IPiketable
 *
 * 1. extends Anggota       → mewarisi abstract class
 * 2. implements IPiketable → memenuhi kontrak interface
 */
public class Koordinator extends Anggota implements IPiketable {

    private String divisiKoordinasi;
    private int jumlahAnggota;

    public Koordinator(String idAnggota, String nama, String noHp,
                       boolean aktif, String divisiKoordinasi, int jumlahAnggota) {
        super(idAnggota, nama, noHp, aktif);
        setDivisiKoordinasi(divisiKoordinasi);
        setJumlahAnggota(jumlahAnggota);
    }

    public String getDivisiKoordinasi() { return divisiKoordinasi; }
    public int getJumlahAnggota()       { return jumlahAnggota; }

    public void setDivisiKoordinasi(String d) {
        this.divisiKoordinasi = (d == null || d.trim().isEmpty()) ? "Umum" : d.trim();
    }
    public void setJumlahAnggota(int j) {
        this.jumlahAnggota = (j < 0) ? 0 : j;
    }

    // =====================================================================
    // IMPLEMENTASI dari ABSTRACT CLASS Anggota
    // =====================================================================

    @Override
    public String getJenis() {
        return "Koordinator";
    }

    @Override
    public String getInfoTambahan() {
        return "Koor: " + divisiKoordinasi + " (" + jumlahAnggota + " org)";
    }

    @Override
    public void tampilDetail() {
        System.out.println("+--------------------------------------------+");
        System.out.println("|           DETAIL KOORDINATOR               |");
        System.out.println("+--------------------------------------------+");
        System.out.println("  ID            : " + getIdAnggota());
        System.out.println("  Nama          : " + getNama());
        System.out.println("  Jenis         : " + getJenis());
        System.out.println("  No HP         : " + getNoHp());
        System.out.println("  Status        : " + (isAktif() ? "Aktif" : "Nonaktif"));
        System.out.println("  Div. Koordinasi: " + divisiKoordinasi);
        System.out.println("  Jumlah Anggota: " + jumlahAnggota + " orang");
        System.out.println("+--------------------------------------------+");
    }

    // =====================================================================
    // IMPLEMENTASI dari INTERFACE IPiketable
    // =====================================================================

    @Override
    public String getDeskripsiTugas() {
        return "Mengkoordinasikan " + jumlahAnggota + " anggota divisi " +
                divisiKoordinasi + " dalam pelaksanaan piket kebersihan.";
    }

    @Override
    public void getLaporanAktivitas() {
        System.out.println("+--------------------------------------------+");
        System.out.println("|       LAPORAN AKTIVITAS KOORDINATOR        |");
        System.out.println("+--------------------------------------------+");
        System.out.println("  Nama           : " + getNama());
        System.out.println("  Div. Koordinasi: " + divisiKoordinasi);
        System.out.println("  Jml. Anggota   : " + jumlahAnggota + " orang");
        System.out.println("  Tugas Utama    : " + getDeskripsiTugas());
        System.out.println("+--------------------------------------------+");
    }
}