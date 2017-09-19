package com.kamilkalfas.docy.compiler.markdown;

import com.kamilkalfas.docy.compiler.contract.DocumentWriter;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class MarkdownWriterImplTest {

    private DocumentWriter cut;

    @Before public void setUp() throws Exception {
        cut = new MarkdownWriterImpl();
    }

    @Test public void constructor() throws Exception {
        assertEquals("Should be empty!", "", cut.asString());
    }

    @Test public void h1() throws Exception {
        // given
        final String expected = "# h1";
        // when
        cut.h1("h1");
        // then
        assertEquals(expected, cut.asString());
    }

    @Test public void h2() throws Exception {
        // given
        final String expected = "## h2";
        // when
        cut.h2("h2");
        // then
        assertEquals(expected, cut.asString());
    }

    @Test public void h3() throws Exception {
        // given
        final String expected = "### h3";
        // when
        cut.h3("h3");
        // then
        assertEquals(expected, cut.asString());
    }

    @Test public void linkedListItem() throws Exception {
        // given
        final String expected = "- [value](#value)";
        // when
        cut.linkedListItem("value");
        // then
        assertEquals(expected, cut.asString());

        final String expectedLowerCased = "- [VALUE](#value)";
        cut = new MarkdownWriterImpl();
        cut.linkedListItem("VALUE");
        assertEquals(expectedLowerCased, cut.asString());
    }

    @Test public void boldText() throws Exception {
        // given
        final String expected = "**text**\n";
        // when
        cut.boldText("text");
        // then
        assertEquals(expected, cut.asString());
    }

    @Test public void boldListItem() throws Exception {
        // given
        final String expected = "- **text**\n";
        // when
        cut.boldListItem("text");
        // then
        assertEquals(expected, cut.asString());
    }

    @Test public void blockquotes() throws Exception {
        // given
        final String expected = "> text\n";
        // when
        cut.blockquotes("text");
        // then
        assertEquals(expected, cut.asString());
    }


    @Test public void twoColTable() throws Exception {
        // given
        final String expected = "|||\n|:---:|:---:|\n";;
        // when
        cut.twoColTable();
        // then
        assertEquals(expected, cut.asString());
    }

    @Test public void rowFirstCol() throws Exception {
        // given
        final String expected = "| text";
        // when
        cut.rowFirstCol("text");
        // then
        assertEquals(expected, cut.asString());
    }

    @Test public void rowSecondCol() throws Exception {
        // given
        final String expected = " | text |\n";
        // when
        cut.rowSecondCol("text");
        // then
        assertEquals(expected, cut.asString());
    }

    @Test public void newLine() throws Exception {
        // given
        final String expected = "\n";
        // when
        cut.newLine();
        // then
        assertEquals(expected, cut.asString());
    }

    @Test public void linkedColumnText() throws Exception {
        // given
        final String expected = "| [TEXT](#text)";
        // when
        String result = cut.linkedColumnText("TEXT");
        cut.rowFirstCol(result);
        // then
        assertEquals(expected, cut.asString());
    }

}