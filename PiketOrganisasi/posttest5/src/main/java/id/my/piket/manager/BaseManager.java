package id.my.piket.manager;

public class BaseManager {
    protected static void cetakJudul(String judul) { System.out.println("\n=== " + judul + " ==="); }
    protected static void cetakOK(String p)    { System.out.println("[OK] " + p); }
    protected static void cetakError(String p) { System.out.println("[!] " + p); }
    protected static void cetakInfo(String p)  { System.out.println("[i] " + p); }
}