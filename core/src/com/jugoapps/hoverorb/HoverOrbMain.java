package com.jugoapps.hoverorb;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.actions.RotateByAction;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.ScreenViewport;

import java.util.Random;

public class HoverOrbMain extends ApplicationAdapter implements StageInterface {

	ScreenViewport screenViewport;
	OrthographicCamera camera;

	StartStage startStage;

	GameStage gameStage;
	Actor ball;
	float screenHeight;
	float screenWidth;
	float ballY;
	float ballX;
	float gravity;
	float velocityY;
	float velocityX;
	int maxVelocityX;
	float forceY;

	SettingsStage settingsStage;

	ThemesStage themesStage;

	Texture playBtnTexture;
	Texture settingsBtnTexture;
	Texture homeBtnTexture;
	Texture themesBtnTexture;
	Texture ballTexture;

	@Override
	public void create () {

		screenWidth = Gdx.graphics.getWidth();
		screenHeight = Gdx.graphics.getHeight();

		screenViewport = new ScreenViewport();
		camera = new OrthographicCamera(screenWidth, screenHeight);
		camera.setToOrtho(false, screenWidth, screenHeight);

		playBtnTexture = new Texture("play_button.png");
		settingsBtnTexture = new Texture("settings_button.png");
		homeBtnTexture = new Texture("home_button.png");
		themesBtnTexture = new Texture("themes_button.png");
		ballTexture = new Texture("ball.png");

		startStage = new StartStage(playBtnTexture, settingsBtnTexture,
				themesBtnTexture, this);

		gameStage = new GameStage(ballTexture, this);
		ball = gameStage.getActors().get(0);

		settingsStage = new SettingsStage(homeBtnTexture, this);

		themesStage = new ThemesStage(homeBtnTexture, this);

		/* Change this input processor to startStage and the booleans in
		* GameStage.java and StartStage.java when done the gameStage */
		Gdx.input.setInputProcessor(gameStage);

		gravity = 5;
		maxVelocityX = 8;
		forceY = 100;

	}

	@Override
	public void render () {

		ScreenUtils.clear(1, 0, 0, 1);

		startStage.draw();

		gameStage.draw();
		velocityY += gravity;
		ballY -= velocityY;
		ballX += velocityX;
		ball.setPosition(ballX, ballY);
		if (ball.getY() <= 0) {
			ballY = 0;
			velocityY = 0;
			velocityX = 0;
		} else if (ball.getY() + ball.getHeight() > screenHeight) {
			velocityY *= -1;
		} else if (ball.getX() + ball.getWidth() > screenWidth) {
			velocityX *= -1;
			ballX -= 1;
		} else if (ball.getX() <= 0) {
			velocityX *= -1;
			ballX += 1;
		}
		ball.addListener(new ClickListener() {
			@Override
			public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
				velocityY = -forceY;

				Random random = new Random();
				float randomDirection = random.nextInt(2) +1;
				velocityX = (float) random.nextInt(maxVelocityX) +1;

				if (randomDirection == 1) {
					velocityX *= -2;
				} else if (randomDirection == 2) {
					velocityX *= 2;
				}
				return super.touchDown(event, x, y, pointer, button);
			}
		});
		RotateByAction rotate = new RotateByAction();
		rotate.setAmount(-4f);
		if (ballY > 0){
			ball.addAction(rotate);
		} else {
			ball.removeAction(rotate);
		}


		settingsStage.draw();

		themesStage.draw();
	}

	@Override
	public void goToGameSetup() {
		gameStage.setVisible(true);
		gameStage.setViewport(screenViewport);
		gameStage.getBatch().setProjectionMatrix(camera.combined);
		Gdx.input.setInputProcessor(gameStage);
	}

	@Override
	public void goToHome() {
		startStage.setVisible(true);
		startStage.setViewport(screenViewport);
		startStage.getBatch().setProjectionMatrix(camera.combined);
		Gdx.input.setInputProcessor(startStage);
	}

	@Override
	public void goToSettings() {
		settingsStage.setVisible(true);
		settingsStage.setViewport(screenViewport);
		settingsStage.getBatch().setProjectionMatrix(camera.combined);
		Gdx.input.setInputProcessor(settingsStage);
	}

	@Override
	public void goToThemes() {
		themesStage.setVisible(true);
		themesStage.setViewport(screenViewport);
		themesStage.getBatch().setProjectionMatrix(camera.combined);
		Gdx.input.setInputProcessor(themesStage);
	}

	@Override
	public void dispose () {
		startStage.dispose();
		gameStage.dispose();
		settingsStage.dispose();
		themesStage.dispose();
		playBtnTexture.dispose();
		settingsBtnTexture.dispose();
		themesBtnTexture.dispose();
		ballTexture.dispose();
	}

}
