package org.learning.animals;

import org.learning.animals.builders.BirdBuilder;
import org.learning.animals.builders.FishBuilder;
import org.learning.animals.builders.MammalsBuilder;
import org.learning.animals.builders.ReptilesBuilder;

public class App
{
    public static void main( String[] args )
    {
        Mammals bear = new MammalsBuilder().addName("bear").addTall(100L).addWeight(100L).addCover("fur").
                addSound("bu").addLifeExpectancy(15).build();
        bear.getAnimal();
        bear.breath();

        Bird pigeon = new BirdBuilder().addName("Pigeon").addTall(10L).addWeight(1L).addSound("kurlyk-kurlyk").
                addLifeExpectancy(5).build();
        pigeon.getAnimal();
        pigeon.feed();
        pigeon.breath();
        pigeon.speak("Kurlyk-kurlyk");
        pigeon.fly();
        pigeon.reproduce("eggs");

        Fish pike = new FishBuilder().addName("Pike").addTall(100L).addWeight(15L).addLifeExpectancy(5).
                addSound("Keep silence").build();
        pike.getAnimal();
        pike.feed();
        pike.breath();
        pike.speak("nothing");
        pike.swim();
        pike.reproduce("caviar");

        Reptiles lizard = new ReptilesBuilder().addName("Lizard").addTall(10L).addWeight(10L).addLifeExpectancy(5).
                addSound("keep silence").build();

        lizard.getAnimal();
        lizard.feed();
        lizard.speak("khhhhh");
        lizard.move();
        lizard.reproduce("eggs");

        Mammals cat = new MammalsBuilder().addName("cat").addTall(15L).addWeight(20L).addSound("may-may").
                addLifeExpectancy(15).build();
        cat.getAnimal();
        cat.feed();
        cat.speak("Myau");
        cat.move();
        cat.reproduce("live birth (kitten)");
    }
}
