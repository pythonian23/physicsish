package io.github.pythonian23.physicsish;

public class Force {
    public float x;
    public float y;

    public Force() {}

    public Force(float x, float y) {
        this.x = x;
        this.y = y;
    }

    public float toAccelX(float mass) {
        return x/mass;
    }

    public float toAccelY(float mass) {
        return y/mass;
    }

    public void add(Force force) {
        this.x += force.x;
        this.y += force.y;
    }

    public void reset() {
        this.x = 0;
        this.y = 0;
    }
}
