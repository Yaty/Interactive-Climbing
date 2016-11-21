/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mygdx.game.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.mygdx.game.ICGame;
import com.mygdx.game.gameword.worlds.TouchMe;

/**
 *
 * @author Alexis Clément, Hugo Da Roit, Benjamin Lévèque, Alexis Montagne
 */
public class GameChooserScreen implements Screen {
    private final ICGame game;  
    private final Stage stage;
    private Skin skin;
    
    /**
     * Ce constructeur initialise les boutons avec leur images
     * @param game represente le jeu pour agir dessus (le lancer)
     */
    
    public GameChooserScreen (ICGame game){
        this.game = game;        
        stage = new Stage();
        Gdx.input.setInputProcessor(stage); // Le stage va s'occuper des E/S
        create(); // Création du skin de base, puis ajout ci-dessous
        createButtons();
    }
    
    private void createButtons() {
        TextButton btnJeu1 = new TextButton("Jouer à Touch me !", skin);
        btnJeu1.setPosition((Gdx.graphics.getWidth()/2)-(btnJeu1.getWidth()/2), (Gdx.graphics.getHeight()/2)-(btnJeu1.getHeight()/2) + 35);
        btnJeu1.addListener(new ClickListener() {
                @Override
                public void clicked(InputEvent event, float x, float y) {
                    dispose();
                    game.setScreen(new GameScreen(new TouchMe()));
                };
        });   
        
        TextButton btnRetour = new TextButton("Retour", skin);
        btnRetour.setPosition((Gdx.graphics.getWidth()/2)-(btnRetour.getWidth()/2), (Gdx.graphics.getHeight()/2)-(btnRetour.getHeight()/2) - 35);
        btnRetour.addListener(new ClickListener() {
                @Override
                public void clicked(InputEvent event, float x, float y) {
                    dispose();
                    game.setScreen(new MainMenuScreen(game));
                };
        });  
        
        stage.addActor(btnJeu1);
        stage.addActor(btnRetour);
    }
    
    private void create() {     
        //Create a font
        BitmapFont font = new BitmapFont();
        skin = new Skin();
        skin.add("default", font);

        //Create a texture
        Pixmap pixmap = new Pixmap((int)Gdx.graphics.getWidth()/4,(int)Gdx.graphics.getHeight()/10, Pixmap.Format.RGB888);
        pixmap.setColor(Color.WHITE);
        pixmap.fill();
        skin.add("background",new Texture(pixmap));

        //Create a button style
        TextButton.TextButtonStyle textButtonStyle = new TextButton.TextButtonStyle();
        textButtonStyle.up = skin.newDrawable("background", Color.GRAY);
        textButtonStyle.down = skin.newDrawable("background", Color.DARK_GRAY);
        textButtonStyle.checked = skin.newDrawable("background", Color.DARK_GRAY);
        textButtonStyle.over = skin.newDrawable("background", Color.LIGHT_GRAY);
        textButtonStyle.font = skin.getFont("default");
        skin.add("default", textButtonStyle);
    }

    @Override
    public void show() {
    }
    
    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(1, 1, 1, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        stage.act();
        stage.draw();
    }

    
    @Override
    public void resize(int width, int height) {
    }

    @Override
    public void pause() {
    }

    @Override
    public void resume() {
    }

    @Override
    public void hide() {
    }

    @Override
    public void dispose() {
        stage.dispose();
        skin.dispose();
    }
    
}
