package com.kamilkalfas.docy.compiler.contract;

import java.io.IOException;
import java.nio.file.Path;

public interface FileStore {

    String HOME = System.getProperty("user.dir");

    Path createFile() throws IOException;

    void delete(Path filePath) throws IOException;

    void write(Path filePath, byte[] stream) throws IOException;

    String read(Path filePath) throws IOException;
}
