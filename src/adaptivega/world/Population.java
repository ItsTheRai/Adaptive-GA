/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package adaptivega.world;

import clustering.Cluster;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 *
 * @author Raimonds Grismanausks
 */
public class Population {
    private List<PathCoords> population;
    //private double bestFitness;

    public Population(List<PathCoords> population) {
        this.population = population;
        //bestFitness=Double.MAX_VALUE;
    }
    
    
    //public double getBestFitness(){
        //double tempFit = Double.MAX_VALUE;
        //for (PathCoords pc:population){
        //    if()
        //}
    ///}

    public List<PathCoords> getPopulation() {
        return population;
    }

    public void setPopulation(List<PathCoords> population) {
        this.population = population;
    }

    public List<Cluster> clusterPopulation(int numberClusters){
        Population tempPop = new Population(new ArrayList<>());
        for(int i=0;i<population.size();i++){
            tempPop.population.add(population.get(i));
        }
        //choose initial centres
        Random r = new Random();
        List<Cluster> list = new ArrayList<>();
        for(int i=0;i<numberClusters;i++){
            int rand=r.nextInt(tempPop.getPopulation().size());
            PathCoords randomMember= tempPop.getPopulation().remove(rand);
            List<PathCoords> rList = new ArrayList<>();
            rList.add(randomMember);
            list.add(new Cluster(rList));
        }
        
        //loop untill all members of the population are assigned clusters
        for(PathCoords p:tempPop.population){
            double bestDistance = 99999;
            Cluster bestCluster=null;
            for(int i=0;i<numberClusters;i++){
                double tempD=list.get(i).getDistance(p);
                if(tempD<bestDistance){
                    bestDistance=tempD;
                    bestCluster=list.get(i);
                }
            }
            bestCluster.addToCluster(p);
        }
        //both clusters are full now, reevaluate of everything is clustered correctly
        boolean change = true;
        while(change){
            change=false;
            for(int i=0;i<numberClusters;i++){
                Cluster c = list.get(i);
                for(PathCoords p:list.get(i).getCoords()){
                    double bestDist=c.getDistance(p);
                
                    for(int k=0;k<numberClusters;k++){
                        if(list.get(i).getDistance(p)<bestDist){
                            bestDist=list.get(i).getDistance(p);
                            c=list.get(k);
                        }
                    }
                if(c!=list.get(i)){
                    list.get(i).removeFromCluster(p);
                    c.addToCluster(p);
                    change=true;
                    break;
                }
                }
                if (change) break;
            }
        }
        return list;
    }
    
    public PathCoords getNearestMember(Cluster c){
        return null;
    }
//    public double getBestFitness() {
//        return bestFitness;
//    }
//
//    public void setBestFitness(double bestFitness) {
//        this.bestFitness = bestFitness;
//    }
}
