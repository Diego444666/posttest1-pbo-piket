package id.my.piket.manager;

import id.my.piket.model.Anggota;
import id.my.piket.model.AnggotaBiasa;
import id.my.piket.model.Koordinator;
import id.my.piket.model.Pengurus;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * AnggotaManager
 *
 * Menerapkan STATIC POLYMORPHISM (Method Overloading) pada method tampilkanAnggota():
 *
 * Versi 1: tampilkanAnggota()              → tampil SEMUA anggota
 * Versi 2: tampilkanAnggota(String jenis)  → tampil berdasarkan JENIS
 * Versi 3: tampilkanAnggota(boolean aktif) → tampil berdasarkan STATUS AKTIF
 *
 * Ketiga method ini punya NAMA SAMA tapi PARAMETER BERBEDA = Method Overloading
 */
public class AnggotaManager {

    private ArrayList<Anggota> daftarAnggota;
    private int counterID;

    public AnggotaManager() {
        this.daftarAnggota = new ArrayList<>();
        this.counterID = 1;
        isiDataAwal();
    }

    private void isiDataAwal() {
        daftarAnggota.add(new AnggotaBiasa("AGT001", "Budi Santoso",  "081234567890", true,  "Kebersihan", 2023));
        daftarAnggota.add(new AnggotaBiasa("AGT002", "Siti Rahayu",   "081234567891", true,  "Logistik",   2022));
        daftarAnggota.add(new AnggotaBiasa("AGT003", "Ahmad Fauzi",   "081234567892", true,  "Humas",      2023));
        daftarAnggota.add(new AnggotaBiasa("AGT004", "Dewi Lestari",  "081234567893", true,  "Kebersihan", 2024));
        daftarAnggota.add(new Pengurus    ("AGT005", "Rizky Pratama", "081234567894", true,  "Ketua",      "2024/2025"));
        daftarAnggota.add(new Pengurus    ("AGT006", "Indah Permata", "081234567895", true,  "Sekretaris", "2024/2025"));
        daftarAnggota.add(new Koordinator ("AGT007", "Fajar Nugraha", "081234567896", true,  "Kebersihan", 5));
        daftarAnggota.add(new AnggotaBiasa("AGT008", "Riko Pratama",  "081234567897", false, "Acara",      2024));
        counterID = 9;
    }

    private String buatID() { return String.format("AGT%03d", counterID++); }

    // =========================================================
    // STATIC POLYMORPHISM — METHOD OVERLOADING
    // 3 method dengan nama SAMA tapi parameter BERBEDA
    // =========================================================

    /**
     * OVERLOAD Versi 1 — Tanpa parameter
     * Menampilkan SEMUA anggota tanpa filter apapun
     */
    public void tampilkanAnggota() {
        BaseManager.cetakJudul("DAFTAR SEMUA ANGGOTA");
        if (daftarAnggota.isEmpty()) { BaseManager.cetakError("Belum ada data."); return; }
        cetakHeaderTabel();
        for (Anggota a : daftarAnggota) System.out.println(a.toString());
        cetakFooterTabel();
        long biasa = 0, pengurus = 0, koor = 0;
        for (Anggota a : daftarAnggota) {
            if (a instanceof AnggotaBiasa) biasa++;
            else if (a instanceof Pengurus) pengurus++;
            else if (a instanceof Koordinator) koor++;
        }
        System.out.println("Total: " + daftarAnggota.size() +
                " (Biasa: " + biasa + " | Pengurus: " + pengurus + " | Koordinator: " + koor + ")");
    }

    /**
     * OVERLOAD Versi 2 — Parameter String jenis
     * Menampilkan anggota yang difilter berdasarkan JENIS
     */
    public void tampilkanAnggota(String jenis) {
        BaseManager.cetakJudul("DAFTAR ANGGOTA - JENIS: " + jenis.toUpperCase());
        cetakHeaderTabel();
        boolean ada = false;
        for (Anggota a : daftarAnggota) {
            if (a.getJenis().equalsIgnoreCase(jenis)) { System.out.println(a.toString()); ada = true; }
        }
        cetakFooterTabel();
        if (!ada) BaseManager.cetakError("Tidak ada anggota dengan jenis: " + jenis);
    }

    /**
     * OVERLOAD Versi 3 — Parameter boolean aktifSaja
     * Menampilkan anggota berdasarkan STATUS AKTIF
     */
    public void tampilkanAnggota(boolean aktifSaja) {
        String label = aktifSaja ? "AKTIF" : "NONAKTIF";
        BaseManager.cetakJudul("DAFTAR ANGGOTA - STATUS: " + label);
        cetakHeaderTabel();
        boolean ada = false;
        for (Anggota a : daftarAnggota) {
            if (a.isAktif() == aktifSaja) { System.out.println(a.toString()); ada = true; }
        }
        cetakFooterTabel();
        if (!ada) BaseManager.cetakError("Tidak ada anggota dengan status: " + label);
    }

    // =========================================================
    // HELPER TABEL
    // =========================================================
    private void cetakHeaderTabel() {
        String g = "+----------+----------------------+---------------+---------------+----------+------------------------+";
        System.out.println(g);
        System.out.printf("| %-8s | %-20s | %-13s | %-13s | %-8s | %-22s |%n",
                "ID", "Nama", "Jenis", "No HP", "Status", "Info Tambahan");
        System.out.println(g);
    }

    private void cetakFooterTabel() {
        System.out.println("+----------+----------------------+---------------+---------------+----------+------------------------+");
    }

    // =========================================================
    // CRUD
    // =========================================================

    public void tambah(Scanner sc) {
        BaseManager.cetakJudul("TAMBAH ANGGOTA BARU");
        System.out.println("Pilih Jenis:");
        System.out.println("  1. Anggota Biasa");
        System.out.println("  2. Pengurus");
        System.out.println("  3. Koordinator");
        System.out.print("Pilihan: ");
        String pj = sc.nextLine().trim();
        if (!pj.equals("1") && !pj.equals("2") && !pj.equals("3")) { BaseManager.cetakError("Pilihan tidak valid."); return; }

        System.out.print("Nama     : ");
        String nama = sc.nextLine();
        if (!Validator.isNotEmpty(nama)) { BaseManager.cetakError("Nama tidak boleh kosong!"); return; }

        System.out.print("No HP    : ");
        String noHp = sc.nextLine();

        System.out.print("Aktif? (1=Ya / 0=Tidak): ");
        boolean aktif = sc.nextLine().trim().equals("1");

        String id = buatID();

        switch (pj) {
            case "1":
                System.out.print("Divisi      : ");
                String divisi = sc.nextLine();
                if (!Validator.isNotEmpty(divisi)) { BaseManager.cetakError("Divisi tidak boleh kosong!"); counterID--; return; }
                System.out.print("Tahun Masuk : ");
                int tahun = 2024;
                try { tahun = Integer.parseInt(sc.nextLine().trim()); }
                catch (NumberFormatException e) { BaseManager.cetakError("Tahun tidak valid, diset 2024."); }
                daftarAnggota.add(new AnggotaBiasa(id, Validator.bersihkan(nama), noHp, aktif, divisi, tahun));
                break;
            case "2":
                System.out.print("Jabatan : ");
                String jabatan = sc.nextLine();
                if (!Validator.isNotEmpty(jabatan)) { BaseManager.cetakError("Jabatan tidak boleh kosong!"); counterID--; return; }
                System.out.print("Periode : ");
                String periode = sc.nextLine();
                if (periode.isEmpty()) periode = "2024/2025";
                daftarAnggota.add(new Pengurus(id, Validator.bersihkan(nama), noHp, aktif, jabatan, periode));
                break;
            case "3":
                System.out.print("Divisi Koordinasi : ");
                String divK = sc.nextLine();
                if (!Validator.isNotEmpty(divK)) { BaseManager.cetakError("Divisi tidak boleh kosong!"); counterID--; return; }
                System.out.print("Jumlah Anggota    : ");
                int jml = 0;
                try { jml = Integer.parseInt(sc.nextLine().trim()); }
                catch (NumberFormatException e) { BaseManager.cetakError("Jumlah tidak valid, diset 0."); }
                daftarAnggota.add(new Koordinator(id, Validator.bersihkan(nama), noHp, aktif, divK, jml));
                break;
        }
        BaseManager.cetakOK("Anggota ditambahkan! ID: " + id);
    }

    public void cari(Scanner sc) {
        BaseManager.cetakJudul("CARI ANGGOTA");
        System.out.print("Cari (nama/ID): ");
        String kata = sc.nextLine().trim().toLowerCase();
        cetakHeaderTabel();
        boolean ada = false;
        for (Anggota a : daftarAnggota) {
            if (a.getIdAnggota().toLowerCase().contains(kata) || a.getNama().toLowerCase().contains(kata)) {
                System.out.println(a.toString()); ada = true;
            }
        }
        cetakFooterTabel();
        if (!ada) BaseManager.cetakError("Tidak ditemukan.");
    }

    public void lihatDetail(Scanner sc) {
        BaseManager.cetakJudul("LIHAT DETAIL ANGGOTA");
        tampilkanAnggota();
        System.out.print("\nMasukkan ID: ");
        String id = sc.nextLine().trim().toUpperCase();
        Anggota target = cariById(id);
        if (target == null) { BaseManager.cetakError("ID tidak ditemukan."); return; }
        // Dynamic Polymorphism: Java otomatis panggil tampilDetail() versi subclass!
        target.tampilDetail();
    }

    public void update(Scanner sc) {
        BaseManager.cetakJudul("UPDATE ANGGOTA");
        tampilkanAnggota();
        System.out.print("\nID yang mau diupdate: ");
        String id = sc.nextLine().trim().toUpperCase();
        Anggota target = cariById(id);
        if (target == null) { BaseManager.cetakError("ID tidak ditemukan."); return; }

        System.out.println("Jenis: " + target.getJenis());
        System.out.println("[Enter untuk skip]");

        System.out.print("Nama baru  : ");
        String nama = sc.nextLine().trim();
        if (!nama.isEmpty()) target.setNama(nama);

        System.out.print("No HP baru : ");
        String noHp = sc.nextLine().trim();
        if (!noHp.isEmpty()) target.setNoHp(noHp);

        System.out.print("Status (1=Aktif / 0=Nonaktif / Enter=skip): ");
        String s = sc.nextLine().trim();
        if (s.equals("1")) target.setAktif(true);
        else if (s.equals("0")) target.setAktif(false);

        if (target instanceof AnggotaBiasa) {
            AnggotaBiasa ab = (AnggotaBiasa) target;
            System.out.print("Divisi baru      : ");
            String d = sc.nextLine().trim(); if (!d.isEmpty()) ab.setDivisi(d);
            System.out.print("Tahun masuk baru : ");
            String t = sc.nextLine().trim();
            if (!t.isEmpty()) { try { ab.setTahunMasuk(Integer.parseInt(t)); } catch (NumberFormatException e) { BaseManager.cetakError("Tahun tidak valid."); } }
        } else if (target instanceof Pengurus) {
            Pengurus p = (Pengurus) target;
            System.out.print("Jabatan baru : "); String j = sc.nextLine().trim(); if (!j.isEmpty()) p.setJabatan(j);
            System.out.print("Periode baru : "); String per = sc.nextLine().trim(); if (!per.isEmpty()) p.setPeriode(per);
        } else if (target instanceof Koordinator) {
            Koordinator k = (Koordinator) target;
            System.out.print("Divisi Koordinasi baru : "); String dk = sc.nextLine().trim(); if (!dk.isEmpty()) k.setDivisiKoordinasi(dk);
            System.out.print("Jumlah Anggota baru    : ");
            String jm = sc.nextLine().trim();
            if (!jm.isEmpty()) { try { k.setJumlahAnggota(Integer.parseInt(jm)); } catch (NumberFormatException e) { BaseManager.cetakError("Jumlah tidak valid."); } }
        }
        BaseManager.cetakOK("Data berhasil diupdate!");
    }

    public void hapus(Scanner sc) {
        BaseManager.cetakJudul("HAPUS ANGGOTA");
        tampilkanAnggota();
        System.out.print("\nID yang mau dihapus: ");
        String id = sc.nextLine().trim().toUpperCase();
        Anggota target = cariById(id);
        if (target == null) { BaseManager.cetakError("ID tidak ditemukan."); return; }
        System.out.print("Yakin hapus " + target.getNama() + "? (y/n): ");
        if (sc.nextLine().trim().equalsIgnoreCase("y")) { daftarAnggota.remove(target); BaseManager.cetakOK(target.getNama() + " berhasil dihapus!"); }
        else BaseManager.cetakInfo("Dibatalkan.");
    }

    public Anggota cariById(String id) {
        for (Anggota a : daftarAnggota) { if (a.getIdAnggota().equalsIgnoreCase(id)) return a; }
        return null;
    }

    public ArrayList<Anggota> getDaftarAnggota() { return daftarAnggota; }

    public void menu(Scanner sc) {
        boolean lanjut = true;
        while (lanjut) {
            System.out.println("\n+--------------------------------------+");
            System.out.println("|         MENU KELOLA ANGGOTA          |");
            System.out.println("+--------------------------------------+");
            System.out.println("| 1. Lihat Semua Anggota               |");
            System.out.println("| 2. Lihat Per Jenis (Overload ver.2)  |");
            System.out.println("| 3. Lihat Per Status (Overload ver.3) |");
            System.out.println("| 4. Lihat Detail Anggota (Override)   |");
            System.out.println("| 5. Cari Anggota                      |");
            System.out.println("| 6. Tambah Anggota                    |");
            System.out.println("| 7. Update Anggota                    |");
            System.out.println("| 8. Hapus Anggota                     |");
            System.out.println("| 0. Kembali                           |");
            System.out.println("+--------------------------------------+");
            System.out.print("Pilih: ");
            switch (sc.nextLine().trim()) {
                case "1": tampilkanAnggota(); break;
                case "2":
                    System.out.println("  1. Anggota Biasa  2. Pengurus  3. Koordinator");
                    System.out.print("  Pilih: ");
                    String pj = sc.nextLine().trim();
                    if (pj.equals("1"))      tampilkanAnggota("Anggota Biasa");
                    else if (pj.equals("2")) tampilkanAnggota("Pengurus");
                    else if (pj.equals("3")) tampilkanAnggota("Koordinator");
                    else BaseManager.cetakError("Pilihan tidak valid.");
                    break;
                case "3":
                    System.out.println("  1. Aktif  2. Nonaktif");
                    System.out.print("  Pilih: ");
                    String ps = sc.nextLine().trim();
                    if (ps.equals("1"))      tampilkanAnggota(true);
                    else if (ps.equals("2")) tampilkanAnggota(false);
                    else BaseManager.cetakError("Pilihan tidak valid.");
                    break;
                case "4": lihatDetail(sc); break;
                case "5": cari(sc); break;
                case "6": tambah(sc); break;
                case "7": update(sc); break;
                case "8": hapus(sc); break;
                case "0": lanjut = false; break;
                default: BaseManager.cetakError("Pilihan tidak valid.");
            }
        }
    }
}