package io.github.some_example_name;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Level1Screen implements Screen {
    private final SpriteBatch batch;
    private final Texture level1Background;

    public Level1Screen(SpriteBatch batch) {
        this.batch = batch;
        this.level1Background = new Texture("level1.jpg"); // Replace with your actual level 1 background image
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        batch.begin();
        batch.draw(level1Background, 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        batch.end();


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
        level1Background.dispose();
    }
}
