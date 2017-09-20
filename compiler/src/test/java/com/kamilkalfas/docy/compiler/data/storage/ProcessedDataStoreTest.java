package com.kamilkalfas.docy.compiler.data.storage;

import com.kamilkalfas.docy.compiler.FileWrapper;
import com.kamilkalfas.docy.compiler.contract.Store;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.nio.file.Path;

import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class ProcessedDataStoreTest {

    @Mock private FileWrapper mockWrapper;

    private Store cut;

    @Before public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);

        cut = new ProcessedDataStore("testFile", mockWrapper);
    }

    @Test public void createFile_when_projectName_empty() throws Exception {
        // given
        cut = new ProcessedDataStore("", mockWrapper);

        // then
        assertEquals(null, cut.createFile());
    }


    @Test public void createFile_when_projectName_empty_or_null() throws Exception {
        // given
        cut = new ProcessedDataStore(null, mockWrapper);

        // then
        assertEquals(null, cut.createFile());
    }

    @Test public void createFile_directory_and_file_do_non_exist() throws Exception {
        // given
        when(mockWrapper.notExists(any(Path.class))).thenReturn(true, true);

        // when
        cut.createFile();

        // then
        verify(mockWrapper, times(2)).notExists(any(Path.class));
        verify(mockWrapper, times(1)).createDirectory(any(Path.class));
        verify(mockWrapper, times(1)).createFile(any(Path.class));
    }

    @Test public void createFile_directory_exist_but_file_do_not() throws Exception {
        // given
        when(mockWrapper.notExists(any(Path.class))).thenReturn(false, true);

        // when
        cut.createFile();

        // then
        verify(mockWrapper, times(2)).notExists(any(Path.class));
        verify(mockWrapper, times(0)).createDirectory(any(Path.class));
        verify(mockWrapper, times(1)).createFile(any(Path.class));
    }

    @Test public void createFile_directory_and_file_already_exist() throws Exception {
        // given
        when(mockWrapper.notExists(any(Path.class))).thenReturn(false, false);

        // when
        cut.createFile();

        // then
        verify(mockWrapper, times(2)).notExists(any(Path.class));
        verify(mockWrapper, times(0)).createDirectory(any(Path.class));
        verify(mockWrapper, times(0)).createFile(any(Path.class));
    }

    @Test public void delete() throws Exception {
        // when
        cut.delete(any(Path.class));

        // then
        verify(mockWrapper, times(1)).delete(any(Path.class));
    }

    @Test public void write() throws Exception {
        // when
        cut.write(any(Path.class), any(byte[].class));

        // then
        verify(mockWrapper, times(1)).write(any(Path.class), any(byte[].class));
    }

    @Test public void read() throws Exception {
        // when
        cut.read(any(Path.class));

        // then
        verify(mockWrapper, times(1)).read(any(Path.class));
    }

}