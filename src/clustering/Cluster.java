/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clustering;

import adaptivega.world.PathCoords;
import java.util.List;

/**
 *
 * @author Raimonds Grismanausks
 */
public class Cluster {
    private List<PathCoords> clusterMembers;
    private double[] meanXArray;
    private double[] meanYArray;
    
    public Cluster(List<PathCoords> clusterMembers){
        this.clusterMembers=clusterMembers;
        this.updateMean();
    }
    
    public void addToCluster(PathCoords ind){
        this.clusterMembers.add(ind);
        updateMean();
    }
    
    public PathCoords removeFromCluster(PathCoords p) {
        clusterMembers.remove(p);
        updateMean();
        return p;
    }
    
    public void updateMean(){
        int vectorSize=this.clusterMembers.get(0).getCoords().size();
        double dataArray[][][] = 
                new double[this.clusterMembers.size()][this.clusterMembers.get(0).getCoords().size()][2];
        //get all coords values for all members of the population
        for(int i=0;i<dataArray.length;i++){
            for(int j=0;j<dataArray[i].length;j++){
                dataArray[i][j][0]=this.clusterMembers.get(i).getCoords().get(j).getX();
                dataArray[i][j][1]=this.clusterMembers.get(i).getCoords().get(j).getY();
            }
        }
        //extract all line coords 
        //we assume here that all vectors are of the same length
        double[] eachVectorMeanX=new double[vectorSize];
        double[] eachVectorMeanY=new double[vectorSize];
        for(int i=0;i<vectorSize;i++){
            double means1[] = new double[this.clusterMembers.size()];
            double means2[] = new double[this.clusterMembers.size()];
            //copy all the vector information for all members to array
            for(int j =0;j<this.clusterMembers.size();j++){
                means1[j]=dataArray[j][i][0];
                means2[j]=dataArray[j][i][1];
            }
            //this is the mean value of each coordinate vector
            eachVectorMeanX[i] = this.getMean(means1);
            eachVectorMeanY[i] = this.getMean(means2);
        }
        double[] d = new double[2];
        this.meanXArray=eachVectorMeanX;
        this.meanYArray=eachVectorMeanY;
    }
    
    
    public double getDistance(PathCoords coords){
        double distanceX=0;
        double distanceY=0;
        double distance=0;
        for(int i=0;i<coords.getCoords().size();i++){
            distanceX+=Math.abs(this.meanXArray[i]-coords.getCoords().get(i).getX());
            distanceY+=Math.abs(this.meanYArray[i]-coords.getCoords().get(i).getY());
            //calculate euclidien distance
            distance+=Math.sqrt(Math.pow(distanceX, 2)+Math.pow(distanceY, 2));
        }
        
        //average the distance from each coordinate set
//        distance=distance/coords.getCoords().size();
        return distance;
    }
    
    public int getClusterSize(){
        return this.clusterMembers.size();
    }
    //returns a double that indicates how far the furthest member is from the cluster
    public double getLargestDistance(){
        double size=0;
        for(PathCoords p:this.clusterMembers){
            double tempDistance=this.getDistance(p);
            if(tempDistance>size){
                size=tempDistance;
            }
        }
        return size;
        
    }
   public double getMean(double[] array){
        double m=0;
        for (int  i=0;i<array.length;i++){
            m +=array[i];
        }
        return m/array.length;
    }
    


    public double[] getMeanX() {
        return meanXArray;
    }

    public double[] getMeanY() {
        return meanYArray;
    }

    public List<PathCoords> getCoords() {
        return this.clusterMembers;
    }

    
    
    
}