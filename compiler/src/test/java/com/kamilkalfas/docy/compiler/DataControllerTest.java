package com.kamilkalfas.docy.compiler;

import com.kamilkalfas.docy.compiler.contract.DataManager;
import com.kamilkalfas.docy.compiler.contract.Repository;
import com.kamilkalfas.docy.compiler.processor.model.dto.AcDto;
import com.kamilkalfas.docy.compiler.processor.model.dto.AnnotationsDto;
import com.kamilkalfas.docy.compiler.processor.model.dto.IssueDto;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.TreeSet;

import static org.mockito.BDDMockito.given;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

public class DataControllerTest {

    @Mock private FileWrapper mockWrapper;
    @Mock private Repository<List<AnnotationsDto>> mockRepository;

    private DataManager cut;

    @Before public void setUp() {
        MockitoAnnotations.initMocks(this);

        cut = new DataController(mockRepository, mockWrapper);
    }

    @Test public void assemble() throws Exception {
        // given
        given(mockRepository.get(any(Path.class))).willReturn(testData());
        given(mockWrapper.newDirectoryStream(any(Path.class))).willReturn(new TestDirectoryStream());

        // when
        cut.assemble();

        // then
        verify(mockWrapper, times(1)).newDirectoryStream(any(Path.class));
        verify(mockRepository, times(3)).get(any(Path.class));
    }

    @Test public void clearStore() throws Exception {
        // given
        given(mockWrapper.newDirectoryStream(any(Path.class))).willReturn(new TestDirectoryStream());

        // when
        cut.clearStore();

        // then
        verify(mockWrapper, times(1)).newDirectoryStream(any(Path.class));
        verify(mockWrapper, times(4)).deleteIfExists(any(Path.class));
    }

    @Test public void clearEnv() throws Exception {
        // given
        given(mockWrapper.newDirectoryStream(any(Path.class))).willReturn(new TestDirectoryStream());

        // when
        cut.clearEnv();

        // then
        verify(mockWrapper, times(1)).newDirectoryStream(any(Path.class));
        verify(mockWrapper, times(4)).deleteIfExists(any(Path.class));

    }

    private final class TestDirectoryStream implements DirectoryStream<Path> {
        List<Path> fake = Arrays.asList(Paths.get(""), Paths.get(""), Paths.get(""));
        @Override
        public Iterator<Path> iterator() {
            return fake.iterator();
        }

        @Override
        public void close() throws IOException {

        }
    }

    private List<AnnotationsDto> testData() {
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