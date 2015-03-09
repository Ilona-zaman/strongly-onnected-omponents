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
		int nuberiteration = 20;
		try {
			graph = read.readGraph(Files.readAllLines(Paths.get("test_3.txt")));
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
						.println("Please, choose algoritm which you want to use: \n1 - Ant Colony Optimization (Classic), \n2 - Ant Colony Optimization (Threshold), \n3 - Ant Colony Optimization with Modification, \n4 - Ant Colony Optimization (Random).");
				@SuppressWarnings("resource")
				Scanner scaner = new Scanner(System.in);
				String selectedAlgoritm = scaner.nextLine();
				if (selectedAlgoritm.contains("1")) {
					System.out.println("Ant Colony Optimization (Classic)");
					components.antColonyOpimizationClassic(graph,
							nuberiteration);
				} else if (selectedAlgoritm.contains("2")) {
					System.out.println("Ant Colony Optimization (Threshold)");
					components.antColonyOptimizationThreshold(graph,
							nuberiteration);
				} else if (selectedAlgoritm.contains("3")) {
					System.out
							.println("Ant Colony Optimization With Modification");
					components.antColonyOptimizationModification(graph,
							nuberiteration);
				} else if (selectedAlgoritm.contains("4")) {
					System.out.println("Ant Colony Optimization (Random)");
					components.antColonyOptimizationRandom(graph,
							nuberiteration);
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
