package adaptivega.world;
/* * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import adaptivega.world.IllegalZone;
import adaptivega.world.Line;
import clustering.Cluster;
import java.awt.geom.Line2D;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import javafx.geometry.Side;

/**
 *
 * @author Raimonds Grismanausks
 */
public class World {
    private int size;
    private int startx;
    private int starty;
    private int endx;
    private int endy;
    private int nForbiddenZones;//=7;//number of forbidden zones in map
//    private int nFZsides;//=4;    //num sides to illegal zone polygons
//    private final int nCoords=2*nFZsides;// 2*num points in polygons describing forbidden zones
    private List<IllegalZone> illegalZones;

    public World(int size, int startx, int starty, int endx, int endy, List<IllegalZone> illegalZones) {
        this.size = size;
        this.startx = startx;
        this.starty = starty;
        this.endx = endx;
        this.endy = endy;
        this.nForbiddenZones = illegalZones.size();
//        this.nFZsides = nFZsides;
        this.illegalZones = illegalZones;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public int getStartx() {
        return startx;
    }

    public void setStartx(int startx) {
        this.startx = startx;
    }

    public int getStarty() {
        return starty;
    }

    public void setStarty(int starty) {
        this.starty = starty;
    }

    public int getEndx() {
        return endx;
    }

    public void setEndx(int endx) {
        this.endx = endx;
    }

    public int getEndy() {
        return endy;
    }

    public void setEndy(int endy) {
        this.endy = endy;
    }

    public int getnForbiddenZones() {
        return nForbiddenZones;
    }

    public void setnForbiddenZones(int nForbiddenZones) {
        this.nForbiddenZones = nForbiddenZones;
    }

//    public int getnFZsides() {
//        return nFZsides;
//    }
//
//    public void setnFZsides(int nFZsides) {
//        this.nFZsides = nFZsides;
//    }

    public List<IllegalZone> getIllegalZones() {
        return illegalZones;
    }

    public void setIllegalZones(List<IllegalZone> illegalZones) {
        this.illegalZones = illegalZones;
    }
    
    public boolean crosses(Line line1,Line line2){
    double A,B,k,x1diff,y1diff,x2diff,y2diff;

	x1diff = (line1.getEndx()- line1.getStartx());//line1.getEndx - line1.getStartx);
	y1diff = (line1.getEndy()-line1.getStarty());//line1.getEndy - line1.getStarty);
	x2diff = (line2.getEndx()-line2.getStartx());//line2.getEndx - line2.getStartx); /* x2diff and y2diff cannot be zero at
//same time*/
	y2diff = (line2.getEndy()-line2.getStarty());//line2.getEndy - line2.getStarty);


/*  boundary cases   ****************************************************/

        //lines are paralell
	if((x1diff ==0 && x2diff==0 && line1.getStartx() != line2.getStartx() )
            || (y1diff==0 && y2diff==0 && line1.getStarty() != line2.getStarty()))
		return false;  /*parallel */

	if(x1diff ==0 && x2diff==0 && line1.getStartx() == line2.getStartx())
	 {
             if((between(line1.getStarty(),line2.getStarty(),line2.getEndy()) ||
	       between(line1.getEndy(),line2.getStarty(),line2.getEndy())))
	     return true; /*parallel but touching */
	  else return false;
	 }


	if(y1diff==0 && y2diff==0 && line1.getStarty() == line2.getStarty())
	{if((between(line1.getStartx(),line2.getStartx(),line2.getEndx()) ||
	       between(line1.getEndx(),line2.getStartx(),line2.getEndx())))
		{return true;} /*parallel but touching*/
	 else return false;
	}


	if(x1diff==0 && y1diff==0)  /* actually a point */
	{
            A=(line1.getStartx() - line2.getStartx())/x2diff;
		if(0<=A && A<=1 && A== ((line1.getStarty() - line2.getStarty())/y2diff))
			{return true;}
		else return false;
	}
	if(x1diff==0)
	{ 
            B=(line1.getStartx() - line2.getStartx())/x2diff;
		if(0<=B && B<=1)
		{A=(line2.getStarty() + B*(y2diff) - line1.getStarty())/y1diff;
		   if(0<=A && A<=1)
		   {
		    return true;
		  }
		   else return false;
		}
	       else return false;
	}
	if(x2diff==0)
	{
            A=(line2.getStartx() - line1.getStartx())/x1diff;
		if(0<=A && A<=1)
		{B=(line1.getStarty() + (A*y1diff) - line2.getStarty())/y2diff;
			if(0.0 <= B && B <= 1.0)
			{
			  return true;
			}
			else return false;
		}
		else return false;
	}
	if(y1diff==0)
	{
            B=(line1.getStarty() - line2.getStarty())/y2diff;
		if(0<=B && B<=1)
		{A=(line2.getStartx() + B*x2diff - line1.getStartx())/x1diff;
			if(0<=A && A<=1)
			{
			  return true;
			}
			else return false;
		}
		else return false;
	}
	if(y2diff==0)
	{
            A=(line2.getStarty() - line1.getStarty())/y1diff;
		if(0<=A && A<=1)
		{B=(line1.getStartx() + A*x1diff  - line2.getStartx())/x2diff;
			if(0<=B && B<=1)
			{
			 return true;
			}
			else return false;
		}
		else return false;
	}
/* standard cases   ********************************************/


/* else see if values between 0 and 1 for A and B for vector eqn.
r1 + A(r2-r1) = R1 + B(R2-R1), wher r1,r2 and R1,R2 are vectors of end pnts of
line segments */
	if(y1diff != 0 && y2diff != 0)
	{k=((x2diff*y1diff/x1diff) - y2diff);
	   if(k==0)  /* parallel, singularity  */
	   {A=(line1.getStartx() - line2.getStartx())/x2diff;
		if(0<=A && A<=1 && A== ((line1.getStarty() - line2.getStarty())/y2diff))
			{ return true;} /*2 lines coincident*/
		else
		{A=(line1.getEndx() - line2.getStartx())/x2diff;
		if(0<=A && A<=1 && A== ((line1.getEndy() - line2.getStarty())/y2diff))
			{ return true;} /*2 lines coincident*/

		else return false; /* parallel */
		}
	}
	   else
	    {B=((line2.getStarty()) + ((line1.getStartx())*y1diff/x1diff) - (line1.getStarty() + ((line2.getStartx())*y1diff/x1diff)))/k;
		if(0<=B && B<=1)
		{A = ((line2.getStartx()/x1diff) + B*(x2diff/x1diff)-((line1.getStartx())/x1diff));
			if(0<=A && A<=1)
			  {  return true;}
			else return false;
		}
		else return false;
	   }
	}
	return false;
    }

    public int numberOfIllegalCrosses(Line line){
        
	int count=0;
	for(int i=0;i<this.illegalZones.size();i++){
		for(int j=0;j<this.illegalZones.get(i).getnSides();j++){
			if(crosses(line,this.illegalZones.get(i).getSideAt(j))){
				count++;
				break;
			}
		}
	}
	return count;
    }
    
    public boolean between(int x, int y1, int y2){
        if(y1>y2)
	{if(y2 <= x && x <= y1)
		return true;
	}
	else
	{if(y1<=x && x <= y2)
		return true;
	}
	return false;
    }

    public void printEverything(Population newPop) {
        for (int i=0;i<this.size;i++){
            for (int j=0;j<this.size;j++){
                if(i==this.startx&&j==this.starty){
                    System.out.print(" S ");
                    continue;
                }
                if(i==this.endx&&j==this.endy){
                    System.out.print(" E ");
                    continue;
                }
                
                
                boolean flag1 =false;
                for (IllegalZone z:this.illegalZones){
                    for(Line s:z.getSides()){
                        
                        if(this.isPointOnLine(new Vector(j,i), s)){//this.crosses(new Line(i,j,i,j), s)){
                            System.out.print(" x ");
                            flag1=true;
                            break;
                        }
                        
                        //else System.out.print("   ");
                    }
                    if(flag1)break;
                }
                
                if(flag1) continue;
                boolean flag =false;
//                for(PathCoords p : newPop.getPopulation()){
//                        flag = false;
//                    for(Vector v:p.coords){
//                        if(v.x==j&&v.y==i){
//                            flag=true;
//                            System.out.println(" A ");
//                            break;
//                    }
//                    }
//                    if(flag)continue;
                    for(PathCoords p : newPop.getPopulation()){
                    Path path = p.getPath();
                    for(Line l:path.getPath()){
                        if(this.isPointOnLine(new Vector(j,i), l)){
                            System.out.print(" + ");
                            flag=true;break;
                        }
                    }
                    if(flag){
                            break;
                        }
                }
                if(flag) continue;
                    System.out.print("   ");
            }
            System.out.println("");
        }
    }
    
    public boolean isPointOnLine(Vector v,Line l){
        double d=Line2D.ptSegDist(l.getStartx(),l.starty,l.getEndx(),l.getEndy(),v.getX(), v.getY());
        //System.out.println(d);
        //rounding up the values as we are working in a discrete environment with 
        //countnuous values for vectors
        return d<0.5&&d>=0;
        
    }

    
    public void printChromosome(PathCoords bestMember) {
        for (int i=0;i<this.size;i++){
            for (int j=0;j<this.size;j++){
                if(j==this.startx&&i==this.starty){
                    System.out.print(" S ");
                    continue;
                }
                if(j==this.endx&&i==this.endy){
                    System.out.print(" E ");
                    continue;
                }
                
                
                boolean flag1 =false;
                for (IllegalZone z:this.illegalZones){
                    for(Line s:z.getSides()){
                        
                        if(this.isPointOnLine(new Vector(j,i), s)){//this.crosses(new Line(i,j,i,j), s)){
                            System.out.print(" X ");
                            flag1=true;
                            break;
                        }
                        
                        //else System.out.print("   ");
                    }
                    if(flag1)break;
                }
                
                if(flag1) continue;
                boolean flag =false;
                if(bestMember==null){
                        
                    }
                else{
                    Path path = bestMember.getPath();//p.getPath();
                    
                    
                        for(Line l:path.getPath()){
                        if(this.isPointOnLine(new Vector(j,i), l)){
                            System.out.print(" # ");
                            flag=true;break;
                        }
                    }
                    }
                if(flag) continue;
                    System.out.print(" ' ");
            }
            System.out.println("");
        }
        System.out.println("");
    }
}