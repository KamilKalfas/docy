package com.kamilkalfas.docy.compiler.contract;

public interface Mapper<X, Y> {

    X map(Y collection);
}
