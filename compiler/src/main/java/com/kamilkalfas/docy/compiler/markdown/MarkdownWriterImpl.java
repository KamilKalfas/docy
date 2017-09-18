package com.kamilkalfas.docy.compiler.markdown;

import com.kamilkalfas.docy.compiler.contract.DocumentWriter;

public class MarkdownWriterImpl implements DocumentWriter {
    private String content;

    public MarkdownWriterImpl() {
        content = "";
    }

    @Override
    public void h1(String s) {
        content += "# " + s;
    }

    @Override
    public void h2(String s) {
        content += "## " + s;
    }

    @Override
    public void h3(String s) {
        content += "### " + s;
    }

    @Override
    public void linkedListItem(String s) {
        content += "- [" + s + "](#" + s.toLowerCase() + ")";
    }

    @Override
    public void boldText(String s) {
        content += "**" + s + "**\n";
    }

    @Override
    public void boldListItem(String s) {
        content += "- **" + s + "**\n";
    }

    @Override
    public void blockquotes(String s) {
        content += "> " + s + "\n";
    }

    @Override
    public void twoColTable() {
        content += "|||\n|:---:|:---:|\n";
    }

    @Override
    public void rowFirstCol(String s) {
        content += "| " + s;
    }

    @Override
    public void rowSecondCol(String s) {
        content += " | " + s + " |\n";
    }

    @Override
    public void newLine() {
        content += "\n";
    }

    @Override
    public String linkedColumnText(String s) {
        return "[" + s + "](#" + s.toLowerCase() + ")";
    }

    @Override
    public String asString() {
        return content;
    }
}
