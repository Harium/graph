package com.harium.storage.graph;

import java.util.*;

public abstract class GenericComplexGraph<N, E extends GenericEdge<N>> {

    protected Set<E> edges;
    protected Set<Node<N>> nodes;

    protected Map<Node<N>, List<E>> edgesByNodes;

    public GenericComplexGraph() {
        super();

        nodes = new LinkedHashSet<Node<N>>();
        edges = new LinkedHashSet<E>();
        edgesByNodes = new HashMap<Node<N>, List<E>>();
    }

    public Set<Node<N>> getNodes() {
        return nodes;
    }

    public void setNodes(Set<Node<N>> nodes) {
        this.nodes = nodes;
    }

    public Node<N> addNode(Node<N> node) {
        this.nodes.add(node);
        return node;
    }

    public Map<Node<N>, List<E>> getAllEdges() {
        return edgesByNodes;
    }

    public List<E> getAllEdgesAsList() {

        List<E> list = new ArrayList<E>();
        for (List<E> nodeEdges : edgesByNodes.values()) {
            list.addAll(nodeEdges);
        }

        return list;
    }

    public Set<E> getEdges() {
        return edges;
    }

    public List<E> getEdges(Node<N> node) {

        if (edgesByNodes.containsKey(node)) {
            return edgesByNodes.get(node);
        }

        return new ArrayList<E>();
    }

    public E addEdge(E edge) {

        edges.add(edge);
        addNodesFromEdge(edge);

        Node<N> origin = edge.getOrigin();

        if (!edgesByNodes.containsKey(origin)) {
            edgesByNodes.put(origin, new ArrayList<E>());
        }

        List<E> originEdges = edgesByNodes.get(origin);
        if (!originEdges.contains(edge)) {
            edgesByNodes.get(origin).add(0, edge);
        }

        return edge;
    }

    private void addNodesFromEdge(GenericEdge<N> edge) {

        Node<N> origin = edge.getOrigin();

        if (!nodes.contains(origin)) {
            nodes.add(origin);
        }

        Node<N> destination = edge.getDestination();

        if (!nodes.contains(destination)) {
            nodes.add(destination);
        }
    }

    public boolean hasDiretionalEdge(Node<N> origin, Node<N> destination) {
        for (E edge : edges) {
            if (edge.getOrigin() == origin && edge.getDestination() == destination) {
                return true;
            }
        }
        return false;
    }

    public boolean hasEdge(Node<N> origin, Node<N> destination) {

        for (E edge : edges) {
            if (edge.getOrigin() == origin && edge.getDestination() == destination) {
                return true;
            }
            if (edge.getDestination() == origin && edge.getOrigin() == destination) {
                return true;
            }
        }
        return false;
    }

    public void mergeGraph(GenericComplexGraph<N, E> anotherGraph) {
        for (E edge : anotherGraph.edges) {
            addEdge(edge);
        }
    }

    public void clear() {
        nodes.clear();
        edges.clear();
        edgesByNodes.clear();
    }

}