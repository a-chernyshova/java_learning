package org.learning.animals_CB;

public class AppCB
{
    public static void main( String[] args )
    {
        MammalsCB bear = (MammalsCB) MammalsCB.newBuilder().setName("bear").setHeight(100L).setWeight(100L).setCover("fur").
                setSound("bu").setHabitat("forest").setLifeExpectancy(15).build();

        bear.getAnimal();
        bear.breath();

        BirdCB pigeon = (BirdCB)BirdCB.newBuilder().setName("Pigeon").setLifeExpectancy(2).setHabitat("parks, cities").setHeight(10L).setWeight(10L).setCover("feather").build();
        pigeon.getAnimal();
        pigeon.feed();
        pigeon.breath();
        pigeon.speak("Kurlyk-kurlyk");
        pigeon.fly();
        pigeon.reproduce("eggs");

        FishCB pike = (FishCB)FishCB.newBuilder().setName("Pike").setHeight(100L).setHabitat("rivers").
                setWeight(15L).setLifeExpectancy(5).setSound("Keep silence").setCover("scale").build();
        pike.getAnimal();
        pike.feed();
        pike.breath();
        pike.speak("nothing");
        pike.swim();
        pike.reproduce("caviar");

        MammalsCB cat = (MammalsCB)MammalsCB.newBuilder().setName("cat").setHeight(15L).setWeight(20L).setSound("may-may").
                setLifeExpectancy(15).setHabitat("flat").setLimbs("paws").setCover("fur").build();
        cat.getAnimal();
        cat.feed();
        cat.speak("Myau");
        cat.move();
        cat.reproduce("live birth (kitten)");
    }
}
