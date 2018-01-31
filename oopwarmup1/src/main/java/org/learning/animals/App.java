package org.learning.animals;

public class App
{
    public static void main( String[] args )
    {
        Animal animal = new Animal("Bear", 123L, 150L, "fur", "limbs", 30, "forest");
        animal.getAnimal();

        Birds bird = new Birds("Pigeon", 10L, 1L, "xxx", "yyy", 1, "forest");
        bird.getAnimal();
        bird.feeding();
        bird.speaking("Kurlyk-kurlyk");
        bird.moving();
        bird.proliferation("eggs");

        Fish fish = new Fish("Pike", 80L, 8L, "cover", "limbs", 4, "river");
        fish.getAnimal();
        fish.feeding();
        fish.speaking("nothing");
        fish.moving();
        fish.proliferation("caviar");

        Reptiles reptile = new Reptiles("Lizard", 100L, 5L, "scin", "pows", 10,
                "somewhere");
        reptile.getAnimal();
        reptile.feeding();
        reptile.speaking("khhhhh");
        reptile.moving();
        reptile.proliferation("eggs");

        Mammals cat = new Mammals("Cat", 40L, 5L, "fur", "paws", 15, "home");
        cat.getAnimal();
        cat.feeding();
        cat.speaking("Myau");
        cat.moving();
        cat.proliferation("live birth (kitten)");
    }
}
