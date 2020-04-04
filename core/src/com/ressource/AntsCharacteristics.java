package com.ressource;

public enum AntsCharacteristics {
    WORKER_ANT(50, 100, 50, 5);


    private float speed;
    private float sensibility;
    private float movementDeviation;
    private float carryingPotential;

    AntsCharacteristics(float speed, float sensibility, float movementDeviation, float carryingPotential) {
        this.sensibility = sensibility;
        this.speed = speed;
        this.movementDeviation = movementDeviation;
        this.carryingPotential = carryingPotential;
    }

    public float getSpeed() {
        return speed;
    }

    public float getSensibility() {
        return sensibility;
    }

    public float getMovementDeviation() {
        return movementDeviation;
    }

    public float getCarryingPotential() {
        return carryingPotential;
    }


}
