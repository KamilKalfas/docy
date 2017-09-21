package com.kamilkalfas.docy.compiler.contract;

import javax.tools.Diagnostic;

public interface MessageCallback {

    void printMessage(Diagnostic.Kind kind, String message);

}
