package com.aissatna.medilinkbackend.util;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.security.SecureRandom;
import java.util.Random;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class PasswordUtil {

    private static final int MIN_PASSWORD_LENGTH    = 8;
    private static final int MAX_PASSWORD_LENGTH    = 12;
    private static final String CHAR_LOWER          = "abcdefghijklmnopqrstuvwxyz";
    private static final String CHAR_UPPER          = CHAR_LOWER.toUpperCase();
    private static final String NUMBER              = "0123456789";
    private static final String SPECIAL             = "(&%*$!@#)";
    private static final String PASSWORD_ALLOW_BASE = CHAR_LOWER + CHAR_UPPER + NUMBER + SPECIAL;


    /**
     * Generate random password
     * @return String
     */
    public static String generateRandomPassword() {
        //Get random number between minPasswordLength and maxPasswordLength
        final SecureRandom randomPasswordLength = new SecureRandom();
        int [] value = {};
        int passwordLength = getRandomWithExclusion(randomPasswordLength, MIN_PASSWORD_LENGTH, MAX_PASSWORD_LENGTH, value);
        StringBuilder returnValue = new StringBuilder (passwordLength);
        //Random Uppercase index applied in generation of password in returnValue
        int indexUpperCase = getRandomWithExclusion(randomPasswordLength, 1, passwordLength, value);
        //Random Lower Case index applied in generation of password in returnValue
        int indexLowerCase = getRandomWithExclusion(randomPasswordLength, 1, passwordLength, indexUpperCase);
        int [] excludedIndex = {indexUpperCase, indexLowerCase};
        //Random Lower Case index applied in generation of password in returnValue
        int indexOneDigit = getRandomWithExclusion(randomPasswordLength, 1, passwordLength, excludedIndex);
        int [] excludedIndexDigit = {indexUpperCase, indexLowerCase, indexOneDigit};
        //Random Lower Case index applied in generation of password in returnValue
        int indexOneSpecial = getRandomWithExclusion(randomPasswordLength, 1, passwordLength, excludedIndexDigit);
        for (int i = 1; i <= passwordLength; i++) {
            int rndCharAt;
            char rndChar;

            //Place UpperCase
            if (i == indexUpperCase) {
                rndCharAt = randomPasswordLength.nextInt(CHAR_UPPER.length());
                rndChar = CHAR_UPPER.charAt(rndCharAt);
            }
            //Place LowerCase
            else if (i == indexLowerCase) {
                rndCharAt = randomPasswordLength.nextInt(CHAR_LOWER.length());
                rndChar = CHAR_LOWER.charAt(rndCharAt);
            }
            //Place One digit
            else if (i == indexOneDigit) {
                rndCharAt = randomPasswordLength.nextInt(NUMBER.length());
                rndChar = NUMBER.charAt(rndCharAt);
            }
            //Place One special
            else if (i == indexOneSpecial) {
                rndCharAt = randomPasswordLength.nextInt(SPECIAL.length());
                rndChar = SPECIAL.charAt(rndCharAt);
            }
            else {
                rndCharAt = randomPasswordLength.nextInt(PASSWORD_ALLOW_BASE .length());
                rndChar = PASSWORD_ALLOW_BASE.charAt(rndCharAt);
            }
            returnValue.append(rndChar);
        }
        return returnValue.toString();
    }

    /**
     * method for generate random number in range with excluded number
     * @param rnd Random object
     * @param start start index
     * @param end end index
     * @param exclude excluded indexes
     * @return int
     */
    private static int getRandomWithExclusion(Random rnd, int start, int end, int... exclude) {
        int random = start + rnd.nextInt(end - start + 1 - exclude.length);
        for (int ex : exclude) {
            if (random < ex) {
                break;
            }
            random++;
        }
        return random;
    }


}
