package com.game;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.Array;


public class World extends Stage {
    public static World mainWorld;

    private float zoneSize;
    private ShapeRenderer shape;

    private Array<Array<Zone>> LZone;

    public World(float zoneSize) {
        this.zoneSize = zoneSize;
        this.LZone = new Array<>();
        shape = new ShapeRenderer();
    }

    public float getZoneSize() {
        return zoneSize;
    }

    public void addZone(int column, int row, boolean endRow, Zone zone) {
        this.LZone.get(column).add(zone);
        if (column > 0) { //Construct the list of neighbouring Zones
            zone.addNeighbour(this.LZone.get(column - 1).get(row), true);
            if (row > 0) {
                zone.addNeighbour(this.LZone.get(column - 1).get(row - 1), true);
            }
            if (!endRow) {
                zone.addNeighbour(this.LZone.get(column - 1).get(row + 1), true);
            }
        }
        if (row > 0) {
            zone.addNeighbour(this.LZone.get(column).get(row - 1), true);
        }

    }

    @Override
    public void draw() {
        super.draw();
        shape.setColor(Color.RED);
        shape.begin(ShapeRenderer.ShapeType.Filled);
        shape.circle(400, 200, 1);
        shape.end();

    }

    public void addColumn() {
        this.LZone.add(new Array<Zone>());
    }

    public Zone getZoneAt(int column, int row) {
        return this.LZone.get(column).get(row);
    }

}
