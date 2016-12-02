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
public class CheckPoint extends Actor {
    private final int id;
    private final Vector2 position;
    private final Texture texture;
    private float largeur, hauteur;
    
    public CheckPoint(int id, Vector2 position, float hauteur, float largeur) {
        this.id = id;
        this.position = position;
        this.texture = new Texture(Gdx.files.internal("checkpoint.png"));
        this.largeur = largeur;
        this.hauteur = hauteur;
    }
    
    @Override
    public void draw(Batch batch, float delta) {
        batch.draw(texture, position.x, position.y, largeur, hauteur);
    }

    public int getId() {
        return id;
    }

    public Vector2 getPosition() {
        return position;
    }

    public float getLargeur() {
        return largeur;
    }

    public void setLargeur(float largeur) {
        this.largeur = largeur;
    }

    public float getHauteur() {
        return hauteur;
    }

    public void setHauteur(float hauteur) {
        this.hauteur = hauteur;
    }
    
    
}
