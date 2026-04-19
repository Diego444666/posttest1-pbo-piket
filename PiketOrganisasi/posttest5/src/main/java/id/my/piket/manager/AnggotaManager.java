package id.my.piket.manager;

import id.my.piket.model.Anggota;
import id.my.piket.model.AnggotaBiasa;
import id.my.piket.model.IPiketable;
import id.my.piket.model.Koordinator;
import id.my.piket.model.Pengurus;
import java.util.ArrayList;
import java.util.Scanner;

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
        daftarAnggota.add(new AnggotaBiasa("AGT004", "Dewi Lestari",  "081234567893", false, "Kebersihan", 2024));
        daftarAnggota.add(new Pengurus("AGT005", "Rizky Pratama",  "081234567894", true,  "Ketua",      "2024/2025"));
        daftarAnggota.add(new Pengurus("AGT006", "Indah Permata",  "081234567895", true,  "Sekretaris", "2024/2025"));
        daftarAnggota.add(new Koordinator("AGT007", "Fajar Nugraha", "081234567896", true, "Kebersihan", 5));
        counterID = 8;
    }

    private String buatID() { return String.format("AGT%03d", counterID++); }

    private void cetakHeader() {
        String garis = "+----------+----------------------+---------------+---------------+----------+------------------------+";
        System.out.println(garis);
        System.out.printf("| %-8s | %-20s | %-13s | %-13s | %-8s | %-22s |%n",
                "ID", "Nama", "Jenis", "No HP", "Status", "Info Tambahan");
        System.out.println(garis);
    }
    private void cetakGaris() {
        System.out.println("+----------+----------------------+---------------+---------------+----------+------------------------+");
    }

    // ===================== CREATE =====================
    public void tambah(Scanner sc) {
        BaseManager.cetakJudul("TAMBAH ANGGOTA BARU");
        System.out.println("Pilih Jenis:");
        System.out.println("  1. Anggota Biasa");
        System.out.println("  2. Pengurus");
        System.out.println("  3. Koordinator");
        System.out.print("Pilihan: ");
        String pilih = sc.nextLine().trim();
        if (!pilih.equals("1") && !pilih.equals("2") && !pilih.equals("3")) {
            BaseManager.cetakError("Pilihan tidak valid."); return;
        }

        System.out.print("Nama    : ");
        String nama = sc.nextLine();
        if (!Validator.isNotEmpty(nama)) { BaseManager.cetakError("Nama tidak boleh kosong!"); return; }

        System.out.print("No HP   : ");
        String noHp = sc.nextLine();

        System.out.print("Aktif? (1=Ya / 0=Tidak): ");
        boolean aktif = sc.nextLine().trim().equals("1");

        String id = buatID();

        switch (pilih) {
            case "1":
                System.out.print("Divisi      : ");
                String div = sc.nextLine();
                if (!Validator.isNotEmpty(div)) { BaseManager.cetakError("Divisi tidak boleh kosong!"); counterID--; return; }
                System.out.print("Tahun Masuk : ");
                int tahun = 2024;
                try { tahun = Integer.parseInt(sc.nextLine().trim()); }
                catch (NumberFormatException e) { BaseManager.cetakError("Tidak valid, diset 2024."); }
                daftarAnggota.add(new AnggotaBiasa(id, Validator.bersihkan(nama), noHp, aktif, div, tahun));
                break;
            case "2":
                System.out.print("Jabatan : ");
                String jab = sc.nextLine();
                if (!Validator.isNotEmpty(jab)) { BaseManager.cetakError("Jabatan tidak boleh kosong!"); counterID--; return; }
                System.out.print("Periode : ");
                String per = sc.nextLine();
                if (per.isEmpty()) per = "2024/2025";
                daftarAnggota.add(new Pengurus(id, Validator.bersihkan(nama), noHp, aktif, jab, per));
                break;
            case "3":
                System.out.print("Divisi Koordinasi : ");
                String dk = sc.nextLine();
                if (!Validator.isNotEmpty(dk)) { BaseManager.cetakError("Divisi tidak boleh kosong!"); counterID--; return; }
                System.out.print("Jumlah Anggota    : ");
                int jml = 0;
                try { jml = Integer.parseInt(sc.nextLine().trim()); }
                catch (NumberFormatException e) { BaseManager.cetakError("Tidak valid, diset 0."); }
                daftarAnggota.add(new Koordinator(id, Validator.bersihkan(nama), noHp, aktif, dk, jml));
                break;
        }
        BaseManager.cetakOK("Anggota berhasil ditambahkan! ID: " + id);
    }

    // ===================== READ =====================
    public void tampilSemua() {
        BaseManager.cetakJudul("DAFTAR SEMUA ANGGOTA");
        if (daftarAnggota.isEmpty()) { BaseManager.cetakError("Belum ada data."); return; }
        cetakHeader();
        for (Anggota a : daftarAnggota) System.out.println(a.toString());
        cetakGaris();
        long b = 0, p = 0, k = 0;
        for (Anggota a : daftarAnggota) {
            if (a instanceof Koordinator) k++;
            else if (a instanceof Pengurus) p++;
            else if (a instanceof AnggotaBiasa) b++;
        }
        System.out.println("Total: " + daftarAnggota.size() +
                " (Biasa: " + b + " | Pengurus: " + p + " | Koordinator: " + k + ")");
    }

    // ===================== LIHAT DETAIL (Polymorphism - Override) =====================
    public void lihatDetail(Scanner sc) {
        tampilSemua();
        System.out.print("\nMasukkan ID untuk lihat detail: ");
        Anggota target = cariById(sc.nextLine().trim().toUpperCase());
        if (target == null) { BaseManager.cetakError("ID tidak ditemukan."); return; }
        target.tampilDetail(); // Dynamic Polymorphism dari Posttest 4
    }

    // ===================== LIHAT LAPORAN (Interface IPiketable) =====================
    public void lihatLaporan(Scanner sc) {
        BaseManager.cetakJudul("LAPORAN AKTIVITAS (via Interface IPiketable)");
        tampilSemua();
        System.out.print("\nMasukkan ID untuk lihat laporan: ");
        Anggota target = cariById(sc.nextLine().trim().toUpperCase());
        if (target == null) { BaseManager.cetakError("ID tidak ditemukan."); return; }

        // Cek apakah objek ini implements IPiketable
        // Semua subclass Anggota implements IPiketable, jadi ini selalu true
        if (target instanceof IPiketable) {
            IPiketable piketable = (IPiketable) target;
            // Panggil method dari INTERFACE
            System.out.println("\n  Deskripsi Tugas: " + piketable.getDeskripsiTugas());
            piketable.getLaporanAktivitas(); // Method interface ke-2
        } else {
            BaseManager.cetakError("Anggota ini tidak mengimplementasikan IPiketable.");
        }
    }

    // ===================== FILTER (Overloading dari Posttest 4) =====================
    public void tampilDenganFilter(String keyword) {
        BaseManager.cetakJudul("FILTER: Nama/ID mengandung \"" + keyword + "\"");
        String kata = keyword.toLowerCase();
        boolean ada = false;
        cetakHeader();
        for (Anggota a : daftarAnggota) {
            if (a.getNama().toLowerCase().contains(kata) || a.getIdAnggota().toLowerCase().contains(kata)) {
                System.out.println(a.toString()); ada = true;
            }
        }
        cetakGaris();
        if (!ada) BaseManager.cetakError("Tidak ada yang cocok.");
    }

    public void tampilDenganFilter(String keyword, boolean aktif) {
        BaseManager.cetakJudul("FILTER: \"" + keyword + "\" + Status " + (aktif ? "Aktif" : "Nonaktif"));
        String kata = keyword.toLowerCase();
        boolean ada = false;
        cetakHeader();
        for (Anggota a : daftarAnggota) {
            if ((a.getNama().toLowerCase().contains(kata) || a.getIdAnggota().toLowerCase().contains(kata))
                    && a.isAktif() == aktif) {
                System.out.println(a.toString()); ada = true;
            }
        }
        cetakGaris();
        if (!ada) BaseManager.cetakError("Tidak ada yang cocok.");
    }

    public void tampilDenganFilter(boolean aktif) {
        BaseManager.cetakJudul("FILTER: Anggota " + (aktif ? "Aktif" : "Nonaktif"));
        boolean ada = false;
        cetakHeader();
        for (Anggota a : daftarAnggota) {
            if (a.isAktif() == aktif) { System.out.println(a.toString()); ada = true; }
        }
        cetakGaris();
        if (!ada) BaseManager.cetakError("Tidak ada anggota " + (aktif ? "aktif" : "nonaktif") + ".");
    }

    public void menuFilter(Scanner sc) {
        BaseManager.cetakJudul("FILTER ANGGOTA");
        System.out.println("  1. Cari berdasarkan Nama/ID");
        System.out.println("  2. Cari berdasarkan Nama/ID + Status");
        System.out.println("  3. Tampilkan berdasarkan Status saja");
        System.out.print("Pilih: ");
        switch (sc.nextLine().trim()) {
            case "1":
                System.out.print("Nama/ID: ");
                tampilDenganFilter(sc.nextLine().trim());
                break;
            case "2":
                System.out.print("Nama/ID: "); String kw = sc.nextLine().trim();
                System.out.print("Status (1=Aktif/0=Nonaktif): ");
                tampilDenganFilter(kw, sc.nextLine().trim().equals("1"));
                break;
            case "3":
                System.out.print("Tampilkan (1=Aktif/0=Nonaktif): ");
                tampilDenganFilter(sc.nextLine().trim().equals("1"));
                break;
            default: BaseManager.cetakError("Tidak valid.");
        }
    }

    // ===================== UPDATE =====================
    public void update(Scanner sc) {
        BaseManager.cetakJudul("UPDATE ANGGOTA");
        tampilSemua();
        System.out.print("\nID yang mau diupdate: ");
        Anggota target = cariById(sc.nextLine().trim().toUpperCase());
        if (target == null) { BaseManager.cetakError("ID tidak ditemukan."); return; }

        System.out.println("Jenis: " + target.getJenis());
        System.out.println("[Enter untuk skip]");

        System.out.print("Nama baru  : "); String n = sc.nextLine().trim(); if (!n.isEmpty()) target.setNama(n);
        System.out.print("No HP baru : "); String h = sc.nextLine().trim(); if (!h.isEmpty()) target.setNoHp(h);
        System.out.print("Status (1=Aktif/0=Nonaktif/Enter=skip): ");
        String s = sc.nextLine().trim();
        if (s.equals("1")) target.setAktif(true);
        else if (s.equals("0")) target.setAktif(false);

        if (target instanceof AnggotaBiasa) {
            AnggotaBiasa ab = (AnggotaBiasa) target;
            System.out.print("Divisi baru      : "); String d = sc.nextLine().trim(); if (!d.isEmpty()) ab.setDivisi(d);
            System.out.print("Tahun masuk baru : "); String t = sc.nextLine().trim();
            if (!t.isEmpty()) { try { ab.setTahunMasuk(Integer.parseInt(t)); } catch (NumberFormatException e) { BaseManager.cetakError("Tidak valid."); } }
        } else if (target instanceof Pengurus) {
            Pengurus p = (Pengurus) target;
            System.out.print("Jabatan baru : "); String j = sc.nextLine().trim(); if (!j.isEmpty()) p.setJabatan(j);
            System.out.print("Periode baru : "); String per = sc.nextLine().trim(); if (!per.isEmpty()) p.setPeriode(per);
        } else if (target instanceof Koordinator) {
            Koordinator k = (Koordinator) target;
            System.out.print("Divisi Koordinasi baru : "); String dk = sc.nextLine().trim(); if (!dk.isEmpty()) k.setDivisiKoordinasi(dk);
            System.out.print("Jumlah Anggota baru    : "); String jk = sc.nextLine().trim();
            if (!jk.isEmpty()) { try { k.setJumlahAnggota(Integer.parseInt(jk)); } catch (NumberFormatException e) { BaseManager.cetakError("Tidak valid."); } }
        }
        BaseManager.cetakOK("Data berhasil diupdate!");
    }

    // ===================== DELETE =====================
    public void hapus(Scanner sc) {
        BaseManager.cetakJudul("HAPUS ANGGOTA");
        tampilSemua();
        System.out.print("\nID yang mau dihapus: ");
        Anggota target = cariById(sc.nextLine().trim().toUpperCase());
        if (target == null) { BaseManager.cetakError("ID tidak ditemukan."); return; }
        System.out.print("Yakin hapus " + target.getNama() + "? (y/n): ");
        if (sc.nextLine().trim().equalsIgnoreCase("y")) {
            daftarAnggota.remove(target);
            BaseManager.cetakOK(target.getNama() + " berhasil dihapus!");
        } else BaseManager.cetakInfo("Dibatalkan.");
    }

    // ===================== HELPER =====================
    public Anggota cariById(String id) {
        for (Anggota a : daftarAnggota) { if (a.getIdAnggota().equalsIgnoreCase(id)) return a; }
        return null;
    }

    public ArrayList<Anggota> getDaftarAnggota() { return daftarAnggota; }

    // ===================== MENU =====================
    public void menu(Scanner sc) {
        boolean lanjut = true;
        while (lanjut) {
            System.out.println("\n+------------------------------------+");
            System.out.println("|        MENU KELOLA ANGGOTA         |");
            System.out.println("+------------------------------------+");
            System.out.println("| 1. Lihat Semua Anggota             |");
            System.out.println("| 2. Lihat Detail Anggota            |");
            System.out.println("| 3. Lihat Laporan (Interface)       |");
            System.out.println("| 4. Filter Anggota (Overloading)    |");
            System.out.println("| 5. Tambah Anggota                  |");
            System.out.println("| 6. Update Anggota                  |");
            System.out.println("| 7. Hapus Anggota                   |");
            System.out.println("| 0. Kembali                         |");
            System.out.println("+------------------------------------+");
            System.out.print("Pilih: ");
            switch (sc.nextLine().trim()) {
                case "1": tampilSemua(); break;
                case "2": lihatDetail(sc); break;
                case "3": lihatLaporan(sc); break;
                case "4": menuFilter(sc); break;
                case "5": tambah(sc); break;
                case "6": update(sc); break;
                case "7": hapus(sc); break;
                case "0": lanjut = false; break;
                default: BaseManager.cetakError("Pilihan tidak valid.");
            }
        }
    }
}