/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package adaptivega.world;

import java.util.List;

/**
 *
 * @author Raimonds Grismanausks
 */
public class Path {
    List<Line> path;
    public Path(List<Line> path){
        this.path=path;
    }

    public List<Line> getPath() {
        return path;
    }

    public void setPath(List<Line> path) {
        this.path = path;
    }
    
    public Line getLineAt(int index){
        return this.path.get(index);
    }
    
    public void changePathAt(int index,Line line){
        this.path.set(index, line);
    }
    
    public void changePathSegmentAt(int startIndex,List<Line> path){
        int index=startIndex;
        for (int i=0;i<path.size();i++){
            this.path.set(index++, path.get(i));
        }
    }
    
    public PathCoords getPathCoords(){
        PathCoords pathCoords = new PathCoords(null);
        int i;
        for (i=0;i<this.path.size();i++){
            pathCoords.coords.add(new Vector(this.path.get(i).startx,this.path.get(i).starty));
        }
        pathCoords.coords.add(new Vector(this.path.get(i).endx,this.path.get(i).endy));
        return pathCoords;
    }
}