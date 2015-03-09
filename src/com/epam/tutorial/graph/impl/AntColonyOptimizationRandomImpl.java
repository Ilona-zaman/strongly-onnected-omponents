package com.epam.tutorial.graph.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.epam.tutorial.graph.entity.Ant;
import com.epam.tutorial.graph.entity.Graph;
import com.epam.tutorial.graph.entity.Link;
import com.epam.tutorial.graph.entity.Node;

public class AntColonyOptimizationRandomImpl extends AntColonyOptimizationClassicImpl{
	
	@Override
	public Link selectLink(Node node, Ant ant, Graph graph, int numberStep) {
		List<Link> selectedLinks = new ArrayList<Link>();
		Link selectedLink = null;
		for (Link link : node.getChilds()) {
			if (!ant.getNodesPath().contains(link.getTarget())) {
				selectedLinks.add(link);
			}
		}
		if (selectedLinks.size() == 0) {
			ant.getNodesPath().remove(0);
			ant.setWeightPath(ant.getWeightPath()
					- ant.getPath().get(0).getWeight());
			ant.getPath().remove(0);
			selectedLink = selectLink(node, ant, graph, numberStep);
			return selectedLink;
		} else {
			Random random = new Random();
			selectedLink = selectedLinks.get(random.nextInt(selectedLinks
					.size()));
			return selectedLink;
		}

	}

}
