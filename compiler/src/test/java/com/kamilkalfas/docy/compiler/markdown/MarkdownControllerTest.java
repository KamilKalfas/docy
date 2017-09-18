package com.kamilkalfas.docy.compiler.markdown;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class MarkdownControllerTest {

    private MarkdownController mCut;

    @Before
    public void setUp() throws Exception {
        mCut = new MarkdownController(new MarkdownWriterImpl(), new MarkdownWriterImpl());
    }

    @Test public void when_writers_called_and_asString_called_then_return_value_of_header_and_body() {
        // given
        String expected = "# h1\n## h2";
        mCut.header.h1("h1");
        mCut.body.h2("h2");

        // then
        String actual = mCut.asString();

        assertEquals(expected, actual);
    }

    @Test public void when_writers_not_called_and_asString_called_then_return_empty() {
        // given
        String expected = "";

        // then
        String actual = mCut.asString();

        assertEquals(expected, actual);
    }
}