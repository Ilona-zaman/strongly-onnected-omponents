package com.epam.tutorial.graph.main;

import com.epam.tutorial.graph.entity.Graph;
import com.epam.tutorial.graph.impl.GraphProcessorImpl;

public class Main {

	public static void main(String[] args) {

		Reader read = new Reader();
		Graph graph = read.readGraph(read.readFile("input.txt"));
		GraphProcessorImpl components = new GraphProcessorImpl();
		System.out.println(components.isStrongly—onnected(graph));
	}

}
