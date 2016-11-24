/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mygdx.game.gameword.touchme;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.mygdx.game.gameword.GameRenderer;
import com.mygdx.game.ichelpers.touchme.CheckPoint;

/**
 * @author Hugo Da Roit - contact@hdaroit.fr
 */
public class TouchMeRenderer extends GameRenderer {
   
    public TouchMeRenderer(TouchMe gameWorld) {
        super(gameWorld);
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0, 0);
        Gdx.gl.glClear( GL20.GL_COLOR_BUFFER_BIT | GL20.GL_DEPTH_BUFFER_BIT );
        batch.begin();
        for(CheckPoint checkpoint : gameWorld.getCheckPoints()) {
            checkpoint.draw(batch, delta);
        }
        time.draw(batch, String.valueOf(((gameWorld.getTimer()-System.currentTimeMillis())/1000) % 60) + "s", Gdx.graphics.getWidth()-40, Gdx.graphics.getHeight()-10);
        batch.end();
    }   
}