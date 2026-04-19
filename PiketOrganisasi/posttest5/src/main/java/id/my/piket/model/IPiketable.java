package id.my.piket.model;

/**
 * IPiketable - INTERFACE
 *
 * Interface ini adalah "kontrak" yang harus dipenuhi oleh
 * semua jenis anggota yang bisa melaksanakan piket.
 *
 * Setiap class yang implements interface ini WAJIB membuat
 * semua method yang ada di sini:
 *   1. getDeskripsiTugas()   → menjelaskan tugas piket sesuai jenisnya
 *   2. getLaporanAktivitas() → menampilkan laporan aktivitas sesuai jenisnya
 *
 * Menggunakan keyword 'interface' sesuai Modul 6.
 */
public interface IPiketable {

    // Method 1 — wajib diimplementasi oleh semua class yang implements ini
    // Tidak ada body (isinya) → hanya definisi kontrak!
    String getDeskripsiTugas();

    // Method 2 — wajib diimplementasi oleh semua class yang implements ini
    void getLaporanAktivitas();
}