package com.epam.tutorial.graph;

import com.epam.tutorial.graph.entity.Graph;
import com.epam.tutorial.graph.entity.Node;

public interface GraphProcessor {

	boolean isStrongly—onnected(Graph graph);
	public void dfs(Node node, Graph graph);

}
