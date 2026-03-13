package id.my.piket.manager;

/**
 * BaseManager - Kelas dasar untuk semua Manager
 *
 * Kelas ini mendemonstrasikan penggunaan ACCESS MODIFIER:
 * - public  : kelas ini bisa diakses dari mana saja
 * - protected : method di bawah hanya bisa diakses dari:
 *              1. Dalam kelas ini sendiri
 *              2. Kelas lain dalam package id.my.piket.manager
 *              3. Subclass (kelas turunan) di package manapun
 */
public class BaseManager {

    // =========================================================
    // PROTECTED METHODS
    // Bisa dipakai oleh AnggotaManager, LokasiManager,
    // JadwalManager karena mereka berada di package yang sama
    // =========================================================

    protected static void cetakJudul(String judul) {
        System.out.println("\n=== " + judul + " ===");
    }

    protected static void cetakOK(String pesan) {
        System.out.println("[OK] " + pesan);
    }

    protected static void cetakError(String pesan) {
        System.out.println("[!] " + pesan);
    }

    protected static void cetakInfo(String pesan) {
        System.out.println("[i] " + pesan);
    }

    protected static void cetakGaris() {
        System.out.println("--------------------------------------------------");
    }
}