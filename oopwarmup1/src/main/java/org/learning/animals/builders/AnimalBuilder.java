package org.learning.animals.builders;

import org.learning.animals.Animal;

public abstract class AnimalBuilder<T extends Animal> {
    protected T animal;

    public AnimalBuilder<T> addName(String name) {
        this.animal.setName(name);
        return this;
    }

    public AnimalBuilder<T> addTall(Long tall) {
        this.animal.setHeight(tall);
        return this;
    }

    public AnimalBuilder<T> addWeight (Long weight) {
        this.animal.setWeight(weight);
        return this;
    }

    public AnimalBuilder<T> addCover (String cover) {
        this.animal.setCover(cover);
        return this;
    }

    public AnimalBuilder<T> addSound (String sound){
        this.animal.setSound(sound);
        return this;
    }

    public AnimalBuilder<T> addLifeExpectancy(Integer lifeExpectancy){
        this.animal.setLifeExpectancy(lifeExpectancy);
        return this;
    }

    public T build () {
        return animal;
    }

//    protected Animal animal;
//
//    public AnimalBuilder addName(String name) {
//        animal.setName(name);
//        return this;
//    }
//
//    public AnimalBuilder addTall(Long tall) {
//        animal.setHeight(tall);
//        return this;
//    }
//
//    public AnimalBuilder addWeight (Long weight) {
//        animal.setWeight(weight);
//        return this;
//    }
//
//    public Animal build () {
//        return animal;
//    }
//
//
}
