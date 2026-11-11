package edu.ub.ds2627.anotub.model;

import java.util.Objects;

public final class Punt2D {
    private final int x;
    private final int y;

    public Punt2D(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Punt2D punt)) {
            return false;
        }
        return x == punt.x && y == punt.y;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }
}
