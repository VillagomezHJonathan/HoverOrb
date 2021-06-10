package com.jugoapps.hoverorb;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;


public class GameStage extends Stage {
    private boolean visible = true;

    float pausePosX;
    float pausePosY;

    public GameStage(Texture ballTexture,
                     Texture pauseBtnTexture,
                     final StageInterface stageInterface) {

        BallActor ball = new BallActor(ballTexture);
        ball.setName("ball");

        Image pauseBtn = new Image(pauseBtnTexture);
        pauseBtn.setSize(125, 125);
        pausePosX = getWidth() - pauseBtn.getWidth();
        pausePosY = getHeight() - pauseBtn.getHeight();
        pauseBtn.setPosition(pausePosX - 10, pausePosY - 10);
        pauseBtn.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                setVisible(false);
                stageInterface.goToEnd();
            }
        });

        addActor(pauseBtn);
        addActor(ball);
    }

    @Override
    public void draw() {
        if (visible) {
            getBatch().setProjectionMatrix(getCamera().combined);
            act(Gdx.graphics.getDeltaTime());
            super.draw();
        }
    }

    public void setVisible(boolean visible) {

        this.visible = visible;
    }
}