package com.bheternal.nlp;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * OneHot
 *
 * @author Zain
 * @date 2020/10/19
 */
public class OneHot {


    private String[] input = null;
    private Map<String, Integer> dict = null;
    private WordVector[] vectors = null;

    public OneHot() {
    }

    public static void main(String[] args) {
        OneHot oneHot = new OneHot();
        WordVector[] encode = oneHot.fitTransform(new String[]{"啊啊", "啊啊", "哈", "你", "好"});
        System.out.println("vectors " + Arrays.asList(encode));
    }

    public WordVector[] fitTransform(String[] input) {
        int inputSize = input.length;
        this.input = input;
        this.dict = new HashMap<>(inputSize);
        this.vectors = new WordVector[inputSize];

        for (int i = 0, j = i; i < inputSize; i++) {
            String word = input[i];
            if (!dict.containsKey(word)) {
                dict.put(word, j++);
            }
        }

        for (int i = 0; i < inputSize; i++) {
            String word = input[i];
            vectors[i] = new WordVector(word, dict.get(word), dict.size());
        }

        return vectors;
    }

    public static class WordVector {
        private final String word;
        private final byte[] vector;

        public WordVector(String word, int index, int size) {
            this.word = word;
            this.vector = toVector(index, size);
        }

        private byte[] toVector(int index, int size) {
            byte[] vector = new byte[size];
            vector[index] = 1;
            return vector;
        }

        @Override
        public String toString() {
            return word + " " + Arrays.toString(vector);
        }
    }


}
