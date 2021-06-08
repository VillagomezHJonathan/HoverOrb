package com.jugoapps.hoverorb;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.utils.ScreenUtils;

public class HoverOrbMain extends ApplicationAdapter implements StageInterface {
	StartStage startStage;
	GameStage gameStage;
	Texture img;
	Texture ball;
	
	@Override
	public void create () {
		img = new Texture("play_button.png");
		ball = new Texture("ball.png");

		startStage = new StartStage(img, this);
		gameStage = new GameStage(ball, this);
		Gdx.input.setInputProcessor(startStage);
	}

	@Override
	public void render () {
		ScreenUtils.clear(1, 0, 0, 1);

		startStage.draw();
		gameStage.draw();
	}

	@Override
	public void startGame() {
		gameStage.setVisible(true);
		Gdx.input.setInputProcessor(gameStage);
	}

	@Override
	public void returnToStart() {
		startStage.setVisible(true);
		Gdx.input.setInputProcessor(startStage);
	}

	@Override
	public void dispose () {
		startStage.dispose();
		gameStage.dispose();
		img.dispose();
		ball.dispose();
	}

}
