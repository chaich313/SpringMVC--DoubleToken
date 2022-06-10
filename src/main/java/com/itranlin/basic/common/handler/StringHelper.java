package com.itranlin.basic.common.handler;

/**
 * @author itranlin
 */
public class StringHelper {
    public static String getObjectValue(Object obj) {
        return obj == null ? "" : obj.toString();
    }
}
