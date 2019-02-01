package com.welld.points.repositories;

import com.welld.points.models.Point;
import com.welld.points.models.Segment;
import org.springframework.stereotype.Repository;

import java.util.HashSet;
import java.util.Set;

@Repository
public class PointRepository {
    private Set<Point> points;
    private Set<Segment> segments;

    public PointRepository() {
        this.points = new HashSet<>();
        this.segments = new HashSet<>();
    }

    public Set<Point> getPoints() {
        return new HashSet<>(points);
    }

    public Set<Segment> getSegments() {
        return new HashSet<>(segments);
    }

    public boolean addPoint(Point point) {
        boolean added = points.add(point);
        if (added) {
            if (points.size() > 2) {
                this.segments.forEach(segment -> segment.addPoint(point));
                this.points.stream()
                        .filter(currentPoint -> !currentPoint.equals(point))
                        .forEach(currentPoint -> {
                            Segment newSegment = new Segment(currentPoint, point);
                            if(this.segments.stream().noneMatch(segment -> segment.equals(newSegment)))
                                this.segments.add(newSegment);
                        });
            } else if (points.size() > 1) {
                segments.add(new Segment(points));
            }
        }
        return added;
    }

    public boolean removePoint(Point point) {
        boolean removed = points.remove(point);
        segments.removeIf(segment -> segment.getPoints().size() <= 2 && segment.includePoint(point));
        return removed;
    }

    public void removeAllPoints() {
        points.clear();
        segments.clear();
    }
}
