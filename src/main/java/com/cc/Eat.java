package com.cc;

interface Eat {
    public default void drink() {
        System.out.println("I drink milk");
    }

    public void eat();
}

interface Speak extends Eat {
    public default void eat() {
        System.out.println("I eat solid food now");
    }

    public void speak();
}

abstract class Walk implements Speak {
    public void speak() {
        System.out.println("I can say my name now");
    }

    abstract void walk();
}

class Child {
    private String name;

    public Child(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}

class Newborn extends Child implements Eat {

    public Newborn(String name) {
        super(name);
    }

    @Override
    public void eat() {
        System.out.println("I can’t eat solid food yet");
    }
}

class Infant extends Child implements Speak {

    public Infant(String name) {
        super(name);
    }

    @Override
    public void drink() {
        Speak.super.drink();
    }

    @Override
    public void eat() {
        Speak.super.eat();
    }

    @Override
    public void speak() {
        System.out.println("I can say bu bu bu");
    }
}

class Toddler extends Walk {
    private String name;

    public String getName() {
        return name;
    }

    public Toddler(String name) {
        this.name = name;
    }

    @Override
    public void drink() {
        super.drink();
    }

    @Override
    public void eat() {
        super.eat();
    }

    @Override
    void walk() {
        System.out.println("I can now walk");
    }
}


class InterfaceTest {
    public static void main(String[] args) {
        Newborn cutie1 = new Newborn("Fiona");
        System.out.println("\nMy name is " + cutie1.getName());
        cutie1.drink();
        cutie1.eat();

        Infant cutie2 = new Infant("Raya");
        System.out.println("\nMy name is " + cutie2.getName());
        cutie2.drink();
        cutie2.eat();
        cutie2.speak();

        Toddler cutie3 = new Toddler("Ted");
        System.out.println("\nMy name is " + cutie3.getName());
        cutie3.drink();
        cutie3.eat();
        cutie3.speak();
        cutie3.walk();
    }
}
