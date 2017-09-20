package com.kamilkalfas.docy.compiler.env;

public class EnvHelper {
    private static final String MODULES = "DOCY_MODULES";

    private EnvHelper() {}

    public static int getNumberOfModules() {
        final String val = System.getenv(MODULES);
        if (null == val || val.isEmpty()) {
            throw new IllegalStateException("Variable not defined");
        }
        return Integer.valueOf(val);
    }
}
