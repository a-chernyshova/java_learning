package org.learning.animals.builders;

import org.learning.animals.Mammals;

public class MammalsBuilder extends AnimalBuilder<Mammals> {
    public MammalsBuilder() {
        this.animal = new Mammals();
        this.animal.setCover("skin or fur");
        this.animal.setHabitat("ocean, forest, savannah");
    }
}
