package com.kamilkalfas.docky.compiler.processor.model.mapper;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.kamilkalfas.docky.compiler.contract.Mapper;
import com.kamilkalfas.docky.compiler.processor.model.DocumentModel;
import com.kamilkalfas.docky.compiler.processor.model.dto.AnnotationsDto;

import org.junit.Before;
import org.junit.Test;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class DtoMapperTest {

    private static final String JSON_INPUT = "[{\"issueNumber\":\"BBDEV-001\",\"featureName\":\"login\",\"testName\":\"@Test name\",\"issue\":{\"acs\":[{\"number\":\"AC2\"," +
        "\"description\":\"Lorem ipsum dolor sit amet consectetur adipiscing elit. Donec quis consequat felis id maximus ante.\"}]}},{\"issueNumber\":\"BBDEV-001\"," +
        "\"featureName\":\"login\",\"testName\":\"@Test name2\",\"issue\":{\"acs\":[{\"number\":\"AC2\",\"description\":\"Lorem ipsum dolor sit amet consectetur adipiscing elit." +
        " Donec quis consequat felis id maximus ante.\"}]}},{\"issueNumber\":\"BBDEV-001\",\"featureName\":\"login\",\"testName\":\"@Test name3\"," +
        "\"issue\":{\"acs\":[{\"number\":\"AC2\",\"description\":\"Lorem ipsum dolor sit amet consectetur adipiscing elit. Donec quis consequat felis id maximus ante.\"}]}}," +
        "{\"issueNumber\":\"BBDEV-001\",\"featureName\":\"login\",\"testName\":\"@Test name4\",\"issue\":{\"acs\":[{\"number\":\"AC1\",\"description\":\"Lorem ipsum dolor sit " +
        "amet consectetur adipiscing elit.\"}]}},{\"issueNumber\":\"BBDEV-001\",\"featureName\":\"login\",\"testName\":\"@Test name5\",\"issue\":{\"acs\":[{\"number\":\"AC1\"," +
        "\"description\":\"Lorem ipsum dolor sit amet consectetur adipiscing elit.\"}]}},{\"issueNumber\":\"BBDEV-001\",\"featureName\":\"login\",\"testName\":\"@Test " +
        "test_num_one\",\"issue\":{\"acs\":[{\"number\":\"AC1\",\"description\":\"Lorem ipsum dolor sit amet consectetur adipiscing elit.\"},{\"number\":\"AC2\"," +
        "\"description\":\"Lorem ipsum dolor sit amet consectetur adipiscing elit. Donec quis consequat felis id maximus ante.\"}]}},{\"issueNumber\":\"BBDEV-002\"," +
        "\"featureName\":\"login\",\"testName\":\"@Test test_num_three\",\"issue\":{\"acs\":[{\"number\":\"AC1\",\"description\":\"Lorem ipsum dolor sit amet\"}]}}," +
        "{\"issueNumber\":\"BBDEV-003\",\"featureName\":\"login\",\"testName\":\"@Test test_num_two\",\"issue\":{\"acs\":[{\"number\":\"AC1\",\"description\":\"Lorem ipsum dolor" +
        " sit amet consectetur adipiscing elit. Donec quis consequat felis\"}]}},{\"issueNumber\":\"BBDEV-007\",\"featureName\":\"login\",\"testName\":\"@Test test_num_one\"," +
        "\"issue\":{\"acs\":[{\"number\":\"AC1\",\"description\":\"Lorem ipsum dolor sit amet consectetur adipiscing elit.\"}]}},{\"issueNumber\":\"BBDEV-101\"," +
        "\"featureName\":\"database\",\"testName\":\"@Test test_num_one_two\",\"issue\":{\"acs\":[{\"number\":\"AC2\",\"description\":\"Lorem ipsum dolor sit amet consectetur " +
        "adipiscing elit. Donec quis consequat felis id maximus ante.\"}]}},{\"issueNumber\":\"BBDEV-102\",\"featureName\":\"database\",\"testName\":\"@Test test_num_two_two\"," +
        "\"issue\":{\"acs\":[{\"number\":\"AC2\",\"description\":\"Lorem ipsum dolor sit amet consectetur adipiscing elit. Donec quis consequat felis id maximus ante. Integer id" +
        " commodo ipsum.\"}]}},{\"issueNumber\":\"BBDEV-103\",\"featureName\":\"database\",\"testName\":\"@Test test_num_three_two\",\"issue\":{\"acs\":[{\"number\":\"AC2\"," +
        "\"description\":\"Lorem ipsum dolor sit amet consectetur adipiscing elit. Donec quis consequat felis id maximus ante. Integer id commodo ipsum. Nullam \"}]}}]";
    private static final String JSON_OUTPUT = "{\"uniqueFeatures\":[{\"name\":\"database\",\"uniqueIssues\":[{\"number\":\"BBDEV-101\",\"uniqueACs\":[{\"name\":\"AC2\"," +
        "\"description\":\"Lorem ipsum dolor sit amet consectetur adipiscing elit. Donec quis consequat felis id maximus ante.\",\"testNames\":[\"@Test test_num_one_two\"]}]}," +
        "{\"number\":\"BBDEV-102\",\"uniqueACs\":[{\"name\":\"AC2\",\"description\":\"Lorem ipsum dolor sit amet consectetur adipiscing elit. Donec quis consequat felis id " +
        "maximus ante. Integer id commodo ipsum.\",\"testNames\":[\"@Test test_num_two_two\"]}]},{\"number\":\"BBDEV-103\",\"uniqueACs\":[{\"name\":\"AC2\"," +
        "\"description\":\"Lorem ipsum dolor sit amet consectetur adipiscing elit. Donec quis consequat felis id maximus ante. Integer id commodo ipsum. Nullam \"," +
        "\"testNames\":[\"@Test test_num_three_two\"]}]}]},{\"name\":\"login\",\"uniqueIssues\":[{\"number\":\"BBDEV-001\",\"uniqueACs\":[{\"name\":\"AC1\"," +
        "\"description\":\"Lorem ipsum dolor sit amet consectetur adipiscing elit.\",\"testNames\":[\"@Test name4\",\"@Test name5\",\"@Test test_num_one\"]},{\"name\":\"AC2\"," +
        "\"description\":\"Lorem ipsum dolor sit amet consectetur adipiscing elit. Donec quis consequat felis id maximus ante.\",\"testNames\":[\"@Test name\",\"@Test name2\"," +
        "\"@Test name3\",\"@Test test_num_one\"]}]},{\"number\":\"BBDEV-002\",\"uniqueACs\":[{\"name\":\"AC1\",\"description\":\"Lorem ipsum dolor sit amet\"," +
        "\"testNames\":[\"@Test test_num_three\"]}]},{\"number\":\"BBDEV-003\",\"uniqueACs\":[{\"name\":\"AC1\",\"description\":\"Lorem ipsum dolor sit amet consectetur " +
        "adipiscing elit. Donec quis consequat felis\",\"testNames\":[\"@Test test_num_two\"]}]},{\"number\":\"BBDEV-007\",\"uniqueACs\":[{\"name\":\"AC1\"," +
        "\"description\":\"Lorem ipsum dolor sit amet consectetur adipiscing elit.\",\"testNames\":[\"@Test test_num_one\"]}]}]}]}";
    private Mapper<DocumentModel, List<AnnotationsDto>> mCut;

    @Before
    public void setUp() throws Exception {
        mCut = new DtoMapper();
    }

    @Test public void given_empty_collection_do_nothing() {
        // given
        List<AnnotationsDto> emptyCollection = new ArrayList<>();

        // when
        DocumentModel result = mCut.map(emptyCollection);

        // then
        assertTrue(result.uniqueFeatures.isEmpty());
    }

    @Test public void given_collection_return_document_model() {
        // given
        DocumentModel expected = loadExpectedData();
        List<AnnotationsDto> annotationsDtos = loadTestData();

        // when
        DocumentModel result = mCut.map(annotationsDtos);

        // then
        assertEquals(expected.uniqueFeatures.size(), result.uniqueFeatures.size());
        assertEquals(expected.uniqueFeatures, result.uniqueFeatures);
    }

    private List<AnnotationsDto> loadTestData() {
        Gson gson = new Gson();
        final Type typeToken = new TypeToken<List<AnnotationsDto>>() {}.getType();
        return gson.fromJson(JSON_INPUT, typeToken);
    }

    private DocumentModel loadExpectedData() {
        Gson gson = new Gson();
        return gson.fromJson(JSON_OUTPUT, DocumentModel.class);
    }
}