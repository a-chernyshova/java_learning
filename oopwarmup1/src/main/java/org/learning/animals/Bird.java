package org.learning.animals;

public class Bird extends Animal implements Flyable{

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
