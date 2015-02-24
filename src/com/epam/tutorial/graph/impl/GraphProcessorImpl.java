package com.epam.tutorial.graph.impl;

import java.util.List;

import com.epam.tutorial.graph.GraphProcessor;
import com.epam.tutorial.graph.entity.Graph;
import com.epam.tutorial.graph.entity.Link;
import com.epam.tutorial.graph.entity.Node;

public class GraphProcessorImpl implements GraphProcessor {

	@Override
	public boolean isStrongly—onnected(Graph graph) {
		dfs(graph.getNodes().get(0), graph);
		List<Node> nodes = graph.getNodes();
		for (Node node: nodes){
			if(!node.getIsVisited()){
				return false;
			}
		}
		return true;
	}
	
	@Override
	public void dfs(Node node, Graph graph) {
		node.setVisited(true);
		List<Link> links = graph.getLinks();
		for(Link link: links){
			if(link.getSource().getNumber() == node.getNumber()){
				link.getSource().setVisited(true);
			}else if (link.getTarget().getNumber() == node.getNumber()){
				link.getTarget().setVisited(true);
			}
		}
		for (Link link : links) {
			if (link.getSource().getNumber() == node.getNumber()) {
				if (!link.getTarget().getIsVisited()) {
					dfs(link.getTarget(), graph);
				}
			}
		}
	}
}