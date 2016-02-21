/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package methodClass;

import adaptivega.world.Path;
import adaptivega.world.PathCoords;

/**
 *
 * @author Raimonds Grismanausks
 */
public interface MutationFunction {
    public PathCoords doMutation0(PathCoords path1,double mutationProb,int size);
}
