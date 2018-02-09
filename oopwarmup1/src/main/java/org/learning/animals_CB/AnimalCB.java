package org.learning.animals_CB;

import org.learning.animals.Livable;

public class AnimalCB implements Livable {
    private String name;
    private Long height;
    private Long weight;
    private String cover;
    private String limbs;
    private Integer lifeExpectancy;
    private String habitat;
    private String sound;

    protected AnimalCB(){}

    public String getName(){
        return name;
    }
    public Long getHeight(){
        return height;
    }
    public Long getWeight(){
        return weight;
    }
    public String getCover(){
        return cover;
    }
    public String getLimbs(){
        return limbs;
    }
    public Integer getLifeExpectancy() {
        return lifeExpectancy;
    }
    public String getHabitat(){
        return habitat;
    }
    public String getSound() {
        return sound;
    }

    protected abstract class Builder {

        protected Builder(){}

        public Builder setName(String name){
            AnimalCB.this.name = name;
            return this;
        }
        public Builder setHeight(Long height){
            AnimalCB.this.height = height;
            return this;
        }
        public Builder setWeight(Long weight){
            AnimalCB.this.weight = weight;
            return this;
        }
        public Builder setCover(String cover){
            AnimalCB.this.cover = cover;
            return this;
        }
        public Builder setLimbs(String limbs){
            AnimalCB.this.limbs = limbs;
            return this;
        }
        public Builder setLifeExpectancy(Integer lifeExpectancy){
            AnimalCB.this.lifeExpectancy = lifeExpectancy;
            return this;
        }
        public Builder setHabitat(String habitat){
            AnimalCB.this.habitat = habitat;
            return this;
        }
        public Builder setSound(String sound){
            AnimalCB.this.sound = sound;
            return this;
        }
        public abstract AnimalCB build();
    }

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
    public void move(){
        System.out.println("It move with " + limbs);
    }
    public void feed() {
        System.out.println("Animals feed smth");
    }
}
