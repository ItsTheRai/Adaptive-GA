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
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;

import astar.ISearchNode;

public class ClosedSetHash implements IClosedSet {
	private HashMap<Integer, ISearchNode> hashMap;
	private Comparator<ISearchNode> comp;
	
	public ClosedSetHash(Comparator<ISearchNode> comp) {
		this.hashMap = new HashMap<Integer, ISearchNode>();
		this.comp = comp;
		
	}

	@Override
	public boolean contains(ISearchNode node) {
		return this.hashMap.containsKey(node.keyCode());
	}

	@Override
	public void add(ISearchNode node) {
		this.hashMap.put(node.keyCode(), node);
	}

	@Override
	public ISearchNode min() {
		return Collections.min(hashMap.values(), comp);
	}
}