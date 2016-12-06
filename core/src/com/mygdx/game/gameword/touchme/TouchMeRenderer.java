/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mygdx.game.gameword.touchme;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.mygdx.game.gameword.GameRenderer;
import com.mygdx.game.ichelpers.touchme.CheckPoint;

/**
 * @author Hugo Da Roit - contact@hdaroit.fr
 */
public class TouchMeRenderer extends GameRenderer {
    private ShapeRenderer shapeRenderer;
    
    public TouchMeRenderer(TouchMe gameWorld) {
        super(gameWorld);
        shapeRenderer = new ShapeRenderer();
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0, 0);
        Gdx.gl.glClear( GL20.GL_COLOR_BUFFER_BIT | GL20.GL_DEPTH_BUFFER_BIT );
        dessinerCheckPoints(delta);
        dessinerSegments(); 
    }   

    private void dessinerCheckPoints(float delta) {
        batch.begin();
        for(int i = 0 ; i < gameWorld.getCheckPoints().size() ; i++)
            gameWorld.getCheckPoints().get(i).draw(batch, delta);
        time.draw(batch, String.valueOf(((gameWorld.getTimer()-System.currentTimeMillis())/1000) % 60) + "s", Gdx.graphics.getWidth()-40, Gdx.graphics.getHeight()-10);   
        batch.end();
    }

    private void dessinerSegments() {
        shapeRenderer.setProjectionMatrix(batch.getProjectionMatrix());
        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
        shapeRenderer.setColor(Color.RED);
        for(int i = 0 ; i < gameWorld.getCheckPoints().size() - 1 ; i++) {
            CheckPoint cp = gameWorld.getCheckPoints().get(i);
            CheckPoint cpNext = gameWorld.getCheckPoints().get(i+1);
            shapeRenderer.line(cp.getPosition().x+cp.getLargeur()/2, cp.getPosition().y+cp.getHauteur()/2, cpNext.getPosition().x+cpNext.getLargeur()/2, cpNext.getPosition().y+cpNext.getHauteur()/2);
        }
        shapeRenderer.end();
    }
}