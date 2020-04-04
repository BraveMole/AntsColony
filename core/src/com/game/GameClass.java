package com.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.process.WorldCreation;
import com.ressource.TextureLoader;
import com.ressource.WorldSettings;

import static com.game.World.mainWorld;

public class GameClass extends ApplicationAdapter {
    SpriteBatch batch;
    Texture img;
    private OrthographicCamera cam;


    @Override
    public void create() {
        TextureLoader.loadTexture();
        WorldCreation.Creation(WorldSettings.TEST_WORLD);
        cam = new OrthographicCamera(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        cam.position.set(cam.viewportWidth / 2f, cam.viewportHeight / 2f, 0);
        cam.update();
        mainWorld.getViewport().setCamera(cam);
    }

    @Override
    public void render() {
        Gdx.gl.glClearColor(1, 1, 1, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT | GL20.GL_DEPTH_BUFFER_BIT | (Gdx.graphics.getBufferFormat().coverageSampling ? GL20.GL_COVERAGE_BUFFER_BIT_NV : 0));
        mainWorld.act();
        mainWorld.draw();
    }

    @Override
    public void dispose() {
        batch.dispose();
        img.dispose();
    }
}
