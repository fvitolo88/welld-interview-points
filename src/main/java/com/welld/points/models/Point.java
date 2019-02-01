package com.welld.points.models;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.welld.points.exceptions.IllegalPointArgumentException;

import java.util.Objects;

public class Point {

    private Double x;
    private Double y;

    @JsonCreator
    public Point(@JsonProperty("x") Double x, @JsonProperty("y") Double y) {
        if (x == null || y == null)
            throw new IllegalPointArgumentException(String.format("Coordinates cannot be null - [x: %s, y: %s]", x, y));
        this.x = x;
        this.y = y;
    }

    public Double getX() {
        return x;
    }

    public Double getY() {
        return y;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Point point = (Point) o;

        return (Objects.equals(x, point.x)) && (Objects.equals(y, point.y));
    }

    @Override
    public int hashCode() {
        int result = x != null ? x.hashCode() : 0;
        result = 31 * result + (y != null ? y.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Point{x=" + x + ", y=" + y + '}';
    }
}
