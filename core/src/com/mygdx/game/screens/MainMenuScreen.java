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

public class MainMenuScreen implements Screen {

    private final ICGame game;  
    private final Stage stage;
    private Skin skin;
    
    /**
     * Ce constructeur initialise les boutons avec leur images
     * @param game represente le jeu pour agir dessus (le lancer)
     */
    
    public MainMenuScreen (ICGame game){
        this.game = game;        
        stage = new Stage();
        Gdx.input.setInputProcessor(stage); // Le stage va s'occuper des E/S
        create(); // Création du skin de base, puis ajout ci-dessous
        createButtons();
    }
    
    private void createButtons() {
        TextButton btnInfos = new TextButton("Informations", skin);
        btnInfos.setPosition((Gdx.graphics.getWidth()/2)-(btnInfos.getWidth()/2), (Gdx.graphics.getHeight()/2)-(btnInfos.getHeight()/2));
        btnInfos.addListener(new ClickListener() {
                @Override
                public void clicked(InputEvent event, float x, float y) {
                    dispose();
                    game.setScreen(new InfosScreen(game));
                };
        });   
        
        TextButton btnChoixPartie = new TextButton("Nouvelle partie", skin);
        btnChoixPartie.setPosition((Gdx.graphics.getWidth()/2)-(btnChoixPartie.getWidth()/2), (Gdx.graphics.getHeight()/2)-(btnChoixPartie.getHeight()/2) + btnInfos.getHeight() + 20);
        btnChoixPartie.addListener(new ClickListener() {
                @Override
                public void clicked(InputEvent event, float x, float y) {
                    dispose();
                    game.setScreen(new GameChooserScreen(game));
                };
        });
        
        TextButton btnQuitter = new TextButton("Quitter", skin);
        btnQuitter.setPosition((Gdx.graphics.getWidth()/2)-(btnQuitter.getWidth()/2), (Gdx.graphics.getHeight()/2)-(btnQuitter.getHeight()/2) - btnInfos.getHeight() - 20);
        btnQuitter.addListener(new ClickListener() {
                @Override
                public void clicked(InputEvent event, float x, float y) {
                    dispose();
                    Gdx.app.exit();
                };
        });  
        
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
