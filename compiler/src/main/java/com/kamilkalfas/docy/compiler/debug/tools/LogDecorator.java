package com.kamilkalfas.docy.compiler.debug.tools;

import javax.annotation.processing.Messager;
import javax.annotation.processing.ProcessingEnvironment;
import javax.tools.Diagnostic;

public final class LogDecorator {

    private Messager messager;
    private static LogDecorator sInstance;

    private LogDecorator(ProcessingEnvironment processingEnv) {
        messager = processingEnv.getMessager();
    }

    public static void init(ProcessingEnvironment processingEnv) {
        sInstance = new LogDecorator(processingEnv);
    }

    public static void note(Object object) {
        sInstance.messager.printMessage(Diagnostic.Kind.NOTE, JsonLog.from(object));
    }

    public static void error(Object object) {
        sInstance.messager.printMessage(Diagnostic.Kind.ERROR, JsonLog.from(object));
    }

    public static void warning(Object object) {
        sInstance.messager.printMessage(Diagnostic.Kind.WARNING, JsonLog.from(object));
    }

    public static void other(Object object) {
        sInstance.messager.printMessage(Diagnostic.Kind.OTHER, JsonLog.from(object));
    }
}
