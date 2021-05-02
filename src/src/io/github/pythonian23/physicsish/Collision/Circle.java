package io.github.pythonian23.physicsish.Collision;

import io.github.pythonian23.physicsish.Object;
import io.github.pythonian23.physicsish.Physicsish;

public class Circle extends Object {

    public Circle(float mass, float radius) {
        super(mass);

        this.radius = radius;
    }

    @Override
    protected void applyCollisions(float deltaT) {
        for (int i = 0; i < collisionsLoc.toArray().length; i++) {
            // Move out of the collision
            Float[] loc = collisionsLoc.get(i);
            Object obj = collisionsObj.get(i);
            float hypo = Physicsish.distanceBetween(x, y, loc[0], loc[1]);
            x += (loc[0] - x) * (hypo - radius) / hypo + vel_x * deltaT;
            y += (loc[1] - y) * (hypo - radius) / hypo + vel_y * deltaT;

            // Momentum
            float[] impactLine = new float[] {x - obj.x, y - obj.y};
            float angle = (float) Math.acos((impactLine[0] * vel_x + impactLine[1] * vel_y) / (Physicsish.distanceBetween(impactLine[0], impactLine[1], 0, 0) * Physicsish.distanceBetween(vel_x, vel_y, 0, 0)));

//            vel_x = (momentumX + obj.momentumX) / mass;
//            vel_y = (momentumY + obj.momentumY) / mass;
        }

        // Torque - Choose Pivot Point
    }
}
