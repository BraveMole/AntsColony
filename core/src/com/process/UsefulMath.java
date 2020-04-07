package com.process;

import com.actors.Pheromone;
import com.actors.WorkerAnt;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.utils.Array;
import com.game.Zone;

import java.util.Random;

import static com.game.World.mainWorld;

public class UsefulMath {
    static Random rand = new Random();

    public static Vector2 setVector(float orientationInDegree, float size) {
        return new Vector2((float) (size * Math.cos(Math.toRadians(orientationInDegree))), (float) (size * Math.sin(Math.toRadians(orientationInDegree))));
    }

    public static Vector2 setVector(Actor actor, Actor actorgoal, float size) {
        return new Vector2(actorgoal.getX() - actor.getX(), actorgoal.getY() - actor.getY()).setLength(size);
    }

    public static float getOrientationInDegree(Actor a, Actor b) {
        return (float) Math.toDegrees(Math.atan((b.getY() - a.getY()) / (b.getX() - a.getX())));
    }

    public static float getDistanceActors(Actor a, Actor b) {
        return (float) (Math.sqrt(Math.pow(a.getX() - b.getX(), 2) + Math.pow(a.getY() - b.getY(), 2)));
    }

    public static boolean isCloseToAZone(Actor a, Zone b) {
        return (Math.abs(a.getX() - b.getX()) < (mainWorld.getSettings().getZoneSize() * 1.5) & Math.abs(a.getY() - b.getY()) < (mainWorld.getSettings().getZoneSize() * 1.5));
    }

    public static float nextGaussian(float deviation) {
        return (float) rand.nextGaussian() * deviation;
    }
}
