package com.kamilkalfas.docky.compiler.processor.model.dto;

public class AnnotationsDto {

    public String issueNumber;
    public String featureName;
    public String testName;
    public IssueDto issue;

    private AnnotationsDto(Builder builder) {
        this.issueNumber = builder.issueNumber;
        this.featureName = builder.featureName;
        this.testName = builder.testName;
        this.issue = builder.issue;
    }

    public static class Builder {
        private String issueNumber;
        private String featureName;
        private String testName;

        private IssueDto issue;

        public Builder() {

        }

        public Builder issueNumber(String issueNumber) {
            this.issueNumber = issueNumber;
            return this;
        }

        public Builder featureName(String featureName) {
            this.featureName = featureName;
            return this;
        }

        public Builder issue(IssueDto issue) {
            this.issue = issue;
            return this;
        }

        public Builder testName(String name) {
            this.testName = "@Test " + name;
            return this;
        }

        public AnnotationsDto build() {
            return new AnnotationsDto(this);
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        AnnotationsDto that = (AnnotationsDto) o;

        if (!issueNumber.equals(that.issueNumber)) return false;
        return featureName.equals(that.featureName);
    }

    @Override
    public int hashCode() {
        int result = issueNumber.hashCode();
        result = 31 * result + featureName.hashCode();
        return result;
    }
}
