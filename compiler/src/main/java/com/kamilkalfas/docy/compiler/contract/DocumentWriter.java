package com.kamilkalfas.docy.compiler.contract;

public interface DocumentWriter {

    void h1(String s);

    void h2(String s);

    void h3(String s);

    void linkedListItem(String s);

    void boldText(String s);

    void boldListItem(String s);

    void blockquotes(String s);

    void twoColTable();

    void rowFirstCol(String s);

    void rowSecondCol(String s);

    void newLine();

    String linkedColumnText(String s);

    String asString();
}
