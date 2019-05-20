package com.thang.datastructure.intervaltree;

/**
 * Created by thangpn7 on 2019/05/20.
 */

import java.util.ArrayList;
import java.util.List;

public class IntervalTree<T> {

    private IntervalNode<T> head;
    private List<Interval<T>> intervalList;
    private boolean inSync;
    private int size;

    public IntervalTree() {
        this.head = new IntervalNode<>();
        this.intervalList = new ArrayList<>();
        this.inSync = true;
        this.size = 0;
    }

    public IntervalTree(List<Interval<T>> intervalList) {
        this.head = new IntervalNode<>(intervalList);
        this.intervalList = new ArrayList<>();
        this.intervalList.addAll(intervalList);
        this.inSync = true;
        this.size = intervalList.size();
    }

    public List<T> get(long time) {
        List<Interval<T>> intervals = getIntervals(time);
        List<T> result = new ArrayList<>();
        for (Interval<T> interval : intervals)
            result.add(interval.getData());
        return result;
    }

    public List<Interval<T>> getIntervals(long time) {
        build();
        return head.stab(time);
    }

    public List<T> get(long start, long end) {
        List<Interval<T>> intervals = getIntervals(start, end);
        List<T> result = new ArrayList<>();
        for (Interval<T> interval : intervals)
            result.add(interval.getData());
        return result;
    }

    public List<Interval<T>> getIntervals(long start, long end) {
        build();
        return head.query(new Interval<T>(start, end, null));
    }

    public void addInterval(Interval<T> interval) {
        intervalList.add(interval);
        inSync = false;
    }

    public void addInterval(long begin, long end, T data) {
        intervalList.add(new Interval<>(begin, end, data));
        inSync = false;
    }

    public boolean inSync() {
        return inSync;
    }

    public void build() {
        if (!inSync) {
            head = new IntervalNode<>(intervalList);
            inSync = true;
            size = intervalList.size();
        }
    }

    public int currentSize() {
        return size;
    }

    public int listSize() {
        return intervalList.size();
    }

    @Override
    public String toString() {
        return nodeString(head, 0);
    }

    private String nodeString(IntervalNode<T> node, int level) {
        if (node == null)
            return "";

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < level; i++)
            sb.append("\t");
        sb.append(node).append("\n");
        sb.append(nodeString(node.getLeft(), level + 1));
        sb.append(nodeString(node.getRight(), level + 1));
        return sb.toString();
    }
}
