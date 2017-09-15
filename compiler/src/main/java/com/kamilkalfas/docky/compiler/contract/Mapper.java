package com.kamilkalfas.docky.compiler.contract;

public interface Mapper<X, Y> {

    X map(Y collection);
}
