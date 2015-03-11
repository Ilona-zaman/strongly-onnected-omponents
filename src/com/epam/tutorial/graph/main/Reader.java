package com.epam.tutorial.graph.main;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.epam.tutorial.graph.entity.Graph;
import com.epam.tutorial.graph.entity.Link;
import com.epam.tutorial.graph.entity.Node;

public class Reader {

	BufferedReader input = null;
	int n = 0;

	public Graph readGraph(List<String> lines) {

		Graph graph = new Graph();
		List<Link> links = new ArrayList<Link>();
		List<Node> nodes = new ArrayList<Node>();

		for (String line : lines) {

			Link link = new Link();
			Node source = new Node();
			Node targe = new Node();

			String[] a = line.split("=");
			link.setWeight(Double.parseDouble(a[1]));

			String[] b = a[0].split(",");

			source.setNumber(Integer.parseInt(b[0]));
			link.setSource(source);
			boolean isThereSource = nodes.contains(source);
			if (!isThereSource) {
				nodes.add(source);
			}

			targe.setNumber(Integer.parseInt(b[1]));
			link.setTarget(targe);
			boolean isThereTarge = nodes.contains(targe);
			if (!isThereTarge) {
				nodes.add(targe);
			}

			links.add(link);
		}

		for (Node node : nodes) {
			List<Link> childs = new ArrayList<Link>();
			for (Link link : links) {
				if (link.getSource().getNumber() == node.getNumber()) {
					childs.add(link);
				}
			}
			for (Link link : links) {
				if (link.getSource().equals(node)) {
					link.getSource().setChilds(childs);
				}
				if (link.getTarget().equals(node)) {
					link.getTarget().setChilds(childs);
				}
			}
			node.setChilds(childs);

		}

		graph.setNodes(nodes);
		graph.setLinks(links);

		for (Link link : links) {
			link.setPheromone(0.5);
		}

		return graph;
	}

	File createFile(String testName, String formatFile, String algoritm)
			throws IOException {
		n++;
		File file = new File("D:/Projects/strongly-сonnected-сomponents/"
				+ algoritm + "/" + testName + "_" + String.valueOf(n)
				+ formatFile);
		if (!file.exists()) {
			file.createNewFile();
			return file;
		} else {
			return createFile(testName, formatFile, algoritm);
		}

	}
}