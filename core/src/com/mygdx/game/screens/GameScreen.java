/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mygdx.game.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.mygdx.game.gameword.worlds.GameWorld;

/**
 *
 * @author Alexis Clément, Hugo Da Roit, Benjamin Lévèque, Alexis Montagne
 */
public class GameScreen implements Screen {
    private GameWorld gameWorld;
    
    public GameScreen(GameWorld gameWorld) {
        this.gameWorld = gameWorld;
    }
    
    @Override
    public void show() {
    }
    
    @Override
    public void render(float delta) {
        Gdx.app.log("GameScreen", "Render");
        gameWorld.update(delta);
    }
    
    @Override
    public void resize(int width, int height) {
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
