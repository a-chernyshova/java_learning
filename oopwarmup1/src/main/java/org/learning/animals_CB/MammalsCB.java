package org.learning.animals_CB;

public class MammalsCB extends AnimalCB {
    public void feed(){
        System.out.println("Feeding: grass or predator.");
    }
    public void move(){
        System.out.println("Walking with help " + this.getLimbs() + ". Live in " + this.getHabitat());
    }

    public static Builder newBuilder(){
        return new MammalsCB().new Builder();
    }

    public class Builder extends AnimalCB.Builder{

        public AnimalCB build() {
            return MammalsCB.this;
        }
    }
}
