/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mygdx.game.gameword.worlds;

import com.mygdx.game.gameword.renderer.GameRenderer;

/**
 *
 * @author Alexis Clément, Hugo Da Roit, Benjamin Lévèque, Alexis Montagne
 */
public abstract class GameWorld {
    protected GameRenderer gameRenderer;
    public abstract void update(float delta);
    public abstract void dispose();
}