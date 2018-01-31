package org.learning.animals;

import com.sun.org.apache.xpath.internal.SourceTree;

public class Birds extends Animal implements Abilities{

    public Birds(String name, Long tall, Long weight, String cover, String limbs, Integer lifeExpectancy, String habitat) {
        this.name = name;
        this.tall = tall;
        this.weight = weight;
        this.cover = "feathers";
        this.limbs = "wings";
        this.lifeExpectancy = lifeExpectancy;
        this.habitat = "forest";
    }

    public void feeding(){
        System.out.println("Feeding source: grass, roots, insects");
    }
    public void moving(){
        System.out.println("Flying with help " + this.limbs + ". Also can walk." + " Live in " + habitat);
    }
    public void breathing(){
        System.out.println("Breathing with lungs.");
    }
}
