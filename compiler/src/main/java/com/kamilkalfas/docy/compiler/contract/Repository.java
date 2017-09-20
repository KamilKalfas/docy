package com.kamilkalfas.docy.compiler.contract;

import java.nio.file.Path;

public interface Repository<T> {

    T get(Path path);

    boolean put(T instance);
}
