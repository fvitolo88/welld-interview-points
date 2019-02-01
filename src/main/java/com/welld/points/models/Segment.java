package com.welld.points.models;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

public class Segment {

    private Set<Point> points;

    public Segment(Point firstPoint, Point secondPoint) {
        if (firstPoint == null || secondPoint == null || firstPoint.equals(secondPoint))
            throw new IllegalArgumentException("Segment need two different points at least");
        this.points = new HashSet<>(Arrays.asList(firstPoint, secondPoint));
    }

    public Segment(Set<Point> points) {
        if (points == null || points.stream().filter(Objects::nonNull).collect(Collectors.toSet()).size() < 2)
            throw new IllegalArgumentException("Segment need two different points at least");
        this.points = new HashSet<>();
        this.points.addAll(points);
    }

    public Set<Point> getPoints() {
        return new HashSet<>(points);
    }

    public boolean includePoint(Point point) {
        Point firstPoint = (Point) this.points.toArray()[0];
        Point secondPoint = (Point) this.points.toArray()[1];

        return ((xAxisParallel() && firstPoint.getY().equals(point.getY())) ||
                (yAxisParallel() && firstPoint.getX().equals(point.getX())))
                ||
                ((point.getX() - firstPoint.getX()) / (secondPoint.getX() - firstPoint.getX()) ==
                        (point.getY() - firstPoint.getY()) / (secondPoint.getY() - firstPoint.getY()));
    }

    public boolean addPoint(Point point) {
        return this.includePoint(point) && this.points.add(point);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Segment segment = (Segment) o;

        Point segmentFirstPoint = (Point) segment.points.toArray()[0];
        Point segmentSecondPoint = (Point) segment.points.toArray()[1];

        return this.includePoint(segmentFirstPoint) && this.includePoint(segmentSecondPoint);
    }

    private boolean parallelToAnAxis(Function<Point, Double> axisGetter) {
        Point segmentFirstPoint = (Point) this.points.toArray()[0];
        Point segmentSecondPoint = (Point) this.points.toArray()[1];

        return axisGetter.apply(segmentFirstPoint).equals(axisGetter.apply(segmentSecondPoint));
    }

    private boolean xAxisParallel() {
        return parallelToAnAxis(Point::getY);
    }

    private boolean yAxisParallel() {
        return parallelToAnAxis(Point::getX);
    }

    @Override
    public String toString() {
        return "Segment{ \n" +
                points.stream()
                        .map(Point::toString)
                        .collect(Collectors.joining("\n")) + '\n' +
                '}';
    }
}
