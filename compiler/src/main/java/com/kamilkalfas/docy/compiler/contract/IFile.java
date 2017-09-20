package com.kamilkalfas.docy.compiler.contract;

import java.io.IOException;
import java.nio.file.Path;

public interface IFile {

    Path createFile() throws IOException;

    void delete(Path filePath) throws IOException;

    void write(Path filePath, byte[] stream) throws IOException;

    String read(Path filePath) throws IOException;
}
