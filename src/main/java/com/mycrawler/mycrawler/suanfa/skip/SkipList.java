package com.mycrawler.mycrawler.suanfa.skip;

import java.util.Random;

public class SkipList {

    public SkipListEntry head;  // First element of the top level
    public SkipListEntry tail;  // Last element of the top level

    public int n;       // number of entries in the Skip List
    public int h;       // Height

    public Random r;    // Coin toss


    public SkipList() {
        SkipListEntry p1, p2;

        // 创建一个 -oo 和一个 +oo 对象
        p1 = new SkipListEntry(SkipListEntry.negInf, null);
        p2 = new SkipListEntry(SkipListEntry.posInf, null);

        // 将 -oo 和 +oo 相互连接
        p1.right = p2;
        p2.left = p1;

        // 给 head 和 tail 初始化
        head = p1;
        tail = p2;

        n = 0;
        h = 0;
        r = new Random();
    }

    private SkipListEntry findEntry(String key) {

        SkipListEntry p;

        // 从head头节点开始查找
        p = head;

        while(true) {
            // 从左向右查找，直到右节点的key值大于要查找的key值
            while(p.right.key != SkipListEntry.posInf
                    && p.right.key.compareTo(key) <= 0) {
                p = p.right;
            }

            // 如果有更低层的节点，则向低层移动
            if(p.down != null) {
                p = p.down;
            } else {
                break;
            }
        }

        // 返回p，！注意这里p的key值是小于等于传入key的值的（p.key <= key）
        return p;
    }

    public Integer get(String key) {

        SkipListEntry p;

        p = findEntry(key);

        if(p.key.equals(key)) {
            return p.value;
        } else {
            return null;
        }
    }

    //如果put的key值在跳跃表中存在，则进行修改操作；
    //
    //如果put的key值在跳跃表中不存在，则需要进行新增节点的操作，并且需要由random随机数决定新加入的节点的高度（最大level）；
    //
    //当新添加的节点高度达到跳跃表的最大level，需要添加一个空白层（除了-oo和+oo没有别的节点）
    //————————————————
    private void insert(String key,Integer value) {
        int i=0;
        SkipListEntry p = findEntry(key);
        SkipListEntry entry=null;
        if (p.key.equals(key)) {
            p.value = value;
        } else {
            entry = new SkipListEntry(key, value);
            entry.right = p.right;
            p.right.left = entry;
            p.right = entry;
            entry.left = p;
        }
        // 再使用随机数决定是否要向更高level攀升


        while (r.nextDouble() < 0.5) {
            // 如果新元素的级别已经达到跳跃表的最大高度，则新建空白层
            if (i >= h) {
                addEmptyLevel();
            }

            // 从p向左扫描含有高层节点的节点
            while (p.up == null) {
                p = p.left;
            }

            p = p.up;

            // 新增和q指针指向的节点含有相同key值的节点对象
            // 这里需要注意的是除底层节点之外的节点对象是不需要value值的

            SkipListEntry z = new SkipListEntry(key, null);

            z.left = p;
            z.right = p.right;
            p.right.left = z;
            p.right = z;

            z.down = entry;
            entry.up = z;

            entry = z;
            i = i + 1;
        }

        n = n + 1;


    }


    private void addEmptyLevel() {

        SkipListEntry p1, p2;

        p1 = new SkipListEntry(SkipListEntry.negInf, null);
        p2 = new SkipListEntry(SkipListEntry.posInf, null);

        p1.right = p2;
        p1.down = head;

        p2.left = p1;
        p2.down = tail;

        head.up = p1;
        tail.up = p2;

        head = p1;
        tail = p2;

        h = h + 1;
    }


    public Integer remove(String key) {

        SkipListEntry p, q;

        p = findEntry(key);

        if(!p.key.equals(key)) {
            return null;
        }

        Integer oldValue = p.value;
        while(p != null) {
            q = p.up;
            p.left.right = p.right;
            p.right.left = p.left;
            p = q;
        }

        return oldValue;
    }

}
