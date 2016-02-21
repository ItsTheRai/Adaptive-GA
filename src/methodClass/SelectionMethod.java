/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package methodClass;

import adaptivega.world.Population;
import adaptivega.world.World;

/**
 *
 * @author Raimonds Grismanausks
 */
public interface SelectionMethod {
    public Population selectPopulation(Population p,World w);
}
