package com.kamilkalfas.test.two;

import com.kamilkalfas.docy.annotations.AC;
import com.kamilkalfas.docy.annotations.BDD;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class TestCaseTwo {

    @BDD(issue = "I-2002", feature = "f-two",
         acs = @AC(number = "AC1", description = "Something somewhere cause we care about documentation!"))
    @Test
    public void test_case_one() {
        assertEquals(1, 1);
    }

    @BDD(issue = "I-2002", feature = "f-two",
         acs = @AC(number = "AC2", description = "Something somewhere cause we care about documentation!"))
    @Test
    public void test_case_two() {
        assertEquals(1, 1);
    }

    @BDD(issue = "I-2003", feature = "f-one", acs = {
        @AC(number = "AC1", description = "Something somewhere cause we care about documentation!"),
        @AC(number = "AC2", description = "Something somewhere cause we care about documentation!"),
        @AC(number = "AC3", description = "Something somewhere cause we care about documentation!")
    })
    @Test
    public void test_case_three() {
        assertEquals(1, 1);
    }

    @BDD(issue = "I-2002", feature = "f-two", acs = {
        @AC(number = "AC1", description = "Something somewhere cause we care about documentation!"),
        @AC(number = "AC2", description = "Something somewhere cause we care about documentation!"),
        @AC(number = "AC3", description = "Something somewhere cause we care about documentation!")
    })
    @Test
    public void test_case_four() {
        assertEquals(1, 1);
    }
}
