package com.welld.points.models;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import static org.junit.Assert.assertTrue;

@RunWith(JUnit4.class)
public class SegmentTest {

    @Test
    public void shouldNotAddSameSegmentToSet() {
        Point point_00 = new Point(0d, 0d);
        Point point_11 = new Point(1d, 1d);
        Point point_22 = new Point(2d, 2d);

        Segment segment_00_11 = new Segment(point_00, point_11);
        Segment segment_00_22 = new Segment(point_00, point_22);

        assertTrue(segment_00_11.equals(segment_00_22));
    }
}
