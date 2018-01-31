package org.learning.animals;

public class Mammals extends Animal implements Abilities {

    public Mammals(String name, Long tall, Long weight, String cover, String limbs, Integer lifeExpectancy, String habitat) {
        this.name = name;
        this.tall = tall;
        this.weight = weight;
        this.cover = "skin or fur";
        this.limbs = "paws";
        this.lifeExpectancy = lifeExpectancy;
        this.habitat = "everywhere";
    }

    public void feeding(){
        System.out.println("Feeding: grass or predator.");
    }
    public void moving(){
        System.out.println("Walking with help " + this.limbs + ". Live in " + habitat);
    }
    public void breathing(){
        System.out.println("Breath with lungs.");
    }
}
