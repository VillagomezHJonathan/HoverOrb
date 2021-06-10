package com.jugoapps.hoverorb;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

public class EndStage extends Stage {
    private boolean visible = false;

    public EndStage(Texture homeBtnTexture,
                    final StageInterface stageInterface) {

        Table table = new Table();
        table.setFillParent(true);
        table.top();

        Image homeBtn = new Image(homeBtnTexture);
        homeBtn.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                setVisible(false);
                stageInterface.goToHome();
            }
        });

        table.add(homeBtn);

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
