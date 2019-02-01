package com.welld.points.repositories;

import com.welld.points.models.Point;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

@RunWith(JUnit4.class)
public class PointRepositoryTest {

    private PointRepository pointRepository;

    @Before
    public void setUp(){
        this.pointRepository = new PointRepository();
    }

    @Test
    public void shouldAddPointToRepository() {
        this.pointRepository.addPoint(new Point(0d,0d));

        assertEquals(this.pointRepository.getPoints().size(),1);
        assertEquals(this.pointRepository.getSegments().size(), 0);
    }

    @Test
    public void shouldAddSegmentWhenTwoPointsAreAdded() {
        this.pointRepository.addPoint(new Point(0d,0d));

        assertEquals(this.pointRepository.getPoints().size(), 1);
        assertEquals(this.pointRepository.getSegments().size(), 0);

        this.pointRepository.addPoint(new Point(1d,1d));

        assertEquals(this.pointRepository.getPoints().size(), 2);
        assertEquals(this.pointRepository.getSegments().size(), 1);
    }

    @Test
    public void shouldAddPointToSegmentWithoutCreateAnotherSegment() {
        this.pointRepository.addPoint(new Point(0d,0d));

        assertEquals(this.pointRepository.getPoints().size(), 1);
        assertEquals(this.pointRepository.getSegments().size(), 0);

        this.pointRepository.addPoint(new Point(1d,1d));

        assertEquals(this.pointRepository.getPoints().size(), 2);
        assertEquals(this.pointRepository.getSegments().size(), 1);

        this.pointRepository.addPoint(new Point(3d,3d));

        assertEquals(this.pointRepository.getPoints().size(), 3);
        assertEquals(this.pointRepository.getSegments().size(), 1);
    }

    @Test
    public void shouldAddPointToSegmentWhenThisIsParallelToAnAxis() {
        this.pointRepository.addPoint(new Point(0d,0d));

        assertEquals(this.pointRepository.getPoints().size(), 1);
        assertEquals(this.pointRepository.getSegments().size(), 0);

        this.pointRepository.addPoint(new Point(1d,1d));

        assertEquals(this.pointRepository.getPoints().size(), 2);
        assertEquals(this.pointRepository.getSegments().size(), 1);

        this.pointRepository.addPoint(new Point(0d,1d));

        assertEquals(this.pointRepository.getPoints().size(), 3);
        assertEquals(this.pointRepository.getSegments().size(), 3);

        this.pointRepository.addPoint(new Point(-1d,1d));

        assertEquals(this.pointRepository.getPoints().size(), 4);
        assertEquals(this.pointRepository.getSegments().size(), 4);
    }

}
