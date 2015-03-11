package com.epam.tutorial.graph.main;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Scanner;

import com.epam.tutorial.graph.entity.Graph;
import com.epam.tutorial.graph.impl.GraphProcessorImpl;

public class Main {

	@SuppressWarnings("resource")
	public static void main(String[] args) {

		Reader read = new Reader();
		Graph graph;
		int numberIteration = 20;
		String testName = "test_3";
		String formatFile = ".txt";

		try {
			graph = read.readGraph(Files.readAllLines(Paths.get(testName
					+ formatFile)));
			try {
				GraphProcessorImpl components = new GraphProcessorImpl();
				System.out
						.println("Please, choose algoritm which you want to use: \n1 - Ant Colony Optimization (Classic), \n2 - Ant Colony Optimization (Threshold), \n3 - Ant Colony Optimization (Random), \n4 - Ant Colony Optimization (Greedy), \n5 - Ant Colony Optimization with Modification.");
				Scanner scaner = new Scanner(System.in);
				String selectedAlgoritm = scaner.nextLine();
				if (selectedAlgoritm.contains("1")) {
					String algoritm = "antColonyOptimizationClassic";
					File file = read.createFile(testName, formatFile, algoritm);
					System.out.println("Ant Colony Optimization (Classic)");
					components.antColonyOpimizationClassic(graph,
							numberIteration, file);
				} else if (selectedAlgoritm.contains("2")) {
					String algoritm = "antColonyOptimizationThreshold";
					File file = read.createFile(testName, formatFile, algoritm);
					System.out.println("Ant Colony Optimization (Threshold)");
					components.antColonyOptimizationThreshold(graph,
							numberIteration, file);
				} else if (selectedAlgoritm.contains("3")) {
					String algoritm = "antColonyOptimizationRandom";
					File file = read.createFile(testName, formatFile, algoritm);
					System.out.println("Ant Colony Optimization (Random)");
					components.antColonyOptimizationRandom(graph,
							numberIteration, file);
				} else if (selectedAlgoritm.contains("4")) {
					String algoritm = "antColonyOptimizationGreedy";
					File file = read.createFile(testName, formatFile, algoritm);
					System.out.println("Ant Colony Optimization (Greedy)");
					components.antColonyOptimizationGreedy(graph,
							numberIteration, file);
				} else if (selectedAlgoritm.contains("5")) {
					System.out
							.println("Ant Colony Optimization With Modification");
					long startTime = System.nanoTime();
					components.antColonyOptimizationModification(graph,
							numberIteration);
					long timeSpent = System.nanoTime() - startTime;
					System.out.println("program worked " + timeSpent + " ns");
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
