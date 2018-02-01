package org.learning.animals;

public class Mammals extends Animal {

    public Mammals(String name, Long tall, Long weight, String cover, String limbs, Integer lifeExpectancy, String habitat) {
        this.name = name;
        this.tall = tall;
        this.weight = weight;
        this.cover = "skin or fur";
        this.limbs = "paws";
        this.lifeExpectancy = lifeExpectancy;
        this.habitat = "everywhere";
    }

    public void feed(){
        System.out.println("Feeding: grass or predator.");
    }
    public void move(){
        System.out.println("Walking with help " + this.limbs + ". Live in " + habitat);
    }
}
