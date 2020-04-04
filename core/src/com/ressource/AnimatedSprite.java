package com.ressource;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class AnimatedSprite extends Sprite {
    private TextureRegion[] arrayFrame;
    private float realX;
    private float realY;
    private float frameDuration;
    private float animationDuration;
    private int frameIndex;
    private float timeElapsed;


    public AnimatedSprite(float frameDuration, Texture texture, TextureRegion[] keyFrames, float x, float y, float rotation, float width, float height) {
        this.frameDuration = frameDuration;
        this.animationDuration = frameDuration * keyFrames.length;
        this.arrayFrame = keyFrames;
        this.timeElapsed = 0;
        this.frameIndex = 0;
        this.setTexture(texture);
        this.setRegion(keyFrames[frameIndex]);
        this.setPosition(x, y);
        this.setSize(width, height);
        this.setRotation(rotation);
    }

    public AnimatedSprite(Texture texture, float x, float y, float rotation, float width, float height) {
        this.setTexture(texture);
        this.setRegion(0, 0, texture.getWidth(), texture.getHeight());
        this.setPosition(x, y);
        this.setSize(width, height);
        this.setRotation(rotation);
    }

    public AnimatedSprite(Texture texture, float x, float y, float rotation) {
        this(texture, x, y, rotation, texture.getWidth(), texture.getHeight());
    }

    @Override
    public void setPosition(float x, float y) {
        this.realX = x;
        this.realY = y;
        this.setX(x - getWidth() / 2f);
        this.setY(y - getHeight() / 2f);
    }

    @Override
    public void setSize(float width, float height) {
        super.setSize(width, height);
        this.setOrigin(width / 2, height / 2);
        this.setPosition(realX, realY);
    }

    public void animate(float delta) {
        timeElapsed += delta;
        while (timeElapsed >= animationDuration) {
            timeElapsed -= animationDuration;
        }
        if ((int) (timeElapsed / frameDuration) != frameIndex) {
            frameIndex = (int) (timeElapsed / frameDuration);
            this.setRegion(arrayFrame[frameIndex]);
        }
    }


}