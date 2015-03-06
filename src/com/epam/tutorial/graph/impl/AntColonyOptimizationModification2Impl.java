package com.epam.tutorial.graph.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.epam.tutorial.graph.entity.Ant;
import com.epam.tutorial.graph.entity.Graph;
import com.epam.tutorial.graph.entity.Link;
import com.epam.tutorial.graph.entity.Node;

public class AntColonyOptimizationModification2Impl extends
		AntColonyOptimizationImpl {

	@Override
	public Ant run(Graph graph) {
		List<Node> nodes = graph.getNodes();
		List<Ant> ants = new ArrayList<Ant>();
		for (Node node : nodes) {
			Ant ant = new Ant();
			ant.setNumberAnt(node.getNumber());
			ant.getNodesPath().add(node);
			ant.getVizitedNode().add(node);
			ants.add(ant);
		}
		int step = 1;
		while (step != graph.getNodes().size() + 1) {
			for (Ant ant : ants) {
				while (ant.getNodesPath().size() != step) {
					Link selectedlLink = selectLink(
							ant.getNodesPath().get(
									ant.getNodesPath().size() - 1), ant, graph,
							step);
					ant.getPath().add(selectedlLink);
					ant.setWeightPath(ant.getWeightPath()
							+ selectedlLink.getWeight());
					ant.getNodesPath().add(selectedlLink.getTarget());
					ant.getVizitedNode().add(selectedlLink.getSource());
					if (ant.getVizitedNode().size() == 2) {
						ant.getVizitedNode().remove();
					}
					selectedlLink.setPheromone(selectedlLink.getPheromone() + 1
							/ ant.getWeightPath());
				}
			}
			step++;
		}
		Ant bestAnt = new Ant();
		double lenghtBestPath = 100;

		for (Ant ant : ants) {
			if (lenghtBestPath > ant.getWeightPath()) {
				lenghtBestPath = ant.getWeightPath();
				bestAnt = ant;
			}
		}
		return bestAnt;
	}

	@Override
	public Link selectLink(Node node, Ant ant, Graph graph, int numberStep) {
		double probability = 0.3;
		Link selectedLink = null;
		List<Link> selectedLinks = new ArrayList<Link>();
		for (Link link : node.getChilds()) {
			if (!ant.getVizitedNode().contains(link.getTarget())
					&& probability(link, ant) > probability) {
				selectedLinks.add(link);
			}
		}
		if (selectedLinks.size() == 0) {
			System.out.println("Error");
			return null;
		} else {
			Random random = new Random();
			selectedLink = selectedLinks.get(random.nextInt(selectedLinks
					.size()));
			return selectedLink;
		}

	}

}
