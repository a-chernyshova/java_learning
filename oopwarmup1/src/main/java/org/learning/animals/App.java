package org.learning.animals;

public class App
{
    public static void main( String[] args )
    {
        Mammals bear = new Mammals("Bear", 123L, 150L, "fur", "limbs", 30, "forest");
        bear.getAnimal();

        Bird bird = new Bird("Pigeon", 10L, 1L, "xxx", "yyy", 1, "forest");
        bird.getAnimal();
        bird.feed();
        bird.speak("Kurlyk-kurlyk");
        bird.move();
        bird.reproduce("eggs");

        Fish fish = new Fish("Pike", 80L, 8L, "cover", "limbs", 4, "river");
        fish.getAnimal();
        fish.feed();
        fish.speak("nothing");
        fish.move();
        fish.reproduce("caviar");

        Reptiles reptile = new Reptiles("Lizard", 100L, 5L, "scin", "pows", 10,
                "somewhere");
        reptile.getAnimal();
        reptile.feed();
        reptile.speak("khhhhh");
        reptile.move();
        reptile.reproduce("eggs");

        Mammals cat = new Mammals("Cat", 40L, 5L, "fur", "paws", 15, "home");
        cat.getAnimal();
        cat.feed();
        cat.speak("Myau");
        cat.move();
        cat.reproduce("live birth (kitten)");
    }
}
