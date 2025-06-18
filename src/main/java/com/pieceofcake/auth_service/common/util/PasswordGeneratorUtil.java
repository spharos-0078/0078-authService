package com.pieceofcake.auth_service.common.util;

import java.security.SecureRandom;

public class PasswordGeneratorUtil {
    private static final String CHAR_SET = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
    private static final String SPECIAL_CHARS = "@#$!";
    private static final int DEFAULT_LENGTH = 10;
    private static final SecureRandom random = new SecureRandom();

    public static String generate(int length) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < length-1; i++) {
            sb.append(CHAR_SET.charAt(random.nextInt(CHAR_SET.length())));
        }
        sb.append(SPECIAL_CHARS.charAt(random.nextInt(SPECIAL_CHARS.length())));

        return sb.toString();
    }

    public static String generate() {
        return generate(DEFAULT_LENGTH);
    }
}