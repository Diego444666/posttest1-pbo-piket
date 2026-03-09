package id.my.piket.model;

public class Anggota {
    private String idAnggota;
    private String nama;
    private String divisi;
    private String noHp;
    private boolean aktif;

    // Constructor
    public Anggota(String idAnggota, String nama, String divisi, String noHp, boolean aktif) {
        this.idAnggota = idAnggota;
        this.nama = nama;
        this.divisi = divisi;
        this.noHp = noHp;
        this.aktif = aktif;
    }

    // Getter
    public String getIdAnggota() { return idAnggota; }
    public String getNama() { return nama; }
    public String getDivisi() { return divisi; }
    public String getNoHp() { return noHp; }
    public boolean isAktif() { return aktif; }

    // Setter
    public void setNama(String nama) { this.nama = nama; }
    public void setDivisi(String divisi) { this.divisi = divisi; }
    public void setNoHp(String noHp) { this.noHp = noHp; }
    public void setAktif(boolean aktif) { this.aktif = aktif; }

    @Override
    public String toString() {
        return String.format("| %-8s | %-20s | %-15s | %-13s | %-8s |",
                idAnggota, nama, divisi, noHp, aktif ? "Aktif" : "Nonaktif");
    }
}