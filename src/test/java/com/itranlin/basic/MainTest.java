package com.itranlin.basic;

import java.util.regex.Pattern;

/**
 * @author itranlin
 * @since 2022/6/8 14:56
 */
public class MainTest {
    private static final Pattern progressPattern = Pattern.compile("progress-\\d");

    public static void main(String[] args) {
        System.out.println(progressPattern.matcher("progress-e").matches());
    }
}
