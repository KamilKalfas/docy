package com.kamilkalfas.docy.compiler;

import java.io.IOException;
import java.io.Writer;

import javax.annotation.processing.ProcessingEnvironment;
import javax.tools.JavaFileObject;

public class ProjectHelper {
    public static final String ENV_DEBUG = "debug";
    private static final String BUILD = "build";

    private final ProcessingEnvironment processingEnv;
    private final String path;

    public ProjectHelper(ProcessingEnvironment processingEnv) {
        this.processingEnv = processingEnv;
        this.path = obtainPath();
    }

    private String obtainPath() {
        String sourcePath;
        try {
            JavaFileObject generationForPath = processingEnv.getFiler().createSourceFile("PathFor" + getClass().getSimpleName());
            Writer writer = generationForPath.openWriter();
            sourcePath = generationForPath.toUri().getPath();
            writer.close();
            generationForPath.delete();
        } catch (IOException e) {
            sourcePath = "";
        }
        return sourcePath;
    }

    public String projectName() {
        String projectName = "";
        String current = "";
        String previous = "";

        if (!path.isEmpty()) {
            for (String string : path.split("/")) {
                if (previous.isEmpty()) {
                    previous = string;
                } else if (BUILD.equals(current)) {
                    projectName = previous;
                    break;
                } else {
                    previous = current;
                    current = string;
                }
            }
        }
        return projectName;
    }

    public String environment() {
        String env = "";
        if (!path.isEmpty()) {
            String[] split = path.split("/");
            int len = split.length;
            // len - 1 = file name
            // len - 2 = env folder
            env = split[len - 2];
        }
        return env;
    }
}
