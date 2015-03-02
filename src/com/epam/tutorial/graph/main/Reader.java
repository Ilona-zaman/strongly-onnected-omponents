package com.epam.tutorial.graph.main;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.epam.tutorial.graph.entity.Graph;
import com.epam.tutorial.graph.entity.Link;
import com.epam.tutorial.graph.entity.Node;

public class Reader {

	BufferedReader input = null;

	public List<String> readFile(String fileName) {
		try {
			input = new BufferedReader(new FileReader(fileName));
		} catch (FileNotFoundException e) {
			System.err.println("File \"" + fileName + "\" not found");
			throw new RuntimeException(e);
		}
		List<String> lines = new ArrayList<String>();
		String line;
		try {
			while ((line = input.readLine()) != null) {
				lines.add(line);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		try {
			input.close();
		} catch (IOException e) {
			System.err.println("File \"" + fileName + "\" can't be close");
			throw new RuntimeException(e);
		}
		return lines;

	}

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
		
		for(Node node: nodes){
			List<Link> childs = new ArrayList<Link>();
			for(Link link: links){
				if(link.getSource().getNumber()==node.getNumber()){
					childs.add(link);
				}
			}
			node.setChilds(childs);
		}
		
		graph.setNodes(nodes);
		graph.setLinks(links);
				
		return graph;
	}
}