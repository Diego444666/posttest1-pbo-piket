package id.my.piket.manager;

public class BaseManager {
    protected static void cetakJudul(String judul) {
        System.out.println("\n=== " + judul + " ===");
    }
    protected static void cetakOK(String pesan)    { System.out.println("[OK] " + pesan); }
    protected static void cetakError(String pesan) { System.out.println("[!] " + pesan); }
    protected static void cetakInfo(String pesan)  { System.out.println("[i] " + pesan); }
}