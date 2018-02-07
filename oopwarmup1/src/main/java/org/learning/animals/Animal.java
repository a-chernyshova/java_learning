package org.learning.animals;

public abstract class Animal implements Livable {
    protected String name;
    protected Long height;
    protected Long weight;
    protected String cover;
    protected String limbs;
    protected Integer lifeExpectancy;
    protected String habitat;
    protected String sound;

    public void getAnimal(){
        System.out.println("\n" + name + "\nHabitat: " + habitat + "\nTall average :" + height + ", weight average: " +
        weight + ".\n" + "Covered with: " + cover + ".\nAverage of life expectancy: " + lifeExpectancy);
    }
    public void speak(String sound){
        System.out.println("It says: " + sound );
    }
    public void reproduce(String way){
        System.out.println("Creating childs: " + way);
    }
    public void breath(){
        System.out.println("Breath with lungs.");
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setHeight(Long height) {
        this.height = height;
    }

    public void setWeight(Long weight) {
        this.weight = weight;
    }

    public void setCover(String cover) {
        this.cover = cover;
    }

    public void setLimbs(String limbs) {
        this.limbs = limbs;
    }

    public void setLifeExpectancy(Integer lifeExpectancy) {
        this.lifeExpectancy = lifeExpectancy;
    }

    public void setHabitat(String habitat) {
        this.habitat = habitat;
    }

    public void setSound(String sound){
        this.sound = sound;
    }
}
