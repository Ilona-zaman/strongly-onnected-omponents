package com.epam.tutorial.graph.impl;

import com.epam.tutorial.graph.entity.Ant;
import com.epam.tutorial.graph.entity.Graph;
import com.epam.tutorial.graph.entity.Link;
import com.epam.tutorial.graph.entity.Node;

public class AntColonyOptimizationGreedyImpl extends
		AntColonyOptimizationClassicImpl {

	@Override
	public Link selectLink(Node node, Ant ant, Graph graph, int numberStep) {
		double probability = 0;
		Link selectedLink = null;
		for (Link link : node.getChilds()) {

			if (!ant.getNodesPath().contains(link.getTarget())
					&& probability(link, ant) >= probability) {
				probability = probability(link, ant);
				selectedLink = link;
			}
		}

		if (selectedLink == null) {
			ant.getNodesPath().remove(0);
			ant.setWeightPath(ant.getWeightPath()
					- ant.getPath().get(0).getWeight());
			ant.getPath().remove(0);
			selectedLink = selectLink(node, ant, graph, numberStep);
			return selectedLink;
		} else {
			return selectedLink;
		}

	}
}
