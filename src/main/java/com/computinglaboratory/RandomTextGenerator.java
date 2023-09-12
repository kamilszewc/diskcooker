package com.computinglaboratory;

import java.util.Random;

/**
 * RandomTextGenerator class
 */
public class RandomTextGenerator {
    /**
     * Generates random text of specified length
     * @param length text length
     * @return random string
     */
    public static String generate(int length) {
        return new Random().ints(97, 122)
                .limit(length)
                .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
                .toString();
    }

    /**
     * Generates random text of specified length and specified seed
     * @param length text length
     * @return random string
     */
    public static String generate(int length, long seed) {
        return new Random(seed).ints(97, 122)
                .limit(length)
                .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
                .toString();
    }
}
