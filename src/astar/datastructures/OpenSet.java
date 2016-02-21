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
import java.util.Comparator;
import java.util.PriorityQueue;

import astar.ISearchNode;

public class OpenSet implements IOpenSet {
	private PriorityQueue<ISearchNode> Q;
	
	public OpenSet(Comparator<ISearchNode> comp) {
		Q = new PriorityQueue<ISearchNode>(1000, comp);
    }

	@Override
	public void add(ISearchNode node) {
		this.Q.add(node);
	}

	@Override
	public void remove(ISearchNode node) {
		this.Q.remove(node);

	}

	@Override
	public ISearchNode poll() {
		return this.Q.poll();
	}

	@Override
    public ISearchNode getNode(ISearchNode node) {
        for(ISearchNode openSearchNode : this.Q) {
            if(openSearchNode.equals(node)) {
                return openSearchNode;
            }
        }
        return null;
    }

	@Override
	public int size() {
		return this.Q.size();
	}

}