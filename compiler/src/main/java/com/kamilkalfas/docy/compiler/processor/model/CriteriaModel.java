package com.kamilkalfas.docy.compiler.processor.model;

import java.util.ArrayList;
import java.util.List;

public class CriteriaModel implements Comparable {
    public String name;
    public String description;
    public List<String> testNames = new ArrayList<>();

    @Override
    public int compareTo(Object o) {
        CriteriaModel that = (CriteriaModel) o;
        return this.name.compareTo(that.name);
    }
}
