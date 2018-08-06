package com.harium.storage.graph;

import com.harium.storage.graph.directional.Direction;


public class DirectionalEdge<N> extends WeightEdge<N> {
	
	private Direction direction = Direction.BIDIRECTIONAL;
	
	public DirectionalEdge(Node<N> origin, Node<N> destination) {
		super(origin, destination);
	}

	public Direction getDirection() {
		return direction;
	}

	public void setDirection(Direction direction) {
		this.direction = direction;
	}
		
}
