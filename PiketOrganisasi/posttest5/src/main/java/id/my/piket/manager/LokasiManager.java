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

    private String buatID() { return String.format("LOK%03d", counterID++); }

    public void tambah(Scanner sc) {
        BaseManager.cetakJudul("TAMBAH LOKASI");
        System.out.print("Nama Lokasi  : "); String nama = sc.nextLine();
        if (!Validator.isNotEmpty(nama)) { BaseManager.cetakError("Nama tidak boleh kosong!"); return; }
        System.out.print("Deskripsi    : "); String desk  = sc.nextLine();
        System.out.print("Lantai       : "); String lantai = sc.nextLine();
        System.out.print("Kapasitas    : ");
        int kap = 1;
        try { kap = Integer.parseInt(sc.nextLine().trim()); }
        catch (NumberFormatException e) { BaseManager.cetakError("Tidak valid, diset 1."); }
        String id = buatID();
        daftarLokasi.add(new LokasiPiket(id, Validator.bersihkan(nama), Validator.bersihkan(desk), Validator.bersihkan(lantai), kap));
        BaseManager.cetakOK("Lokasi ditambahkan! ID: " + id);
    }

    public void tampilSemua() {
        BaseManager.cetakJudul("DAFTAR LOKASI PIKET");
        if (daftarLokasi.isEmpty()) { BaseManager.cetakError("Belum ada data."); return; }
        String g = "+----------+----------------------+---------------------------+-----------+----------+";
        System.out.println(g);
        System.out.printf("| %-8s | %-20s | %-25s | %-9s | %-8s |%n", "ID", "Nama Lokasi", "Deskripsi", "Lantai", "Kapasitas");
        System.out.println(g);
        for (LokasiPiket l : daftarLokasi) System.out.println(l.toString());
        System.out.println(g);
        System.out.println("Total: " + daftarLokasi.size() + " lokasi");
    }

    public void update(Scanner sc) {
        BaseManager.cetakJudul("UPDATE LOKASI");
        tampilSemua();
        System.out.print("\nID yang mau diupdate: ");
        LokasiPiket t = cariById(sc.nextLine().trim().toUpperCase());
        if (t == null) { BaseManager.cetakError("ID tidak ditemukan."); return; }
        System.out.println("[Enter untuk skip]");
        System.out.print("Nama baru      : "); String n = sc.nextLine().trim(); if (!n.isEmpty()) t.setNamaLokasi(n);
        System.out.print("Deskripsi baru : "); String d = sc.nextLine().trim(); if (!d.isEmpty()) t.setDeskripsi(d);
        System.out.print("Lantai baru    : "); String l = sc.nextLine().trim(); if (!l.isEmpty()) t.setLantai(l);
        System.out.print("Kapasitas baru : "); String k = sc.nextLine().trim();
        if (!k.isEmpty()) { try { t.setKapasitasOrang(Integer.parseInt(k)); } catch (NumberFormatException e) { BaseManager.cetakError("Tidak valid."); } }
        BaseManager.cetakOK("Lokasi diupdate!");
    }

    public void hapus(Scanner sc) {
        BaseManager.cetakJudul("HAPUS LOKASI");
        tampilSemua();
        System.out.print("\nID yang mau dihapus: ");
        LokasiPiket t = cariById(sc.nextLine().trim().toUpperCase());
        if (t == null) { BaseManager.cetakError("ID tidak ditemukan."); return; }
        System.out.print("Yakin hapus " + t.getNamaLokasi() + "? (y/n): ");
        if (sc.nextLine().trim().equalsIgnoreCase("y")) { daftarLokasi.remove(t); BaseManager.cetakOK("Berhasil dihapus!"); }
        else BaseManager.cetakInfo("Dibatalkan.");
    }

    public LokasiPiket cariById(String id) {
        for (LokasiPiket l : daftarLokasi) { if (l.getIdLokasi().equalsIgnoreCase(id)) return l; }
        return null;
    }

    public ArrayList<LokasiPiket> getDaftarLokasi() { return daftarLokasi; }

    public void menu(Scanner sc) {
        boolean lanjut = true;
        while (lanjut) {
            System.out.println("\n+----------------------------------+");
            System.out.println("|      MENU KELOLA LOKASI PIKET    |");
            System.out.println("+----------------------------------+");
            System.out.println("| 1. Lihat Semua Lokasi            |");
            System.out.println("| 2. Tambah Lokasi                 |");
            System.out.println("| 3. Update Lokasi                 |");
            System.out.println("| 4. Hapus Lokasi                  |");
            System.out.println("| 0. Kembali                       |");
            System.out.println("+----------------------------------+");
            System.out.print("Pilih: ");
            switch (sc.nextLine().trim()) {
                case "1": tampilSemua(); break;
                case "2": tambah(sc); break;
                case "3": update(sc); break;
                case "4": hapus(sc); break;
                case "0": lanjut = false; break;
                default: BaseManager.cetakError("Pilihan tidak valid.");
            }
        }
    }
}