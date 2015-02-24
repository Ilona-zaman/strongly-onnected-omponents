package strongly—onnected—omponents.impl;

import java.util.List;

import stronglyConnectedComponents.entity.Graph;
import stronglyConnectedComponents.entity.Link;
import stronglyConnectedComponents.entity.Node;
import strongly—onnected—omponents.Components;

public class DFSComponents implements Components {

	@Override
	public boolean isStrongly—onnected() {
		return false;
	}

	public void dfs(Node node, Graph graph) {
		node.setVisited(true);
		List<Link> links = graph.getLinks();
		for (Link link : links) {
			if (link.getSource() == node) {
				if (!link.getTarge().isVisited()) {
					dfs(link.getTarge(), graph);
				}
			}
		}
	}
}