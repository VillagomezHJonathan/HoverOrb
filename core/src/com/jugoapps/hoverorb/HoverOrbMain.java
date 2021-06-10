package com.jugoapps.hoverorb;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.actions.RotateByAction;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.ScreenUtils;

import java.util.Random;

public class HoverOrbMain extends ApplicationAdapter implements StageInterface {

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
	float randomDirectionX;
	float forceY;
	int maxVelocityX;

	SettingsStage settingsStage;

	ThemesStage themesStage;

	EndStage endStage;

	Texture playBtnTexture;
	Texture settingsBtnTexture;
	Texture homeBtnTexture;
	Texture themesBtnTexture;
	Texture pauseBtnTexture;
	Texture ballTexture;

	@Override
	public void create () {
		screenWidth = Gdx.graphics.getWidth();
		screenHeight = Gdx.graphics.getHeight();

		playBtnTexture = new Texture("play_button.png");
		settingsBtnTexture = new Texture("settings_button.png");
		homeBtnTexture = new Texture("home_button.png");
		themesBtnTexture = new Texture("themes_button.png");
		pauseBtnTexture = new Texture("pause_button.png");
		ballTexture = new Texture("ball.png");

		startStage = new StartStage(playBtnTexture, settingsBtnTexture,
				themesBtnTexture, this);

		gameStage = new GameStage(ballTexture, pauseBtnTexture, this);
		ball = gameStage.getRoot().findActor("ball");
		ballY = ball.getY();
		ballX = ball.getX();
		gravity = 5;
		maxVelocityX = 8;
		forceY = 100;

		settingsStage = new SettingsStage(homeBtnTexture, this);

		themesStage = new ThemesStage(homeBtnTexture, this);

		endStage = new EndStage(homeBtnTexture, this);

		/* Change this input processor and viewport to startStage and the booleans in
		* GameStage.java and StartStage.java when done the gameStage */
		Gdx.input.setInputProcessor(gameStage);

		ball.addListener(new ClickListener() {
			@Override
			public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
				velocityY = -forceY;

				Random random = new Random();
				randomDirectionX = random.nextInt(2) +1;
				velocityX = (float) random.nextInt(maxVelocityX) +1;

				if (randomDirectionX == 1) {
					velocityX *= -2;
				} else if (randomDirectionX == 2) {
					velocityX *= 2;
				}
				return true;
			}
		});

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
		RotateByAction rotateLeft = new RotateByAction();
		rotateLeft.setAmount(4f);
		RotateByAction rotateRight = new RotateByAction();
		rotateRight.setAmount(-4f);
		if (ball.getY() > 0 && randomDirectionX == 1) {
			ball.addAction(rotateLeft);
			if (ball.getActions().contains(rotateRight, true)) {
				ball.removeAction(rotateRight);
			}
		} else if (ball.getY() > 0 && randomDirectionX == 2) {
			ball.addAction(rotateRight);
			if (ball.getActions().contains(rotateLeft, true)) {
				ball.removeAction(rotateLeft);
			}
		}

		settingsStage.draw();

		themesStage.draw();

		endStage.draw();
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
	public void goToEnd() {
		endStage.setVisible(true);
		Gdx.input.setInputProcessor(endStage);
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
