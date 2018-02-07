package org.learning.animals.builders;

import org.learning.animals.Reptiles;

public class ReptilesBuilder extends AnimalBuilder<Reptiles> {
    public ReptilesBuilder(){
        this.animal = new Reptiles();
        this.animal.setCover("skin");
        this.animal.setLimbs("paws");
    }
}
