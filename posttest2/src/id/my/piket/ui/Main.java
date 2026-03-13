package id.my.piket.ui;

import id.my.piket.manager.AnggotaManager;
import id.my.piket.manager.JadwalManager;
import id.my.piket.manager.LokasiManager;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        AnggotaManager anggotaManager = new AnggotaManager();
        LokasiManager lokasiManager   = new LokasiManager();
        JadwalManager jadwalManager   = new JadwalManager(anggotaManager, lokasiManager);

        System.out.println("=================================================");
        System.out.println("  SISTEM MANAJEMEN JADWAL PIKET KEBERSIHAN");
        System.out.println("  Sekretariat Organisasi Mahasiswa");
        System.out.println("  Informatika - Universitas Mulawarman");
        System.out.println("  [ Posttest 2 - Encapsulation ]");
        System.out.println("=================================================");
        System.out.println("[OK] Sistem berhasil dimuat!");

        boolean jalan = true;
        while (jalan) {
            System.out.println("\n+----------------------------------+");
            System.out.println("|           MENU UTAMA             |");
            System.out.println("+----------------------------------+");
            System.out.println("| 1. Kelola Data Anggota           |");
            System.out.println("| 2. Kelola Data Lokasi Piket      |");
            System.out.println("| 3. Kelola Jadwal Piket           |");
            System.out.println("| 4. Lihat Rekap Jadwal            |");
            System.out.println("| 0. Keluar                        |");
            System.out.println("+----------------------------------+");
            System.out.print("Pilih: ");

            switch (sc.nextLine().trim()) {
                case "1": anggotaManager.menu(sc); break;
                case "2": lokasiManager.menu(sc); break;
                case "3": jadwalManager.menu(sc); break;
                case "4": jadwalManager.rekap(); break;
                case "0":
                    System.out.println("\nTerima kasih! Sampai jumpa!");
                    jalan = false;
                    break;
                default:
                    System.out.println("[!] Pilihan tidak valid, coba lagi.");
            }
        }

        sc.close();
    }
}