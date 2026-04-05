package id.my.piket.manager;

class Validator {
    static boolean isNotEmpty(String value) {
        return value != null && !value.trim().isEmpty();
    }
    static boolean isPositiveNumber(int value) { return value > 0; }
    static boolean isValidJam(String jam) {
        return jam != null && jam.matches("\\d{2}:\\d{2}");
    }
    static String bersihkan(String value) {
        return (value == null) ? "" : value.trim();
    }
}