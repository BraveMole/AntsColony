package com.process;

import com.game.World;
import com.game.Zone;

public class SearchZone {
    public static Zone searchZone(float x, float y) {
        try {
            return World.mainWorld.getZoneAt((int) (x / World.mainWorld.getZoneSize()), (int) (y / World.mainWorld.getZoneSize()));
        } catch (IndexOutOfBoundsException e) {
            System.out.println((int) (x / World.mainWorld.getZoneSize()) + " " + (int) (y / World.mainWorld.getZoneSize()));
        }
        return null;
    }
}
