package com.kamilkalfas.docy.compiler;

import com.kamilkalfas.docy.compiler.contract.DataManager;
import com.kamilkalfas.docy.compiler.contract.Repository;
import com.kamilkalfas.docy.compiler.data.storage.ProcessedDataStore;
import com.kamilkalfas.docy.compiler.env.EnvStore;
import com.kamilkalfas.docy.compiler.processor.model.dto.AnnotationsDto;

import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class DataController implements DataManager {

    private final Repository<List<AnnotationsDto>> repository;
    private final FileWrapper fileWrapper;

    public DataController(final Repository<List<AnnotationsDto>> repository, final FileWrapper fileWrapper) {
        this.repository = repository;
        this.fileWrapper = fileWrapper;
    }

    @Override
    public List<AnnotationsDto> assemble() {
        List<AnnotationsDto> data = new ArrayList<>();
        try (DirectoryStream<Path> directoryStream = fileWrapper.newDirectoryStream(ProcessedDataStore.DIRECTORY)) {
            for (Path path : directoryStream) {
                List<AnnotationsDto> parsedFile = repository.get(path);
                if (!parsedFile.isEmpty()) {
                    data.addAll(parsedFile);
                }
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return data;
    }

    @Override
    public void clearStore() {
        clear(ProcessedDataStore.DIRECTORY);
    }

    @Override
    public void clearEnv() {
        clear(EnvStore.DIRECTORY);
    }

    private void clear(Path dirPath) {
        try (
            DirectoryStream<Path> directoryStream = fileWrapper.newDirectoryStream(dirPath)) {
            for (Path path : directoryStream) {
                fileWrapper.deleteIfExists(path);
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        } finally {
            try {
                fileWrapper.deleteIfExists(dirPath);
            } catch (IOException e) {
                e.printStackTrace(); // love java <3
            }
        }
    }
}
