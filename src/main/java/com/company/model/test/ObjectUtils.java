package com.company.model.test;

public class ObjectUtils {

    public static boolean equals(Object x, Object y) {
        return x == y || x != null && x.equals(y);
    }
}
