package com.ressource;

public enum WorldSettings {
    TEST_WORLD(2000, 2000, 200, 10, 0.9f);
    private float xLimit;
    private float yLimit;
    private float pheromoneEvaporationRate;//0.9 means 10% of the pheromone disappear every sec
    private float distanceBetweenPheromones;
    private int zoneSize;

    WorldSettings(float xLimit, float yLimit, int zoneSize, float distanceBetweenPheromones, float pheromoneEvaporationRate) {
        this.xLimit = xLimit;
        this.yLimit = yLimit;
        this.zoneSize = zoneSize;
        this.distanceBetweenPheromones = distanceBetweenPheromones;
        this.pheromoneEvaporationRate = pheromoneEvaporationRate;
    }

    public float getDistanceBetweenPheromones() {
        return distanceBetweenPheromones;
    }

    public float getPheromoneEvaporationRate() {
        return pheromoneEvaporationRate;
    }

    public float getxLimit() {
        return xLimit;
    }

    public float getyLimit() {
        return yLimit;
    }

    public int getZoneSize() {
        return zoneSize;
    }
}
