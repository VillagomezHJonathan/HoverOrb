package com.jugoapps.hoverorb;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.utils.ScreenUtils;

public class HoverOrbMain extends ApplicationAdapter implements StageInterface {
	StartStage startStage;
	GameStage gameStage;
	SettingsStage settingsStage;

	Texture playBtn;
	Texture settingsBtn;
	Texture homeBtn;
	Texture ball;
	
	@Override
	public void create () {
		playBtn = new Texture("play_button.png");
		settingsBtn = new Texture("settings_button.png");
		homeBtn = new Texture("home_button.png");
		ball = new Texture("ball.png");

		startStage = new StartStage(playBtn, settingsBtn, this);
		gameStage = new GameStage(ball, this);
		settingsStage = new SettingsStage(homeBtn, this);
		Gdx.input.setInputProcessor(startStage);
	}

	@Override
	public void render () {
		ScreenUtils.clear(1, 0, 0, 1);

		startStage.draw();
		gameStage.draw();
		settingsStage.draw();
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
	public void goToSettings() {
		settingsStage.setVisible(true);
		Gdx.input.setInputProcessor(settingsStage);
	}

	@Override
	public void dispose () {
		startStage.dispose();
		gameStage.dispose();
		playBtn.dispose();
		settingsBtn.dispose();
		ball.dispose();
	}

}
