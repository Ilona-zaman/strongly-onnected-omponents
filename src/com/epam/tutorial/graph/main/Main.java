package com.epam.tutorial.graph.main;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

import com.epam.tutorial.graph.entity.Graph;
import com.epam.tutorial.graph.entity.Node;
import com.epam.tutorial.graph.impl.GraphProcessorImpl;

public class Main {

	public static void main(String[] args) {

		Reader read = new Reader();
		Graph graph;
		try {
			graph = read.readGraph(Files.readAllLines(Paths.get("input.txt")));
			GraphProcessorImpl components = new GraphProcessorImpl();
			System.out.println(components.isStronglyConnected(graph));
			List<Integer> connectedComponents = components
					.findingConnectedComponents(graph);
			for (Node node : graph.getNodes()) {
				System.out.println(node.getNumber() + " -> "
						+ connectedComponents.get(node.getNumber() - 1));
			}
			components.antColonyOpimization(graph, 10);
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}
