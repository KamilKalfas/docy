package com.kamilkalfas.docy.compiler.data.storage;

import com.kamilkalfas.docy.compiler.FileWrapper;
import com.kamilkalfas.docy.compiler.contract.Store;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

public class ProcessedDataStore implements Store {

    private static final String DIR_NAME = ".env/data";
    private static final String EXTENSTION = ".data";

    public static final Path DIRECTORY = Paths.get(HOME + File.separator + DIR_NAME);

    private final String fileName;
    private final FileWrapper wrapper;

    public ProcessedDataStore(String projectName, FileWrapper wrapper) {
        this.fileName = projectName;
        this.wrapper = wrapper;
    }

    @Override
    public Path createFile() throws IOException {
        Path path = null;
        if (notEmpty()) {
            if (wrapper.notExists(DIRECTORY)) {
                wrapper.createDirectory(DIRECTORY);
            }
            path = wrapper.get(HOME + File.separator + DIR_NAME + File.separator + fileName + EXTENSTION);
            if (wrapper.notExists(path)) {
                wrapper.createFile(path);
            }
        }
        return path;
    }

    @Override
    public void delete(Path filePath) throws IOException {
        wrapper.delete(filePath);
    }

    @Override
    public void write(Path filePath, byte[] stream) throws IOException {
        wrapper.write(filePath, stream, true);
    }

    @Override
    public String read(Path filePath) throws IOException {
        return wrapper.read(filePath);
    }

    private boolean notEmpty() {
        return null != fileName && !fileName.isEmpty();
    }
}
