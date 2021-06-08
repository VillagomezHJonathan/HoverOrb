package com.jugoapps.hoverorb;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.utils.ScreenUtils;

public class HoverOrbMain extends ApplicationAdapter implements StageInterface {

	StartStage startStage;
	GameStage gameStage;
	SettingsStage settingsStage;
	ThemesStage themesStage;

	Texture playBtn;
	Texture settingsBtn;
	Texture homeBtn;
	Texture themesBtn;
	Texture ball;

	@Override
	public void create () {

		playBtn = new Texture("play_button.png");
		settingsBtn = new Texture("settings_button.png");
		homeBtn = new Texture("home_button.png");
		themesBtn = new Texture("themes_button.png");
		ball = new Texture("ball.png");

		startStage = new StartStage(playBtn, settingsBtn, themesBtn, this);
		gameStage = new GameStage(ball, this);
		settingsStage = new SettingsStage(homeBtn, this);
		themesStage = new ThemesStage(homeBtn, this);

		/* Change this input processor to startStage and the booleans in
		* GameStage.java and StartStage.java when done the gameStage */
		Gdx.input.setInputProcessor(gameStage);
	}

	@Override
	public void render () {

		ScreenUtils.clear(1, 0, 0, 1);

		startStage.draw();

		gameStage.draw();

		settingsStage.draw();

		themesStage.draw();
	}

	@Override
	public void goToGameSetup() {
		gameStage.setVisible(true);
		Gdx.input.setInputProcessor(gameStage);
	}

	@Override
	public void goToHome() {
		startStage.setVisible(true);
		Gdx.input.setInputProcessor(startStage);
	}

	@Override
	public void goToSettings() {
		settingsStage.setVisible(true);
		Gdx.input.setInputProcessor(settingsStage);
	}

	@Override
	public void goToThemes() {
		themesStage.setVisible(true);
		Gdx.input.setInputProcessor(themesStage);
	}

	@Override
	public void dispose () {
		startStage.dispose();
		gameStage.dispose();
		settingsStage.dispose();
		themesStage.dispose();
		playBtn.dispose();
		settingsBtn.dispose();
		themesBtn.dispose();
		ball.dispose();
	}

}
