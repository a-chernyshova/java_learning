package org.learning.animals_CB;

import org.learning.animals.Swimmable;

public class FishCB extends AnimalCB implements Swimmable {

    public void feed(){
        System.out.println("Feeding source: plankton.");
    }
    public void move(){
        System.out.println("Swimming with help " + this.getLimbs() + ". Live in " + this.getHabitat());
    }
    public void breath(){
        System.out.println("Breath with gills.");
    }

    public void swim() {
        System.out.println("Fish can swimm.");
    }

    public static Builder newBuilder(){
        return new FishCB().new Builder();
    }

    public class Builder extends AnimalCB.Builder{

        public AnimalCB build() {
            return FishCB.this;
        }
    }
}
