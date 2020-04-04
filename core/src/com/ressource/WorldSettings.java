package com.ressource;

public enum WorldSettings {
    TEST_WORLD(2000, 2000, 200);;
    private float xLimit;
    private float yLimit;
    private float zoneSize;

    WorldSettings(float xLimit, float yLimit, float zoneSize) {
        this.xLimit = xLimit;
        this.yLimit = yLimit;
        this.zoneSize = zoneSize;

    }

    public float getxLimit() {
        return xLimit;
    }

    public float getyLimit() {
        return yLimit;
    }

    public float getZoneSize() {
        return zoneSize;
    }
}
