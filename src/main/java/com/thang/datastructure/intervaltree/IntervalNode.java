package com.thang.datastructure.intervaltree;

import java.util.*;

/**
 * Created by thangpn7 on 2019/05/20.
 */
public class IntervalNode<T> {

    private SortedMap<Interval<T>, List<Interval<T>>> intervals;
    private long center;
    private IntervalNode<T> leftNode;
    private IntervalNode<T> rightNode;

    public IntervalNode() {
        intervals = new TreeMap<>();
        center = 0;
        leftNode = null;
        rightNode = null;
    }

    public IntervalNode(List<Interval<T>> intervalList) {

        intervals = new TreeMap<>();

        SortedSet<Long> endpoints = new TreeSet<>();

        for (Interval<T> interval : intervalList) {
            endpoints.add(interval.getStart());
            endpoints.add(interval.getEnd());
        }

        long median = getMedian(endpoints);
        center = median;

        List<Interval<T>> left = new ArrayList<>();
        List<Interval<T>> right = new ArrayList<>();

        for (Interval<T> interval : intervalList) {
            if (interval.getEnd() < median)
                left.add(interval);
            else if (interval.getStart() > median)
                right.add(interval);
            else {
                List<Interval<T>> posting = intervals.get(interval);
                if (posting == null) {
                    posting = new ArrayList<>();
                    intervals.put(interval, posting);
                }
                posting.add(interval);
            }
        }

        if (left.size() > 0)
            leftNode = new IntervalNode<T>(left);
        if (right.size() > 0)
            rightNode = new IntervalNode<T>(right);
    }

    public List<Interval<T>> stab(long time) {
        List<Interval<T>> result = new ArrayList<>();

        for (Map.Entry<Interval<T>, List<Interval<T>>> entry : intervals.entrySet()) {
            if (entry.getKey().contains(time)) {
                result.addAll(entry.getValue());
            } else if (entry.getKey().getStart() > time) {
                break;
            }
        }

        if (time < center && leftNode != null)
            result.addAll(leftNode.stab(time));
        else if (time > center && rightNode != null)
            result.addAll(rightNode.stab(time));
        return result;
    }

    public List<Interval<T>> query(Interval<?> target) {
        List<Interval<T>> result = new ArrayList<Interval<T>>();

        for (Map.Entry<Interval<T>, List<Interval<T>>> entry : intervals
                .entrySet()) {
            if (entry.getKey().intersects(target))
                result.addAll(entry.getValue());
            else if (entry.getKey().getStart() > target.getEnd())
                break;
        }

        if (target.getStart() < center && leftNode != null)
            result.addAll(leftNode.query(target));
        if (target.getEnd() > center && rightNode != null)
            result.addAll(rightNode.query(target));
        return result;
    }

    public long getCenter() {
        return center;
    }

    public void setCenter(long center) {
        this.center = center;
    }

    public IntervalNode<T> getLeft() {
        return leftNode;
    }

    public void setLeft(IntervalNode<T> left) {
        this.leftNode = left;
    }

    public IntervalNode<T> getRight() {
        return rightNode;
    }

    public void setRight(IntervalNode<T> right) {
        this.rightNode = right;
    }

    private Long getMedian(SortedSet<Long> set) {
        int i = 0;
        int middle = set.size() / 2;
        for (Long point : set) {
            if (i == middle)
                return point;
            i++;
        }
        return null;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(center).append(": ");
        for (Map.Entry<Interval<T>, List<Interval<T>>> entry : intervals.entrySet()) {
            sb.append("[")
                    .append(entry.getKey().getStart())
                    .append(",")
                    .append(entry.getKey().getEnd())
                    .append("]:{");
            for (Interval<T> interval : entry.getValue()) {
                sb.append("(")
                        .append(interval.getStart())
                        .append(",")
                        .append(interval.getEnd())
                        .append(",")
                        .append(interval.getData())
                        .append(")");
            }
            sb.append("} ");
        }
        return sb.toString();
    }
}
