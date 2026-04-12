package id.my.piket.model;

/**
 * Koordinator - SUBCLASS dari Anggota
 * Relasi: Koordinator IS-AN Anggota ✅
 */
public class Koordinator extends Anggota {

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

    public void setDivisiKoordinasi(String divisiKoordinasi) {
        this.divisiKoordinasi = (divisiKoordinasi == null || divisiKoordinasi.trim().isEmpty()) ? "Umum" : divisiKoordinasi.trim();
    }
    public void setJumlahAnggota(int jumlahAnggota) {
        this.jumlahAnggota = (jumlahAnggota < 0) ? 0 : jumlahAnggota;
    }

    // ============================================================
    // OVERRIDING #1
    // ============================================================
    @Override
    public String getJenis() {
        return "Koordinator";
    }

    // ============================================================
    // OVERRIDING #2
    // ============================================================
    @Override
    public String getInfoTambahan() {
        return "Koordinasi: " + divisiKoordinasi + " (" + jumlahAnggota + " org)";
    }

    // ============================================================
    // OVERRIDING #3 - tampilDetail() versi Koordinator
    // Berbeda lagi: Koordinator tampilkan divisi koordinasi & jumlah anggota
    // ============================================================
    @Override
    public void tampilDetail() {
        super.tampilDetail();
        System.out.println("  [Info Khusus Koordinator]");
        System.out.println("  Div. Koor  : " + divisiKoordinasi);
        System.out.println("  Jml Anggota: " + jumlahAnggota + " orang");
        System.out.println("+--------------------------------------------+");
    }
}