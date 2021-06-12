package com.agentdid127.agentlib.util;


import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.security.MessageDigest;
import java.util.UUID;
import java.util.regex.Pattern;

public class Util {

/* Commented out since Mojang added a new account system, which will need to be fixed.
    /**
     * Checks if a UUID is a bedrock UUID
     * @param uuid
     * @return
     *
    public static boolean isBedrock(String uuid) {

        if (uuid.startsWith("00000000")) return true;
        else return false;
    }

    private static final Pattern UUID_PATTERN = Pattern.compile("([0-9a-fA-F]{8})([0-9a-fA-F]{4})([0-9a-fA-F]{4})([0-9a-fA-F]{4})([0-9a-fA-F]+)");
    private static final HttpProfileRepository repository = new HttpProfileRepository("minecraft");


    /**
     * Gets UUID from Player Username
     * @param username
     * @return
     *
    public static UUID getUUID(String username) {
        Profile[] profile = repository.findProfilesByNames(username);
        if (profile.length == 1) {
            return UUID.fromString(UUID_PATTERN.matcher(profile[0].getId()).replaceFirst("$1-$2-$3-$4-$5"));
        }
        return null;
    }*/

    /**
     * Gets byte count of local file
     * @param digest
     * @param file
     * @return
     * @throws IOException
     */
    public static String getFileChecksum(MessageDigest digest, File file) throws IOException
    {
        //Get file input stream for reading the file content
        FileInputStream fis = new FileInputStream(file);

        //Create byte array to read data in chunks
        byte[] byteArray = new byte[1024];
        int bytesCount = 0;

        //Read file data and update in message digest
        while ((bytesCount = fis.read(byteArray)) != -1) {
            digest.update(byteArray, 0, bytesCount);
        };

        //close the stream; We don't need it now.
        fis.close();

        //Get the hash's bytes
        byte[] bytes = digest.digest();

        //This bytes[] has bytes in decimal format;
        //Convert it to hexadecimal format
        StringBuilder sb = new StringBuilder();
        for(int i=0; i< bytes.length ;i++)
        {
            sb.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
        }

        //return complete hash
        return sb.toString();
    }

    /**
     * Gets bytes from hexidecimals
     * @param hexString
     * @return
     */
    public static byte[] decodeHexString(String hexString) {
        if (hexString.length() % 2 == 1) {
            throw new IllegalArgumentException(
                    "Invalid hexadecimal String supplied.");
        }

        byte[] bytes = new byte[hexString.length() / 2];
        for (int i = 0; i < hexString.length(); i += 2) {
            bytes[i / 2] = hexToByte(hexString.substring(i, i + 2));
        }
        return bytes;
    }

    private static byte hexToByte(String hexString) {
        int firstDigit = toDigit(hexString.charAt(0));
        int secondDigit = toDigit(hexString.charAt(1));
        return (byte) ((firstDigit << 4) + secondDigit);
    }

    private static int toDigit(char hexChar) {
        int digit = Character.digit(hexChar, 16);
        if(digit == -1) {
            throw new IllegalArgumentException(
                    "Invalid Hexadecimal Character: "+ hexChar);
        }
        return digit;
    }
}
