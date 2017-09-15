package com.kamilkalfas.docky.compiler.processor.model.mapper;

import com.kamilkalfas.docky.compiler.contract.Mapper;
import com.kamilkalfas.docky.compiler.processor.model.dto.AnnotationsDto;

import org.junit.Before;
import org.junit.Test;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.lang.model.element.Element;

import static org.junit.Assert.assertTrue;

public class AnnotationMapperTest {

    private Mapper<List<AnnotationsDto>, Set<? extends Element>> mCut;

    @Before
    public void setUp() throws Exception {
        mCut = new AnnotationMapper();
    }

    @Test
    public void given_empty_collection_do_nothing() {
        // given
        Set<? extends Element> emptyCollection = new HashSet<>();

        // when
        List<AnnotationsDto> result = mCut.map(emptyCollection);

        // then
        assertTrue(result.isEmpty());
    }

}