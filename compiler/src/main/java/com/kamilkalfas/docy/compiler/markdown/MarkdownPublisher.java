package com.kamilkalfas.docy.compiler.markdown;

import com.kamilkalfas.docy.compiler.contract.FileStore;
import com.kamilkalfas.docy.compiler.debug.tools.LogDecorator;
import com.kamilkalfas.docy.compiler.processor.model.CriteriaModel;
import com.kamilkalfas.docy.compiler.processor.model.DocumentModel;
import com.kamilkalfas.docy.compiler.processor.model.FeatureModel;
import com.kamilkalfas.docy.compiler.processor.model.IssueModel;

import java.nio.file.Path;

public class MarkdownPublisher {

    private static final String TITLE = "AC Test Coverage";
    private final MarkdownController markdownController;
    private final FileStore markdownStore;

    public MarkdownPublisher(final MarkdownController documentWriter,
                             final FileStore markdownStore) {
        this.markdownController = documentWriter;
        this.markdownStore = markdownStore;
    }

    public String prepare(final DocumentModel document) {
        String retVal = "";
        markdownController.header.h1(TITLE);
        markdownController.header.newLine();
        markdownController.header.twoColTable();
        int index = 0;
        for (FeatureModel feature : document.uniqueFeatures) {
            addColumn(index, feature.name);
            markdownController.body.h2(feature.name);
            markdownController.body.newLine();
            for (IssueModel issue : feature.uniqueIssues) {
                markdownController.body.linkedListItem(issue.number);
                markdownController.body.newLine();
            }
            for (IssueModel issue : feature.uniqueIssues) {
                markdownController.body.h3(issue.number);
                markdownController.body.newLine();

                for (CriteriaModel ac : issue.uniqueACs) {
                    markdownController.body.boldText(ac.name);
                    markdownController.body.newLine();
                    for (String testName : ac.testNames) {
                        markdownController.body.boldText(testName);
                        markdownController.body.newLine();
                    }
                    markdownController.body.blockquotes(ac.description);
                    markdownController.body.newLine();
                }
            }
            index++;
            retVal = markdownController.asString();
        }
        return retVal;
    }

    public void publish(final String doc) {
        if (!doc.isEmpty()) {
            try {
                final Path docPath = markdownStore.createFile();
                markdownStore.write(docPath, doc.getBytes());
            } catch (Exception e) {
                LogDecorator.errorJson(e.getMessage());
            }
        }
    }

    private void addColumn(int index, String featureName) {
        String column;
        if (index % 2 == 0) {
            column = markdownController.header.linkedColumnText(featureName);
            markdownController.header.rowFirstCol(column);
        } else {
            column = markdownController.header.linkedColumnText(featureName);
            markdownController.header.rowSecondCol(column);
        }
    }

}
