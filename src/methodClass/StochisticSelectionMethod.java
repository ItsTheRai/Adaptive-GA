/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package methodClass;

import adaptivega.world.PathCoords;
import adaptivega.world.Population;
import adaptivega.world.World;
import java.util.ArrayList;

/**
 *
 * @author Raimonds Grismanausks
 */
public class StochisticSelectionMethod implements SelectionMethod{

    public StochisticSelectionMethod() {
    }

    @Override
    //returns a new population selected based on fitness distribution
    //chromosomes can be pressesnt repeatedly
    public Population selectPopulation(Population p,World w) {
        Population newPop = new Population(new ArrayList<>());
        int n=p.getPopulation().size();
	double [] fitness = new double [n];
        
        FitnessFunction s = new FitnessFunctionImp();
        //get all fitnesses
        for (int i=0;i<n;i++){
            fitness[i]=s.getFitness(w, p.getPopulation().get(i));
        }
        //get fittest individual
        PathCoords maxFitness = s.getFittestIndividual(w, p);
        double bestFitness = s.getFitness(w, maxFitness);
	int  [] counter = new int[n];
	int n_select=n;
	int index=0;
	boolean notaccepted;
//        newPop.getPopulation().add(maxFitness);
	for (int i=0; i<n_select; i++){
		notaccepted=true;
		while (notaccepted){
			index= (int)(n*Math.random());
			if(Math.random()<(1/fitness[index])/bestFitness) {
                            notaccepted=false;
                        }
		}
                newPop.getPopulation().add(p.getPopulation().get(index));
		counter[index]++;
	}
        return newPop;
    }
}