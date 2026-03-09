package id.my.piket.manager;

import id.my.piket.model.LokasiPiket;
import java.util.ArrayList;
import java.util.Scanner;

public class LokasiManager {
    private ArrayList<LokasiPiket> daftarLokasi;
    private int counterID;

    public LokasiManager() {
        this.daftarLokasi = new ArrayList<>();
        this.counterID = 1;
        isiDataAwal();
    }

    private void isiDataAwal() {
        daftarLokasi.add(new LokasiPiket("LOK001", "Ruang Rapat Utama", "Ruang rapat lantai 1", "Lantai 1", 3));
        daftarLokasi.add(new LokasiPiket("LOK002", "Dapur Sekretariat", "Area dapur dan pantry", "Lantai 1", 2));
        daftarLokasi.add(new LokasiPiket("LOK003", "Toilet Pria",       "Toilet pria lantai 1",  "Lantai 1", 2));
        daftarLokasi.add(new LokasiPiket("LOK004", "Toilet Wanita",     "Toilet wanita lantai 1","Lantai 1", 2));
        daftarLokasi.add(new LokasiPiket("LOK005", "Ruang Arsip",       "Ruang penyimpanan dok", "Lantai 2", 2));
        daftarLokasi.add(new LokasiPiket("LOK006", "Aula Kegiatan",     "Ruang serbaguna",       "Lantai 2", 4));
        counterID = 7;
    }

    private String buatID() {
        return String.format("LOK%03d", counterID++);
    }

    // ======= CREATE =======
    public void tambah(Scanner sc) {
        System.out.println("\n=== TAMBAH LOKASI PIKET ===");

        System.out.print("Nama Lokasi  : ");
        String nama = sc.nextLine().trim();
        if (nama.isEmpty()) { System.out.println("[!] Nama tidak boleh kosong!"); return; }

        System.out.print("Deskripsi    : ");
        String desk = sc.nextLine().trim();

        System.out.print("Lantai       : ");
        String lantai = sc.nextLine().trim();

        System.out.print("Kapasitas (orang) : ");
        int kap = 1;
        try { kap = Integer.parseInt(sc.nextLine().trim()); }
        catch (NumberFormatException e) { System.out.println("[!] Input angka tidak valid, diset 1."); }

        String id = buatID();
        daftarLokasi.add(new LokasiPiket(id, nama, desk, lantai, kap));
        System.out.println("[OK] Lokasi ditambahkan! ID: " + id);
    }

    // ======= READ =======
    public void tampilSemua() {
        System.out.println("\n=== DAFTAR LOKASI PIKET ===");
        if (daftarLokasi.isEmpty()) { System.out.println("[!] Belum ada data."); return; }

        System.out.println("+----------+----------------------+---------------------------+-----------+----------+");
        System.out.printf("| %-8s | %-20s | %-25s | %-9s | %-8s |%n", "ID", "Nama Lokasi", "Deskripsi", "Lantai", "Kapasitas");
        System.out.println("+----------+----------------------+---------------------------+-----------+----------+");
        for (LokasiPiket l : daftarLokasi) {
            System.out.println(l.toString());
        }
        System.out.println("+----------+----------------------+---------------------------+-----------+----------+");
        System.out.println("Total: " + daftarLokasi.size() + " lokasi");
    }

    public void cari(Scanner sc) {
        System.out.print("\nCari (nama/ID): ");
        String kata = sc.nextLine().trim().toLowerCase();
        boolean ada = false;
        System.out.println("+----------+----------------------+---------------------------+-----------+----------+");
        System.out.printf("| %-8s | %-20s | %-25s | %-9s | %-8s |%n", "ID", "Nama Lokasi", "Deskripsi", "Lantai", "Kapasitas");
        System.out.println("+----------+----------------------+---------------------------+-----------+----------+");
        for (LokasiPiket l : daftarLokasi) {
            if (l.getIdLokasi().toLowerCase().contains(kata) || l.getNamaLokasi().toLowerCase().contains(kata)) {
                System.out.println(l.toString());
                ada = true;
            }
        }
        System.out.println("+----------+----------------------+---------------------------+-----------+----------+");
        if (!ada) System.out.println("[!] Tidak ditemukan.");
    }

    // ======= UPDATE =======
    public void update(Scanner sc) {
        System.out.println("\n=== UPDATE LOKASI ===");
        tampilSemua();
        System.out.print("\nMasukkan ID yang mau diupdate: ");
        String id = sc.nextLine().trim().toUpperCase();

        LokasiPiket target = cariById(id);
        if (target == null) { System.out.println("[!] ID tidak ditemukan."); return; }

        System.out.println("[Kosongkan Enter kalau tidak mau ubah]");

        System.out.print("Nama baru      : ");
        String nama = sc.nextLine().trim();
        if (!nama.isEmpty()) target.setNamaLokasi(nama);

        System.out.print("Deskripsi baru : ");
        String desk = sc.nextLine().trim();
        if (!desk.isEmpty()) target.setDeskripsi(desk);

        System.out.print("Lantai baru    : ");
        String lantai = sc.nextLine().trim();
        if (!lantai.isEmpty()) target.setLantai(lantai);

        System.out.print("Kapasitas baru (0=skip): ");
        String kap = sc.nextLine().trim();
        if (!kap.isEmpty() && !kap.equals("0")) {
            try { target.setKapasitasOrang(Integer.parseInt(kap)); }
            catch (NumberFormatException e) { System.out.println("[!] Input tidak valid, dilewati."); }
        }

        System.out.println("[OK] Lokasi berhasil diupdate!");
    }

    // ======= DELETE =======
    public void hapus(Scanner sc) {
        System.out.println("\n=== HAPUS LOKASI ===");
        tampilSemua();
        System.out.print("\nMasukkan ID yang mau dihapus: ");
        String id = sc.nextLine().trim().toUpperCase();

        LokasiPiket target = cariById(id);
        if (target == null) { System.out.println("[!] ID tidak ditemukan."); return; }

        System.out.print("Yakin hapus " + target.getNamaLokasi() + "? (y/n): ");
        if (sc.nextLine().trim().equalsIgnoreCase("y")) {
            daftarLokasi.remove(target);
            System.out.println("[OK] " + target.getNamaLokasi() + " berhasil dihapus!");
        } else {
            System.out.println("[i] Dibatalkan.");
        }
    }

    // ======= HELPER =======
    public LokasiPiket cariById(String id) {
        for (LokasiPiket l : daftarLokasi) {
            if (l.getIdLokasi().equalsIgnoreCase(id)) return l;
        }
        return null;
    }

    public ArrayList<LokasiPiket> getDaftarLokasi() { return daftarLokasi; }

    // ======= MENU =======
    public void menu(Scanner sc) {
        boolean lanjut = true;
        while (lanjut) {
            System.out.println("\n+----------------------------------+");
            System.out.println("|      MENU KELOLA LOKASI PIKET    |");
            System.out.println("+----------------------------------+");
            System.out.println("| 1. Lihat Semua Lokasi            |");
            System.out.println("| 2. Cari Lokasi                   |");
            System.out.println("| 3. Tambah Lokasi                 |");
            System.out.println("| 4. Update Lokasi                 |");
            System.out.println("| 5. Hapus Lokasi                  |");
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