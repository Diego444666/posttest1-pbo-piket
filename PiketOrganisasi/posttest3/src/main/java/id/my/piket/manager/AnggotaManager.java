package id.my.piket.manager;

import id.my.piket.model.Anggota;
import id.my.piket.model.AnggotaBiasa;
import id.my.piket.model.Pengurus;
import id.my.piket.model.Koordinator;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * AnggotaManager - mengelola CRUD semua jenis anggota
 *
 * ArrayList<Anggota> bisa menyimpan AnggotaBiasa, Pengurus, dan Koordinator
 * karena ketiganya adalah subclass dari Anggota (Polymorphism)
 */
public class AnggotaManager {

    // ArrayList<Anggota> bisa menampung semua subclass-nya!
    private ArrayList<Anggota> daftarAnggota;
    private int counterID;

    public AnggotaManager() {
        this.daftarAnggota = new ArrayList<>();
        this.counterID = 1;
        isiDataAwal();
    }

    private void isiDataAwal() {
        // Data AnggotaBiasa
        daftarAnggota.add(new AnggotaBiasa("AGT001", "Budi Santoso",  "081234567890", true,  "Kebersihan", 2023));
        daftarAnggota.add(new AnggotaBiasa("AGT002", "Siti Rahayu",   "081234567891", true,  "Logistik",   2022));
        daftarAnggota.add(new AnggotaBiasa("AGT003", "Ahmad Fauzi",   "081234567892", true,  "Humas",      2023));
        daftarAnggota.add(new AnggotaBiasa("AGT004", "Dewi Lestari",  "081234567893", true,  "Kebersihan", 2024));

        // Data Pengurus
        daftarAnggota.add(new Pengurus("AGT005", "Rizky Pratama",  "081234567894", true,  "Ketua",      "2024/2025"));
        daftarAnggota.add(new Pengurus("AGT006", "Indah Permata",  "081234567895", true,  "Sekretaris", "2024/2025"));

        // Data Koordinator
        daftarAnggota.add(new Koordinator("AGT007", "Fajar Nugraha", "081234567896", true, "Kebersihan", 5));

        counterID = 8;
    }

    private String buatID() {
        return String.format("AGT%03d", counterID++);
    }

    // ===================== CREATE =====================
    public void tambah(Scanner sc) {
        BaseManager.cetakJudul("TAMBAH ANGGOTA BARU");

        // Pilih jenis anggota dulu
        System.out.println("Pilih Jenis Anggota:");
        System.out.println("  1. Anggota Biasa");
        System.out.println("  2. Pengurus");
        System.out.println("  3. Koordinator");
        System.out.print("Pilihan: ");
        String pilihJenis = sc.nextLine().trim();

        if (!pilihJenis.equals("1") && !pilihJenis.equals("2") && !pilihJenis.equals("3")) {
            BaseManager.cetakError("Pilihan tidak valid.");
            return;
        }

        // Input data yang SAMA untuk semua jenis (dari superclass Anggota)
        System.out.print("Nama         : ");
        String nama = sc.nextLine();
        if (!Validator.isNotEmpty(nama)) { BaseManager.cetakError("Nama tidak boleh kosong!"); return; }

        System.out.print("No HP        : ");
        String noHp = sc.nextLine();

        System.out.print("Aktif? (1=Ya / 0=Tidak): ");
        boolean aktif = sc.nextLine().trim().equals("1");

        String id = buatID();

        // Input data KHUSUS sesuai jenis subclass
        switch (pilihJenis) {
            case "1": // AnggotaBiasa
                System.out.print("Divisi       : ");
                String divisi = sc.nextLine();
                if (!Validator.isNotEmpty(divisi)) { BaseManager.cetakError("Divisi tidak boleh kosong!"); counterID--; return; }

                System.out.print("Tahun Masuk  : ");
                int tahun = 2024;
                try { tahun = Integer.parseInt(sc.nextLine().trim()); }
                catch (NumberFormatException e) { BaseManager.cetakError("Tahun tidak valid, diset 2024."); }

                daftarAnggota.add(new AnggotaBiasa(id, Validator.bersihkan(nama), noHp, aktif, divisi, tahun));
                break;

            case "2": // Pengurus
                System.out.print("Jabatan      : ");
                String jabatan = sc.nextLine();
                if (!Validator.isNotEmpty(jabatan)) { BaseManager.cetakError("Jabatan tidak boleh kosong!"); counterID--; return; }

                System.out.print("Periode      : ");
                String periode = sc.nextLine();
                if (periode.isEmpty()) periode = "2024/2025";

                daftarAnggota.add(new Pengurus(id, Validator.bersihkan(nama), noHp, aktif, jabatan, periode));
                break;

            case "3": // Koordinator
                System.out.print("Divisi Koordinasi : ");
                String divisiKoor = sc.nextLine();
                if (!Validator.isNotEmpty(divisiKoor)) { BaseManager.cetakError("Divisi tidak boleh kosong!"); counterID--; return; }

                System.out.print("Jumlah Anggota    : ");
                int jumlah = 0;
                try { jumlah = Integer.parseInt(sc.nextLine().trim()); }
                catch (NumberFormatException e) { BaseManager.cetakError("Jumlah tidak valid, diset 0."); }

                daftarAnggota.add(new Koordinator(id, Validator.bersihkan(nama), noHp, aktif, divisiKoor, jumlah));
                break;
        }

        BaseManager.cetakOK("Anggota berhasil ditambahkan! ID: " + id);
    }

    // ===================== READ =====================
    public void tampilSemua() {
        BaseManager.cetakJudul("DAFTAR SEMUA ANGGOTA");
        if (daftarAnggota.isEmpty()) { BaseManager.cetakError("Belum ada data."); return; }

        String garis = "+----------+----------------------+---------------+---------------+----------+----------------------+";
        System.out.println(garis);
        System.out.printf("| %-8s | %-20s | %-13s | %-13s | %-8s | %-20s |%n",
                "ID", "Nama", "Jenis", "No HP", "Status", "Info Tambahan");
        System.out.println(garis);
        for (Anggota a : daftarAnggota) {
            // getJenis() dan getInfoTambahan() otomatis memanggil versi subclass!
            System.out.println(a.toString());
        }
        System.out.println(garis);

        // Hitung per jenis menggunakan instanceof
        long biasa = 0, pengurus = 0, koordinator = 0;
        for (Anggota a : daftarAnggota) {
            if (a instanceof Koordinator) koordinator++;
            else if (a instanceof Pengurus) pengurus++;
            else if (a instanceof AnggotaBiasa) biasa++;
        }
        System.out.println("Total: " + daftarAnggota.size() +
                " (Biasa: " + biasa + " | Pengurus: " + pengurus + " | Koordinator: " + koordinator + ")");
    }

    public void cari(Scanner sc) {
        BaseManager.cetakJudul("CARI ANGGOTA");
        System.out.print("Cari (nama/ID): ");
        String kata = sc.nextLine().trim().toLowerCase();

        boolean ada = false;
        String garis = "+----------+----------------------+---------------+---------------+----------+----------------------+";
        System.out.println(garis);
        System.out.printf("| %-8s | %-20s | %-13s | %-13s | %-8s | %-20s |%n",
                "ID", "Nama", "Jenis", "No HP", "Status", "Info Tambahan");
        System.out.println(garis);
        for (Anggota a : daftarAnggota) {
            if (a.getIdAnggota().toLowerCase().contains(kata) ||
                    a.getNama().toLowerCase().contains(kata)) {
                System.out.println(a.toString());
                ada = true;
            }
        }
        System.out.println(garis);
        if (!ada) BaseManager.cetakError("Tidak ditemukan.");
    }

    public void tampilPerJenis(Scanner sc) {
        BaseManager.cetakJudul("TAMPIL PER JENIS ANGGOTA");
        System.out.println("  1. Anggota Biasa");
        System.out.println("  2. Pengurus");
        System.out.println("  3. Koordinator");
        System.out.print("Pilih: ");
        String pilih = sc.nextLine().trim();

        String garis = "+----------+----------------------+---------------+---------------+----------+----------------------+";
        System.out.println(garis);
        System.out.printf("| %-8s | %-20s | %-13s | %-13s | %-8s | %-20s |%n",
                "ID", "Nama", "Jenis", "No HP", "Status", "Info Tambahan");
        System.out.println(garis);

        boolean ada = false;
        for (Anggota a : daftarAnggota) {
            boolean cocok = false;
            if (pilih.equals("1") && a instanceof AnggotaBiasa) cocok = true;
            if (pilih.equals("2") && a instanceof Pengurus) cocok = true;
            if (pilih.equals("3") && a instanceof Koordinator) cocok = true;
            if (cocok) { System.out.println(a.toString()); ada = true; }
        }
        System.out.println(garis);
        if (!ada) BaseManager.cetakError("Tidak ada data untuk jenis ini.");
    }

    // ===================== UPDATE =====================
    public void update(Scanner sc) {
        BaseManager.cetakJudul("UPDATE ANGGOTA");
        tampilSemua();

        System.out.print("\nMasukkan ID yang mau diupdate: ");
        String id = sc.nextLine().trim().toUpperCase();

        Anggota target = cariById(id);
        if (target == null) { BaseManager.cetakError("ID tidak ditemukan."); return; }

        System.out.println("Jenis: " + target.getJenis());
        System.out.println("[Kosongkan/Enter jika tidak mau diubah]");

        // Update property dari SUPERCLASS (berlaku untuk semua jenis)
        System.out.print("Nama baru  : ");
        String nama = sc.nextLine().trim();
        if (!nama.isEmpty()) target.setNama(nama);

        System.out.print("No HP baru : ");
        String noHp = sc.nextLine().trim();
        if (!noHp.isEmpty()) target.setNoHp(noHp);

        System.out.print("Status (1=Aktif / 0=Nonaktif / Enter=skip): ");
        String s = sc.nextLine().trim();
        if (s.equals("1")) target.setAktif(true);
        else if (s.equals("0")) target.setAktif(false);

        // Update property KHUSUS sesuai jenis subclass menggunakan instanceof
        if (target instanceof AnggotaBiasa) {
            AnggotaBiasa ab = (AnggotaBiasa) target;
            System.out.print("Divisi baru      : ");
            String divisi = sc.nextLine().trim();
            if (!divisi.isEmpty()) ab.setDivisi(divisi);

            System.out.print("Tahun masuk baru : ");
            String tahunStr = sc.nextLine().trim();
            if (!tahunStr.isEmpty()) {
                try { ab.setTahunMasuk(Integer.parseInt(tahunStr)); }
                catch (NumberFormatException e) { BaseManager.cetakError("Tahun tidak valid."); }
            }

        } else if (target instanceof Pengurus) {
            Pengurus p = (Pengurus) target;
            System.out.print("Jabatan baru : ");
            String jabatan = sc.nextLine().trim();
            if (!jabatan.isEmpty()) p.setJabatan(jabatan);

            System.out.print("Periode baru : ");
            String periode = sc.nextLine().trim();
            if (!periode.isEmpty()) p.setPeriode(periode);

        } else if (target instanceof Koordinator) {
            Koordinator k = (Koordinator) target;
            System.out.print("Divisi Koordinasi baru : ");
            String divisiK = sc.nextLine().trim();
            if (!divisiK.isEmpty()) k.setDivisiKoordinasi(divisiK);

            System.out.print("Jumlah Anggota baru    : ");
            String jmlStr = sc.nextLine().trim();
            if (!jmlStr.isEmpty()) {
                try { k.setJumlahAnggota(Integer.parseInt(jmlStr)); }
                catch (NumberFormatException e) { BaseManager.cetakError("Jumlah tidak valid."); }
            }
        }

        BaseManager.cetakOK("Data berhasil diupdate!");
    }

    // ===================== DELETE =====================
    public void hapus(Scanner sc) {
        BaseManager.cetakJudul("HAPUS ANGGOTA");
        tampilSemua();

        System.out.print("\nMasukkan ID yang mau dihapus: ");
        String id = sc.nextLine().trim().toUpperCase();

        Anggota target = cariById(id);
        if (target == null) { BaseManager.cetakError("ID tidak ditemukan."); return; }

        System.out.print("Yakin hapus " + target.getNama() + " (" + target.getJenis() + ")? (y/n): ");
        if (sc.nextLine().trim().equalsIgnoreCase("y")) {
            daftarAnggota.remove(target);
            BaseManager.cetakOK(target.getNama() + " berhasil dihapus!");
        } else {
            BaseManager.cetakInfo("Penghapusan dibatalkan.");
        }
    }

    // ===================== HELPER =====================
    public Anggota cariById(String id) {
        for (Anggota a : daftarAnggota) {
            if (a.getIdAnggota().equalsIgnoreCase(id)) return a;
        }
        return null;
    }

    public ArrayList<Anggota> getDaftarAnggota() { return daftarAnggota; }

    // ===================== MENU =====================
    public void menu(Scanner sc) {
        boolean lanjut = true;
        while (lanjut) {
            System.out.println("\n+----------------------------------+");
            System.out.println("|       MENU KELOLA ANGGOTA        |");
            System.out.println("+----------------------------------+");
            System.out.println("| 1. Lihat Semua Anggota           |");
            System.out.println("| 2. Lihat Per Jenis               |");
            System.out.println("| 3. Cari Anggota                  |");
            System.out.println("| 4. Tambah Anggota                |");
            System.out.println("| 5. Update Anggota                |");
            System.out.println("| 6. Hapus Anggota                 |");
            System.out.println("| 0. Kembali                       |");
            System.out.println("+----------------------------------+");
            System.out.print("Pilih: ");
            switch (sc.nextLine().trim()) {
                case "1": tampilSemua(); break;
                case "2": tampilPerJenis(sc); break;
                case "3": cari(sc); break;
                case "4": tambah(sc); break;
                case "5": update(sc); break;
                case "6": hapus(sc); break;
                case "0": lanjut = false; break;
                default: BaseManager.cetakError("Pilihan tidak valid.");
            }
        }
    }
}