package com.aizain.jhome.computer.data.stack;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class BracketIsValidTest {

    private BracketIsValid bracketIsValid = new BracketIsValid();

    @Test
    void isValid() {
        assertTrue(bracketIsValid.isValid("{[]}"));
        assertTrue(bracketIsValid.isValid("()[]{}"));
        assertFalse(bracketIsValid.isValid("([)]"));
    }

    @Test
    void isValidFast() {
        assertTrue(bracketIsValid.isValidFast(null));
        assertTrue(bracketIsValid.isValidFast(""));
        assertTrue(bracketIsValid.isValidFast("{[]}"));
        assertTrue(bracketIsValid.isValidFast("()[]{}"));
        assertFalse(bracketIsValid.isValidFast("([)]"));
    }

}