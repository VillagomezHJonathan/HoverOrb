package com.jugoapps.hoverorb;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;


public class StartStage extends Stage {
    private boolean visible = true;

    public StartStage(Texture playBtnTexture,
                      Texture settingsBtnTexture,
                      final StageInterface stageInterface) {

        Table table = new Table();
        table.setFillParent(true);
        table.center();

        Image playBtn = new Image(playBtnTexture);
        playBtn.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                setVisible(false);
                stageInterface.startGame();
            }
        });

        Image settingsBtn = new Image(settingsBtnTexture);
        settingsBtn.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                setVisible(false);
                stageInterface.goToSettings();
            }
        });

        table.add(playBtn);
        table.add(settingsBtn);

        addActor(table);
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