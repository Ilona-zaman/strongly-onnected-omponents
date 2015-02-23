package stronglyConnectedComponents.main;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import stronglyConnectedComponents.entity.Graph;
import stronglyConnectedComponents.entity.Link;
import stronglyConnectedComponents.entity.Node;

public class Reader {
	BufferedReader input = null;
	Graph graph = new Graph();

	public Graph readGraph(String fileName) {
		try {
			input = new BufferedReader(new FileReader(fileName));
		} catch (FileNotFoundException e1) {
			System.out.println("File \"" + fileName + "\" not found");
			return null;
		}
		List<Link> list = new ArrayList<Link>();
		String tmp;
		try {
			while ((tmp = input.readLine()) != null) {

				Link link = new Link();
				Node source = new Node();
				Node targe = new Node();

				String[] a = tmp.split("=");
				link.setWeight(Double.parseDouble(a[1]));

				String[] b = a[0].split(",");

				source.setNumber(b[0]);
				link.setSource(source);

				targe.setNumber(b[1]);
				link.setTarge(targe);

				list.add(link);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		graph.setLinks(list);
		return graph;

	}
}