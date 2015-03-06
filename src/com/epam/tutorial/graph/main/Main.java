package com.epam.tutorial.graph.main;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Scanner;

import com.epam.tutorial.graph.entity.Graph;
import com.epam.tutorial.graph.entity.Node;
import com.epam.tutorial.graph.impl.GraphProcessorImpl;

public class Main {

	public static void main(String[] args) {

		Reader read = new Reader();
		Graph graph;
		try {
			graph = read.readGraph(Files.readAllLines(Paths.get("test_1.txt")));
			try {
				GraphProcessorImpl components = new GraphProcessorImpl();
				System.out.println(components.isStronglyConnected(graph));
				List<Integer> connectedComponents = components
						.findingConnectedComponents(graph);
				for (Node node : graph.getNodes()) {
					System.out.println(node.getNumber() + " -> "
							+ connectedComponents.get(node.getNumber() - 1));
				}
				System.out
						.println("Please, choose algoritm which you want to use: 1 - Ant Colony Optimization, 2- Ant Colony Optimization with Modification 1, 3- Ant Colony Optimization with Modification 2");
				@SuppressWarnings("resource")
				Scanner scaner = new Scanner(System.in);
				String selectedAlgoritm = scaner.nextLine();
				if (selectedAlgoritm.contains("1")) {
					System.out.println("Ant Colony Optimization");
					components.antColonyOpimization(graph, 10);
				} else if (selectedAlgoritm.contains("2")) {
					System.out
							.println("Ant Colony Optimization With Modification 1");
					components.antColonyOptimizationModification1(graph, 10);
				} else if (selectedAlgoritm.contains("3")) {
					System.out
							.println("Ant Colony Optimization With Modification 2");
					components.antColonyOptimizationModification2(graph, 10);
				} else {
					System.out.println("Algoritm don't select");
				}
			} catch (Exception e) {
				System.out.println("Graph don't find");
			}

		} catch (IOException e) {
			e.printStackTrace();
		}

	}
}
