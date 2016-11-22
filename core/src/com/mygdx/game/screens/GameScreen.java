/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mygdx.game.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.mygdx.game.gameword.GameWorld;

/**
 *
 * @author Alexis Clément, Hugo Da Roit, Benjamin Lévèque, Alexis Montagne
 */
public class GameScreen implements Screen {
    private GameWorld gameWorld;
    private Stage stage;
    
    public GameScreen(GameWorld gameWorld) {
        this.gameWorld = gameWorld;
        stage = new Stage(new ScreenViewport());
        Gdx.input.setInputProcessor(stage);
    }
    
    @Override
    public void show() {
    }
    
    @Override
    public void render(float delta) {
        gameWorld.update(delta);
    }
    
    @Override
    public void resize(int width, int height) {
        stage.getViewport().update(width, height, true);
    }

    @Override
    public void pause() {
    }

    @Override
    public void resume() {
    }

    @Override
    public void hide() {
    }

    @Override
    public void dispose() {
        gameWorld.dispose();
    }
    
}
