package com.opfgroup.demo;

public class DemoUtils {

    public int add(int x, int y) {

        return (x + y);
    }

    public long negate(long x) {

        return -x;
    }

    public Object checkNull(Object obj) {

        if (obj != null) {
            return obj;
        }
        return null;
    }
}
