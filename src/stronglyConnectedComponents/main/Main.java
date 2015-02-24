package stronglyConnectedComponents.main;

import stronglyConnectedComponents.entity.Graph;
import strongly—onnected—omponents.impl.DFSComponents;

public class Main {

	public static void main(String[] args) {

		Reader r = new Reader();
		Graph graph = r.readGraph("input.txt");
		DFSComponents components = new DFSComponents();
		components.dfs(graph.getNodes().get(0), graph);
	}

}
