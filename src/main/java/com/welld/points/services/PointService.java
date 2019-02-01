package com.welld.points.services;

import com.welld.points.models.Segment;
import com.welld.points.repositories.PointRepository;
import com.welld.points.models.Point;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.stream.Collectors;

@Service
public class PointService {

    @Autowired
    private PointRepository pointRepository;

    public Set<Point> getPoints() {
        return pointRepository.getPoints();
    }

    public boolean addPoint(Point point) {
        return pointRepository.addPoint(point);
    }

    public boolean removePoint(Point point) {
        return pointRepository.removePoint(point);
    }

    public Set<Segment> getAllSegments(){
        return pointRepository.getSegments();
    }

    public Set<Segment> getAllSegmentsPassingThroughNPoints(int n) {
        return pointRepository.getSegments().stream()
                .filter(segment -> segment.getPoints().size() >= n)
                .collect(Collectors.toSet());
    }

    public void deleteAllPoints() {
        pointRepository.removeAllPoints();
    }
}
