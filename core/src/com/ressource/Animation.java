package com.ressource;

import com.badlogic.gdx.graphics.g2d.TextureRegion;

public enum Animation {
    WORKER_ANT_WALKING(1 / 6f, TextureLoader.workerAntWalking, 25, 37),
    WORKER_ANT_EATING(1 / 2f, TextureLoader.workerAntEating, 25, 37),
    WORKER_ANT_STILL(0, TextureLoader.workerAntEating, 25, 37);

    private float frameDuration;
    private TextureRegion[] keyFrames;
    private float width;
    private float height;

    Animation(float frameDuration, TextureRegion[] keyFrames, float width, float height) {
        this.frameDuration = frameDuration;
        this.keyFrames = keyFrames;
        this.width = width;
        this.height = height;
    }

    public float getFrameDuration() {
        return frameDuration;
    }

    public TextureRegion[] getKeyFrames() {
        return keyFrames;
    }

    public float getWidth() {
        return width;
    }

    public float getHeight() {
        return height;
    }
}
