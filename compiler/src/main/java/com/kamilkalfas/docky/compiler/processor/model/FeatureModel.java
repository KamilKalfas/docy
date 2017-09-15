package com.kamilkalfas.docky.compiler.processor.model;

import java.util.SortedSet;
import java.util.TreeSet;

public class FeatureModel implements Comparable {
    public String name;
    public SortedSet<IssueModel> uniqueIssues = new TreeSet<>();

    @Override
    public int compareTo(Object o) {
        return this.name.compareTo(((FeatureModel) o).name);
    }
}
