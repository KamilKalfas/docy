package com.kamilkalfas.docy.compiler.processor;

import com.kamilkalfas.docy.compiler.ProjectHelper;

import java.util.Set;

import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.ProcessingEnvironment;
import javax.annotation.processing.RoundEnvironment;
import javax.annotation.processing.SupportedAnnotationTypes;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.TypeElement;

@SupportedAnnotationTypes("com.kamilkalfas.docy.annotations.BDD")
public class Docy extends AbstractProcessor {

    @Override
    public synchronized void init(ProcessingEnvironment processingEnv) {
        super.init(processingEnv);
    }

    @Override
    public boolean process(Set<? extends TypeElement> annotations, RoundEnvironment env) {
        final ProjectHelper helper = new ProjectHelper(processingEnv);
        String projectName = helper.projectName();
        if (null == projectName || projectName.isEmpty()) {
            return false;
        }

        return true;
    }

    @Override
    public SourceVersion getSupportedSourceVersion() {
        return SourceVersion.RELEASE_7;
    }
}
