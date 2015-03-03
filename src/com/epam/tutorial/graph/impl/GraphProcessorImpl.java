package com.epam.tutorial.graph.impl;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import com.epam.tutorial.graph.GraphProcessor;
import com.epam.tutorial.graph.entity.Ant;
import com.epam.tutorial.graph.entity.Graph;
import com.epam.tutorial.graph.entity.Link;
import com.epam.tutorial.graph.entity.Node;

public class GraphProcessorImpl implements GraphProcessor {

	List<Node> visitedNodes = new ArrayList<Node>();
	final double A = 0.5;
	final double B = 0.5;
	final double Q = 0.8;

	@Override
	public boolean isStronglyConnected(Graph graph) {
		dfs(graph.getNodes().get(0), graph);
		List<Node> nodes = graph.getNodes();
		for (Node node : nodes) {
			if (!node.getIsVisited()) {
				setVisitedFalse(graph);
				return false;
			}
		}
		setVisitedFalse(graph);
		return true;
	}

	@Override
	public void dfs(Node startNode, Graph graph) {
		startNode.setVisited(true);
		visitedNodes.add(startNode);
		for (Link link : startNode.getChilds()) {
			if (!visitedNodes.contains(link.getTarget())) {
				dfs(link.getTarget(), graph);
			}
		}
	}

	@Override
	public List<Integer> findingConnectedComponents(Graph graph) {

		int ccNum = 0;
		List<Node> nodes = graph.getNodes();
		List<Integer> connectedComponents = firstInitArrayConnectedComponents(graph);

		for (Node node : nodes) {
			if (!node.getIsVisited()) {
				ccNum++;

				connectedComponents.set(node.getNumber() - 1, ccNum);
				bfs(node, graph);

			} else {
				connectedComponents.set(node.getNumber() - 1, ccNum);
			}
		}
		System.out.println(ccNum);
		setVisitedFalse(graph);
		return connectedComponents;

	}

	@Override
	public void bfs(Node startNode, Graph graph) {
		Queue<Node> queue = new LinkedList<Node>();
		queue.add(startNode);
		while (!queue.isEmpty()) {
			Node node = queue.element();
			queue.remove(node);
			visitedNodes.add(node);
			node.setVisited(true);
			for (Link link : node.getChilds()) {
				if (!visitedNodes.contains(link.getTarget())) {
					queue.add(link.getTarget());
				}
			}
		}
	}

	@Override
	public void antColonyOpimization(Graph graph, int numberGeneration) {
		List<Node> bestPath = new ArrayList<Node>();
		for (int i = 0; i < numberGeneration; i++) {
			bestPath = run(graph);
			for (Node node : bestPath) {
				System.out.print(node.getNumber() + " ");
			}
			System.out.println();
			updatePheromon(graph);
		}

	}

	List<Node> run(Graph graph) {
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
				Link selectedlLink = selectLink(
						ant.getNodesPath().get(ant.getNodesPath().size() - 1),
						ant);
				ant.getPath().add(selectedlLink);
				ant.setWeightPath(ant.getWeightPath()
						+ selectedlLink.getWeight());
				ant.getNodesPath().add(selectedlLink.getTarget());

				selectedlLink.setPheromone(selectedlLink.getPheromone() + 1
						/ ant.getWeightPath());

			}
			step++;
		}
		List<Node> bestPath = new ArrayList<Node>();
		double lenghtBestPath = 100;

		for (Ant ant : ants) {
			if (lenghtBestPath > ant.getWeightPath()) {
				lenghtBestPath = ant.getWeightPath();
				bestPath.addAll(ant.getNodesPath());
			}
		}
		return bestPath;
	}

	void updatePheromon(Graph graph) {
		List<Link> links = graph.getLinks();
		for (Link link : links) {
			link.setPheromone(link.getPheromone()*(1-Q));
		}
	}

	Link selectLink(Node node, Ant ant) {

		double probability = 0;
		Link selectedLink = node.getChilds().get(0);
		for (Link link : node.getChilds()) {
			if (probability(link, ant) > probability
					&& !ant.getNodesPath().contains(link.getTarget())) {
				probability = probability(link, ant);
				selectedLink = link;
			}
		}

		return selectedLink;

	}

	double probability(Link startLink, Ant ant) {

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

	List<Integer> firstInitArrayConnectedComponents(Graph graph) {
		List<Integer> connectedComponents = new ArrayList<Integer>(graph
				.getNodes().size());
		for (int i = 0; i < graph.getNodes().size(); i++) {
			connectedComponents.add(0);
		}
		return connectedComponents;
	}

	void setVisitedFalse(Graph graph) {
		List<Node> nodes = graph.getNodes();
		for (Node node : nodes) {
			node.setVisited(false);
		}
		visitedNodes.clear();
	}

}