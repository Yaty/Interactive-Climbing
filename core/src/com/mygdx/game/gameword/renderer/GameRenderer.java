/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mygdx.game.gameword.renderer;

import com.mygdx.game.gameword.worlds.GameWorld;

/**
 *
 * @author Alexis Clément, Hugo Da Roit, Benjamin Lévèque, Alexis Montagne
 */
public abstract class GameRenderer {
    protected GameWorld gameWorld;
    
    public GameRenderer(GameWorld gameWorld) {
        this.gameWorld = gameWorld;
    }

    public abstract void render(float runtime);
    public abstract void dispose();
}
