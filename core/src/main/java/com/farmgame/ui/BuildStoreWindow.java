package com.farmgame.ui;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;

public class BuildStoreWindow extends Window {
    public interface BuildChoiceListener {
        void onChoosePlot();
        void onChoosePen();
    }

    private final BuildChoiceListener listener;

    public BuildStoreWindow(String title, Skin skin, BuildChoiceListener listener) {
        super(title, skin);
        this.listener = listener;
        this.setModal(true);
        this.setMovable(true);
        this.setKeepWithinStage(true);
        this.setSize(300, 200);

        TextButton plotButton = new TextButton("Dodaj pole", skin);
        TextButton penButton = new TextButton("Dodaj zagrodę", skin);
        TextButton closeButton = new TextButton("Zamknij", skin);

        plotButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                if (listener != null) listener.onChoosePlot();
                remove();
            }
        });

        penButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                if (listener != null) listener.onChoosePen();
                remove();
            }
        });

        closeButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                remove();
            }
        });

        Table table = new Table();
        table.add(plotButton).pad(10).row();
        table.add(penButton).pad(10).row();
        table.add(closeButton).pad(10);
        this.add(table).expand().fill();
    }
}
