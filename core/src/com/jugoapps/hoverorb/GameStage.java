package com.jugoapps.hoverorb;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;


public class GameStage extends Stage {
    private boolean visible = true;

    public GameStage(Texture ballTexture,
                     final StageInterface stageInterface) {

        int halfWidth = Gdx.graphics.getWidth() / 2;

        final Image ball = new Image(ballTexture);
        ball.setSize(250, 250);
        ball.setX(halfWidth - (ball.getWidth() / 2));
        ball.addListener(new ClickListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                setVisible(false);
                stageInterface.goToHome();
                return super.touchDown(event, x, y, pointer, button);
            }
        });

        addActor(ball);
    }

    @Override
    public void draw() {
        act(Gdx.graphics.getDeltaTime());
        if (visible) {
            super.draw();
        }
    }

    public void setVisible(boolean visible) {
        this.visible = visible;
    }
}
