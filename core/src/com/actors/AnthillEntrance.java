package com.actors;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.ressource.AnimatedSprite;
import com.ressource.TextureLoader;

public class AnthillEntrance extends Actor {
    private AnimatedSprite sprite;

    public AnthillEntrance(float x, float y) {
        this.setPosition(x, y);
        this.sprite = new AnimatedSprite(TextureLoader.anthill, x, y, 0);
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        this.sprite.draw(batch);
    }
}
