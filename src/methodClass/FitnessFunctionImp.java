/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package methodClass;

import adaptivega.world.Line;
import adaptivega.world.PathCoords;
import adaptivega.world.Population;
import adaptivega.world.World;

/**
 *
 * @author Raimonds Grismanausks
 */
public class FitnessFunctionImp implements FitnessFunction{

    @Override
    public PathCoords getFittestIndividual(World world, Population pop) {
        double[] results = getPolulationFitnessArray(world,pop);
        
//        double bestPossibleFitness = AStart.getBestFitness(world);
//        double bestFitness=999999;// = 0-bestPossibleFitness;
//        empty population or something
        if(results.length<=0){
            return null;
        }
//        set first value as best value to begin with
        double bestResult=results[0];
        int bestInd=0;
        for(int i=1;i<results.length;i++){
            //minimise fitness for best candidate
            if(results[i]<bestResult){
                bestResult=results[i];
                bestInd= i;
            }
        }
//         for (PathCoords individual:pop.getPopulation()){
//            double tempFitness=getFitness(world,individual);
//            if(tempFitness<bestFitness){
//                 bestFitness=tempFitness;
//                 bestInd = individual;
//            }
//        }
        return pop.getPopulation().get(bestInd);
    }
    
    @Override
    public PathCoords getWorstIndividual(World world, Population pop) {
        double[] results = getPolulationFitnessArray(world,pop);
        if(results.length<=0){
            return null;
        }
//        set first value as best value to begin with
        double bestResult=results[0];
        int worstInd=0;
        for(int i=1;i<results.length;i++){
            //maximise fitness for worst candidate
            if(results[i]>bestResult){
                bestResult=results[i];
                worstInd= i;
            }
        }
        return pop.getPopulation().get(worstInd);
    }

    @Override
    public double getFitness(World world, PathCoords individual) {
//        double bestFitness = 100;//AStart.getBestFitness(world);
        double fitness = 0;//-bestFitness;
        int penalty = 0;
        int i=0;
        for (Line l:individual.getPath().getPath()){
            fitness +=l.getLength();
            //add a penalty for crossing an illegal zone proportional to the length of the line
            double pen = world.numberOfIllegalCrosses(l);
            fitness+=pen*100;
//        System.out.println("illegal cross for line "+i+": "+world.numberOfIllegalCrosses(l));
        i++;
        }
        return fitness;
    }
    
    public double[] getPolulationFitnessArray(World world,Population population){
        double[] results = new double[population.getPopulation().size()];
        for(int i=0;i<results.length;i++){
            results[i]=getFitness(world,population.getPopulation().get(i));
        }
        return results;
    }
}