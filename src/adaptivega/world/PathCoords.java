/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package adaptivega.world;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Raimonds Grismanausks
 */
public class PathCoords {
 
    List<Vector> coords;

    public PathCoords(List<Vector> coords) {
        if(coords==null){
            this.coords=new ArrayList<>();
        }
        else
        this.coords = new ArrayList<Vector>(coords);
    }

    public List<Vector> getCoords() {
        return coords;
    }

    public Vector getPathAt(int index){
        return this.coords.get(index);
    }
    
    public void setCoords(List<Vector> coords) {
        this.coords = coords;
    }
    
    public void changePathAt(int index,Vector v){
        this.coords.set(index, v);
    }
    
    public List<Vector> getPathCoordsFrom(int startIndex){
        return this.coords.subList(startIndex, this.coords.size());
    }
    
    public List<Vector> getPathCoordsTo(int endIndex){
        return this.coords.subList(0, endIndex+1);
    }
    
    public void changePathCoordsFrom(int startIndex,List<Vector> path){
        //int index=startIndex;
        for (int i=startIndex;i<path.size();i++){
            this.coords.set(i, path.get(i));
            //this.path.set(index++, path.get(i));
        }
    }
    
    public void changePathCoordsTo(int endIndex,List<Vector> path){
        int index=endIndex;
        for (int i=0;i<=index;i++){
            this.coords.set(i, path.get(i));
            //this.path.set(index++, path.get(i));
        }
    }
    
    public Path getPath(){
        Path path = new Path(new ArrayList<>());
        for (int i=0;i<this.coords.size()-1;i++){
            path.getPath().add(new Line(this.coords.get(i).x,this.coords.get(i).y,
            this.coords.get(i+1).x,this.coords.get(i+1).y));
        }
        return path;
    }
}
