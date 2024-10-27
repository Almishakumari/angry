package io.github.some_example_name;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Level3Screen implements Screen {
    private final SpriteBatch batch;
    private final Texture level3Background;
    private final Texture pauseButtonTexture;
    private final float pauseButtonSize = 80f;
    private final PauseDialog pauseDialog;
    private final Main main;

    public Level3Screen(Main main,  SpriteBatch batch) {
        this.batch = batch;
        pauseButtonTexture = new Texture("pause.png");
        this.main = main;
        pauseDialog = new PauseDialog(main, batch);
        this.level3Background = new Texture("level3.jpg");
    }

    @Override
    public void render(float delta) {
        if (!pauseDialog.isPaused()) {
            Gdx.gl.glClearColor(0, 0, 0, 1);
            Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

            batch.begin();
            batch.draw(level3Background, 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
            float pauseButtonX = Gdx.graphics.getWidth() - pauseButtonSize - 20;
            float pauseButtonY = Gdx.graphics.getHeight() - pauseButtonSize - 20;
            batch.draw(pauseButtonTexture, pauseButtonX, pauseButtonY, pauseButtonSize, pauseButtonSize);


            batch.end();

            handleInput();
        } else {
            pauseDialog.render();
        }
    }
    private void handleInput() {
        if (Gdx.input.justTouched()) {
            float touchX = Gdx.input.getX();
            float touchY = Gdx.graphics.getHeight() - Gdx.input.getY();

            float pauseButtonX = Gdx.graphics.getWidth() - pauseButtonSize - 20;
            float pauseButtonY = Gdx.graphics.getHeight() - pauseButtonSize - 20;

            if (touchX >= pauseButtonX && touchX <= pauseButtonX + pauseButtonSize &&
                touchY >= pauseButtonY && touchY <= pauseButtonY + pauseButtonSize) {
                pauseDialog.toggle();
                return;
            }

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
        level3Background.dispose();
    }
}
