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

public interface IClosedSet {

	public boolean contains(ISearchNode node);
	public void add(ISearchNode node);
	public ISearchNode min();

}
