package com.cc;

import java.util.ArrayList;
import java.util.List;

public class Wildcard {

    public static void deleteLiquid(List<? extends Water> list, Water water) {
        list.remove(water);
    }

    public static void addLiquid(List<? super MineralWater> list) {
        list.add(new MineralWater("Mineral Water", 10));
    }

    public static void print(List<?> list) {
        for (
                Object item : list)
            System.out.println(item);
    }


    public static void main(String[] args) {

        List<Liquid> LiquidList = new ArrayList<Liquid>();
        List<MineralWater> MineralwaterList = new ArrayList<MineralWater>();

        addLiquid(LiquidList);

        addLiquid(MineralwaterList);

        addLiquid(MineralwaterList);

        Water Liquid = MineralwaterList.get(0);

        deleteLiquid(MineralwaterList, Liquid);

        print(MineralwaterList);
    }
}