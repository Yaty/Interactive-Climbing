/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mygdx.game.gameword.touchme;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.mygdx.game.gameword.GameWorld;
import com.mygdx.game.ichelpers.touchme.CheckPoint;
import com.mygdx.game.ichelpers.touchme.SaveHandler;
import java.util.ArrayList;

/**
 * @author Hugo Da Roit
 */
public class TouchMe extends GameWorld {
    private final ArrayList<CheckPoint> checkPoints;
    private final TouchMeRenderer gameRenderer;
    private final SaveHandler sh;
    private String path;
    
    public TouchMe(String path) {
        this.gameRenderer = new TouchMeRenderer(this);
        this.path = path;
        sh = new SaveHandler();
        checkPoints = sh.load(path);
        if(checkPoints == null) {
            Gdx.app.error("TouchMe", "Erreur lors du chargement.");
            dispose();
            Gdx.app.exit();
        }     
        started = false;
    }
    
    @Override
    public void update(float delta) {
        if(started) {
            gameRenderer.render(delta);
            // Just clicked existe pas
            if(Gdx.input.isKeyJustPressed(Keys.SPACE) || Gdx.input.justTouched())
                checkPoints.remove(0);
            if(timer <= System.currentTimeMillis() || checkPoints.isEmpty())
                Gdx.app.exit();
        } else {
            gameRenderer.renderWait(delta);
            if(Gdx.input.isKeyPressed(Keys.ANY_KEY) || Gdx.input.justTouched()) {
                this.timer = System.currentTimeMillis() + 20000;
                started = true;
            }
        }
    }

    @Override
    public void dispose() {
        gameRenderer.dispose();
    }
    
    @Override
    public ArrayList<CheckPoint> getCheckPoints() {
        return checkPoints;
    }

    @Override
    public long getTimer() {
        return timer;
    }
    
    @Override
    public boolean isStarted() {
        return started;
    }
}