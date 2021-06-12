package com.jugoapps.hoverorb;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
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
	Actor pauseBtn;
	BitmapFont liveScoreFont;
	int scoreFontSize;
	int gameState;
	int currentScore;
	int maxVelocityX;
	float screenHeight;
	float screenWidth;
	float ballY;
	float ballX;
	float ballStartingPosX;
	float gravity;
	float velocityY;
	float velocityX;
	float randomDirectionX;
	float forceY;

	SettingsStage settingsStage;

	ThemesStage themesStage;

	EndStage endStage;

	Texture playBtnTexture;
	Texture settingsBtnTexture;
	Texture homeBtnTexture;
	Texture themesBtnTexture;
	Texture pauseBtnTexture;
	Texture resetBtnTexture;
	Texture applyBtnTexture;
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
		resetBtnTexture = new Texture("reset_button.png");
		applyBtnTexture = new Texture("apply_button.png");
		ballTexture = new Texture("ball.png");

		startStage = new StartStage(playBtnTexture, settingsBtnTexture,
				themesBtnTexture, this);

		gameStage = new GameStage(ballTexture, pauseBtnTexture, this);
		ball = gameStage.getRoot().findActor("ball");
		pauseBtn = gameStage.getRoot().findActor("pauseBtn");
		ballY = ball.getY();
		ballX = ball.getX();
		ballStartingPosX = screenWidth / 2f - ball.getWidth() / 2f;
		gravity = 5f;
		maxVelocityX = 8;
		forceY = 100f;

		settingsStage = new SettingsStage(homeBtnTexture, this);

		themesStage = new ThemesStage(applyBtnTexture, this);

		endStage = new EndStage(homeBtnTexture, resetBtnTexture, this);

		/* Change this input processor and viewport to startStage and the booleans in
		* GameStage.java and StartStage.java when done the gameStage */
		Gdx.input.setInputProcessor(gameStage);

		liveScoreFont = new BitmapFont();
		scoreFontSize = 200;
		FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal("font.ttf"));
		FreeTypeFontGenerator.FreeTypeFontParameter parameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
		parameter.size = scoreFontSize;
		liveScoreFont = generator.generateFont(parameter);
		generator.dispose();

		gameState = 0;
		ball.addListener(new ClickListener() {
			@Override
			public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
				if (gameState == 1) {
					currentScore++;
				}

				gameState = 1;
				pauseBtn.remove();

				velocityY = -forceY;

				Random random = new Random();
				randomDirectionX = random.nextInt(2) + 1;
				velocityX = (float) random.nextInt(maxVelocityX) + 1;

				if (randomDirectionX == 1) {
					velocityX *= -2f;
				} else if (randomDirectionX == 2) {
					velocityX *= 2f;
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
		gameStage.getBatch().begin();
		if (gameState == 1) {
			liveScoreFont.draw(gameStage.getBatch(), String.valueOf(currentScore), 30f, screenHeight - 30f);
		}
		gameStage.getBatch().end();
		ballMovement();
		ballGameState();
		ballBoundaries();
		ballAnimation();

		settingsStage.draw();

		themesStage.draw();

		endStage.draw();
	}

	public void ballBoundaries() {
		if (ball.getY() + ball.getHeight() > screenHeight) {
			velocityY *= -1f;
		} else if (ball.getX() + ball.getWidth() > screenWidth) {
			velocityX *= -1f;
			ballX -= 1f;
		} else if (ball.getX() <= 0f) {
			velocityX *= -1f;
			ballX += 1f;
		}
	}

	public void ballMovement() {
		velocityY += gravity;
		ballY -= velocityY;
		ballX += velocityX;
		ball.setPosition(ballX, ballY);
	}

	public void ballGameState() {
		if (gameState == 0) {
			gameStage.addActor(pauseBtn);
			currentScore = 0;
			ballY = 0f;
			ballX = ballStartingPosX;
			ball.setRotation(0f);
			velocityY = 0f;
			velocityX = 0f;
		} else if (gameState == 1 && ball.getY() <= 0f) {
			gameStage.setVisible(false);
			goToEnd();
			gameState = 0;
		}
	}

	public void ballAnimation() {
		RotateByAction rotateLeft = new RotateByAction();
		rotateLeft.setAmount(4f);
		RotateByAction rotateRight = new RotateByAction();
		rotateRight.setAmount(-4f);
		if (ball.getY() > 0f && randomDirectionX == 1) {
			ball.addAction(rotateLeft);
			if (ball.getActions().contains(rotateRight, true)) {
				ball.removeAction(rotateRight);
			}
		} else if (ball.getY() > 0f && randomDirectionX == 2) {
			ball.addAction(rotateRight);
			if (ball.getActions().contains(rotateLeft, true)) {
				ball.removeAction(rotateLeft);
			}
		}
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
