/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mygdx.game.gameword;

import com.mygdx.game.ichelpers.touchme.CheckPoint;
import java.util.ArrayList;
import java.util.Date;

/**
 *
 * @author Alexis Clément, Hugo Da Roit, Benjamin Lévèque, Alexis Montagne
 */
public abstract class GameWorld {
    protected GameRenderer gameRenderer;
    protected long timer; 
    protected boolean started;
    public abstract void update(float delta);
    public abstract void dispose();
    public abstract ArrayList<CheckPoint> getCheckPoints();
    public abstract long getTimer();
    public abstract boolean isStarted();
}
