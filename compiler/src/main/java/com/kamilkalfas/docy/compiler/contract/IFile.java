package com.kamilkalfas.docy.compiler.contract;

import java.io.IOException;
import java.nio.file.Path;

public interface IFile extends Writable, Readable {

    Path createFile() throws IOException;

    void delete(Path filePath) throws IOException;
}
