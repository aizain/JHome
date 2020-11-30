package com.bheternal.nlp;

/**
 * Tk
 *
 * @author Zain
 * @date 2020/10/19
 */
public class Tk {

    private final double nGram;
    private final String delimit;

    public Tk() {
        this.nGram = 1;
        this.delimit = "";
    }

    public String[] handler(String token) {
        String[] words = token.split(delimit);
        String[] result = new String[(int) Math.floor(words.length / nGram)];
        StringBuilder builder = new StringBuilder();
        for (int i = 0, j = 0; i < words.length; i++) {
            builder.append(words[i]);
            if (i % nGram == 0 || i == words.length - 1) {
                result[j++] = builder.toString();
                builder = new StringBuilder();
            }
        }
        return result;
    }

}
