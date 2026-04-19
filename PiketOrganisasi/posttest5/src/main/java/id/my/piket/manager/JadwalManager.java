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

    public JadwalManager(AnggotaManager am, LokasiManager lm) {
        this.daftarJadwal   = new ArrayList<>();
        this.anggotaManager = am;
        this.lokasiManager  = lm;
        this.counterID      = 1;
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
        Anggota a = anggotaManager.cariById(sc.nextLine().trim().toUpperCase());
        if (a == null) { BaseManager.cetakError("Anggota tidak ditemukan."); return; }
        if (!a.isAktif()) { BaseManager.cetakError("Anggota tidak aktif."); return; }

        lokasiManager.tampilSemua();
        System.out.print("\nID Lokasi: ");
        LokasiPiket l = lokasiManager.cariById(sc.nextLine().trim().toUpperCase());
        if (l == null) { BaseManager.cetakError("Lokasi tidak ditemukan."); return; }

        System.out.println("\nPilih Hari:");
        for (int i = 0; i < HARI.length; i++) System.out.println("  " + (i+1) + ". " + HARI[i]);
        System.out.print("Pilihan (1-7): ");
        String hari = "Senin";
        try { int p = Integer.parseInt(sc.nextLine().trim()) - 1; if (p >= 0 && p < HARI.length) hari = HARI[p]; }
        catch (NumberFormatException e) { BaseManager.cetakError("Tidak valid, diset Senin."); }

        System.out.print("Jam Mulai   : "); String jm = sc.nextLine().trim(); if (jm.isEmpty()) jm = "07:00";
        System.out.print("Jam Selesai : "); String js = sc.nextLine().trim(); if (js.isEmpty()) js = "08:00";
        System.out.print("Keterangan  : "); String ket = sc.nextLine().trim(); if (ket.isEmpty()) ket = "-";

        String id = buatID();
        daftarJadwal.add(new JadwalPiket(id, a.getIdAnggota(), a.getNama(),
                l.getIdLokasi(), l.getNamaLokasi(), hari, jm, js, "Terjadwal", ket));
        BaseManager.cetakOK("Jadwal ditambahkan! ID: " + id);
    }

    public void tampilSemua() {
        BaseManager.cetakJudul("DAFTAR JADWAL PIKET");
        if (daftarJadwal.isEmpty()) { BaseManager.cetakError("Belum ada jadwal."); return; }
        String g = "+----------+--------------------+----------------------+---------+-----------+------------+--------------+";
        System.out.println(g);
        System.out.printf("| %-8s | %-18s | %-20s | %-7s | %-9s | %-10s | %-12s |%n","ID","Anggota","Lokasi","Hari","Jam","Status","Keterangan");
        System.out.println(g);
        for (JadwalPiket j : daftarJadwal) System.out.println(j.toString());
        System.out.println(g);
        System.out.println("Total: " + daftarJadwal.size() + " jadwal");
    }

    public void update(Scanner sc) {
        BaseManager.cetakJudul("UPDATE JADWAL");
        tampilSemua();
        System.out.print("\nID Jadwal: ");
        JadwalPiket t = cariById(sc.nextLine().trim().toUpperCase());
        if (t == null) { BaseManager.cetakError("ID tidak ditemukan."); return; }

        System.out.println("1.Anggota  2.Lokasi  3.Hari  4.Jam  5.Status  6.Keterangan");
        System.out.print("Pilih: ");
        switch (sc.nextLine().trim()) {
            case "1":
                anggotaManager.tampilSemua();
                System.out.print("ID Anggota baru: ");
                Anggota na = anggotaManager.cariById(sc.nextLine().trim().toUpperCase());
                if (na != null) { t.setIdAnggota(na.getIdAnggota()); t.setNamaAnggota(na.getNama()); BaseManager.cetakOK("Diupdate!"); }
                else BaseManager.cetakError("Tidak ditemukan.");
                break;
            case "2":
                lokasiManager.tampilSemua();
                System.out.print("ID Lokasi baru: ");
                LokasiPiket nl = lokasiManager.cariById(sc.nextLine().trim().toUpperCase());
                if (nl != null) { t.setIdLokasi(nl.getIdLokasi()); t.setNamaLokasi(nl.getNamaLokasi()); BaseManager.cetakOK("Diupdate!"); }
                else BaseManager.cetakError("Tidak ditemukan.");
                break;
            case "3":
                for (int i = 0; i < HARI.length; i++) System.out.println("  " + (i+1) + ". " + HARI[i]);
                System.out.print("Pilih (1-7): ");
                try { int p = Integer.parseInt(sc.nextLine().trim()) - 1; if (p >= 0 && p < HARI.length) { t.setHari(HARI[p]); BaseManager.cetakOK("Hari diupdate!"); } }
                catch (NumberFormatException e) { BaseManager.cetakError("Tidak valid."); }
                break;
            case "4":
                System.out.print("Jam Mulai  : "); String jm = sc.nextLine().trim(); if (Validator.isValidJam(jm)) t.setJamMulai(jm);
                System.out.print("Jam Selesai: "); String js = sc.nextLine().trim(); if (Validator.isValidJam(js)) t.setJamSelesai(js);
                BaseManager.cetakOK("Jam diupdate!");
                break;
            case "5":
                for (int i = 0; i < STATUS.length; i++) System.out.println("  " + (i+1) + ". " + STATUS[i]);
                System.out.print("Pilih (1-3): ");
                try { int p = Integer.parseInt(sc.nextLine().trim()) - 1; if (p >= 0 && p < STATUS.length) { t.setStatus(STATUS[p]); BaseManager.cetakOK("Status diupdate!"); } }
                catch (NumberFormatException e) { BaseManager.cetakError("Tidak valid."); }
                break;
            case "6":
                System.out.print("Keterangan baru: "); String ket = sc.nextLine().trim();
                if (!ket.isEmpty()) { t.setKeterangan(ket); BaseManager.cetakOK("Keterangan diupdate!"); }
                break;
            default: BaseManager.cetakError("Tidak valid.");
        }
    }

    public void hapus(Scanner sc) {
        BaseManager.cetakJudul("HAPUS JADWAL");
        tampilSemua();
        System.out.print("\nID Jadwal: ");
        JadwalPiket t = cariById(sc.nextLine().trim().toUpperCase());
        if (t == null) { BaseManager.cetakError("ID tidak ditemukan."); return; }
        System.out.print("Yakin hapus " + t.getIdJadwal() + "? (y/n): ");
        if (sc.nextLine().trim().equalsIgnoreCase("y")) { daftarJadwal.remove(t); BaseManager.cetakOK("Jadwal dihapus!"); }
        else BaseManager.cetakInfo("Dibatalkan.");
    }

    public void rekap() {
        BaseManager.cetakJudul("REKAP JADWAL PIKET MINGGUAN");
        int tj = 0, sl = 0, ab = 0;
        for (JadwalPiket j : daftarJadwal) {
            if (j.getStatus().equals("Terjadwal")) tj++;
            else if (j.getStatus().equals("Selesai")) sl++;
            else ab++;
        }
        System.out.println("Total: " + daftarJadwal.size() + " | Terjadwal: " + tj + " | Selesai: " + sl + " | Absen: " + ab);
        System.out.println("\n--- Per Hari ---");
        for (String h : HARI) {
            long c = 0;
            for (JadwalPiket j : daftarJadwal) if (j.getHari().equals(h)) c++;
            if (c > 0) System.out.println(h + " : " + c + " jadwal");
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
            System.out.println("| 2. Tambah Jadwal                 |");
            System.out.println("| 3. Update Jadwal                 |");
            System.out.println("| 4. Hapus Jadwal                  |");
            System.out.println("| 5. Rekap Mingguan                |");
            System.out.println("| 0. Kembali                       |");
            System.out.println("+----------------------------------+");
            System.out.print("Pilih: ");
            switch (sc.nextLine().trim()) {
                case "1": tampilSemua(); break;
                case "2": tambah(sc); break;
                case "3": update(sc); break;
                case "4": hapus(sc); break;
                case "5": rekap(); break;
                case "0": lanjut = false; break;
                default: BaseManager.cetakError("Pilihan tidak valid.");
            }
        }
    }
}