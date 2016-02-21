/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package methodClass;

import adaptivega.world.Path;
import adaptivega.world.PathCoords;
import java.util.List;

/**
 *
 * @author Raimonds Grismanausks
 */
public interface CrossoverFunction {
    public List<PathCoords> doCrossower0(PathCoords path1,PathCoords path2,double crossoverProb);
}
