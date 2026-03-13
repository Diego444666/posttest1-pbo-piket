package id.my.piket.manager;

import id.my.piket.model.Anggota;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * AnggotaManager - mengelola CRUD data Anggota
 *
 * Kelas ini menggunakan:
 * - BaseManager.cetakJudul() → PROTECTED method (boleh karena package sama)
 * - Validator.isNotEmpty()   → DEFAULT method (boleh karena package sama)
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
        daftarAnggota.add(new Anggota("AGT001", "Budi Santoso",  "Divisi Kebersihan", "081234567890", true));
        daftarAnggota.add(new Anggota("AGT002", "Siti Rahayu",   "Divisi Logistik",   "081234567891", true));
        daftarAnggota.add(new Anggota("AGT003", "Ahmad Fauzi",   "Divisi Humas",      "081234567892", true));
        daftarAnggota.add(new Anggota("AGT004", "Dewi Lestari",  "Divisi Kebersihan", "081234567893", true));
        daftarAnggota.add(new Anggota("AGT005", "Riko Pratama",  "Divisi Acara",      "081234567894", false));
        counterID = 6;
    }

    private String buatID() {
        return String.format("AGT%03d", counterID++);
    }

    // ===================== CREATE =====================
    public void tambah(Scanner sc) {
        // Menggunakan PROTECTED method dari BaseManager (package sama)
        BaseManager.cetakJudul("TAMBAH ANGGOTA BARU");

        System.out.print("Nama Anggota : ");
        String nama = sc.nextLine();

        // Menggunakan DEFAULT method dari Validator (package sama)
        if (!Validator.isNotEmpty(nama)) {
            BaseManager.cetakError("Nama tidak boleh kosong!");
            return;
        }

        System.out.print("Divisi       : ");
        String divisi = sc.nextLine();
        if (!Validator.isNotEmpty(divisi)) {
            BaseManager.cetakError("Divisi tidak boleh kosong!");
            return;
        }

        System.out.print("No HP        : ");
        String noHp = sc.nextLine();

        System.out.print("Status Aktif? (1=Ya / 0=Tidak) : ");
        boolean aktif = sc.nextLine().trim().equals("1");

        String id = buatID();
        // Setter dengan validasi akan otomatis berjalan di dalam constructor
        daftarAnggota.add(new Anggota(id, Validator.bersihkan(nama),
                Validator.bersihkan(divisi), noHp, aktif));

        BaseManager.cetakOK("Anggota berhasil ditambahkan! ID: " + id);
    }

    // ===================== READ =====================
    public void tampilSemua() {
        BaseManager.cetakJudul("DAFTAR ANGGOTA ORGANISASI");
        if (daftarAnggota.isEmpty()) {
            BaseManager.cetakError("Belum ada data anggota.");
            return;
        }

        String garis = "+----------+----------------------+-----------------+---------------+----------+";
        System.out.println(garis);
        System.out.printf("| %-8s | %-20s | %-15s | %-13s | %-8s |%n",
                "ID", "Nama", "Divisi", "No HP", "Status");
        System.out.println(garis);
        for (Anggota a : daftarAnggota) {
            System.out.println(a.toString());
        }
        System.out.println(garis);
        System.out.println("Total: " + daftarAnggota.size() + " anggota");
    }

    public void cari(Scanner sc) {
        BaseManager.cetakJudul("CARI ANGGOTA");
        System.out.print("Cari (nama/ID): ");
        String kata = sc.nextLine().trim().toLowerCase();

        boolean ada = false;
        String garis = "+----------+----------------------+-----------------+---------------+----------+";
        System.out.println(garis);
        System.out.printf("| %-8s | %-20s | %-15s | %-13s | %-8s |%n",
                "ID", "Nama", "Divisi", "No HP", "Status");
        System.out.println(garis);
        for (Anggota a : daftarAnggota) {
            if (a.getIdAnggota().toLowerCase().contains(kata) ||
                    a.getNama().toLowerCase().contains(kata)) {
                System.out.println(a.toString());
                ada = true;
            }
        }
        System.out.println(garis);
        if (!ada) BaseManager.cetakError("Anggota tidak ditemukan.");
    }

    // ===================== UPDATE =====================
    public void update(Scanner sc) {
        BaseManager.cetakJudul("UPDATE ANGGOTA");
        tampilSemua();

        System.out.print("\nMasukkan ID yang mau diupdate: ");
        String id = sc.nextLine().trim().toUpperCase();

        Anggota target = cariById(id);
        if (target == null) {
            BaseManager.cetakError("ID tidak ditemukan.");
            return;
        }

        System.out.println("Data sekarang → " + target.getNama() + " | " + target.getDivisi());
        System.out.println("[Kosongkan/Enter jika tidak mau diubah]");

        System.out.print("Nama baru   : ");
        String nama = sc.nextLine().trim();
        // Setter otomatis validasi - ini kekuatan Encapsulation!
        if (!nama.isEmpty()) target.setNama(nama);

        System.out.print("Divisi baru : ");
        String divisi = sc.nextLine().trim();
        if (!divisi.isEmpty()) target.setDivisi(divisi);

        System.out.print("No HP baru  : ");
        String noHp = sc.nextLine().trim();
        if (!noHp.isEmpty()) target.setNoHp(noHp);

        System.out.print("Status (1=Aktif / 0=Nonaktif / Enter=skip): ");
        String s = sc.nextLine().trim();
        if (s.equals("1")) target.setAktif(true);
        else if (s.equals("0")) target.setAktif(false);

        BaseManager.cetakOK("Data berhasil diupdate!");
    }

    // ===================== DELETE =====================
    public void hapus(Scanner sc) {
        BaseManager.cetakJudul("HAPUS ANGGOTA");
        tampilSemua();

        System.out.print("\nMasukkan ID yang mau dihapus: ");
        String id = sc.nextLine().trim().toUpperCase();

        Anggota target = cariById(id);
        if (target == null) {
            BaseManager.cetakError("ID tidak ditemukan.");
            return;
        }

        System.out.print("Yakin hapus " + target.getNama() + "? (y/n): ");
        if (sc.nextLine().trim().equalsIgnoreCase("y")) {
            daftarAnggota.remove(target);
            BaseManager.cetakOK(target.getNama() + " berhasil dihapus!");
        } else {
            BaseManager.cetakInfo("Penghapusan dibatalkan.");
        }
    }

    // ===================== HELPER =====================
    public Anggota cariById(String id) {
        for (Anggota a : daftarAnggota) {
            if (a.getIdAnggota().equalsIgnoreCase(id)) return a;
        }
        return null;
    }

    public ArrayList<Anggota> getDaftarAnggota() { return daftarAnggota; }

    // ===================== MENU =====================
    public void menu(Scanner sc) {
        boolean lanjut = true;
        while (lanjut) {
            System.out.println("\n+----------------------------------+");
            System.out.println("|       MENU KELOLA ANGGOTA        |");
            System.out.println("+----------------------------------+");
            System.out.println("| 1. Lihat Semua Anggota           |");
            System.out.println("| 2. Cari Anggota                  |");
            System.out.println("| 3. Tambah Anggota                |");
            System.out.println("| 4. Update Anggota                |");
            System.out.println("| 5. Hapus Anggota                 |");
            System.out.println("| 0. Kembali                       |");
            System.out.println("+----------------------------------+");
            System.out.print("Pilih: ");
            switch (sc.nextLine().trim()) {
                case "1": tampilSemua(); break;
                case "2": cari(sc); break;
                case "3": tambah(sc); break;
                case "4": update(sc); break;
                case "5": hapus(sc); break;
                case "0": lanjut = false; break;
                default: BaseManager.cetakError("Pilihan tidak valid.");
            }
        }
    }
}