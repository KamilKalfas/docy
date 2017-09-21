package com.kamilkalfas.docy.compiler.processor;

import com.google.gson.Gson;
import com.kamilkalfas.docy.annotations.BDD;
import com.kamilkalfas.docy.compiler.FileWrapper;
import com.kamilkalfas.docy.compiler.ProjectHelper;
import com.kamilkalfas.docy.compiler.contract.MessageCallback;
import com.kamilkalfas.docy.compiler.data.storage.ProcessedDataRepository;
import com.kamilkalfas.docy.compiler.data.storage.ProcessedDataStore;
import com.kamilkalfas.docy.compiler.env.EnvHelper;
import com.kamilkalfas.docy.compiler.env.EnvRepository;
import com.kamilkalfas.docy.compiler.env.EnvStore;
import com.kamilkalfas.docy.compiler.env.model.ModuleInfoDto;
import com.kamilkalfas.docy.compiler.markdown.MarkdownController;
import com.kamilkalfas.docy.compiler.markdown.MarkdownPublisher;
import com.kamilkalfas.docy.compiler.markdown.MarkdownStore;
import com.kamilkalfas.docy.compiler.markdown.MarkdownWriterImpl;
import com.kamilkalfas.docy.compiler.processor.model.DocumentModel;
import com.kamilkalfas.docy.compiler.processor.model.dto.AnnotationsDto;
import com.kamilkalfas.docy.compiler.processor.model.mapper.AnnotationMapper;
import com.kamilkalfas.docy.compiler.processor.model.mapper.DtoMapper;

import java.io.IOException;
import java.nio.file.Path;
import java.util.List;
import java.util.Set;

import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.RoundEnvironment;
import javax.annotation.processing.SupportedAnnotationTypes;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.Element;
import javax.lang.model.element.TypeElement;
import javax.tools.Diagnostic;

@SupportedAnnotationTypes("com.kamilkalfas.docy.annotations.BDD")
public class Docy extends AbstractProcessor {

    @Override
    public boolean process(Set<? extends TypeElement> annotations, RoundEnvironment env) {
        messageCallback.printMessage(Diagnostic.Kind.ERROR, "HELLOOOOOO");
        final ProjectHelper helper = new ProjectHelper(processingEnv);
        String projectName = helper.projectName();
        if (null == projectName || projectName.isEmpty()) {
            return false;
        }
        final Gson gson = new Gson();
        final FileWrapper fileWrapper = new FileWrapper();

        final EnvStore envStore = new EnvStore(fileWrapper);
        final EnvRepository envRepository = new EnvRepository(gson, envStore);
        final ProcessedDataStore processedDataStore = new ProcessedDataStore(projectName, fileWrapper);
        final ProcessedDataRepository processedDataRepository = new ProcessedDataRepository(gson, processedDataStore);

        final EnvHelper envHelper = new EnvHelper();
        final Integer definedModules = envHelper.getNumberOfModules(EnvHelper.MODULES);

        try {
            Path envStoreFile = envStore.createFile();

            ModuleInfoDto moduleInfo = envRepository.get(envStoreFile);
            if (ModuleInfoDto.DEFAULT.equals(moduleInfo)) {
                envRepository.put(new ModuleInfoDto(definedModules));
                moduleInfo = envRepository.get(envStoreFile);
            }

            final AnnotationMapper annotationMapper = new AnnotationMapper();
            Set<? extends Element> elementsAnnotatedWith = env.getElementsAnnotatedWith(BDD.class);
            final List<AnnotationsDto> annotationsDtos = annotationMapper.map(elementsAnnotatedWith);
            processedDataRepository.put(annotationsDtos);

        // assemble doc
        if (moduleInfo.getCurrentModuleNumber().equals(moduleInfo.getEnvModuleNumber())) {
            final DtoMapper dtoMapper = new DtoMapper();
            final DocumentModel document = dtoMapper.map(annotationsDtos);
            final MarkdownController markdownController = new MarkdownController(new MarkdownWriterImpl(), new MarkdownWriterImpl());
            final MarkdownStore markdownStore = new MarkdownStore(fileWrapper);
            final MarkdownPublisher publisher = new MarkdownPublisher(markdownController, messageCallback, markdownStore);
            final String doc = publisher.prepare(document);
            publisher.publish(doc);
        }
        } catch (IOException e) {
            messageCallback.printMessage(Diagnostic.Kind.ERROR, e.getMessage());
        }
        return true;
    }

    @Override
    public SourceVersion getSupportedSourceVersion() {
        return SourceVersion.RELEASE_7;
    }

    private MessageCallback messageCallback = new MessageCallback() {
        @Override
        public void printMessage(Diagnostic.Kind kind, String message) {
            processingEnv.getMessager().printMessage(kind, message);
        }
    };

}
