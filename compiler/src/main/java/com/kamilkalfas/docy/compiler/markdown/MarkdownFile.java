package com.kamilkalfas.docy.compiler.markdown;

import com.kamilkalfas.docy.compiler.contract.IFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

public class MarkdownFile implements IFile {

    private static String HOME = System.getProperty("user.dir");
    private static final String FILE_NAME = "test-coverage.md";

    @Override
    public Path createFile() throws IOException {
        final Path path = Paths.get(HOME + File.separator + FILE_NAME);
        Files.deleteIfExists(path);
        Files.createFile(path);
        return path;
    }

    @Override
    public void write(final Path filePath, final byte[] line) throws IOException {
        Files.write(filePath, line, StandardOpenOption.WRITE, StandardOpenOption.APPEND);
    }

    @Override
    public String read(Path filePath) throws IOException {
        throw new UnsupportedOperationException("Markdown file reading not implemented.");
    }

    @Override
    public void delete(Path filePath) throws IOException {
        Files.delete(filePath);
    }
}
