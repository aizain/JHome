package com.bheternal.jhome.computer.algo.stack;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class BracketIsValidTest {

    private final BracketIsValid bracketIsValid = new BracketIsValid();

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