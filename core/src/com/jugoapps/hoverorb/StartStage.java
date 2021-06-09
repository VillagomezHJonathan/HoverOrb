package com.jugoapps.hoverorb;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;


public class StartStage extends Stage {
    private boolean visible = false;

    public StartStage(Texture playBtnTexture,
                      Texture settingsBtnTexture,
                      Texture themesBtnTexture,
                      final StageInterface stageInterface) {

        Table table = new Table();
        table.setFillParent(true);
        table.center();

        Image playBtn = new Image(playBtnTexture);
        playBtn.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                setVisible(false);
                stageInterface.goToGameSetup();
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

        Image themesBtn = new Image(themesBtnTexture);
        themesBtn.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                setVisible(false);
                stageInterface.goToThemes();
            }
        });

        table.add(playBtn);
        table.add(settingsBtn);
        table.add(themesBtn);

        addActor(table);
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
