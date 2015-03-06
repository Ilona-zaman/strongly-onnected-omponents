package com.epam.tutorial.graph.impl;

import java.util.ArrayList;
import java.util.List;

import com.epam.tutorial.graph.AntColonyOptimization;
import com.epam.tutorial.graph.entity.Ant;
import com.epam.tutorial.graph.entity.Graph;
import com.epam.tutorial.graph.entity.Link;
import com.epam.tutorial.graph.entity.Node;

public class AntColonyOptimizationImpl implements AntColonyOptimization {

	final double A = 0.5;
	final double B = 0.5;
	final double Q = 0.8;

	@Override
	public Ant run(Graph graph) {
		List<Node> nodes = graph.getNodes();
		List<Ant> ants = new ArrayList<Ant>();
		for (Node node : nodes) {
			Ant ant = new Ant();
			ant.setNumberAnt(node.getNumber());
			ant.getNodesPath().add(node);
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
	public void updatePheromon(Graph graph) {
		List<Link> links = graph.getLinks();
		for (Link link : links) {
			link.setPheromone(link.getPheromone() * (1 - Q));
		}
	}

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

	@Override
	public double probability(Link startLink, Ant ant) {
		double probability = 0;
		double summ = 0;

		for (Link link : startLink.getSource().getChilds()) {
			if (!ant.getNodesPath().contains(link.getTarget())) {
				summ = summ + Math.pow(link.getPheromone(), A)
						* Math.pow(1 / link.getWeight(), B);
			}
		}

		probability = Math.pow(startLink.getPheromone(), A)
				* Math.pow(1 / startLink.getWeight(), B) / summ;

		return probability;
	}

}
