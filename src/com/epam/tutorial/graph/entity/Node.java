package com.epam.tutorial.graph.entity;

import java.util.List;

public class Node {

	protected int number;
	protected boolean isVisited;
	protected List<Link> childs;

	public int getNumber() {
		return number;
	}

	public void setNumber(int number) {
		this.number = number;
	}

	public boolean getIsVisited() {
		return isVisited;
	}

	public void setVisited(boolean isVisited) {
		this.isVisited = isVisited;
	}

	public List<Link> getChilds() {
		return childs;
	}

	public void setChilds(List<Link> childs) {
		this.childs = childs;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + number;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Node other = (Node) obj;
		if (number != other.number)
			return false;
		return true;
	}

}
