package com.kamilkalfas.docy.compiler.env;

public class EnvHelper {
    public static final String MODULES = "DOCY_MODULES";

    public EnvHelper() {}

    public int getNumberOfModules(final String var) {
        if (null == var || var.isEmpty()) {
            throw new IllegalArgumentException("Variable name null or empty!");
        }
        final String val = System.getenv(var);
        if (null == val || val.isEmpty()) {
            throw new IllegalStateException("Variable not defined");
        }
        return Integer.valueOf(val);
    }
}
