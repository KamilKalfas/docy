package com.kamilkalfas.docy.compiler.markdown;

import com.kamilkalfas.docy.compiler.FileWrapper;
import com.kamilkalfas.docy.compiler.contract.IFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

public class MarkdownFile implements IFile {

    private static final String HOME = System.getProperty("user.dir");
    private static String FILE_NAME = "test-coverage.md";

    private FileWrapper wrapper;

    public MarkdownFile(FileWrapper wrapper) {
        this.wrapper = wrapper;
    }

    @Override
    public Path createFile() throws IOException {
        final Path path = wrapper.get(HOME + File.separator + FILE_NAME);
        wrapper.deleteIfExists(path);
        wrapper.createFile(path);
        return path;
    }

    @Override
    public void write(final Path filePath, final byte[] line) throws IOException {
        wrapper.write(filePath, line);
    }

    @Override
    public String read(Path filePath) throws IOException {
        throw new UnsupportedOperationException("Markdown file reading not implemented.");
    }

    @Override
    public void delete(Path filePath) throws IOException {
        wrapper.delete(filePath);
    }
}
