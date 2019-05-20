package com.thang.datastructure.intervaltree;

import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Created by thangpn7 on 2019/05/20.
 */
class IntervalTreeTest {
    private IntervalTree<String> intervalTree;

    IntervalTreeTest() {
        intervalTree = new IntervalTree<>();

        intervalTree.addInterval(0, 10, "First");
        intervalTree.addInterval(8, 15, "Second");
        intervalTree.addInterval(15, 19, "Third");
        intervalTree.addInterval(17, 25, "Fourth");

        Random random = new Random();
        for (int i = 0; i < 12000; i++) {
            int start = random.nextInt(50000);
            int end = start + random.nextInt(10);
            intervalTree.addInterval(start, end, String.valueOf(i));
        }
        intervalTree.build();
    }

    @Test
    void testFindIntervalOfOnePoint() {
        List<String> result = intervalTree.get(18);
        assertTrue(result.contains("Third"));
        assertTrue(result.contains("Fourth"));
    }

    @Test
    void testFindIntervalOfRange() {
        List<String> result = intervalTree.get(5, 15);
        assertTrue(result.contains("First"));
        assertTrue(result.contains("Second"));
    }

    @Test
    void testFindOutOfRange() {
        List<String> result = intervalTree.get(-1);
        assertTrue(result.isEmpty());
    }
}