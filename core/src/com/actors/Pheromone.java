package com.actors;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;

import static com.game.World.mainWorld;

public class Pheromone extends Actor {

    private Vector2 direction;
    private float distance;

    public Pheromone(Vector2 direction, float distance) {
        this.direction = direction;
        this.distance = distance;
    }

    public Pheromone() {
        this(new Vector2(0, 0), Float.MAX_VALUE);
    }

    public boolean replacePheromone(Vector2 direction, float distance, float pheromonePower) {
        if (this.direction.len() < 0.1 | this.distance >= distance) {
            this.direction = direction.scl(pheromonePower);
            this.distance = distance;
            return true;
        }
        return false;
    }

    @Override
    public float getRotation() {
        return this.getDirection().angle();
    }

    public void evaporate(float rate) {
        this.direction.scl(rate);
    }


    public Vector2 getDirection() {
        return direction;
    }

    public float getDistance() {
        return distance;
    }
}
