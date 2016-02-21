/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package adaptivega;

import adaptivega.basicStuff.BasicPopulation;
import adaptivega.basicStuff.BasicWorld;
import adaptivega.world.IllegalZone;
import adaptivega.world.Line;
import java.util.ArrayList;
import java.util.List;
import methodClass.CrossoverFunctionImp;
import methodClass.FitnessFunctionImp;
import methodClass.StandartMutationFunction;
import methodClass.StochisticSelectionMethod;

/**
 *
 * @author Raimonds Grismanausks
 */
public class AdaptiveGA {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        //square 1
//        World w = new BasicWorld();
        List<IllegalZone> list = new ArrayList<>();
        List<Line> lines = new ArrayList<>();
        lines.add(new Line(7,0,7,25));
        lines.add(new Line(7,25,13,13));
        lines.add(new Line(13,13,13,0));
        lines.add(new Line(13,0,7,0));
        
        //square 2
        List<Line> lines2 = new ArrayList<>();
        lines2.add(new Line(13,30,13,40));
        lines2.add(new Line(13,40,20,40));
        lines2.add(new Line(20,40,25,15));
        lines2.add(new Line(25,15,13,30));
       
        List<Line> lines3 = new ArrayList<>();
        lines3.add(new Line(35,33,39,33));
        lines3.add(new Line(39,33,39,37));
        lines3.add(new Line(39,37,35,37));
        lines3.add(new Line(35,37,35,33));
        
//        List<Line> lines4 = new ArrayList<>();
//        lines4.add(new Line(27,39,33,27));
//        lines4.add(new Line(33,27,25,27));
//        lines4.add(new Line(25,27,27,39));
//        
//        List<Line> lines5 = new ArrayList<>();
//        lines5.add(new Line(27,23,37,20));
//        lines5.add(new Line(37,20,35,10));
//        lines5.add(new Line(35,10,27,23));
//        
        list.add(new IllegalZone(lines));
        list.add(new IllegalZone(lines2));
        list.add(new IllegalZone(lines3));
//        list.add(new IllegalZone(lines4));
//        list.add(new IllegalZone(lines5));
        NewGAClass test = new NewGAClass(new BasicPopulation(300),new BasicWorld(list),0.5,0.2,new StochisticSelectionMethod(),
                new FitnessFunctionImp(),new CrossoverFunctionImp(),new StandartMutationFunction(),true);
        test.runGA(1000);
    }
}