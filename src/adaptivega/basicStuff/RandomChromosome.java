/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package adaptivega.basicStuff;

import adaptivega.world.PathCoords;
import adaptivega.world.Vector;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 *
 * @author Raimonds Grismanausks
 */
//initialises a random route(chromosome) with the starting position [2,2] and
//end position [17,5] as per the sample program
public class RandomChromosome extends PathCoords{

    Random r = new Random();
    
    public RandomChromosome(int size, int mapSize) {
        super(null);
        
        PathCoords list = new PathCoords(null);
        list.getCoords().add(new Vector(2,2));
        for(int i=1;i<size-1;i++){
            list.getCoords().add(new Vector(r.nextInt(mapSize),r.nextInt(mapSize)));
        }
        list.getCoords().add(new Vector(38,39));
        this.setCoords(list.getCoords());
    }
}
