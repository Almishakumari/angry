package io.github.some_example_name;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.viewport.ScreenViewport;

public class WinScreen implements Screen {
    private final Main main;
    private final SpriteBatch batch;
    private final Texture backgroundTexture;
    private final Stage stage;
    private final Skin skin;

    public WinScreen(Main main, SpriteBatch batch) {
        this.main = main;
        this.batch = batch;

        this.backgroundTexture = new Texture("you win.png"); // Your win screen background
        this.stage = new Stage(new ScreenViewport(), batch);
        Gdx.input.setInputProcessor(stage);
        this.skin = new Skin(Gdx.files.internal("uiskin.json"));
        createUI();
    }

    private void createUI() {
        Table table = new Table();
        table.setFillParent(true);
        table.bottom();
        TextButton unlockButton = new TextButton("New Level Unlocked", skin);
        unlockButton.setTransform(true);
        unlockButton.setScale(1.5f);
        System.out.println("New Level Unlocked");
        unlockButton.addListener(new ClickListener() {
            @Override
            public void clicked(com.badlogic.gdx.scenes.scene2d.InputEvent event, float x, float y) {
                main.setScreen(new GamingScreen(main, batch));
            }
        });


        TextButton mainMenuButton = new TextButton("Main Menu", skin);
        mainMenuButton.setTransform(true);
        mainMenuButton.setScale(1.5f);
        mainMenuButton.addListener(new ClickListener() {
            @Override
            public void clicked(com.badlogic.gdx.scenes.scene2d.InputEvent event, float x, float y) {
                main.setScreen(new GamingScreen(main, batch));
            }
        });
        TextButton retryButton = new TextButton("Retry Level", skin);
        retryButton.setTransform(true);
        retryButton.setScale(1.5f);
        retryButton.addListener(new ClickListener() {
            @Override
            public void clicked(com.badlogic.gdx.scenes.scene2d.InputEvent event, float x, float y) {
                main.setScreen(new Level1Screen(main, batch));
            }
        });

        table.add(unlockButton).padBottom(40).row();
        table.add(mainMenuButton).padBottom(40).row();
        table.add(retryButton);

        stage.addActor(table);
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        batch.begin();
        batch.draw(backgroundTexture, 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        batch.end();

        stage.act(delta);
        stage.draw();
    }

    @Override
    public void resize(int width, int height) {
        stage.getViewport().update(width, height, true);
    }

    @Override
    public void show() {}

    @Override
    public void pause() {}

    @Override
    public void resume() {}

    @Override
    public void hide() {}

    @Override
    public void dispose() {
        backgroundTexture.dispose();
        stage.dispose();
        skin.dispose();
    }
}
