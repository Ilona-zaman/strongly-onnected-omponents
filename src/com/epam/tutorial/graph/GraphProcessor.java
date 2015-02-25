package com.epam.tutorial.graph;

import java.util.List;

import com.epam.tutorial.graph.entity.Graph;
import com.epam.tutorial.graph.entity.Node;

public interface GraphProcessor {

	boolean isStronglyConnected(Graph graph);
	
	public void dfs(Node node, Graph graph);
	
	public List<Integer> findingConnectedComponents(Graph graph);

}
