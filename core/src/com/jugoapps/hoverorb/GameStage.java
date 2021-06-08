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

        public BallActor() {
            setBounds(ball.getX(), ball.getY(), ball.getWidth(), ball.getHeight());
            setTouchable(Touchable.enabled);

            addListener(new ClickListener() {
                @Override
                public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                    MoveByAction mba = new MoveByAction();
                    mba.setAmount(0f,100f);
                    mba.setDuration(5f);

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
