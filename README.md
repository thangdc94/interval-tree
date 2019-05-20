# Interval tree

[![Build Status](https://travis-ci.org/thangdc94/interval-tree.svg?branch=master)](https://travis-ci.org/thangdc94/interval-tree)

This is a java program to implement Interval Tree.

In computer science, an interval tree is an ordered tree data structure to hold intervals. Specifically, it allows one to efficiently find all intervals that overlap with any given interval or point. It is often used for windowing queries, for instance, to find all roads on a computerized map inside a rectangular viewport, or to find all visible elements inside a three-dimensional scene. A similar data structure is the segment tree. The trivial solution is to visit each interval and test whether it intersects the given point or interval, which requires T(n) time, where n is the number of intervals in the collection.

Reference:
- https://www.sanfoundry.com/java-program-implement-interval-tree/
- http://www.davismol.net/2016/02/07/data-structures-augmented-interval-tree-to-search-for-interval-overlapping/