package io.github.pythonian23.physicsish;

import io.github.pythonian23.physicsish.Collision.Circle;

import java.util.ArrayList;

public class Physicsish {
    public float G_X;
    public float G_Y;
    public float M_TO_PX;

    private final ArrayList<Object> objects;

    public Physicsish() {
        G_X = 0;
        G_Y = 9.8f;
        M_TO_PX = 100;

        objects = new ArrayList<>();
    }

    public void tick(float deltaT) {
        for (Object object: objects) {
            // Gravity
            if (object.GRAVITY) object.applyForce(G_X, G_Y * object.mass);

            // Collisions
            if (object instanceof Circle) {
                for (Object obj : objects) {
                    if (obj == object) continue;
                    float distance = distanceBetween(object.x, object.y, obj.x, obj.y);
                    if (obj instanceof Circle && (object.radius + obj.radius) > distance) {
                        object.addCollision(
                                (object.x + (obj.x - object.x) * object.radius / (object.radius + obj.radius)),
                                (object.y + (obj.y - object.y) * object.radius / (object.radius + obj.radius)),
                                obj
                        );
                    }
                }
            }

            // Apply & Move
            object.tick(deltaT);
        }
    }

    public ArrayList<Object> getObjects() {
        return objects;
    }

    public void addObject(Object object) {
        objects.add(object);
    }

    public void removeObject(Object object) {
        objects.remove(object);
    }

    public static float distanceBetween(float x1, float y1, float x2, float y2) {
        return (float) Math.sqrt(Math.pow(x1 - x2, 2) + Math.pow(y1 - y2, 2));
    }
}
