package com.epam.tutorial.graph;

import com.epam.tutorial.graph.entity.Ant;
import com.epam.tutorial.graph.entity.Graph;
import com.epam.tutorial.graph.entity.Link;
import com.epam.tutorial.graph.entity.Node;

public interface AntColonyOptimization {

	Ant run(Graph graph);

	void updatePheromon(Graph graph);

	Link selectLink(Node node, Ant ant, Graph graph, int numberStep);

	double probability(Link startLink, Ant ant);

}
