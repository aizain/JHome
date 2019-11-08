package com.aizain.jhome.computer.data.hash;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class IsAnagramTest {

    private IsAnagram isAnagram = new IsAnagram();

    @Test
    void isAnagram() {
        assertTrue(isAnagram.isAnagram("anagram", "nagaram"));
        assertFalse(isAnagram.isAnagram("rat", "cat"));
        assertFalse(isAnagram.isAnagram("aaac", "acac"));
        assertFalse(isAnagram.isAnagram("aaac", "acac"));
    }

    @Test
    void isAnagramWithUnicode() {
        assertTrue(isAnagram.isAnagram("\\u0061\\u0073\\u0064\\u0061\\u0064", "\\u0073\\u0061\\u0064\\u0064\\u0061"));
        assertFalse(isAnagram.isAnagram("\\u0073\\u0061\\u0064\\u0064\\u0061", "\\u0073\\u0061\\u0064\\u0064\\u0063"));
    }

    @Test
    void isAnagramUseHash() {
        assertTrue(isAnagram.isAnagramUseHash("anagram", "nagaram"));
        assertFalse(isAnagram.isAnagramUseHash("rat", "cat"));
        assertFalse(isAnagram.isAnagramUseHash("aaac", "acac"));
        assertFalse(isAnagram.isAnagramUseHash("aaac", "aaab"));
    }

    @Test
    void isAnagramUseHashFast() {
        assertTrue(isAnagram.isAnagramUseHashFast("anagram", "nagaram"));
        assertFalse(isAnagram.isAnagramUseHashFast("rat", "cat"));
        assertFalse(isAnagram.isAnagramUseHashFast("aaac", "acac"));
        assertFalse(isAnagram.isAnagramUseHashFast("aaac", "aaab"));
    }

}