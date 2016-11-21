/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mygdx.game.gameword.renderer;

import com.badlogic.gdx.Gdx;
import com.mygdx.game.gameword.worlds.GameWorld;

/**
 *
 * @author Alexis Clément, Hugo Da Roit, Benjamin Lévèque, Alexis Montagne
 */
public class TouchMeRenderer extends GameRenderer {
    
    public TouchMeRenderer(GameWorld gameWorld) {
        super(gameWorld);
    }

    @Override
    public void render(float runtime) {
        Gdx.app.log("TouchMeRenderer", "Render");
    }

    @Override
    public void dispose() {
    }
    
}
