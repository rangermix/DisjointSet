package io.rangermix.disjointset;

import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class DisjointSetTest {

    @Test
    void unionInteger() {
        var test = new DisjointSet<>(List.of(1, 2, 3, 4, 5, 6, 7, 8));
        System.out.println(test.find(2));

        test.union(2, 7);
        assertEquals(test.find(2), test.find(7));
        System.out.println(test.find(2));

        test.union(6, 7);
        assertEquals(test.find(2), test.find(6));
        System.out.println(test.find(2));

        test.union(1, 8);
        System.out.println(test.find(1));

        test.union(4, 5);
        System.out.println(test.find(4));

        test.union(1, 4);
        System.out.println(test.find(1));
    }

    @Test
    void unionCharacter() {
        var test = new DisjointSet<>(List.of('A', 'B', 'C', 'D', 'E', 'F'));
        var relations = List.of(List.of('A', 'C'), List.of('B', 'C'), List.of('F', 'D'), List.of('B', 'D'));
        for (var relation: relations)
            test.union(relation.get(0), relation.get(1));
        assertEquals(test.allSets().size(), 2);
        System.out.println(test.allSets());
    }
}