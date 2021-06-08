package com.jugoapps.hoverorb;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.actions.MoveByAction;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;

public class BallActor extends Image {

    float screenCenterX = Gdx.graphics.getWidth();
    float ballStartPosX;

    @Override
    public void draw(Batch batch, float parentAlpha) {
        ((TextureRegionDrawable)getDrawable()).draw(batch, getX(), getY(), getOriginX(),
                getOriginY(), getWidth(), getHeight(), getScaleX(), getScaleY(),
                getRotation());
    }

    public BallActor(Texture ball) {
        super(ball);
        setSize(250, 250);
        ballStartPosX = screenCenterX / 2 - getWidth() / 2;
        setPosition(ballStartPosX, 0);
        setBounds(getX(), getY(), getWidth(), getHeight());

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

}