package com.bheternal.nlp;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

/**
 * Bayes
 * <p>
 * 贝叶斯 P(y|x) = P(x|y) * P(y) / P(x)
 * 似然函数 L(yn|x) = C * P(x|y = yn)
 * <p>
 * P(y) 先验概率 边缘概率
 * P(x|y) 似然函数
 * P(x) 先验概率
 * P(y|x) 后验概率
 *
 * @author Zain
 * @date 2020/10/19
 */
public class Bayes {

    public static void main(String[] args) {
        Dataset dataset = new Dataset(new String[][]{
                new String[]{"帅", "不好", "不嫁"},
                new String[]{"帅", "不好", "不嫁"},
                new String[]{"帅", "不好", "嫁"},
                new String[]{"不帅", "好", "不嫁"},
                new String[]{"帅", "好", "嫁"},
                new String[]{"帅", "好", "嫁"},
        });

        System.out.printf("帅不帅 %s \n", dataset.getWordP(0));
        System.out.printf("性格好不好 %s \n", dataset.getWordP(1));
        System.out.printf("嫁不嫁 %s \n", dataset.getWordP(2));

        // P(嫁|帅、不好) = P(帅|嫁) * P(不好|嫁) * P(嫁) / P(帅) * P(不好)
        String[] conditions = new String[]{"帅", "不好"};
    }

    public double getPyx(double pxy, double py, double px) {
        return pxy * py / px;
    }

    public double getNaivePyx(double[] pxy, double py, double[] px) {
        return pow(pxy) * py / pow(px);
    }

    private double pow(double[] values) {
        double result = 1;
        for (double v : values) {
            result *= v;
        }
        return result;
    }

    public static class Dataset {
        private final String[][] rowTable;
        private final Map<Integer, Columns> colTable;
        private Map<String, Double> pWord;

        public Dataset(String[][] rowTable) {
            this.rowTable = rowTable;
            this.colTable = new HashMap<>(this.rowTable.length);
            int rowSize = this.rowTable.length;
            for (int rowIndex = 0; rowIndex < rowSize; rowIndex++) {
                String[] row = this.rowTable[rowIndex];
                for (int colIndex = 0; colIndex < row.length; colIndex++) {
                    Columns columns = this.colTable.getOrDefault(colIndex, new Columns(rowIndex, rowSize));
                    columns.addValue(row[colIndex]);
                    this.colTable.put(colIndex, columns);
                }
            }
        }

        public double getWordConditionalP(int colId, String word) {
            return Optional.ofNullable(colTable.get(colId))
                    .map(columns -> columns.getWordP(word))
                    .orElse(0.0);
        }

        public double getWordP(int colId, String word) {
            return Optional.ofNullable(colTable.get(colId))
                    .map(columns -> columns.getWordP(word))
                    .orElse(0.0);
        }

        public Map<String, Double> getWordP(int colId) {
            return Optional.ofNullable(colTable.get(colId))
                    .map(Columns::getWordP)
                    .orElse(Collections.emptyMap());
        }

    }

    public static class Columns {

        private final int id;
        private final String[] values;
        private final Map<String, Integer> wordCount;

        public Columns(int id, int size) {
            this.id = id;
            this.values = new String[size];
            wordCount = new HashMap<>(size);
        }

        public void addValue(String value) {
            wordCount.merge(value, 1, Integer::sum);
        }

        public double getWordP(String word) {
            return isEmpty()
                    ? 0.0
                    : wordCount.getOrDefault(word, 0) / (double) values.length;
        }

        public Map<String, Double> getWordP() {
            if (isEmpty()) {
                return Collections.emptyMap();
            }

            Map<String, Double> map = new HashMap<>(wordCount.size());
            wordCount.forEach((word, count) -> map.put(word, getWordP(word)));
            return map;
        }

        public boolean isEmpty() {
            return values.length <= 0;
        }

    }

}
