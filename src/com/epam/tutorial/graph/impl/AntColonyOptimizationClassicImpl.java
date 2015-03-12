package com.epam.tutorial.graph.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.epam.tutorial.graph.AntColonyOptimization;
import com.epam.tutorial.graph.entity.Ant;
import com.epam.tutorial.graph.entity.Graph;
import com.epam.tutorial.graph.entity.Link;
import com.epam.tutorial.graph.entity.Node;

public class AntColonyOptimizationClassicImpl implements AntColonyOptimization {

	final static double A = 0.5;
	final static double B = 0.5;
	final static double Q = 0.2;

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
				}
			}
			step++;
		}

		updatePheromon(ants);
		return getBestAnt(ants);

	}

	@Override
	public Ant getBestAnt(List<Ant> ants) {
		Ant bestAnt = new Ant();
		double lenghtBestPath = Double.MAX_VALUE;

		for (Ant ant : ants) {
			if (lenghtBestPath > ant.getWeightPath()) {
				lenghtBestPath = ant.getWeightPath();
				bestAnt = ant;
			}
		}
		return bestAnt;

	}

	@Override
	public void updatePheromon(List<Ant> ants) {
		for (Ant ant : ants) {
			List<Link> path = ant.getPath();
			for (Link link : path) {
				link.setPheromone(link.getPheromone() + 1 / ant.getWeightPath());
			}
		}
	}

	@Override
	public void evaporationPheromon(Graph graph) {
		List<Link> links = graph.getLinks();
		for (Link link : links) {
			link.setPheromone(link.getPheromone() * (1 - Q));
		}
	}

	@Override
	public Link selectLink(Node node, Ant ant, Graph graph, int numberStep) {
		double probability = 0;
		Link selectedLink = null;
		List<Double> probabilitys = new ArrayList<Double>();
		List<Link> selectedLinks = new ArrayList<Link>();
		for (Link link : node.getChilds()) {
			if (!ant.getNodesPath().contains(link.getTarget())) {
				probability = (probability + probability(link, ant) * 100);
				probabilitys.add(probability);
				selectedLinks.add(link);
			}
		}
		Random random = new Random();
		int n = random.nextInt(100);
		for (Double pr : probabilitys)
			if (n > pr) {
				selectedLink = selectedLinks.get(probabilitys.indexOf(pr) + 1);
			} else {
				selectedLink = selectedLinks.get(probabilitys.indexOf(pr));
				break;
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
