package com.au.ps.email.util;

import com.au.ps.email.util.EmailUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

@RunWith(value = Parameterized.class)
public class EmailIdValidatorTest {

    private String emailId;
    private boolean expected;

    public EmailIdValidatorTest(String emailId, boolean expected) {
        this.emailId = emailId;
        this.expected = expected;
    }
    @Parameterized.Parameters(name= "{index}: isValid({0})={1}")
    public static Iterable<Object[]> data() {
        return Arrays.asList(new Object[][]{
                        {"mary@testdomain.com", true},
                        {"mary.smith@testdomain.com", true},
                        {"mary_smith123@testdomain.com", true},
                        {"mary@testdomaindotcom", false},
                        {"mary-smith@testdomain", false},
                        {"testdomain.com", false}
                }
        );
    }

    @Test
    public void testIsValidEmailId() {
        boolean actual= EmailUtil.isValidEmail(emailId);
        assertThat(actual, is(equalTo(expected)));
    }
}
