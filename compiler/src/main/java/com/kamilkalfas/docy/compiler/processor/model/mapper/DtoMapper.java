package com.kamilkalfas.docy.compiler.processor.model.mapper;

import com.kamilkalfas.docy.compiler.contract.Mapper;
import com.kamilkalfas.docy.compiler.processor.model.CriteriaModel;
import com.kamilkalfas.docy.compiler.processor.model.DocumentModel;
import com.kamilkalfas.docy.compiler.processor.model.FeatureModel;
import com.kamilkalfas.docy.compiler.processor.model.IssueModel;
import com.kamilkalfas.docy.compiler.processor.model.dto.AcDto;
import com.kamilkalfas.docy.compiler.processor.model.dto.AnnotationsDto;

import java.util.List;
import java.util.SortedSet;
import java.util.TreeSet;

public class DtoMapper implements Mapper<DocumentModel, List<AnnotationsDto>> {

    @Override
    public DocumentModel map(List<AnnotationsDto> annotationsDtos) {
        DocumentModel document = new DocumentModel();
        if (!annotationsDtos.isEmpty()) {
            SortedSet<String> uniqueFeatures = getUniqueFeatures(annotationsDtos);

            for (String featureName : uniqueFeatures) {
                FeatureModel feature = new FeatureModel();
                feature.name = featureName;
                for (AnnotationsDto annotation : annotationsDtos) {
                    if (feature.name.equals(annotation.featureName)) {
                        IssueModel issue = new IssueModel();
                        issue.number = annotation.issueNumber;
                        for (AcDto acDto : annotation.issue.getAcs()) {
                            CriteriaModel ac = new CriteriaModel();
                            ac.name = acDto.getNumber();
                            ac.description = acDto.getDescription();
                            ac.testNames.add(annotation.testName);
                            issue.uniqueACs.add(ac);
                        }

                        if (!feature.uniqueIssues.add(issue)) {
                            for (IssueModel existing : feature.uniqueIssues) {
                                if (existing.equals(issue)) {
                                    for (CriteriaModel lastCriteria : existing.uniqueACs) {
                                        for (CriteriaModel newCriteria : issue.uniqueACs) {
                                            if (!lastCriteria.name.equals(newCriteria.name)) {
                                                existing.uniqueACs.add(newCriteria);
                                            } else {
                                                for (String newTestName : newCriteria.testNames) {
                                                    if (!lastCriteria.testNames.contains(newTestName)) {
                                                        lastCriteria.testNames.add(newTestName);
                                                    }
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
                document.uniqueFeatures.add(feature);
            }
        }

        return document;
    }

    private SortedSet<String> getUniqueFeatures(List<AnnotationsDto> annotationsDtos) {
        SortedSet<String> uniqueFeatures = new TreeSet<>();
        for (AnnotationsDto dto : annotationsDtos) {
            uniqueFeatures.add(dto.featureName);
        }
        return uniqueFeatures;
    }
}
