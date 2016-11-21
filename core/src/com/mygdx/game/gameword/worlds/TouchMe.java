/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mygdx.game.gameword.worlds;

import com.badlogic.gdx.Gdx;
import com.mygdx.game.gameword.renderer.TouchMeRenderer;

/**
 * @author Hugo Da Roit
 */
public class TouchMe extends GameWorld {

    public TouchMe() {
        this.gameRenderer = new TouchMeRenderer(this);
    }
    
    @Override
    public void update(float delta) {
        Gdx.app.log("TouchMe", "Update");
        gameRenderer.render(delta);
    }

    @Override
    public void dispose() {
        gameRenderer.dispose();
    }
    
}
