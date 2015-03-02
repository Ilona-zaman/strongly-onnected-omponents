package com.epam.tutorial.graph.main;

import java.util.List;

import com.epam.tutorial.graph.entity.Graph;
import com.epam.tutorial.graph.entity.Node;
import com.epam.tutorial.graph.impl.GraphProcessorImpl;

public class Main {

	public static void main(String[] args) {

		Reader read = new Reader();
		Graph graph = read.readGraph(read.readFile("input.txt"));
		GraphProcessorImpl components = new GraphProcessorImpl();
		System.out.println(components.isStronglyConnected(graph));
		List<Integer> connectedComponents = components
				.findingConnectedComponents(graph);
		for (Node node : graph.getNodes()) {
			System.out.println(node.getNumber() + " -> "
					+ connectedComponents.get(node.getNumber() - 1));
		}
	}

}
