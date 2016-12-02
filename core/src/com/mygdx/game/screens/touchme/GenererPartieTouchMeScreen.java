/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mygdx.game.screens.touchme;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.mygdx.game.ICGame;
import com.mygdx.game.gameword.touchme.TouchMe;
import com.mygdx.game.ichelpers.touchme.CheckPoint;
import com.mygdx.game.ichelpers.touchme.SaveHandler;
import com.mygdx.game.screens.GameScreen;
import java.util.ArrayList;

/**
 *
 * @author Alexis Clément, Hugo Da Roit, Benjamin Lévèque, Alexis Montagne
 */
public class GenererPartieTouchMeScreen implements Screen {
    private final ICGame game;
    private final Stage stage;
    private final SpriteBatch batch;
    private final Texture imgCheckpoint;
    private final ArrayList<CheckPoint> checkpoints;
    private final TextButton save, jouerCurrent, nouvelEnregistrement, quitter;
    private final Skin skin;
    private final Image background;
    private final Table table;
    
    public GenererPartieTouchMeScreen(ICGame game) {
        this.game = game;
        batch = new SpriteBatch();
        stage = new Stage(new ScreenViewport());
        checkpoints = new ArrayList();
        imgCheckpoint = new Texture(Gdx.files.internal("checkpoint.png"));
        
        background = new Image();
        background.setSize(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        stage.addActor(background);
        
        skin = new Skin(Gdx.files.internal("./skin/uiskin.json"));
        save = new TextButton("Sauvegarder", skin);
        jouerCurrent = new TextButton("Jouer avec ce modèle", skin);
        nouvelEnregistrement = new TextButton("Nouveau modèle", skin);
        quitter = new TextButton("Quitter", skin);
        
        table = new Table();
        table.add(save, jouerCurrent, nouvelEnregistrement, quitter);
        table.setPosition(Gdx.graphics.getWidth()/2, Gdx.graphics.getHeight()-20);   
        stage.addActor(table);
        
        Gdx.input.setInputProcessor(stage);
    }
    
    @Override
    public void show() {
        background.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                checkpoints.add(new CheckPoint(checkpoints.size(), new Vector2(x-imgCheckpoint.getWidth()/2, y-imgCheckpoint.getHeight()/2), imgCheckpoint.getWidth(), imgCheckpoint.getHeight()));
                stage.addActor(checkpoints.get(checkpoints.size()-1)); // Ajout du nouveau checkpoint dans le stage
            }
        });
        
        stage.addListener(new InputListener() {
            @Override
            public boolean scrolled(InputEvent event, float x, float y, int amount) {
                if(event.getScrollAmount() > 0) { // Zoom
                    checkpoints.get(checkpoints.size()-1).setHauteur(checkpoints.get(checkpoints.size()-1).getHauteur()*1.1f);
                    checkpoints.get(checkpoints.size()-1).setLargeur(checkpoints.get(checkpoints.size()-1).getLargeur()*1.1f);
                } else { // Dézoom
                    checkpoints.get(checkpoints.size()-1).setHauteur(checkpoints.get(checkpoints.size()-1).getHauteur()*0.9f);
                    checkpoints.get(checkpoints.size()-1).setLargeur(checkpoints.get(checkpoints.size()-1).getLargeur()*0.9f);                   
                }
                return true;
            }
        });
        
        save.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                new SaveHandler().save(checkpoints);
                Gdx.app.log("GenererPartieTouchMeScreen", "Il faut afficher qu'on a bien sauvegardé au lieu d'avoir ce msg dégueu.");
            }
        });
        
        jouerCurrent.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                SaveHandler sh = new SaveHandler();
                sh.save(checkpoints);
                String path = sh.getNameLastSave();
                dispose();
                game.setScreen(new GameScreen(new TouchMe(path)));
            }
        });
        
        nouvelEnregistrement.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                checkpoints.clear();
            }
        });
 
        quitter.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                dispose();
                game.setScreen(new MenuTouchMeScreen(game));
            }
        });
    }
    
    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0, 0);
        Gdx.gl.glClear( GL20.GL_COLOR_BUFFER_BIT | GL20.GL_DEPTH_BUFFER_BIT );
        batch.begin();
        stage.act(Gdx.graphics.getDeltaTime());
        stage.draw();
        batch.end();
    }
    
    @Override
    public void resize(int width, int height) {
        stage.getViewport().update(width, height, true);
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
        imgCheckpoint.dispose();
        batch.dispose();
    }
    
}
