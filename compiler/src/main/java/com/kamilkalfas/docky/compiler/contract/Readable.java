package com.kamilkalfas.docky.compiler.contract;

import java.io.IOException;
import java.nio.file.Path;

public interface Readable {

    String read(Path filePath) throws IOException;
}
