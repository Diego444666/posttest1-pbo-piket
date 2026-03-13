package id.my.piket.manager;

/**
 * Validator - Kelas pembantu untuk validasi input
 *
 * Kelas ini mendemonstrasikan DEFAULT ACCESS MODIFIER:
 * - Tidak ada kata kunci public/private/protected di depan class
 * - Artinya kelas ini HANYA bisa diakses dari dalam package
 *   id.my.piket.manager saja
 * - Kelas dari package lain (model, ui) TIDAK BISA mengaksesnya
 */
class Validator {

    // Method-method di bawah juga menggunakan DEFAULT access modifier
    // (tidak ada public/private/protected)

    static boolean isNotEmpty(String value) {
        return value != null && !value.trim().isEmpty();
    }

    static boolean isPositiveNumber(int value) {
        return value > 0;
    }

    static boolean isValidJam(String jam) {
        if (jam == null || jam.isEmpty()) return false;
        // Format jam harus HH:MM misalnya 07:00
        return jam.matches("\\d{2}:\\d{2}");
    }

    static String bersihkan(String value) {
        return (value == null) ? "" : value.trim();
    }
}