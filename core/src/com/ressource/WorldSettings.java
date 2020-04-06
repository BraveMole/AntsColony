package com.ressource;

public enum WorldSettings {
    TEST_WORLD(2000, 2000, 1000, 50, 0.9f, 0.25f);
    private float xLimit;
    private float yLimit;
    private int zoneSize;
    private float distanceBetweenPheromones;
    private float pheromoneEvaporationRate;//0.9 means 10% of the pheromone disappear every sec
    private float AICycleTime;//Time between two AI process

    WorldSettings(float xLimit, float yLimit, int zoneSize, float distanceBetweenPheromones, float pheromoneEvaporationRate, float AICycleTime) {
        this.xLimit = xLimit;
        this.yLimit = yLimit;
        this.zoneSize = zoneSize;
        this.distanceBetweenPheromones = distanceBetweenPheromones;
        this.pheromoneEvaporationRate = pheromoneEvaporationRate;
        this.AICycleTime = AICycleTime;
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

    public float getAICycleTime() {
        return AICycleTime;
    }
}
