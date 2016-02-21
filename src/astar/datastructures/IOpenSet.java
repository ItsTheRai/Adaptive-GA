/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package astar.datastructures;

/**
 *
 * @author Raimonds Grismanausks
 */
import astar.ISearchNode;

public interface IOpenSet {
	
	public void add(ISearchNode node);
	public void remove(ISearchNode node);
	public ISearchNode poll();
	//returns node if present otherwise null
	public ISearchNode getNode(ISearchNode node);
	public int size();

}
