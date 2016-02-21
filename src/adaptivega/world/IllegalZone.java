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
public class IllegalZone {
    private int nSides;
    private List<Line> sides;
    public IllegalZone(List<Line> zoneBorders){
        this.sides=zoneBorders;
        this.nSides=this.sides.size();
    }

    public int getnSides() {
        return nSides;
    }

    public void setnSides(int nSides) {
        this.nSides = nSides;
    }

    public List<Line> getSides() {
        return sides;
    }

    public void setSides(List<Line> sides) {
        this.sides = sides;
    }
    
    public Line getSideAt(int index){
        return this.sides.get(index);
    }
}
