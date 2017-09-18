package com.kamilkalfas.docy.compiler.processor.model;

import java.util.SortedSet;
import java.util.TreeSet;

public class IssueModel implements Comparable {
    public String number;
    public SortedSet<CriteriaModel> uniqueACs;

    public IssueModel() {
        this.uniqueACs = new TreeSet<>();
    }

    @Override
    public int compareTo(Object o) {
        return this.number.compareTo(((IssueModel) o).number);
    }

    @Override
    public boolean equals(Object o) {
        return this.number.equals(((IssueModel) o).number);
    }
}
