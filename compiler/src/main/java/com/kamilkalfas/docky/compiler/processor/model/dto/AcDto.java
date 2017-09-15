package com.kamilkalfas.docky.compiler.processor.model.dto;

import java.util.Objects;

public class AcDto implements Comparable {

    private String number;
    private String description;

    private AcDto(Builder builder) {
        this.number = builder.number;
        this.description = builder.description;
    }

    public String getNumber() {
        return number;
    }

    public String getDescription() {
        return description;
    }

    @Override
    public int compareTo(Object o) {
        return o.equals(this) ? 0 : 1;
    }

    public static class Builder {
        private String number;
        private String description;

        public Builder() {

        }

        public Builder number(String number) {
            this.number = number;
            return this;
        }

        public Builder description(String description) {
            this.description = description;
            return this;
        }

        public AcDto build() {
            return new AcDto(this);
        }

    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AcDto annotationAC = (AcDto) o;
        return Objects.equals(number, annotationAC.number);
    }

    @Override
    public int hashCode() {
        return Objects.hash(number);
    }
}
