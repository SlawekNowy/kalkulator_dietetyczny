package pl.lublin.wsei.core;

import pl.lublin.wsei.klasy.Profil;

public class AppHelper {
    public static Profil zalogowanyProfil;
    public static String bytesToHex(byte[] hash) {
        StringBuilder hexString = new StringBuilder();
        for (byte b : hash) {
            String hex = Integer.toHexString(0xff & b);
            if (hex.length() == 1) hexString.append('0');
            hexString.append(hex);
        }
        return hexString.toString();
    }
}
