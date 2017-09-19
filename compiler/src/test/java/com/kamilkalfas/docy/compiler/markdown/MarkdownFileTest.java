package com.kamilkalfas.docy.compiler.markdown;

import com.kamilkalfas.docy.compiler.FileWrapper;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Matchers;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import static org.junit.Assert.*;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.powermock.api.mockito.PowerMockito.mockStatic;
import static org.powermock.api.mockito.PowerMockito.verifyStatic;


public class MarkdownFileTest {

    @Mock private FileWrapper mockWrapper;

    private MarkdownFile cut;

    @Before public void setUp() {
        MockitoAnnotations.initMocks(this);

        cut = new MarkdownFile(mockWrapper);
    }

    @Test public void createFile() throws Exception {
        cut.createFile();

        verify(mockWrapper, times(1)).get(anyString());

        verify(mockWrapper, times(1)).deleteIfExists(any(Path.class));

        verify(mockWrapper, times(1)).createFile(any(Path.class));
    }

    @Test public void write() throws Exception {
        cut.write(null, "".getBytes());

        verify(mockWrapper, times(1)).write(any(Path.class), any(byte[].class));
    }


    @Test(expected = UnsupportedOperationException.class)
    public void read() throws Exception {
        cut.read(null);
    }

    @Test public void delete() throws Exception {
        cut.delete(null);

        verify(mockWrapper, times(1)).delete(any(Path.class));
    }

}