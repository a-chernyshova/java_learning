package org.learning.animals.builders;

import org.learning.animals.Fish;

public class FishBuilder extends AnimalBuilder<Fish>{

    public FishBuilder(){
        this.animal = new Fish();
        this.animal.setCover("skin or scales");
        this.animal.setHabitat("ocean, sea, river, lake");
        this.animal.setLimbs("gills");
    }
}
