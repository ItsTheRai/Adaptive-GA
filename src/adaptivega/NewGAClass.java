/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package adaptivega;

import adaptivega.world.PathCoords;
import adaptivega.world.Population;
import adaptivega.world.World;
import clustering.Cluster;
import java.util.ArrayList;
import java.util.List;
import methodClass.CrossoverFunction;
import methodClass.FitnessFunction;
import methodClass.MutationFunction;
import methodClass.SelectionMethod;

/**
 *
 * @author Raimonds Grismanausks
 */
public class NewGAClass {
    
    private PathCoords bestMember;
    private PathCoords worstMember;
    private Population population;
    private final World world;
    private double crosseverRate;
    private double mutationRate;
    private final FitnessFunction fitnessFunction;
    private final CrossoverFunction crossFunction;
    private final SelectionMethod selectionMethod;
    private final MutationFunction mutationFunction;
//    private final int maxGen = 1000;
    private boolean isAdaptive;
    private static final double learningRate = 0.005;

    public NewGAClass(Population population, World world, double crosseverRate, double mutationRate, SelectionMethod selectionMethod, 
            FitnessFunction fitnessFunction, CrossoverFunction crossFunction,MutationFunction mutationFunction,boolean isAdaptive) {
        this.population = population;
        this.world = world;
        this.crosseverRate = crosseverRate;
        this.mutationRate = mutationRate;
        this.fitnessFunction = fitnessFunction;
        this.crossFunction = crossFunction;
        this.selectionMethod=selectionMethod;
        this.mutationFunction=mutationFunction;
        this.isAdaptive=isAdaptive;
    }
    
    public void runGA(int steps){
        world.printChromosome(null);
        world.printChromosome(this.fitnessFunction.getFittestIndividual(world, population));//.printEverything(population);
        int timer=0;
        double resultFitness = 11.5;//this is the distance from the global optima (0)
        //loop while solution is found or maxGen timer runs out
        //1 loop is one generation
        System.out.println("--------"+this.fitnessFunction.getFitness(world, this.fitnessFunction.getFittestIndividual(world, population)));
        
        while(timer<steps&&
//            this.fitnessFunction.getFitness(world, this.fitnessFunction.getFittestIndividual(world, population))
//                >resultFitness
                this.fitnessFunction.getFitness(world, this.fitnessFunction.getFittestIndividual(world, population))>77.2){
            System.out.println("---------------------STARTING LOOP-----------------------");
            System.out.println("Loop number :"+timer);
            ///do all the adaptive stuff herer
            //dynamic parameter adjustments
            if(timer==0) {
                PathCoords bestTemp = this.fitnessFunction.getFittestIndividual(world, population);
                PathCoords worstTemp = this.fitnessFunction.getWorstIndividual(world, population);
                this.bestMember=bestTemp;
                this.worstMember = worstTemp;
            }
            //standart case after first iteration
            if(timer>1){//first generation does not have a previous generation to reference
                //get best member of population and set it as reference point
//                System.out.println("old pop  -----------------------------");
//            this.world.printChromosome(this.bestMember);
            //init return population
            Population newPop = new Population(new ArrayList<>());
            //create new population that is used to store using selected selection method
//            add best member of population automatically in the selection method - not needed
            //because the best individual will beadded if not improved
            Population tempPop = this.selectionMethod.selectPopulation(population, world);
//            if(tempPop.getPopulation().size()!=2){
//                System.out.println("asafadfas");
//            }
            while(!tempPop.getPopulation().isEmpty()){
                //corssover results
                List<PathCoords> list = this.crossFunction.doCrossower0(tempPop.getPopulation().remove(0),
                        tempPop.getPopulation().remove(0), 
                        this.crosseverRate);
                //mutate results and add to the new population array
                newPop.getPopulation().add(this.mutationFunction.doMutation0(list.remove(0), mutationRate,this.world.getSize()));
                newPop.getPopulation().add(this.mutationFunction.doMutation0(list.remove(0), mutationRate,this.world.getSize()));
//                if(newPop.getPopulation().size()!=2){
//                System.out.println("asafadfas");
//            }
            }
                PathCoords bestTemp = this.fitnessFunction.getFittestIndividual(world, newPop);
                PathCoords worstTemp = this.fitnessFunction.getWorstIndividual(world, newPop);
                
                if(this.fitnessFunction.getFitness(world, bestTemp)<
                        this.fitnessFunction.getFitness(world, this.bestMember)){
                    System.out.println("best : "+this.fitnessFunction.getFitness(world, bestTemp));
                    System.out.println("best : "+this.fitnessFunction.getFitness(world, bestMember));
                    this.bestMember=bestTemp;
                    this.worstMember = worstTemp;
                }
                //else the new generation is worse than the previous so keep the best individual
                else {
                    //remove the best and worst individual as they are worse than the
                    //previouse generation
                    System.out.println("best : "+this.fitnessFunction.getFitness(world, bestTemp));
                    System.out.println("best : "+this.fitnessFunction.getFitness(world, bestMember));
                    newPop.getPopulation().remove(bestTemp);
                    newPop.getPopulation().add(this.bestMember);
                    
                    newPop.getPopulation().remove(worstTemp);
                    newPop.getPopulation().add(this.bestMember);
                    
                }
                this.population = newPop;//newPop;
                //if this is the first generation, we need to find the fittest and 
                //lease fit member and add them for reference
            }
            
            System.out.println("new pop--------------------------");
            this.world.printChromosome(this.bestMember);
            PathCoords p = fitnessFunction.getFittestIndividual(world, population);
            System.out.println("best fitness pop: "+this.fitnessFunction.getFitness(world, p));
            System.out.println("best fitness var: "+this.fitnessFunction.getFitness(world, bestMember));
            System.out.println("worst fitness var: "+this.fitnessFunction.getFitness(world, worstMember));
            
//          System.out.println("step "+(timer-1)+ "\\"+steps);
            System.out.println("cross" +this.crosseverRate);
            System.out.println("mut "+this.mutationRate);
            
            //adjust Pm and Px if needed
            if(isAdaptive){
                adjustProperties();
            }
        timer++;
        }
    }

    private void adjustProperties(){
//        double tempCross;
//        double tempMut;
        List<Cluster> clusters=population.clusterPopulation(7);
        int[] l=new int[clusters.size()];
        for (int i=0;i<clusters.size();i++){
            l[i]=clusters.get(i).getClusterSize();
        }
        Cluster largestCluster = null;
        Cluster smallestCluster = null;
        int largestSize=Integer.MIN_VALUE;
        int smallestSize = Integer.MAX_VALUE;
        //find smallest and largest cluster
        for (int i=0;i<clusters.size();i++){
            if(clusters.get(i).getClusterSize()>largestSize){
                largestSize=clusters.get(i).getClusterSize();
                largestCluster=clusters.get(i);
            }
            if(clusters.get(i).getClusterSize()<smallestSize){
                smallestSize=clusters.get(i).getClusterSize();
                smallestCluster=clusters.get(i);
            }
        }
        Cluster bestCluster = null;
        Cluster worstCluster= null;
        PathCoords bestMember = this.fitnessFunction.getFittestIndividual(world, population);
        PathCoords worstMember = this.fitnessFunction.getWorstIndividual(world, population);
        int bestSize=-1;
        int worstSize=-3;
        for(Cluster cluster:clusters){
            if(cluster.getCoords().contains(bestMember)){
                bestSize=cluster.getClusterSize();
                bestCluster=cluster;
            }
            if(cluster.getCoords().contains(worstMember)){
                worstSize=cluster.getClusterSize();
                worstCluster=cluster;
            }
        }
        if(bestSize==worstSize){
            bestSize+=1;
        }
        if(largestSize==smallestSize){
            largestSize++;
        }
        double tempBest = (bestSize - smallestSize)/(largestSize-smallestSize);
        double tempWorst = (worstSize - smallestSize)/(largestSize-smallestSize);
        
        //fuzzification of eoutput from clustering
        double positiveSmallBest = 1-tempBest;
        double positiveSmallWorst = 1-tempWorst;
        double positiveBigBest = tempBest;
        double positiveBigWorst = tempWorst;
        
        double mutIncrease;
        double crosIncrease;
        
        //check for all 4 rules
       
        if((largestCluster.getCoords().contains(bestMember)&&
                smallestCluster.getCoords().contains(worstMember))||
                largestSize==bestSize&&
                smallestSize==worstSize){
            
            if(this.crosseverRate-learningRate>0.35){
            this.crosseverRate-=learningRate;
            }
            if(mutationRate-learningRate>0.05)
            this.mutationRate-=learningRate;
        }
        else if((largestCluster.getCoords().contains(bestMember)&&
                largestCluster.getCoords().contains(worstMember))||
                (largestSize==bestSize&&
                largestSize==worstSize)
                &&largestSize==smallestSize){
//            if(this.crossoverRate+learningRate<0.95){
            if(this.crosseverRate+learningRate<0.75){
                this.crosseverRate+=learningRate;
            }
            if(this.mutationRate-learningRate>0.05){
            this.mutationRate-=learningRate;
            }
        }
        else if((smallestCluster.getCoords().contains(bestMember)||
                smallestCluster.getCoords().contains(worstMember))||
                (smallestSize==bestSize&&
                smallestSize==worstSize)
                &&largestSize==smallestSize){
//            if(this.crossoverRate+learningRate<0.95){
            if(this.crosseverRate+learningRate<0.75){
                this.crosseverRate+=learningRate;
            }
            if(this.mutationRate+learningRate<0.3){
            this.mutationRate+=learningRate;
            }
        }
        else if(largestCluster.getCoords().contains(worstMember)&&
                smallestCluster.getCoords().contains(bestMember)||
                largestSize==worstSize&&
                smallestSize==bestSize){
            if(this.crosseverRate-learningRate>065){
            this.crosseverRate-=learningRate;
            }
            if(this.mutationRate<0.3){
            this.mutationRate+=learningRate;
            }
        }
//        System.out.println(bestCluster.getClusterSize());
//        System.out.println("cluster 1: "+clusters.get(0).getClusterSize());
//        System.out.println("cluster 2: "+clusters.get(1).getClusterSize());
//        System.out.println("cluster 3: "+clusters.get(2).getClusterSize());
//        System.out.println("cluster 4: "+clusters.get(3).getClusterSize());
//        System.out.println("cluster 5: "+clusters.get(4).getClusterSize());
//        System.out.println("cluster 6: "+clusters.get(5).getClusterSize());
//        System.out.println("cluster 7: "+clusters.get(6).getClusterSize());
    }
}