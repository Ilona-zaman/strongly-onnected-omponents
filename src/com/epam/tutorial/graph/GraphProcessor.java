package com.epam.tutorial.graph;

import java.util.List;

import com.epam.tutorial.graph.entity.Graph;
import com.epam.tutorial.graph.entity.Node;

public interface GraphProcessor {

	boolean isStronglyConnected(Graph graph);
	
	public void dfs(Node startNode, Graph graph);
	
	public List<Integer> findingConnectedComponents(Graph graph);
	
	public void bfs(Node startNode, Graph graph);
	
	public void antColonyOpimizationClassic(Graph graph, int numberGeneration);
	
	public void antColonyOptimizationThreshold(Graph graph, int numberGeneration);

	public void antColonyOptimizationModification(Graph graph, int numberGeneration);

	public void antColonyOptimizationRandom(Graph graph, int numberGeneration);

}
