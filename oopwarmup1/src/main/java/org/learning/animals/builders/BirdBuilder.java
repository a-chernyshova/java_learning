package org.learning.animals.builders;

import org.learning.animals.Bird;

public class BirdBuilder extends AnimalBuilder<Bird> {

    public BirdBuilder() {
        this.animal = new Bird();
        this.animal.setCover("feathers");
        this.animal.setHabitat("forest");
    }
}
