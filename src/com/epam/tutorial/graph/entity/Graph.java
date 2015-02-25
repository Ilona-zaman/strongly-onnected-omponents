package com.epam.tutorial.graph.entity;

import java.util.List;

public class Graph {

	protected  List<Node> nodes;
	protected  List<Link> links;

	public List<Node> getNodes() {
		return nodes;
	}

	public void setNodes(List<Node> nodes) {
		this.nodes = nodes;
	}

	public List<Link> getLinks() {
		return links;
	}

	public void setLinks(List<Link> links) {
		this.links = links;
	}

}
