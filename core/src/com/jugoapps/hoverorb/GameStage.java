package com.jugoapps.hoverorb;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.scenes.scene2d.actions.MoveByAction;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;



public class GameStage extends Stage {
    private boolean visible = true;

    public static class BallActor extends Actor {
        Sprite ball = new Sprite(new Texture("ball.png"));

        float screenCenterX = Gdx.graphics.getWidth();
        float ballStartPosX;

        public BallActor() {
            ball.setSize(200, 200);
            ballStartPosX = screenCenterX / 2 - ball.getWidth() / 2;
            ball.setPosition(ballStartPosX, 0);
            setBounds(ball.getX(), ball.getY(), ball.getWidth(), ball.getHeight());
            setTouchable(Touchable.enabled);

            addListener(new ClickListener() {
                @Override
                public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                    MoveByAction mba = new MoveByAction();
                    mba.setAmount(0f,1000f);
                    mba.setDuration(0.25f);

                    BallActor.this.addAction(mba);

                    return super.touchDown(event, x, y, pointer, button);
                }
            });
        }

        @Override
        protected void positionChanged() {
            ball.setPosition(getX(), getY());
        }

        @Override
        public void draw(Batch batch, float parentAlpha) {
            ball.draw(batch);
        }
    }

    public GameStage(Texture ballTexture,
                     final StageInterface stageInterface) {

        BallActor ball = new BallActor();
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