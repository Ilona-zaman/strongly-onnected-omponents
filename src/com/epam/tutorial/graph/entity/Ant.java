package com.epam.tutorial.graph.entity;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class Ant {

	protected int numberAnt;
	protected List<Link> path = new ArrayList<Link>();
	protected double weightPath = 0;
	protected List<Node> nodesPath = new ArrayList<Node>();
	protected Queue<Node> vizitedNode = new LinkedList<Node>();

	public Queue<Node> getVizitedNode() {
		return vizitedNode;
	}

	public void setVizitedNode(Queue<Node> vizitedNode) {
		this.vizitedNode = vizitedNode;
	}

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
