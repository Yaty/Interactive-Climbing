/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mygdx.game.screens.touchme;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.SelectBox;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.mygdx.game.ICGame;
import com.mygdx.game.gameword.touchme.TouchMe;
import com.mygdx.game.screens.GameScreen;
import com.mygdx.game.screens.MainMenuScreen;
import java.io.File;
import java.io.FilenameFilter;
import java.util.ArrayList;

/**
 *
 * @author Hugo Da Roit - contact@hdaroit.fr
 */
public class MenuTouchMeScreen implements Screen {
    private final ICGame game;  
    private final Stage stage;
    private Skin skin;
    
    /**
     * Ce constructeur initialise les boutons avec leur images
     * @param game represente le jeu pour agir dessus (le lancer)
     */
    
    public MenuTouchMeScreen (ICGame game){
        this.game = game;        
        stage = new Stage();
        Gdx.input.setInputProcessor(stage); // Le stage va s'occuper des E/S
        create(); // Création du skin de base, puis ajout ci-dessous
        createButtons();
    }
    
    private void createButtons() {
        TextButton btnInfos = new TextButton("Générer une partie", skin);
        btnInfos.setPosition((Gdx.graphics.getWidth()/2)-(btnInfos.getWidth()/2), (Gdx.graphics.getHeight()/2)-(btnInfos.getHeight()/2));
        btnInfos.addListener(new ClickListener() {
                @Override
                public void clicked(InputEvent event, float x, float y) {
                    dispose();
                    game.setScreen(new GenererPartieTouchMeScreen(game));
                };
        });   

        File folder = new File(Gdx.files.internal("./TouchMe/").name());
        File[] listOfFiles = folder.listFiles(new FilenameFilter() {
            @Override
            public boolean accept(File folder, String name) {
                return name.toLowerCase().endsWith(".xml");
            }
        });
        
        String[] arrayFile = new String[listOfFiles.length];
        for(int i = 0 ; i < listOfFiles.length ; i++) {
            arrayFile[i] = listOfFiles[i].getName().replace(".xml", "");
        }        
        
        final SelectBox selectBox = new SelectBox(new Skin(Gdx.files.internal("./skin/uiskin.json")));
        selectBox.setItems((Object[]) arrayFile);
        selectBox.setWidth(200);
        selectBox.setPosition((Gdx.graphics.getWidth()/2)-(selectBox.getWidth()/2), (Gdx.graphics.getHeight()/2)-(selectBox.getHeight()/2) + btnInfos.getHeight() + 70);
        
        TextButton btnChoixPartie = new TextButton("Choisir une partie existante", skin);
        btnChoixPartie.setPosition((Gdx.graphics.getWidth()/2)-(btnChoixPartie.getWidth()/2), (Gdx.graphics.getHeight()/2)-(btnChoixPartie.getHeight()/2) + btnInfos.getHeight() + 20);
        btnChoixPartie.addListener(new ClickListener() {
                @Override
                public void clicked(InputEvent event, float x, float y) {
                    if(selectBox.getSelected() != null) {
                        dispose();
                        String selection = selectBox.getSelected().toString().replace("[", "").replace("]", "");
                        game.setScreen(new GameScreen(new TouchMe(selection + ".xml")));
                    } else {
                        // Ici afficher un messae d'erreur, on ne peut pas ne rien sélectionner
                    }
                };
        });
        
        TextButton btnQuitter = new TextButton("Retour", skin);
        btnQuitter.setPosition((Gdx.graphics.getWidth()/2)-(btnQuitter.getWidth()/2), (Gdx.graphics.getHeight()/2)-(btnQuitter.getHeight()/2) - btnInfos.getHeight() - 20);
        btnQuitter.addListener(new ClickListener() {
                @Override
                public void clicked(InputEvent event, float x, float y) {
                    dispose();
                    game.setScreen(new MainMenuScreen(game));
                };
        });  
        
        stage.addActor(selectBox);
        stage.addActor(btnChoixPartie);
        stage.addActor(btnInfos);
        stage.addActor(btnQuitter);
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
        Gdx.gl.glClearColor(0, 0, 0, 0);
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
