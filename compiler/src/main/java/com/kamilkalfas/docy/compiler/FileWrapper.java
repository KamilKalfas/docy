package com.kamilkalfas.docy.compiler;

import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

public class FileWrapper {

    public Path get(String path) throws IOException {
        return Paths.get(path);
    }

    public void deleteIfExists(Path path) throws IOException {
        Files.deleteIfExists(path);
    }

    public void createFile(Path path) throws IOException {
        Files.createFile(path);
    }

    public void write(Path path, byte[] content) throws IOException {
        write(path, content, false);
    }

    public void write(Path path, byte[] content, boolean override) throws IOException {
        if (override) {
            Files.write(path, content, StandardOpenOption.WRITE, StandardOpenOption.TRUNCATE_EXISTING);
        } else {
            Files.write(path, content, StandardOpenOption.WRITE, StandardOpenOption.APPEND);
        }
    }

    public String read(Path path) throws IOException {
        return new String(Files.readAllBytes(path));
    }

    public void delete(Path path) throws IOException {
        Files.delete(path);
    }

    public boolean notExists(Path path) {
        return Files.notExists(path);
    }

    public void createDirectory(Path path) throws IOException {
        Files.createDirectory(path);
    }

    public DirectoryStream<Path> newDirectoryStream(Path path) throws IOException {
        return Files.newDirectoryStream(path);
    }
}
