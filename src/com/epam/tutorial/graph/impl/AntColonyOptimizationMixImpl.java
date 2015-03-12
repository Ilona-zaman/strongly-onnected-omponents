package com.epam.tutorial.graph.impl;

import java.util.ArrayList;
import java.util.List;

import com.epam.tutorial.graph.entity.Ant;
import com.epam.tutorial.graph.entity.Graph;
import com.epam.tutorial.graph.entity.Link;
import com.epam.tutorial.graph.entity.Node;

public class AntColonyOptimizationMixImpl extends
		AntColonyOptimizationClassicImpl {

	@Override
	public Ant run(Graph graph) {
		List<Node> nodes = graph.getNodes();
		List<Ant> ants = new ArrayList<Ant>();
		for (Node node : nodes) {
			Ant ant = new Ant();
			ant.setNumberAnt(node.getNumber());
			ant.getNodesPath().add(node);
			ants.add(ant);
		}
		int step = 1;
		while (step != graph.getNodes().size() + 1) {
			for (Ant ant : ants) {
				while (ant.getNodesPath().size() != step) {
					if ((double)(ants.indexOf(ant)+1)/(double)ants.size() * 100 <= 30) {
						AntColonyOptimizationClassicImpl a = new AntColonyOptimizationClassicImpl();
						Link selectedlLink = a.selectLink(ant.getNodesPath()
								.get(ant.getNodesPath().size() - 1), ant,
								graph, step);
						ant.getPath().add(selectedlLink);
						ant.setWeightPath(ant.getWeightPath()
								+ selectedlLink.getWeight());
						ant.getNodesPath().add(selectedlLink.getTarget());
					} else if ((double)(ants.indexOf(ant)+1)/(double)ants.size() * 100 <= 60
							&& (double)(ants.indexOf(ant)+1)/(double)ants.size() * 100 > 30) {
						AntColonyOptimizationClassicImpl a = new AntColonyOptimizationThresholdImpl();
						Link selectedlLink = a.selectLink(ant.getNodesPath()
								.get(ant.getNodesPath().size() - 1), ant,
								graph, step);
						ant.getPath().add(selectedlLink);
						ant.setWeightPath(ant.getWeightPath()
								+ selectedlLink.getWeight());
						ant.getNodesPath().add(selectedlLink.getTarget());
					} else if ((double)(ants.indexOf(ant)+1)/(double)ants.size() * 100 <= 80
							&& (double)(ants.indexOf(ant)+1)/(double)ants.size() * 100 * 100 > 60) {
						AntColonyOptimizationClassicImpl a = new AntColonyOptimizationGreedyImpl();
						Link selectedlLink = a.selectLink(ant.getNodesPath()
								.get(ant.getNodesPath().size() - 1), ant,
								graph, step);
						ant.getPath().add(selectedlLink);
						ant.setWeightPath(ant.getWeightPath()
								+ selectedlLink.getWeight());
						ant.getNodesPath().add(selectedlLink.getTarget());
					} else if ((double)(ants.indexOf(ant)+1)/(double)ants.size() * 100 <= 100
							&& (double)(ants.indexOf(ant)+1)/(double)ants.size() * 100 * 100 > 80) {
						AntColonyOptimizationClassicImpl a = new AntColonyOptimizationRandomImpl();
						Link selectedlLink = a.selectLink(ant.getNodesPath()
								.get(ant.getNodesPath().size() - 1), ant,
								graph, step);
						ant.getPath().add(selectedlLink);
						ant.setWeightPath(ant.getWeightPath()
								+ selectedlLink.getWeight());
						ant.getNodesPath().add(selectedlLink.getTarget());
					}
				}
			}
			step++;
		}

		updatePheromon(ants);
		return getBestAnt(ants);

	}

}
