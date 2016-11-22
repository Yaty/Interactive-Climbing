/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mygdx.game.ichelpers.touchme;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;

/**
 *
 * @author Hugo Da Roit - contact@hdaroit.fr
 */
public class CheckPoint {
    private final int id;
    private final Vector2 position;
    private Texture texture;
    
    public CheckPoint(int id, Vector2 position) {
        this.id = id;
        this.position = position;
        this.texture = new Texture(Gdx.files.internal("checkpoint.png"));
    }
    
    public void draw(Batch batch, float delta) {
        batch.draw(texture, position.x, position.y);
    }

    public int getId() {
        return id;
    }

    public Vector2 getPosition() {
        return position;
    }
}
