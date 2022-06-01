package com.jugoapps.hoverorb;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.ScreenUtils;

public class HoverOrb extends Game {
	SpriteBatch batch;
	Texture ballTexture;

	
	@Override
	public void create () {
		batch = new SpriteBatch();
		ballTexture = new Texture("ball.png");

	}

	@Override
	public void render () {
		ScreenUtils.clear(1, 0, 0, 1);

	}
	
	@Override
	public void dispose () {
	}
}
