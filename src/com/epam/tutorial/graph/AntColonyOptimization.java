package com.epam.tutorial.graph;

import java.util.List;

import com.epam.tutorial.graph.entity.Ant;
import com.epam.tutorial.graph.entity.Graph;
import com.epam.tutorial.graph.entity.Link;
import com.epam.tutorial.graph.entity.Node;

public interface AntColonyOptimization {

	Ant run(Graph graph);

	void evaporationPheromon(Graph graph);

	Link selectLink(Node node, Ant ant, Graph graph, int numberStep);

	double probability(Link startLink, Ant ant);

	void updatePheromon(List<Ant> ants);

	Ant getBestAnt(List<Ant> ants);

}
