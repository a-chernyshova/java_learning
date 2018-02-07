package org.learning.animals;

public class Fish extends Animal implements Swimmable {

    public void feed(){
        System.out.println("Feeding source: plankton.");
    }
    public void move(){
        System.out.println("Swimming with help " + this.limbs + ". Live in " + habitat);
    }
    public void breath(){
        System.out.println("Breath with gills.");
    }

    public void swim() {
        System.out.println("Fish can swimm.");
    }
}
