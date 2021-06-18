package io.rangermix.disjointset;

import java.util.*;

public class DisjointSet<E> {
    public static class Node<E> {
        Node<E> parent;
        List<E> set;
        int rank;

        public Node(E e) {
            this.parent = this;
            this.rank = 0;
            this.set = new ArrayList<>();
            this.set.add(e);
        }
    }

    Map<E, Node<E>> nodes = new HashMap<>();

    public DisjointSet() {
    }

    public DisjointSet(Collection<E> collection) {
        collection.forEach(this::add);
    }

    public void add(E e) {
        if (nodes.containsKey(e))
            return;

        var node = new Node<>(e);
        nodes.put(e, node);
    }

    public List<E> find(E e) {
        var node = nodes.get(e);
        if (node==null)
            throw new IllegalArgumentException("Element " + e + " not added!");

        return find(node).set;
    }

    private Node<E> find(Node<E> node) {
        if (node.parent==node)
            return node;

        node.parent = find(node.parent);
        return node.parent;
    }

    public void union(E e1, E e2) {
        var node1 = nodes.get(e1);
        var node2 = nodes.get(e2);
        if (node1==null)
            throw new IllegalArgumentException("Element " + e1 + " not added!");
        if (node2==null)
            throw new IllegalArgumentException("Element " + e2 + " not added!");

        var x = find(node1);
        var y = find(node2);

        if (x==y)
            return;

        // higher rank as x
        if (x.rank < y.rank) {
            var t = x;
            x = y;
            y = t;
        }

        // x as new root
        y.parent = x;
        x.set.addAll(y.set);
        y.set = null;

        // update root rank
        if (x.rank == y.rank)
            ++x.rank;
    }

    public List<List<E>> allSets() {
        List<List<E>> allSets = new ArrayList<>();
        for (var node : nodes.values())
            if (node.set!=null)
                allSets.add(node.set);
        return allSets;
    }
}
