package com.epam.tutorial.graph.entity;

import java.util.ArrayList;
import java.util.List;

public class Ant {

	protected int numberAnt;
	protected List<Link> path = new ArrayList<Link>();
	protected double weightPath = 0;
	protected List<Node> nodesPath = new ArrayList<Node>();

	public int getNumberAnt() {
		return numberAnt;
	}

	public void setNumberAnt(int numberAnt) {
		this.numberAnt = numberAnt;
	}

	public List<Link> getPath() {
		return path;
	}

	public void setPath(List<Link> path) {
		this.path = path;
	}

	public double getWeightPath() {
		return weightPath;
	}

	public void setWeightPath(double weightPath) {
		this.weightPath = weightPath;
	}

	public List<Node> getNodesPath() {
		return nodesPath;
	}

	public void setNodesPath(List<Node> nodesPath) {
		this.nodesPath = nodesPath;
	}

	
	
	
}
