package id.my.piket.manager;

import id.my.piket.model.Anggota;
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

    // Data awal biar langsung ada isi waktu program dibuka
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

    // ======= CREATE =======
    public void tambah(Scanner sc) {
        System.out.println("\n=== TAMBAH ANGGOTA BARU ===");

        System.out.print("Nama Anggota : ");
        String nama = sc.nextLine().trim();
        if (nama.isEmpty()) { System.out.println("[!] Nama tidak boleh kosong!"); return; }

        System.out.print("Divisi       : ");
        String divisi = sc.nextLine().trim();
        if (divisi.isEmpty()) { System.out.println("[!] Divisi tidak boleh kosong!"); return; }

        System.out.print("No HP        : ");
        String noHp = sc.nextLine().trim();

        System.out.print("Status Aktif? (1=Ya / 0=Tidak) : ");
        boolean aktif = sc.nextLine().trim().equals("1");

        String id = buatID();
        daftarAnggota.add(new Anggota(id, nama, divisi, noHp, aktif));
        System.out.println("[OK] Anggota berhasil ditambahkan! ID: " + id);
    }

    // ======= READ =======
    public void tampilSemua() {
        System.out.println("\n=== DAFTAR ANGGOTA ===");
        if (daftarAnggota.isEmpty()) { System.out.println("[!] Belum ada data."); return; }

        System.out.println("+----------+----------------------+-----------------+---------------+----------+");
        System.out.printf("| %-8s | %-20s | %-15s | %-13s | %-8s |%n", "ID", "Nama", "Divisi", "No HP", "Status");
        System.out.println("+----------+----------------------+-----------------+---------------+----------+");
        for (Anggota a : daftarAnggota) {
            System.out.println(a.toString());
        }
        System.out.println("+----------+----------------------+-----------------+---------------+----------+");
        System.out.println("Total: " + daftarAnggota.size() + " anggota");
    }

    public void cari(Scanner sc) {
        System.out.print("\nCari (nama/ID): ");
        String kata = sc.nextLine().trim().toLowerCase();
        boolean ada = false;
        System.out.println("+----------+----------------------+-----------------+---------------+----------+");
        System.out.printf("| %-8s | %-20s | %-15s | %-13s | %-8s |%n", "ID", "Nama", "Divisi", "No HP", "Status");
        System.out.println("+----------+----------------------+-----------------+---------------+----------+");
        for (Anggota a : daftarAnggota) {
            if (a.getIdAnggota().toLowerCase().contains(kata) || a.getNama().toLowerCase().contains(kata)) {
                System.out.println(a.toString());
                ada = true;
            }
        }
        System.out.println("+----------+----------------------+-----------------+---------------+----------+");
        if (!ada) System.out.println("[!] Tidak ditemukan.");
    }

    // ======= UPDATE =======
    public void update(Scanner sc) {
        System.out.println("\n=== UPDATE ANGGOTA ===");
        tampilSemua();
        System.out.print("\nMasukkan ID yang mau diupdate: ");
        String id = sc.nextLine().trim().toUpperCase();

        Anggota target = cariById(id);
        if (target == null) { System.out.println("[!] ID tidak ditemukan."); return; }

        System.out.println("Data sekarang -> Nama: " + target.getNama() + " | Divisi: " + target.getDivisi());
        System.out.println("[Kosongkan Enter kalau tidak mau ubah]");

        System.out.print("Nama baru   : ");
        String nama = sc.nextLine().trim();
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

        System.out.println("[OK] Data berhasil diupdate!");
    }

    // ======= DELETE =======
    public void hapus(Scanner sc) {
        System.out.println("\n=== HAPUS ANGGOTA ===");
        tampilSemua();
        System.out.print("\nMasukkan ID yang mau dihapus: ");
        String id = sc.nextLine().trim().toUpperCase();

        Anggota target = cariById(id);
        if (target == null) { System.out.println("[!] ID tidak ditemukan."); return; }

        System.out.print("Yakin hapus " + target.getNama() + "? (y/n): ");
        if (sc.nextLine().trim().equalsIgnoreCase("y")) {
            daftarAnggota.remove(target);
            System.out.println("[OK] " + target.getNama() + " berhasil dihapus!");
        } else {
            System.out.println("[i] Dibatalkan.");
        }
    }

    // ======= HELPER =======
    public Anggota cariById(String id) {
        for (Anggota a : daftarAnggota) {
            if (a.getIdAnggota().equalsIgnoreCase(id)) return a;
        }
        return null;
    }

    public ArrayList<Anggota> getDaftarAnggota() { return daftarAnggota; }

    // ======= MENU =======
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
                default: System.out.println("[!] Pilihan tidak valid.");
            }
        }
    }
}