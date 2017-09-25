package com.kamilkalfas.docy.compiler;

import java.io.IOException;
import java.io.Writer;

import javax.annotation.processing.ProcessingEnvironment;
import javax.tools.Diagnostic;
import javax.tools.JavaFileObject;

public class ProjectHelper {

    private final ProcessingEnvironment processingEnv;

    public ProjectHelper(ProcessingEnvironment processingEnv) {
        this.processingEnv = processingEnv;
    }

    public String projectName() {
        String projectName = "";
        try {
            JavaFileObject generationForPath = processingEnv.getFiler().createSourceFile("PathFor" + getClass().getSimpleName());
            Writer writer = generationForPath.openWriter();
            String sourcePath = generationForPath.toUri().getPath();
            writer.close();
            generationForPath.delete();
            projectName = getProjectName(sourcePath);
        } catch (IOException e) {
            processingEnv.getMessager().printMessage(Diagnostic.Kind.WARNING, "Unable to determine source file path!");
        }
        return projectName;
    }

    private String getProjectName(String sourcePath) {
        String[] array = sourcePath.split("/");
        int nameIndex = array.length - 5;
        return array[nameIndex];
    }
}
