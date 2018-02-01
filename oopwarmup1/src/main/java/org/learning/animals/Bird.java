package org.learning.animals;

import com.sun.org.apache.xpath.internal.SourceTree;

public class Bird extends Animal implements Flyable{

    public Bird(String name, Long tall, Long weight, String cover, String limbs, Integer lifeExpectancy, String habitat) {
        this.name = name;
        this.tall = tall;
        this.weight = weight;
        this.cover = "feathers";
        this.limbs = "wings";
        this.lifeExpectancy = lifeExpectancy;
        this.habitat = "forest";
    }

    public void feed(){
        System.out.println("Feeding source: grass, roots, insects");
    }
    public void move(){
        System.out.println("Flying with help " + this.limbs + ". Also can walk." + " Live in " + habitat);
    }
    public void fly(){
        System.out.println("Almost all birds can fly.");
    }
}
