package id.my.piket.manager;

import id.my.piket.model.Anggota;
import id.my.piket.model.JadwalPiket;
import id.my.piket.model.LokasiPiket;
import java.util.ArrayList;
import java.util.Scanner;

public class JadwalManager {

    private ArrayList<JadwalPiket> daftarJadwal;
    private AnggotaManager anggotaManager;
    private LokasiManager lokasiManager;
    private int counterID;

    private static final String[] HARI   = {"Senin","Selasa","Rabu","Kamis","Jumat","Sabtu","Minggu"};
    private static final String[] STATUS = {"Terjadwal","Selesai","Absen"};

    public JadwalManager(AnggotaManager anggotaManager, LokasiManager lokasiManager) {
        this.daftarJadwal    = new ArrayList<>();
        this.anggotaManager  = anggotaManager;
        this.lokasiManager   = lokasiManager;
        this.counterID       = 1;
        isiDataAwal();
    }

    private void isiDataAwal() {
        daftarJadwal.add(new JadwalPiket("JDW001","AGT001","Budi Santoso","LOK001","Ruang Rapat Utama","Senin","07:00","08:00","Terjadwal","-"));
        daftarJadwal.add(new JadwalPiket("JDW002","AGT002","Siti Rahayu","LOK002","Dapur Sekretariat","Senin","07:00","08:00","Selesai","Bersih"));
        daftarJadwal.add(new JadwalPiket("JDW003","AGT003","Ahmad Fauzi","LOK003","Toilet Pria","Selasa","07:00","08:00","Terjadwal","-"));
        daftarJadwal.add(new JadwalPiket("JDW004","AGT004","Dewi Lestari","LOK004","Toilet Wanita","Selasa","07:00","08:00","Absen","Sakit"));
        daftarJadwal.add(new JadwalPiket("JDW005","AGT001","Budi Santoso","LOK006","Aula Kegiatan","Rabu","08:00","09:00","Terjadwal","-"));
        counterID = 6;
    }

    private String buatID() { return String.format("JDW%03d", counterID++); }

    public void tambah(Scanner sc) {
        BaseManager.cetakJudul("TAMBAH JADWAL PIKET");
        anggotaManager.tampilSemua();
        System.out.print("\nID Anggota: ");
        Anggota anggota = anggotaManager.cariById(sc.nextLine().trim().toUpperCase());
        if (anggota == null) { BaseManager.cetakError("Anggota tidak ditemukan."); return; }
        if (!anggota.isAktif()) { BaseManager.cetakError("Anggota tidak aktif."); return; }

        lokasiManager.tampilSemua();
        System.out.print("\nID Lokasi: ");
        LokasiPiket lokasi = lokasiManager.cariById(sc.nextLine().trim().toUpperCase());
        if (lokasi == null) { BaseManager.cetakError("Lokasi tidak ditemukan."); return; }

        System.out.println("\nPilih Hari:");
        for (int i = 0; i < HARI.length; i++) System.out.println("  " + (i+1) + ". " + HARI[i]);
        System.out.print("Pilihan (1-7): ");
        String hari = "Senin";
        try { int p = Integer.parseInt(sc.nextLine().trim()) - 1; if (p >= 0 && p < HARI.length) hari = HARI[p]; }
        catch (NumberFormatException e) { BaseManager.cetakError("Tidak valid, diset Senin."); }

        System.out.print("Jam Mulai   (07:00): "); String jm = sc.nextLine().trim(); if (jm.isEmpty()) jm = "07:00";
        System.out.print("Jam Selesai (08:00): "); String js = sc.nextLine().trim(); if (js.isEmpty()) js = "08:00";
        System.out.print("Keterangan          : "); String ket = sc.nextLine().trim(); if (ket.isEmpty()) ket = "-";

        String id = buatID();
        daftarJadwal.add(new JadwalPiket(id, anggota.getIdAnggota(), anggota.getNama(),
                lokasi.getIdLokasi(), lokasi.getNamaLokasi(), hari, jm, js, "Terjadwal", ket));
        BaseManager.cetakOK("Jadwal ditambahkan! ID: " + id);
    }

    public void tampilSemua() {
        BaseManager.cetakJudul("DAFTAR JADWAL PIKET");
        if (daftarJadwal.isEmpty()) { BaseManager.cetakError("Belum ada jadwal."); return; }
        String garis = "+----------+--------------------+----------------------+---------+-----------+------------+--------------+";
        System.out.println(garis);
        System.out.printf("| %-8s | %-18s | %-20s | %-7s | %-9s | %-10s | %-12s |%n", "ID","Anggota","Lokasi","Hari","Jam","Status","Keterangan");
        System.out.println(garis);
        for (JadwalPiket j : daftarJadwal) System.out.println(j.toString());
        System.out.println(garis);
        System.out.println("Total: " + daftarJadwal.size() + " jadwal");
    }

    public void cari(Scanner sc) {
        BaseManager.cetakJudul("CARI JADWAL");
        System.out.println("1. Nama Anggota  2. Hari  3. Status");
        System.out.print("Pilih: "); String pilih = sc.nextLine().trim();
        System.out.print("Keyword: "); String kata = sc.nextLine().trim().toLowerCase();
        String garis = "+----------+--------------------+----------------------+---------+-----------+------------+--------------+";
        System.out.println(garis);
        System.out.printf("| %-8s | %-18s | %-20s | %-7s | %-9s | %-10s | %-12s |%n", "ID","Anggota","Lokasi","Hari","Jam","Status","Keterangan");
        System.out.println(garis);
        boolean ada = false;
        for (JadwalPiket j : daftarJadwal) {
            boolean cocok = (pilih.equals("1") && j.getNamaAnggota().toLowerCase().contains(kata))
                    || (pilih.equals("2") && j.getHari().toLowerCase().contains(kata))
                    || (pilih.equals("3") && j.getStatus().toLowerCase().contains(kata));
            if (cocok) { System.out.println(j.toString()); ada = true; }
        }
        System.out.println(garis);
        if (!ada) BaseManager.cetakError("Tidak ditemukan.");
    }

    public void update(Scanner sc) {
        BaseManager.cetakJudul("UPDATE JADWAL");
        tampilSemua();
        System.out.print("\nID Jadwal yang mau diupdate: ");
        JadwalPiket target = cariById(sc.nextLine().trim().toUpperCase());
        if (target == null) { BaseManager.cetakError("ID tidak ditemukan."); return; }

        System.out.println("1.Ganti Anggota  2.Ganti Lokasi  3.Ganti Hari  4.Ganti Jam  5.Status  6.Keterangan");
        System.out.print("Pilih: ");
        switch (sc.nextLine().trim()) {
            case "1":
                anggotaManager.tampilSemua();
                System.out.print("ID Anggota baru: ");
                Anggota a = anggotaManager.cariById(sc.nextLine().trim().toUpperCase());
                if (a != null) { target.setIdAnggota(a.getIdAnggota()); target.setNamaAnggota(a.getNama()); BaseManager.cetakOK("Anggota diupdate!"); }
                else BaseManager.cetakError("Tidak ditemukan.");
                break;
            case "2":
                lokasiManager.tampilSemua();
                System.out.print("ID Lokasi baru: ");
                LokasiPiket l = lokasiManager.cariById(sc.nextLine().trim().toUpperCase());
                if (l != null) { target.setIdLokasi(l.getIdLokasi()); target.setNamaLokasi(l.getNamaLokasi()); BaseManager.cetakOK("Lokasi diupdate!"); }
                else BaseManager.cetakError("Tidak ditemukan.");
                break;
            case "3":
                for (int i = 0; i < HARI.length; i++) System.out.println("  " + (i+1) + ". " + HARI[i]);
                System.out.print("Pilih (1-7): ");
                try { int p = Integer.parseInt(sc.nextLine().trim()) - 1; if (p >= 0 && p < HARI.length) { target.setHari(HARI[p]); BaseManager.cetakOK("Hari diupdate!"); } }
                catch (NumberFormatException e) { BaseManager.cetakError("Tidak valid."); }
                break;
            case "4":
                System.out.print("Jam Mulai  : "); String jm = sc.nextLine().trim(); if (Validator.isValidJam(jm)) target.setJamMulai(jm);
                System.out.print("Jam Selesai: "); String js = sc.nextLine().trim(); if (Validator.isValidJam(js)) target.setJamSelesai(js);
                BaseManager.cetakOK("Jam diupdate!");
                break;
            case "5":
                for (int i = 0; i < STATUS.length; i++) System.out.println("  " + (i+1) + ". " + STATUS[i]);
                System.out.print("Pilih (1-3): ");
                try { int p = Integer.parseInt(sc.nextLine().trim()) - 1; if (p >= 0 && p < STATUS.length) { target.setStatus(STATUS[p]); BaseManager.cetakOK("Status diupdate!"); } }
                catch (NumberFormatException e) { BaseManager.cetakError("Tidak valid."); }
                break;
            case "6":
                System.out.print("Keterangan baru: "); String ket = sc.nextLine().trim();
                if (!ket.isEmpty()) { target.setKeterangan(ket); BaseManager.cetakOK("Keterangan diupdate!"); }
                break;
            default: BaseManager.cetakError("Pilihan tidak valid.");
        }
    }

    public void hapus(Scanner sc) {
        BaseManager.cetakJudul("HAPUS JADWAL");
        tampilSemua();
        System.out.print("\nID Jadwal yang mau dihapus: ");
        JadwalPiket target = cariById(sc.nextLine().trim().toUpperCase());
        if (target == null) { BaseManager.cetakError("ID tidak ditemukan."); return; }
        System.out.print("Yakin hapus " + target.getIdJadwal() + "? (y/n): ");
        if (sc.nextLine().trim().equalsIgnoreCase("y")) { daftarJadwal.remove(target); BaseManager.cetakOK("Jadwal berhasil dihapus!"); }
        else BaseManager.cetakInfo("Dibatalkan.");
    }

    public void rekap() {
        BaseManager.cetakJudul("REKAP JADWAL PIKET MINGGUAN");
        int terjadwal = 0, selesai = 0, absen = 0;
        for (JadwalPiket j : daftarJadwal) {
            if (j.getStatus().equals("Terjadwal")) terjadwal++;
            else if (j.getStatus().equals("Selesai")) selesai++;
            else absen++;
        }
        System.out.println("Total    : " + daftarJadwal.size());
        System.out.println("Terjadwal: " + terjadwal);
        System.out.println("Selesai  : " + selesai);
        System.out.println("Absen    : " + absen);
        System.out.println("\n--- Per Hari ---");
        for (String h : HARI) {
            long count = 0;
            for (JadwalPiket j : daftarJadwal) if (j.getHari().equals(h)) count++;
            if (count > 0) System.out.println(h + " : " + count + " jadwal");
        }
    }

    public JadwalPiket cariById(String id) {
        for (JadwalPiket j : daftarJadwal) { if (j.getIdJadwal().equalsIgnoreCase(id)) return j; }
        return null;
    }

    public void menu(Scanner sc) {
        boolean lanjut = true;
        while (lanjut) {
            System.out.println("\n+----------------------------------+");
            System.out.println("|     MENU KELOLA JADWAL PIKET     |");
            System.out.println("+----------------------------------+");
            System.out.println("| 1. Lihat Semua Jadwal            |");
            System.out.println("| 2. Cari Jadwal                   |");
            System.out.println("| 3. Tambah Jadwal                 |");
            System.out.println("| 4. Update Jadwal                 |");
            System.out.println("| 5. Hapus Jadwal                  |");
            System.out.println("| 6. Rekap Mingguan                |");
            System.out.println("| 0. Kembali                       |");
            System.out.println("+----------------------------------+");
            System.out.print("Pilih: ");
            switch (sc.nextLine().trim()) {
                case "1": tampilSemua(); break;
                case "2": cari(sc); break;
                case "3": tambah(sc); break;
                case "4": update(sc); break;
                case "5": hapus(sc); break;
                case "6": rekap(); break;
                case "0": lanjut = false; break;
                default: BaseManager.cetakError("Pilihan tidak valid.");
            }
        }
    }
}