package com.jugoapps.hoverorb;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
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
        setPosition(ballStartPosX, 0f);
        setBounds(getX(), getY(), getWidth(), getHeight());

    }

}