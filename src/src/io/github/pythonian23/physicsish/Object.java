package io.github.pythonian23.physicsish;

import java.awt.*;
import java.util.ArrayList;

public class Object {
    public boolean GRAVITY;
    public boolean AFFECTED_BY_FORCE;

    public float x;
    public float y;

    public float vel_x;
    public float vel_y;

    public float mass;
    public float momentumX;
    public float momentumY;

    public Force force;

    public ArrayList<Float[]> collisionsLoc;
    public ArrayList<Object> collisionsObj;
    public boolean collided;

    public float radius;

    public Color color;

    public Object() {
        this(1);
    }

    public Object(float mass) {
        this.mass = mass;
        force = new Force();

        collisionsLoc = new ArrayList<>();
        collisionsObj = new ArrayList<>();

        GRAVITY = true;
        AFFECTED_BY_FORCE = true;

        color = Color.GREEN;
    }

    public void applyForce(float x, float y) {
        applyForce(new Force(x, y));
    }

    public void applyForce(Force f) {
        force.add(f);
    }

    public void tick(float deltaT) {
        // Calculations
        if (AFFECTED_BY_FORCE) {
            vel_x += force.toAccelX(mass) * deltaT;
            vel_y += force.toAccelY(mass) * deltaT;
        }
        force.reset();

        momentumX = mass * vel_x;
        momentumY = mass * vel_y;

        x += vel_x * deltaT;
        y += vel_y * deltaT;

        // Collisions
        applyCollisions(deltaT);

        collided = !collisionsLoc.isEmpty();

        collisionsLoc.clear();
        collisionsObj.clear();
    }

    protected void addCollision(float x, float y, Object obj) {
        collisionsLoc.add(new Float[] {x, y});
        collisionsObj.add(obj);
    }

    protected void applyCollisions(float deltaT) {}
}
