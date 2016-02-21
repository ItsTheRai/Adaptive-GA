/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package methodClass;

import adaptivega.world.Line;
import adaptivega.world.Path;
import adaptivega.world.PathCoords;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 *
 * @author Raimonds Grismanausks
 */
public class CrossoverFunctionImp implements CrossoverFunction{

    //private final double crossoverProb=0.15;
    //private final double mutationProb=0.85;
    
    
    public CrossoverFunctionImp() {
    }

    
    @Override
    //this wont work as need to change the lines each time the coordinates change, will solve with
    //a coord to line function
    //path one is the inition path, path 2 is a random one to do crossover with
    public List<PathCoords> doCrossower0(PathCoords path1, PathCoords path2,double crossoverProb) {
        
        if(path1.getCoords().get(0).getX()!=2||path1.getCoords().get(0).getY()!=2){
                    System.out.println("uhhhh");
        }
        //check if first adn last set of coords is corrent (matches start adn finish)
        if (path1.getCoords().get(path1.getCoords().size()-1).getX()!=17||
                path1.getCoords().get(path1.getCoords().size()-1).getY()!=15){
//            System.out.println("uhhhh");
        }
        
        
        Random random=new Random(System.currentTimeMillis());
        //if(path1.getCoords().size()!=3){
        //    System.out.println("2");
        //}
        List<PathCoords> list = new ArrayList<>();
        //init return objects
        PathCoords p1 = new PathCoords(new ArrayList<>());
        PathCoords p2 = new PathCoords(new ArrayList<>());
        for (int i=0;i<path1.getCoords().size();i++){
            p1.getCoords().add(path1.getPathAt(i));
            p2.getCoords().add(path2.getPathAt(i));
        }
        if(random.nextDouble()<=crossoverProb){
            int i = random.nextInt(p1.getCoords().size());
            //next index after i is crossed over to the other chromosome
            //copy end of path 2 to path1
                p1.changePathCoordsFrom(i+1, path2.getPathCoordsFrom(i+1));
                //copy beginning of path path1 to path 2
                p2.changePathCoordsTo(i, path1.getPathCoordsTo(i));
            //choose a random version to return 
        }
        if(p1.getCoords().get(0).getX()!=2||p1.getCoords().get(0).getY()!=2){
                    System.out.println("uhhhh");
        }
        if (p1.getCoords().get(p1.getCoords().size()-1).getX()!=17||
                p1.getCoords().get(p1.getCoords().size()-1).getY()!=15){
//            System.out.println("uhhhh");
        }
        list.add(p1);
        list.add(p2);
        return list;
    }
}