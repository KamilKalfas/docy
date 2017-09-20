package com.kamilkalfas.docy.compiler.contract;

public interface Repository<T> {

    T get();

    boolean put(T instance);
}
