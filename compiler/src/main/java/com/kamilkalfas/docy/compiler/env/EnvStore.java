package com.kamilkalfas.docy.compiler.env;

import com.kamilkalfas.docy.compiler.FileWrapper;
import com.kamilkalfas.docy.compiler.contract.Store;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

public class EnvStore implements Store {
    private static final String DIR_NAME = ".env";
    private static final String FILE_NAME = "modules.data";
    private static final String EXTENSTION = ".data";

    public static final Path DIRECTORY = Paths.get(HOME + File.separator + DIR_NAME);

    private final FileWrapper fileWrapper;

    public EnvStore(final FileWrapper fileWrapper) {
        this.fileWrapper = fileWrapper;
    }

    @Override
    public Path createFile() throws IOException {
        if (fileWrapper.notExists(DIRECTORY)) {
            fileWrapper.createDirectory(DIRECTORY);
        }
        Path path = fileWrapper.get(HOME + File.separator + DIR_NAME + File.separator + FILE_NAME + EXTENSTION);
        if (fileWrapper.notExists(path)) {
            fileWrapper.createFile(path);
        }
        return path;
    }

    @Override
    public void delete(Path filePath) throws IOException {
        fileWrapper.delete(filePath);
    }

    @Override
    public void write(Path filePath, byte[] stream) throws IOException {
        fileWrapper.write(filePath, stream, true);
    }

    @Override
    public String read(Path filePath) throws IOException {
        return fileWrapper.read(filePath);
    }
}
