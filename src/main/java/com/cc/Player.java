package com.cc;

import java.util.ArrayList;
import java.util.Comparator;

public class Player {

    public String name;
    public PlayerCategory category;
    public int goalsScored;

    public Player(String name, PlayerCategory category, int goalsScored) {
        this.name = name;
        this.category = category;
        this.goalsScored = goalsScored;
    }
    public Player(int goalsScored) {
        this.goalsScored = goalsScored;
    }


    @Override
    public String toString() {
        return ""+goalsScored;
    }

    public static void main(String[] args) {
        ArrayList<Player> players = new ArrayList<Player>();
        players.add(new Player(10));
        players.add(new Player(3));
        players.add(new Player(2));
        players.add(new Player(7));

       /* ArrayList<Player> players = new ArrayList<Player>();
        Scanner scan = new Scanner(System.in);
        while (true) {
            System.out.print("En怨er player name: ");
            String name = scan.next();
            System.out.print("1.GOALKEEPER\n2.MIDFIELDER\n3.STRIKER\n4.DEFENDER\nChoose player position:");
            int categoryValue = scan.nextInt();
            PlayerCategory playerCategory = null;
            for (PlayerCategory category : PlayerCategory.values()) {
                if (category.ordinal() + 1 == categoryValue) {
                    playerCategory = category;
                    break;
                }
            }
            if (playerCategory == null) return;
            System.out.print("Enter goals scored by " + name + ": ");
            int goalsScored = scan.nextInt();
            players.add(new Player(name, playerCategory, goalsScored));
            System.out.print("want to addmore players? Yes/No: ");
            if (scan.next().equals("No")) break;
        }
        System.out.println(1);

        */

        players.sort(new Comparator<Player>() {
            @Override
            public int compare(Player p1, Player p2) {
                return p1.goalsScored > p2.goalsScored ? 0 : 1;
            }
        });
        players.sort((p1, p2) -> p1.goalsScored > p2.goalsScored ? 0 : 1);
        Utility obj = p3 -> {
            for (Player p : p3) System.out.println(p);
        };
        obj.printList(players);

    }


}