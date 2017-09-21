package com.kamilkalfas.test.two;

import com.kamilkalfas.docy.annotations.AC;
import com.kamilkalfas.docy.annotations.BDD;

import org.junit.Test;

public class TestCaseTwo {

    @BDD(issue = "I-2002", feature = "f-two",
         acs = @AC(number = "AC1", description = "Something somewhere cause we care about documentation!"))
    @Test
    public void test_case_one() {

    }

    @BDD(issue = "I-2002", feature = "f-two",
         acs = @AC(number = "AC2", description = "Something somewhere cause we care about documentation!"))
    @Test
    public void test_case_two() {

    }

    @BDD(issue = "I-2003", feature = "f-one", acs = {
        @AC(number = "AC1", description = "Something somewhere cause we care about documentation!"),
        @AC(number = "AC2", description = "Something somewhere cause we care about documentation!"),
        @AC(number = "AC3", description = "Something somewhere cause we care about documentation!")
    })
    @Test
    public void test_case_three() {

    }

    @BDD(issue = "I-2002", feature = "f-two", acs = {
        @AC(number = "AC1", description = "Something somewhere cause we care about documentation!"),
        @AC(number = "AC2", description = "Something somewhere cause we care about documentation!"),
        @AC(number = "AC3", description = "Something somewhere cause we care about documentation!")
    })
    @Test
    public void test_case_four() {

    }
}
