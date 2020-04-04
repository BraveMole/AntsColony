package com.ressource;

import com.badlogic.gdx.graphics.g2d.TextureRegion;

public enum Animation {
    WORKERANT_WALKING(1 / 6f, TextureLoader.workerAntWalking, 25, 37),
    WORKERANT_EATING(1 / 2f, TextureLoader.workerAntEating, 25, 37);

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
