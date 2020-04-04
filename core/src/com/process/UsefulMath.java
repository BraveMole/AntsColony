package com.process;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.game.Zone;

import java.util.Random;

public class UsefulMath {
    static Random rand = new Random();

    public static Vector2 setVector(float orientationInDegree, float size) {
        return new Vector2((float) (size * Math.cos(Math.toRadians(orientationInDegree))), (float) (size * Math.sin(Math.toRadians(orientationInDegree))));
    }

    public static Vector2 setVector(Actor a, Actor b, float size) {
        return new Vector2(b.getX() - a.getX(), b.getY() - a.getY()).setLength(size);
    }

    public static float getDistanceActors(Actor a, Actor b) {
        return (float) (Math.sqrt(Math.pow(a.getX() - b.getX(), 2) + Math.pow(a.getY() - b.getY(), 2)));
    }

    public static boolean isCloseToAZone(Actor a, Zone b) {
        return (Math.abs(a.getX() - b.getX()) < (b.getSizeOfZone() * 1.5) & Math.abs(a.getY() - b.getY()) < (b.getSizeOfZone() * 1.5));
    }

    public static float nextGaussian(float deviation) {
        return (float) rand.nextGaussian() * deviation;
    }
}
