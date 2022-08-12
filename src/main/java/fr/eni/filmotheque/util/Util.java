package fr.eni.filmotheque.util;

public class Util {

    public static String normalizeStringToCapitalize(String string) {
        return string.trim().substring(0, 1).toUpperCase() + string.trim().substring(1).toLowerCase();
    }

    public static String normalizeStringToUpperCase(String string) {
        return string.trim().toUpperCase();
    }
    public static String normalizeStringToLowerCase(String string) {
        return string.trim().toLowerCase();
    }
}
