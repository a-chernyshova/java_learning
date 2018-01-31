package org.learning.animals;

public class Reptiles extends Animal implements Abilities {

    public Reptiles(String name, Long tall, Long weight, String cover, String limbs, Integer lifeExpectancy, String habitat) {
        this.name = name;
        this.tall = tall;
        this.weight = weight;
        this.cover = "skin";
        this.limbs = "paws";
        this.lifeExpectancy = lifeExpectancy;
        this.habitat = habitat;
    }

    public void feeding(){
        System.out.println("Feeding source: grass and insects.");
    }
    public void moving(){
        System.out.println("Walking with help " + this.limbs + ". Live in " + habitat);
    }
    public void breathing(){
        System.out.println("Reptiles breath with lungs.");
    }
}
