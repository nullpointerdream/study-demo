package com.mycrawler.mycrawler.bitmap;

public class BloomFilter {

    /**
     * 根据需要修改size的大小
     */
    private final int size = 1 << 10;
    private int count;//数量
    private BitMap bits;
    private Hash[] func;//hash算法
    private final int[] seeds = new int[]{3, 5, 7, 11, 13, 31, 37, 61};

    public BloomFilter() {
        bits = new BitMap(size);
        func = new Hash[seeds.length];
        for (int i = 0; i < seeds.length; i++) {
            func[i] = new Hash(size, seeds[i]);
        }
    }

    public void add(String value) {
            for (Hash f : func) {
                bits.add(f.hash(value));
            }
            count++;
    }

    public boolean mightcontains(String value) {
        for (Hash f : func) {
            if (!bits.contain(f.hash(value))) {
                return false;
            }
        }
        return true;
    }

    private static class Hash {
        private int size;
        private int seed;

        public Hash(int size, int seed) {
            this.size = size;
            this.seed = seed;
        }

        private int hash(String value) {
            int result = 0;
            int len = value.length();
            for (int i = 0; i < len; i++) {
                result = seed * result + value.charAt(i);
            }
            return (size - 1) & result;//保证hash后的值 落在bits数组区间
        }
    }

    public int getCount() {
        return count;
    }


    public static void main(String[] args) {
        BloomFilter bloomFilter =new BloomFilter();
        bloomFilter.add("abc");
        bloomFilter.add("qqq");
        bloomFilter.add("www");

        System.out.println(bloomFilter.mightcontains("abc"));
        System.out.println(bloomFilter.mightcontains("rrr"));
        System.out.println(bloomFilter.mightcontains("rred"));

    }
}