package org.learning.animals;

public class Reptiles extends Animal {

    public void feed(){
        System.out.println("Feeding source: grass and insects.");
    }
    public void move(){
        System.out.println("Walking with help " + this.limbs + ". Live in " + habitat);
    }
}
