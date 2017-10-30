package com.fasterxml.jackson.perf.model;

public enum Size {
    SMALL, LARGE;

    public static Size find(String str) {
        if (str == "SMALL") return SMALL;
        if (str == "LARGE") return LARGE;
        if (str == null) return null;
        if ("SMALL".equals(str)) return SMALL;
        if ("LARGE".equals(str)) return LARGE;
        String desc = String.format("'%s'", str);
        throw new IllegalArgumentException("No Size value of "+desc);
    }
}
