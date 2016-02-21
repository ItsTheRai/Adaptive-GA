/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package adaptivega.basicStuff;

import adaptivega.world.PathCoords;
import adaptivega.world.Population;
import java.util.ArrayList;
import static java.util.Collections.list;
import java.util.List;

/**
 *
 * @author Raimonds Grismanausks
 */
public class BasicPopulation extends Population{
    //Population pop;

    public BasicPopulation(int popSize) {
        super(new ArrayList<>());
        //List<PathCoords>
        //pop= Population();
        //list<RandomChromosome>;
        for(int i=0;i<popSize;i++){
            //for random chromosome forst argument is the nuber of lines in a path,
            //the second is the size of the map itself
            this.getPopulation().add(new RandomChromosome(11,40));
        }
    }
}