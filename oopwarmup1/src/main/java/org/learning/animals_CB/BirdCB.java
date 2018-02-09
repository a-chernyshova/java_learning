package org.learning.animals_CB;

import org.learning.animals.Flyable;

public class BirdCB extends AnimalCB implements Flyable {

    public void feed(){
        System.out.println("Feeding source: grass, roots, insects");
    }
    public void move(){
        System.out.println("Flying with help " + this.getLimbs() + ". Also can walk." + " Live in " + this.getHabitat());
    }
    public void fly(){
        System.out.println("Almost all birds can fly.");
    }
    public static Builder newBuilder(){
        return new BirdCB().new Builder();
    }

    public class Builder extends AnimalCB.Builder{

        public AnimalCB build() {
            return BirdCB.this;
        }
    }
}
