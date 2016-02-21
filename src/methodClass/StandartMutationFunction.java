/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package methodClass;

import adaptivega.world.Path;
import adaptivega.world.PathCoords;
import adaptivega.world.Vector;
import java.util.ArrayList;
import java.util.Random;

/**
 *
 * @author Raimonds Grismanausks
 */
public class StandartMutationFunction implements MutationFunction{

    
    @Override
    public PathCoords doMutation0(PathCoords path, double mutationProb, int mapSize) {
        Random random=new Random();
        //this is necassary for some reason or the whole thing will break
        //TODO find out what exactpy goin on
        PathCoords p1 = new PathCoords(new ArrayList());
        for (int i=0;i<path.getCoords().size();i++){
            p1.getCoords().add(new Vector(path.getPathAt(i).getX(),path.getPathAt(i).getY()));
        }
        boolean b = false;
        //size-1 is because the end coordinates can not change
        for (int i=1;i<path.getCoords().size()-1;i++){
            if(random.nextDouble()<=mutationProb){
//                path.getCoords().get(i).setX(random.nextInt(mapSize));
//                path.getCoords().get(i).setY(random.nextInt(mapSize));
                b=true;
                p1.getCoords().get(i).setX(random.nextInt(mapSize));
                p1.getCoords().get(i).setY(random.nextInt(mapSize));
            }
        }
        
        
//        if(p1.getCoords().get(0).getX()!=2&&p1.getCoords().get(0).getY()!=2){
//                    System.out.println("uhhhh");
//                }
//        if (p1.getCoords().get(p1.getCoords().size()-1).getX()!=17&&
//                p1.getCoords().get(p1.getCoords().size()-1).getY()!=15){
//            System.out.println("uhhhh");
//        }
        
        
        if(b==true)return p1;
        else return path;
//        return path;
    }
}