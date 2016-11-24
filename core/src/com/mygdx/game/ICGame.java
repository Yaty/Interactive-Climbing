package com.mygdx.game;

import com.badlogic.gdx.Game;
import com.mygdx.game.gameword.touchme.TouchMe;
import com.mygdx.game.ichelpers.AssetLoader;
import com.mygdx.game.screens.GameScreen;
import com.mygdx.game.screens.MainMenuScreen;

public class ICGame extends Game {
	
	@Override
	public void create () {
            AssetLoader.load();
            setScreen(new MainMenuScreen(this));
            //setScreen(new GameScreen(new TouchMe()));
	}

	@Override
	public void dispose () {
            getScreen().dispose();
	}
}
