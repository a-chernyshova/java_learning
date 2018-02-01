package org.learning.animals;

public abstract class Animal {
    String name;
    Long tall;
    Long weight;
    String cover;
    String limbs;
    Integer lifeExpectancy;
    String habitat;

    public Animal(){}

    public Animal(String name, Long tall, Long weight, String cover, String limbs, Integer lifeExpectancy, String habitat){
        this.name = name;
        this.tall = tall;
        this.weight = weight;
        this.cover = cover;
        this.limbs = limbs;
        this.lifeExpectancy = lifeExpectancy;
        this.habitat = habitat;
    }

    public void getAnimal(){
        System.out.println("\n" + name + "\nHabitat: " + habitat + "\nTall average :" + tall + ", weight average: " +
        weight + ".\n" + "Covered with: " + cover + ".\nAverage of life expectancy: " + lifeExpectancy);
    }
    public void speaking(String sound){
        System.out.println("It says: " + sound );
    }
    public void proliferation(String way){
        System.out.println("Creating childs: " + way);
    }
}
