package com.kamilkalfas.docy.compiler.data.storage;

import com.kamilkalfas.docy.compiler.FileWrapper;
import com.kamilkalfas.docy.compiler.contract.IFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

public class ProcessedDataFile implements IFile {

    private static final String HOME = System.getProperty("user.dir");
    private static final String DIR_NAME = "data";
    private static final String EXTENSTION = ".data";
    private static final Path DIRECTORY = Paths.get(HOME + File.separator + DIR_NAME);

    private final String fileName;
    private final FileWrapper wrapper;

    public ProcessedDataFile(String projectName, FileWrapper wrapper) {
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
        wrapper.write(filePath, stream);
    }

    @Override
    public String read(Path filePath) throws IOException {
        return wrapper.read(filePath);
    }

    private boolean notEmpty() {
        return null != fileName && !fileName.isEmpty();
    }
}
