package com.kamilkalfas.docky.compiler.processor.model;

import java.util.SortedSet;

public class IssueDto {

    private SortedSet<AcDto> acs;

    public SortedSet<AcDto> getAcs() {
        return acs;
    }

    private IssueDto(SortedSet<AcDto> acs) {
        this.acs = acs;
    }

    public static IssueDto with(SortedSet<AcDto> acs) {
        return new IssueDto(acs);
    }

}
