/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mygdx.game.gameword;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

/**
 *
 * @author Hugo Da Roit - contact@hdaroit.fr
 */
public abstract class GameRenderer {
    protected GameWorld gameWorld;
    protected SpriteBatch batch;
    private final BitmapFont wait;
    protected BitmapFont time;
    private final float waitWidth, waitHeight;
    
    
    public GameRenderer(GameWorld gameWorld) {
        this.gameWorld = gameWorld;
        this.batch = new SpriteBatch();
        this.wait = new BitmapFont();
        this.time = new BitmapFont();
        wait.setColor(Color.WHITE);
        time.setColor(Color.WHITE);
        GlyphLayout waitLayout = new GlyphLayout();
        waitLayout.setText(wait, "Veuillez appuyer sur n'importe quelle touche pour lancer le jeu !");
        waitWidth = waitLayout.width;
        waitHeight = waitLayout.height;
    }

    public abstract void render(float delta);
    
    public void renderWait(float delta) {
        Gdx.gl.glClearColor(0, 0, 0, 0);
        Gdx.gl.glClear( GL20.GL_COLOR_BUFFER_BIT | GL20.GL_DEPTH_BUFFER_BIT );
        batch.begin();
        wait.draw(batch, "Veuillez appuyer sur n'importe quelle touche pour lancer le jeu !", (Gdx.graphics.getWidth()/2)-(waitWidth/2), (Gdx.graphics.getHeight()/2)+(waitHeight/2));
        batch.end();
    }
    
    public void dispose() {
        wait.dispose();
        time.dispose();
    };
}
