/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package adaptivega.basicStuff;

import adaptivega.world.IllegalZone;
import adaptivega.world.Line;
import adaptivega.world.Vector;
import adaptivega.world.World;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Raimonds Grismanausks
 */
public class BasicWorld extends World{

    public BasicWorld(List list) {
        super(40,//size
                2,2,//start x and y
                38,39,//end x and y
                list); //list of forbidden zones
        
    }
}