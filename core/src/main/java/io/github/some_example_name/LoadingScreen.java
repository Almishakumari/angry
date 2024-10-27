package io.github.some_example_name;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class LoadingScreen implements Screen {
    private final SpriteBatch batch;
    private final Texture loadingImage;
    private final Main main;
    private float loadingTime;

    public LoadingScreen(SpriteBatch batch, Texture loadingImage, Main main) {
        this.batch = batch;
        this.loadingImage = loadingImage;
        this.main = main;
        this.loadingTime = 0;
    }

    @Override
    public void render(float delta) {

        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        batch.begin();
        batch.draw(loadingImage, 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        batch.end();

        loadingTime += delta;

        if (loadingTime > 2.0f) {
            main.setScreen(new MenuScreen(batch, new Texture("img_1.png"), main));
        }
    }

    @Override
    public void resize(int width, int height) {}

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
        loadingImage.dispose();
    }
}
