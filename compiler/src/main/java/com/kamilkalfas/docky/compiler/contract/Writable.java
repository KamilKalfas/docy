package com.kamilkalfas.docky.compiler.contract;

import java.io.IOException;
import java.nio.file.Path;

public interface Writable {

    void write(Path filePath, byte[] line) throws IOException;
}
