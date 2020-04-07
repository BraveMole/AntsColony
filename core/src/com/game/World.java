package com.game;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.Array;
import com.ressource.WorldSettings;
import com.ui.WorldInputProcessor;


public class World extends Stage {
    public static World mainWorld;
    private WorldSettings settings;

    private float zoneSize;
    private ShapeRenderer shape;
    private Group ants;
    private Group environment;
    private Group zones;
    private WorldInputProcessor inputProcessor;

    private Array<Array<Zone>> LZone;

    public World(WorldSettings settings) {
        this.settings = settings;
        this.zoneSize = settings.getZoneSize();
        this.LZone = new Array<>();
        shape = new ShapeRenderer();
        ants = new Group();
        zones = new Group();
        environment = new Group();
        super.addActor(zones);
        super.addActor(environment);
        super.addActor(ants);
        this.getViewport().getCamera().viewportHeight = this.getViewport().getScreenHeight();
        this.getViewport().getCamera().viewportWidth = this.getViewport().getScreenWidth();
    }

    @Override
    public void act() {
        super.act();
        this.inputProcessor.act();
    }

    public void setInputProcessor(WorldInputProcessor inputProcessor) {
        this.inputProcessor = inputProcessor;
    }

    public WorldSettings getSettings() {
        return settings;
    }

    public void addAnt(Actor actor) {
        this.ants.addActor(actor);
    }

    public void addEnvironment(Actor actor) {
        this.environment.addActor(actor);
    }

    public float getZoneSize() {
        return zoneSize;
    }

    public void addZone(int column, int row, boolean endRow, Zone zone) {
        this.LZone.get(column).add(zone);
        zones.addActor(zone);
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
    }

    public void addColumn() {
        this.LZone.add(new Array<Zone>());
    }

    public Zone getZoneAt(int column, int row) {
        return this.LZone.get(column).get(row);
    }

}
