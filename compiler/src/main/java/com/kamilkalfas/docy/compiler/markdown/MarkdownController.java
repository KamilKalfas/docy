package com.kamilkalfas.docy.compiler.markdown;

import com.kamilkalfas.docy.compiler.contract.DocumentWriter;

public class MarkdownController {

    public DocumentWriter header;
    public DocumentWriter body;

    public MarkdownController(DocumentWriter header, DocumentWriter body) {
        this.header = header;
        this.body = body;
    }

    public String asString() {
        String retVal;
        if (header.asString().isEmpty() && body.asString().isEmpty()) {
            retVal = "";
        } else {
            retVal = header.asString() + "\n" + body.asString();
        }
        return retVal;
    }

}
