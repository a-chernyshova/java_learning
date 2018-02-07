package org.learning.animals;

public class Mammals extends Animal {

    public void feed(){
        System.out.println("Feeding: grass or predator.");
    }
    public void move(){
        System.out.println("Walking with help " + this.limbs + ". Live in " + habitat);
    }
}
