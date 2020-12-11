package com.bheternal.jhome.computer.theory;

/**
 * CacheLineEffectDemo
 * <p>
 * https://tech.meituan.com/2016/11/18/disruptor.html
 * <p>
 * 缓存行
 * <p>
 * Cache是由很多个cache line组成的。每个cache line通常是64字节，并且它有效地引用主内存中的一块儿地址。
 * 一个Java的long类型变量是8字节，因此在一个缓存行中可以存8个long类型的变量。
 * CPU每次从主存中拉取数据时，会把相邻的数据也存入同一个cache line。
 * 在访问一个long数组的时候，如果数组中的一个值被加载到缓存中，它会自动加载另外7个。
 * 因此你能非常快的遍历这个数组。事实上，你可以非常快速的遍历在连续内存块中分配的任意数据结构。
 * <p>
 * 下面的例子是测试利用cache line的特性和不利用cache line的特性的效果对比
 *
 * @author Zain
 * @date 2020/12/10
 */
public class CacheLineEffectDemo {

    //考虑一般缓存行大小是64字节，一个 long 类型占8字节
    static long[][] arr;

    public static void main(String[] args) {
        arr = new long[1024 * 1024][];
        for (int i = 0; i < 1024 * 1024; i++) {
            arr[i] = new long[16];
            for (int j = 0; j < 16; j++) {
                arr[i][j] = 0L;
            }
        }

        // Test001
        long sum = 0L;
        long marked = System.currentTimeMillis();
        for (int i = 0; i < 1024 * 1024; i += 1) {
            for (int j = 0; j < 16; j++) {
                sum = arr[i][j];
            }
        }
        System.out.println("Loop times:" + (System.currentTimeMillis() - marked) + "ms");

        // Test001
        sum = 0L;
        marked = System.currentTimeMillis();
        for (int i = 0; i < 1024 * 1024; i += 1) {
            for (int j = 0; j < 8; j++) {
                sum = arr[i][j];
            }
        }
        System.out.println("Loop times:" + (System.currentTimeMillis() - marked) + "ms");

        // Test001
        sum = 0L;
        marked = System.currentTimeMillis();
        for (int i = 0; i < 1024 * 1024; i += 1) {
            for (int j = 0; j < 8; j++) {
                sum = arr[i][j];
            }
        }
        System.out.println("Loop times:" + (System.currentTimeMillis() - marked) + "ms");

        // Test002
        sum = 0L;
        marked = System.currentTimeMillis();
        for (int i = 0; i < 8; i += 1) {
            for (int j = 0; j < 1024 * 1024; j++) {
                sum = arr[j][i];
            }
        }
        System.out.println("Loop times:" + (System.currentTimeMillis() - marked) + "ms");


        // Test003
        sum = 0L;
        marked = System.currentTimeMillis();
        for (int i = 0; i < 1024 * 1024; i += 1) {
            for (int j = 0; j < 8; j++) {
                sum += arr[i][j];
            }
        }
        System.out.println("Loop times:" + (System.currentTimeMillis() - marked) + "ms");


        // Test004
        sum = 0L;
        marked = System.currentTimeMillis();
        for (int i = 0; i < 8; i += 1) {
            for (int j = 0; j < 1024 * 1024; j++) {
                sum += arr[j][i];
            }
        }
        System.out.println("Loop times:" + (System.currentTimeMillis() - marked) + "ms");
    }

}
