package com.mycrawler.mycrawler.suanfa;

public class BitMap {
    //保存数据的
    private byte[] bits;

    //能够存储多少数据
    private int capacity;


    public BitMap(int capacity){
        this.capacity = capacity;

        //1bit能存储8个数据，那么capacity数据需要多少个bit呢，capacity/8+1,右移3位相当于除以8
        bits = new byte[(capacity >>3 )+1];
    }

    public void add(int num){
        // num/8得到byte[]的index
        int arrayIndex = num >> 3;

        // num%8得到在byte[index]的位置
        int position = num & 0x07;

        //将1左移position后，那个位置自然就是1，然后和以前的数据做|，这样，那个位置就替换成1了。
        bits[arrayIndex] |= 1 << position;
    }

    public boolean contain(int num){
        // num/8得到byte[]的index
        int arrayIndex = num >> 3;

        // num%8得到在byte[index]的位置
        int position = num & 0x07;

        //将1左移position后，那个位置自然就是1，然后和以前的数据做&，判断是否为0即可
        return (bits[arrayIndex] & (1 << position)) !=0;
    }

    public void clear(int num){
        // num/8得到byte[]的index
        int arrayIndex = num >> 3;

        // num%8得到在byte[index]的位置
        int position = num & 0x07;

        //将1左移position后，那个位置自然就是1，然后对取反，再与当前值做&，即可清除当前的位置了.
        bits[arrayIndex] &= ~(1 << position);
    }

    public void println() {
        //打印的数字
        int digit=0;
        for(byte i=0;i<bits.length;i++){
            byte num =bits[i];
            int bitcount=8;

            //循环打印值为1的数字
            while (bitcount>0){
                if((num&1)==1){
                    System.out.print(digit+" ");
                }
                bitcount--;
                digit++;
                num>>=1;//右移
            }
        }
        System.out.println();
    }


    public static void main(String[] args) {
        BitMap bitmap = new BitMap(10);

        int[] arr=new int[]{7,5,10,6,9};
        for (int i : arr) {
            bitmap.add(i);
        }
        bitmap.println();
        System.out.println("7是否存在:"+bitmap.contain(7));
        System.out.println("删除7");
        bitmap.clear(7);
        System.out.println("7是否存在:"+bitmap.contain(7));

        bitmap.println();
    }
}


