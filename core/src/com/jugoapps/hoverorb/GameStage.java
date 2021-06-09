package com.jugoapps.hoverorb;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Stage;


public class GameStage extends Stage {
    private boolean visible = true;

    public GameStage(Texture ballTexture,
                     final StageInterface stageInterface) {

        BallActor ball = new BallActor(ballTexture);

        addActor(ball);
    }

    @Override
    public void draw() {
        if (visible) {
            act(Gdx.graphics.getDeltaTime());
            super.draw();
        }
    }

    public void setVisible(boolean visible) {

        this.visible = visible;
    }
}