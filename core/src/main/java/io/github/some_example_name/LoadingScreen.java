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
    private float loadingTime; // Track the loading time

    public LoadingScreen(SpriteBatch batch, Texture loadingImage, Main main) {
        this.batch = batch;
        this.loadingImage = loadingImage;
        this.main = main;
        this.loadingTime = 0; // Initialize loading time
    }

    @Override
    public void render(float delta) {
        // Clear the screen
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        // Draw loading image
        batch.begin();
        batch.draw(loadingImage, 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        batch.end();

        // Increment loading time
        loadingTime += delta;

        // After a delay, transition to the MenuScreen
        if (loadingTime > 2.0f) { // Simulate a loading time of 2 seconds
            main.setScreen(new MenuScreen(batch, new Texture("img_1.png"), main));
            //main.setScreen(new MenuScreen(batch, new Texture("img2.png"), main));


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
