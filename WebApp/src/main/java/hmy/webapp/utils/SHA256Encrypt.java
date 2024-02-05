package hmy.webapp.utils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * SHA-256 encryption class
 * This class is used to encrypt a string using the SHA-256 algorithm.
 * @author Huang Miaoyan
 */
public class SHA256Encrypt {

    /**
     * Encrypt the input string using the SHA-256 algorithm.
     * @param input The string to be encrypted
     * @return The encrypted string
     */
    public static String encrypt(String input) {
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] encodedHash = digest.digest(input.getBytes());
            return bytesToHexString(encodedHash);
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("SHA-256 algorithm not supported", e);
        }
    }

    /**
     * Convert a byte array to a hexadecimal string.
     * @param bytes The byte array to be converted
     * @return The hexadecimal string
     */
    private static String bytesToHexString(byte[] bytes) {
        StringBuilder hexString = new StringBuilder(2 * bytes.length);
        for (byte b : bytes) {
            String hex = Integer.toHexString(0xff & b);
            if (hex.length() == 1) {
                hexString.append('0');
            }
            hexString.append(hex);
        }
        return hexString.toString();
    }
}
