package com.epam.tutorial.graph.impl;

import java.util.ArrayList;
import java.util.List;

import com.epam.tutorial.graph.GraphProcessor;
import com.epam.tutorial.graph.entity.Graph;
import com.epam.tutorial.graph.entity.Link;
import com.epam.tutorial.graph.entity.Node;

public class GraphProcessorImpl implements GraphProcessor {

	@Override
	public boolean isStronglyConnected(Graph graph) {
		dfs(graph.getNodes().get(0), graph);
		List<Node> nodes = graph.getNodes();
		for (Node node : nodes) {
			if (!node.getIsVisited()) {
				return false;
			}
		}
		return true;
	}

	List<Node> nodes = new ArrayList<Node>();

	@Override
	public void dfs(Node startNode, Graph graph) {
		startNode.setVisited(true);
		nodes.add(startNode);
		for (Link link : startNode.getChilds()) {
			if (!nodes.contains(link.getTarget())) {
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
				dfs(node, graph);

			} else {
				connectedComponents.set(node.getNumber() - 1, ccNum);
			}
		}
		System.out.println(ccNum);
		return connectedComponents;

	}

	public List<Integer> firstInitArrayConnectedComponents(Graph graph) {
		List<Integer> connectedComponents = new ArrayList<Integer>(graph
				.getNodes().size());
		for (int i = 0; i < graph.getNodes().size(); i++) {
			connectedComponents.add(0);
		}
		return connectedComponents;
	}

}