package stronglyConnectedComponents.entity;

public class Link {

	Node source;
	Node targe;
	double weight;

	public Node getSource() {
		return source;
	}

	public void setSource(Node source) {
		this.source = source;
	}

	public Node getTarge() {
		return targe;
	}

	public void setTarge(Node targe) {
		this.targe = targe;
	}

	public double getWeight() {
		return weight;
	}

	public void setWeight(double weight) {
		this.weight = weight;
	}

}
