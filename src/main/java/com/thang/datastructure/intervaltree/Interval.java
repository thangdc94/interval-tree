package com.thang.datastructure.intervaltree;

import java.util.Objects;

/**
 * Created by thangpn7 on 2019/05/20.
 */
public class Interval<T> implements Comparable<Interval<T>> {

    private long start;
    private long end;
    private T data;

    public Interval(long start, long end, T data) {
        this.start = start;
        this.end = end;
        this.data = data;
    }

    public long getStart() {
        return start;
    }

    public void setStart(long start) {
        this.start = start;
    }

    public long getEnd() {
        return end;
    }

    public void setEnd(long end) {
        this.end = end;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public boolean contains(long time) {
        return time <= end && time >= start;
    }

    public boolean intersects(Interval<?> other) {
        return other.getEnd() > start && other.getStart() < end;
    }

    public int compareTo(Interval<T> other) {
        if (start < other.getStart())
            return -1;
        else if (start > other.getStart())
            return 1;
        else return Long.compare(end, other.getEnd());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Interval<?> interval = (Interval<?>) o;
        return start == interval.start &&
                end == interval.end &&
                Objects.equals(data, interval.data);
    }

    @Override
    public int hashCode() {
        return Objects.hash(start, end, data);
    }
}
