package com.kamilkalfas.docy.compiler.processor.model.mapper;

import com.kamilkalfas.docy.annotations.AC;
import com.kamilkalfas.docy.annotations.BDD;
import com.kamilkalfas.docy.compiler.contract.Mapper;
import com.kamilkalfas.docy.compiler.processor.model.dto.AcDto;
import com.kamilkalfas.docy.compiler.processor.model.dto.AnnotationsDto;
import com.kamilkalfas.docy.compiler.processor.model.dto.IssueDto;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;

import javax.lang.model.element.Element;

public class AnnotationMapper implements Mapper<List<AnnotationsDto>, Set<? extends Element>> {

    @Override
    public List<AnnotationsDto> map(Set<? extends Element> collection) {
        List<AnnotationsDto> dtoList = new ArrayList<>();
        if (!collection.isEmpty()) {
            for (Element element : collection) {
                BDD annotation = element.getAnnotation(BDD.class);
                SortedSet<AcDto> acList = new TreeSet<>();
                for (AC criteria : annotation.acs()) {
                    acList.add(new AcDto.Builder()
                        .description(criteria.description())
                        .number(criteria.number())
                        .build());
                }
                dtoList.add(new AnnotationsDto.Builder()
                    .issueNumber(annotation.issue())
                    .featureName(annotation.feature())
                    .issue(IssueDto.with(acList))
                    .testName(element.getSimpleName().toString())
                    .build()
                );
            }
            Collections.sort(dtoList, new Comparator<AnnotationsDto>() {
                @Override
                public int compare(AnnotationsDto o1, AnnotationsDto o2) {
                    return o1.issueNumber.compareTo(o2.issueNumber);
                }
            });
        }
        return dtoList;
    }
}
