package com.welld.points.controllers;

import com.fasterxml.jackson.databind.exc.InvalidDefinitionException;
import com.welld.points.models.Point;
import com.welld.points.models.Segment;
import com.welld.points.services.PointService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequestMapping(value = "points", consumes = "application/json", produces = "application/json")
public class PointsController {

    @Autowired
    private PointService pointService;

    @RequestMapping(method = RequestMethod.POST, value = "point")
    public ResponseEntity<Point> addPoint(@RequestBody Point point) {
        pointService.addPoint(point);
        return ResponseEntity.ok().body(point);
    }

    @RequestMapping(method = RequestMethod.GET, value = "space")
    public ResponseEntity<Set<Point>> space() {
        return ResponseEntity.ok(pointService.getPoints());
    }

    @RequestMapping(method = RequestMethod.GET, value = "lines/{n}")
    public ResponseEntity<Set<Segment>> lines(@PathVariable Integer n) {
        return ResponseEntity.ok(pointService.getAllSegmentsPassingThroughNPoints(n));
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "space")
    public void deleteSpace() {
        pointService.deleteAllPoints();
    }

    @ExceptionHandler(value = {InvalidDefinitionException.class})
    public ResponseEntity InvalidDefinitionException(InvalidDefinitionException e){
        return ResponseEntity.badRequest().body(e.getMessage());
    }
}
