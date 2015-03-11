package com.epam.tutorial.graph.impl;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import com.epam.tutorial.graph.GraphProcessor;
import com.epam.tutorial.graph.entity.Ant;
import com.epam.tutorial.graph.entity.Graph;
import com.epam.tutorial.graph.entity.Link;
import com.epam.tutorial.graph.entity.Node;

public class GraphProcessorImpl implements GraphProcessor {

	List<Node> visitedNodes = new ArrayList<Node>();
	final double A = 0.5;
	final double B = 0.5;
	final double Q = 0.8;

	@Override
	public boolean isStronglyConnected(Graph graph) {
		dfs(graph.getNodes().get(0), graph);
		if (visitedNodes.size() == graph.getNodes().size()) {
			setVisitedFalse(graph);
			return true;
		}
		setVisitedFalse(graph);
		return false;
	}

	@Override
	public void dfs(Node startNode, Graph graph) {
		visitedNodes.add(startNode);
		for (Link link : startNode.getChilds()) {
			if (!visitedNodes.contains(link.getTarget())) {
				dfs(link.getTarget(), graph);
			}
		}
	}

	@Override
	public List<Integer> findingConnectedComponents(Graph graph) {

		int ccNum = 0;
		List<Node> nodes = graph.getNodes();
		List<Integer> connectedComponents = firstInitArrayConnectedComponents(graph);

		for (Node node : nodes) {
			if (!node.getIsVisited()) {
				ccNum++;

				connectedComponents.set(node.getNumber() - 1, ccNum);
				bfs(node, graph);

			} else {
				connectedComponents.set(node.getNumber() - 1, ccNum);
			}
		}
		System.out.println(ccNum);
		setVisitedFalse(graph);
		return connectedComponents;

	}

	@Override
	public void bfs(Node startNode, Graph graph) {
		Queue<Node> queue = new LinkedList<Node>();
		queue.add(startNode);
		while (!queue.isEmpty()) {
			Node node = queue.element();
			queue.remove(node);
			visitedNodes.add(node);
			node.setVisited(true);
			for (Link link : node.getChilds()) {
				if (!visitedNodes.contains(link.getTarget())
						&& !queue.contains(link.getTarget())) {
					queue.add(link.getTarget());
				}
			}
		}
	}

	@Override
	public void antColonyOpimizationClassic(Graph graph, int numberGeneration,
			File file) {
		long startTime = System.nanoTime();
		AntColonyOptimizationClassicImpl antColonyOptimization = new AntColonyOptimizationClassicImpl();
		List<Node> bestPath = new ArrayList<Node>();
		FileWriter f = null;
		try {
			f = new FileWriter(file);
			f.write("Ant Colony Optimization (Classic)" + "\n" + "A = " + A
					+ " B = " + B + " Q = " + Q + "\n");
			for (int i = 0; i < numberGeneration; i++) {
				Ant bestAnt = antColonyOptimization.run(graph);
				bestPath = bestAnt.getNodesPath();

				for (Node node : bestPath) {
					f.write(node.getNumber() + " ");
				}
				f.write("-> " + bestAnt.getWeightPath() + "\n");

				for (Node node : bestPath) {
					System.out.print(node.getNumber() + " ");
				}
				System.out.print("-> " + bestAnt.getWeightPath());
				System.out.println();
				antColonyOptimization.evaporationPheromon(graph);

			}
			long timeSpent = System.nanoTime() - startTime;
			System.out.println("program worked " + timeSpent + " ns");
			f.write("program worked " + timeSpent + " ns");
			f.flush();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (f != null) {
				try {
					f.close();
				} catch (IOException ex) {
				}
			}
		}

	}

	@Override
	public void antColonyOptimizationThreshold(Graph graph,
			int numberGeneration, File file) {
		long startTime = System.nanoTime();
		AntColonyOptimizationClassicImpl antColonyOptimization = new AntColonyOptimizationThresholdImpl();
		List<Node> bestPath = new ArrayList<Node>();
		FileWriter f = null;
		try {
			f = new FileWriter(file);
			f.write("Ant Colony Optimization (Classic)" + "\n" + "A = " + A
					+ " B = " + B + " Q = " + Q + "\n");
			for (int i = 0; i < numberGeneration; i++) {
				Ant bestAnt = antColonyOptimization.run(graph);
				bestPath = bestAnt.getNodesPath();
				for (Node node : bestPath) {
					f.write(node.getNumber() + " ");
				}
				f.write("-> " + bestAnt.getWeightPath() + "\n");
				for (Node node : bestPath) {
					System.out.print(node.getNumber() + " ");
				}
				System.out.print("-> " + bestAnt.getWeightPath());
				System.out.println();
				antColonyOptimization.evaporationPheromon(graph);
			}
			long timeSpent = System.nanoTime() - startTime;
			System.out.println("program worked " + timeSpent + " ns");
			f.write("program worked " + timeSpent + " ns");
			f.flush();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (f != null) {
				try {
					f.close();
				} catch (IOException ex) {
				}
			}
		}

	}

	@Override
	public void antColonyOptimizationRandom(Graph graph, int numberGeneration,
			File file) {
		long startTime = System.nanoTime();
		AntColonyOptimizationClassicImpl antColonyOptimization = new AntColonyOptimizationRandomImpl();
		List<Node> bestPath = new ArrayList<Node>();
		FileWriter f = null;
		try {
			f = new FileWriter(file);
			f.write("Ant Colony Optimization (Classic)" + "\n" + "A = " + A
					+ " B = " + B + " Q = " + Q + "\n");
			for (int i = 0; i < numberGeneration; i++) {
				Ant bestAnt = antColonyOptimization.run(graph);
				bestPath = bestAnt.getNodesPath();
				for (Node node : bestPath) {
					f.write(node.getNumber() + " ");
				}
				f.write("-> " + bestAnt.getWeightPath() + "\n");
				for (Node node : bestPath) {
					System.out.print(node.getNumber() + " ");
				}
				System.out.print("-> " + bestAnt.getWeightPath());
				System.out.println();
				antColonyOptimization.evaporationPheromon(graph);
			}
			long timeSpent = System.nanoTime() - startTime;
			System.out.println("program worked " + timeSpent + " ns");
			f.write("program worked " + timeSpent + " ns");
			f.flush();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (f != null) {
				try {
					f.close();
				} catch (IOException ex) {
				}
			}
		}

	}

	@Override
	public void antColonyOptimizationGreedy(Graph graph, int numberGeneration,
			File file) {
		long startTime = System.nanoTime();
		AntColonyOptimizationClassicImpl antColonyOptimization = new AntColonyOptimizationGreedyImpl();
		List<Node> bestPath = new ArrayList<Node>();
		FileWriter f = null;
		try {
			f = new FileWriter(file);
			f.write("Ant Colony Optimization (Classic)" + "\n" + "A = " + A
					+ " B = " + B + " Q = " + Q + "\n");
			for (int i = 0; i < numberGeneration; i++) {
				Ant bestAnt = antColonyOptimization.run(graph);
				bestPath = bestAnt.getNodesPath();
				for (Node node : bestPath) {
					f.write(node.getNumber() + " ");
				}
				f.write("-> " + bestAnt.getWeightPath() + "\n");
				for (Node node : bestPath) {
					System.out.print(node.getNumber() + " ");
				}
				System.out.print("-> " + bestAnt.getWeightPath());
				System.out.println();
				antColonyOptimization.evaporationPheromon(graph);
			}
			long timeSpent = System.nanoTime() - startTime;
			System.out.println("program worked " + timeSpent + " ns");
			f.write("program worked " + timeSpent + " ns");
			f.flush();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (f != null) {
				try {
					f.close();
				} catch (IOException ex) {
				}
			}
		}
	}

	@Override
	public void antColonyOptimizationModification(Graph graph,
			int numberGeneration) {
		AntColonyOptimizationClassicImpl antColonyOptimization = new AntColonyOptimizationModificationImpl();
		List<Node> bestPath = new ArrayList<Node>();
		for (int i = 0; i < numberGeneration; i++) {
			Ant bestAnt = antColonyOptimization.run(graph);
			bestPath = bestAnt.getNodesPath();
			for (Node node : bestPath) {
				System.out.print(node.getNumber() + " ");
			}
			System.out.print("-> " + bestAnt.getWeightPath());
			System.out.println();
			antColonyOptimization.evaporationPheromon(graph);
		}

	}

	List<Integer> firstInitArrayConnectedComponents(Graph graph) {
		List<Integer> connectedComponents = new ArrayList<Integer>(graph
				.getNodes().size());
		for (int i = 0; i < graph.getNodes().size(); i++) {
			connectedComponents.add(0);
		}
		return connectedComponents;
	}

	void setVisitedFalse(Graph graph) {
		List<Node> nodes = graph.getNodes();
		for (Node node : nodes) {
			node.setVisited(false);
		}
		visitedNodes.clear();
	}

}