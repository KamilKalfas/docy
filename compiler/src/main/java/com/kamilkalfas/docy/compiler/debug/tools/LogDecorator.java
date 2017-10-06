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

    public static void noteJson(Object object) {
        sInstance.messager.printMessage(Diagnostic.Kind.NOTE, JsonLog.from(object));
    }

    public static void errorJson(Object object) {
        sInstance.messager.printMessage(Diagnostic.Kind.ERROR, JsonLog.from(object));
    }

    public static void warningJson(Object object) {
        sInstance.messager.printMessage(Diagnostic.Kind.WARNING, JsonLog.from(object));
    }

    public static void otherJson(Object object) {
        sInstance.messager.printMessage(Diagnostic.Kind.OTHER, JsonLog.from(object));
    }

    public static void note(Object obj) {
        sInstance.messager.printMessage(Diagnostic.Kind.NOTE, obj.toString());
    }

    public static void error(Object obj) {
        sInstance.messager.printMessage(Diagnostic.Kind.ERROR, obj.toString());
    }

    public static void warning(Object obj) {
        sInstance.messager.printMessage(Diagnostic.Kind.WARNING, obj.toString());
    }

    public static void other(Object obj) {
        sInstance.messager.printMessage(Diagnostic.Kind.OTHER, obj.toString());
    }

}
