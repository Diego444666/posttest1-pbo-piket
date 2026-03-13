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

    // ===================== CREATE =====================
    public void tambah(Scanner sc) {
        BaseManager.cetakJudul("TAMBAH LOKASI PIKET");

        System.out.print("Nama Lokasi      : ");
        String nama = sc.nextLine();
        if (!Validator.isNotEmpty(nama)) {
            BaseManager.cetakError("Nama lokasi tidak boleh kosong!");
            return;
        }

        System.out.print("Deskripsi        : ");
        String desk = sc.nextLine();

        System.out.print("Lantai           : ");
        String lantai = sc.nextLine();

        System.out.print("Kapasitas (orang): ");
        int kap = 1;
        try {
            kap = Integer.parseInt(sc.nextLine().trim());
            // Validasi dilakukan oleh setter di dalam LokasiPiket
        } catch (NumberFormatException e) {
            BaseManager.cetakError("Input angka tidak valid, kapasitas diset 1.");
        }

        String id = buatID();
        daftarLokasi.add(new LokasiPiket(id,
                Validator.bersihkan(nama),
                Validator.bersihkan(desk),
                Validator.bersihkan(lantai),
                kap));

        BaseManager.cetakOK("Lokasi ditambahkan! ID: " + id);
    }

    // ===================== READ =====================
    public void tampilSemua() {
        BaseManager.cetakJudul("DAFTAR LOKASI PIKET");
        if (daftarLokasi.isEmpty()) {
            BaseManager.cetakError("Belum ada data lokasi.");
            return;
        }

        String garis = "+----------+----------------------+---------------------------+-----------+----------+";
        System.out.println(garis);
        System.out.printf("| %-8s | %-20s | %-25s | %-9s | %-8s |%n",
                "ID", "Nama Lokasi", "Deskripsi", "Lantai", "Kapasitas");
        System.out.println(garis);
        for (LokasiPiket l : daftarLokasi) {
            System.out.println(l.toString());
        }
        System.out.println(garis);
        System.out.println("Total: " + daftarLokasi.size() + " lokasi");
    }

    public void cari(Scanner sc) {
        BaseManager.cetakJudul("CARI LOKASI");
        System.out.print("Cari (nama/ID): ");
        String kata = sc.nextLine().trim().toLowerCase();

        boolean ada = false;
        String garis = "+----------+----------------------+---------------------------+-----------+----------+";
        System.out.println(garis);
        System.out.printf("| %-8s | %-20s | %-25s | %-9s | %-8s |%n",
                "ID", "Nama Lokasi", "Deskripsi", "Lantai", "Kapasitas");
        System.out.println(garis);
        for (LokasiPiket l : daftarLokasi) {
            if (l.getIdLokasi().toLowerCase().contains(kata) ||
                    l.getNamaLokasi().toLowerCase().contains(kata)) {
                System.out.println(l.toString());
                ada = true;
            }
        }
        System.out.println(garis);
        if (!ada) BaseManager.cetakError("Lokasi tidak ditemukan.");
    }

    // ===================== UPDATE =====================
    public void update(Scanner sc) {
        BaseManager.cetakJudul("UPDATE LOKASI");
        tampilSemua();

        System.out.print("\nMasukkan ID yang mau diupdate: ");
        String id = sc.nextLine().trim().toUpperCase();

        LokasiPiket target = cariById(id);
        if (target == null) {
            BaseManager.cetakError("ID tidak ditemukan.");
            return;
        }

        System.out.println("[Kosongkan/Enter jika tidak mau diubah]");

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
        String kapStr = sc.nextLine().trim();
        if (!kapStr.isEmpty() && !kapStr.equals("0")) {
            try {
                target.setKapasitasOrang(Integer.parseInt(kapStr));
            } catch (NumberFormatException e) {
                BaseManager.cetakError("Input tidak valid, dilewati.");
            }
        }

        BaseManager.cetakOK("Lokasi berhasil diupdate!");
    }

    // ===================== DELETE =====================
    public void hapus(Scanner sc) {
        BaseManager.cetakJudul("HAPUS LOKASI");
        tampilSemua();

        System.out.print("\nMasukkan ID yang mau dihapus: ");
        String id = sc.nextLine().trim().toUpperCase();

        LokasiPiket target = cariById(id);
        if (target == null) {
            BaseManager.cetakError("ID tidak ditemukan.");
            return;
        }

        System.out.print("Yakin hapus " + target.getNamaLokasi() + "? (y/n): ");
        if (sc.nextLine().trim().equalsIgnoreCase("y")) {
            daftarLokasi.remove(target);
            BaseManager.cetakOK(target.getNamaLokasi() + " berhasil dihapus!");
        } else {
            BaseManager.cetakInfo("Penghapusan dibatalkan.");
        }
    }

    // ===================== HELPER =====================
    public LokasiPiket cariById(String id) {
        for (LokasiPiket l : daftarLokasi) {
            if (l.getIdLokasi().equalsIgnoreCase(id)) return l;
        }
        return null;
    }

    public ArrayList<LokasiPiket> getDaftarLokasi() { return daftarLokasi; }

    // ===================== MENU =====================
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
                default: BaseManager.cetakError("Pilihan tidak valid.");
            }
        }
    }
}