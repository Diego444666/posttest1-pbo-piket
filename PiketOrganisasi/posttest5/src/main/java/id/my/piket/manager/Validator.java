package id.my.piket.manager;

class Validator {
    static boolean isNotEmpty(String v)  { return v != null && !v.trim().isEmpty(); }
    static boolean isValidJam(String v)  { return v != null && v.matches("\\d{2}:\\d{2}"); }
    static String  bersihkan(String v)   { return (v == null) ? "" : v.trim(); }
}