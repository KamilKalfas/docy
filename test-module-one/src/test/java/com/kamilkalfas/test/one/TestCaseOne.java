package com.kamilkalfas.test.one;

import com.kamilkalfas.docy.annotations.AC;
import com.kamilkalfas.docy.annotations.BDD;

import org.junit.Test;

public class TestCaseOne {

    @BDD(issue = "I-0001", feature = "f-one",
         acs = @AC(number = "AC1", description = "Something somewhere cause we care about documentation!"))
    @Test
    public void test_case_one() {

    }

    @BDD(issue = "I-0001", feature = "f-one",
         acs = @AC(number = "AC2", description = "Something somewhere cause we care about documentation!"))
    @Test
    public void test_case_two() {

    }

    @BDD(issue = "I-0001", feature = "f-one", acs = {
        @AC(number = "AC3", description = "Something somewhere cause we care about documentation!"),
        @AC(number = "AC4", description = "Something somewhere cause we care about documentation!"),
        @AC(number = "AC5", description = "Something somewhere cause we care about documentation!")
    })
    @Test
    public void test_case_three() {

    }

    @BDD(issue = "I-0001", feature = "f-one", acs = {
        @AC(number = "AC1", description = "Something somewhere cause we care about documentation!"),
        @AC(number = "AC2", description = "Something somewhere cause we care about documentation!"),
        @AC(number = "AC5", description = "Something somewhere cause we care about documentation!")
    })
    @Test
    public void test_case_four() {

    }
}
