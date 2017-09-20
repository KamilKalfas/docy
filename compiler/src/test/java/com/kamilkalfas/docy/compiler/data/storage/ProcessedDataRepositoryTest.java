package com.kamilkalfas.docy.compiler.data.storage;

import com.google.gson.Gson;
import com.kamilkalfas.docy.compiler.contract.Store;
import com.kamilkalfas.docy.compiler.contract.Repository;
import com.kamilkalfas.docy.compiler.processor.model.dto.AcDto;
import com.kamilkalfas.docy.compiler.processor.model.dto.AnnotationsDto;
import com.kamilkalfas.docy.compiler.processor.model.dto.IssueDto;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import java.lang.reflect.Type;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.TreeSet;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.powermock.api.mockito.PowerMockito.when;

@RunWith(PowerMockRunner.class)
@PrepareForTest(Gson.class)
public class ProcessedDataRepositoryTest {

    private Gson mockGson;
    @Mock private Store mockStoreFile;

    private Repository<List<AnnotationsDto>> cut;

    @Before public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        mockGson = PowerMockito.mock(Gson.class);

        when(mockGson.toJson(any())).thenReturn("im not a json");

        cut = new ProcessedDataRepository(mockGson, mockStoreFile);
    }

    @Test public void when_put_called_with_empty_collection_then_data_not_saved() throws Exception {
        // given
        List<AnnotationsDto> testData = new ArrayList<>();

        // when
        boolean actual = cut.put(testData);

        // then
        verify(mockStoreFile, times(0)).createFile();
        verify(mockGson, times(0)).toJson(any());
        assertFalse("Data should not be saved!", actual);
    }

    @Test public void when_put_called_with_null_then_data_not_saved() throws Exception {
        // given
        List<AnnotationsDto> testData = null;

        // when
        boolean actual = cut.put(testData);

        // then
        verify(mockStoreFile, times(0)).createFile();
        verify(mockGson, times(0)).toJson(any());
        assertFalse("Data should not be saved!", actual);
    }

    @Test public void when_put_called_with_collection_then_data_saved() throws Exception {
        // given
        List<AnnotationsDto> testData = prepareTestData();

        // when
        boolean actual = cut.put(testData);

        // then
        verify(mockStoreFile, times(1)).createFile();
        verify(mockGson, times(1)).toJson(any());
        assertTrue("Data should be saved!", actual);
    }

    @Test public void when_get_called_then_should_return_data() throws Exception {
        // when
        cut.get(any(Path.class));

        // then
        verify(mockStoreFile, times(1)).read(any(Path.class));
        verify(mockGson, times(1)).fromJson(anyString(), any(Type.class));
    }

    private List<AnnotationsDto> prepareTestData() {
        List<AnnotationsDto> retVal = new ArrayList<>();
        retVal.add(new AnnotationsDto.Builder()
            .featureName("F01")
            .issueNumber("F-001")
            .testName("test-01")
            .issue(IssueDto.with(new TreeSet<AcDto>()))
            .build());
        retVal.add(new AnnotationsDto.Builder()
            .featureName("F02")
            .issueNumber("F-002")
            .testName("test-02")
            .issue(IssueDto.with(new TreeSet<AcDto>()))
            .build());
        retVal.add(new AnnotationsDto.Builder()
            .featureName("F03")
            .issueNumber("F-003")
            .testName("test-03")
            .issue(IssueDto.with(new TreeSet<AcDto>()))
            .build());
        return retVal;
    }
}