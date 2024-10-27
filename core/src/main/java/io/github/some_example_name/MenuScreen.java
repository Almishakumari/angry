package io.github.some_example_name;
import com.badlogic.gdx.utils.Timer;
import com.badlogic.gdx.utils.Timer.Task;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.Texture;

public class MenuScreen implements Screen {
    private final SpriteBatch batch;
    private final Texture menuBackground;
    private  Texture play;
    private Texture[] menu1Images;

    private final Main main;

    public MenuScreen(SpriteBatch batch, Texture menuBackground, Main main) {
        this.batch = batch;
        this.menuBackground = menuBackground;
        this.main = main;

        menu1Images = new Texture[3];
        menu1Images[0] = new Texture("img2.png");
        menu1Images[1] = new Texture("img_2.png");
        menu1Images[2] = new Texture("quit.png");

    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        float playButtonX = 500;
        float playButtonY = 10;
        float playButtonWidth = 600;
        float playButtonHeight = 600;

        batch.begin();
        batch.draw(menuBackground, 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        batch.draw(menu1Images[0], playButtonX, playButtonY, playButtonWidth, playButtonHeight);
        float otherButtonX = 520;
        float otherButtonY = 380;
        float otherButtonWidth = 520;
        float otherButtonHeight = 520;
        float quitButtonX = 1250;
        float quitButtonY = 600;
        float quitButtonWidth = 500;
        float quitButtonHeight = 500;

        batch.draw(menu1Images[1], otherButtonX, otherButtonY, otherButtonWidth, otherButtonHeight);
        batch.draw(menu1Images[2], quitButtonX, quitButtonY, quitButtonWidth, quitButtonHeight);

        batch.end();

        if (Gdx.input.justTouched()) {
            float touchX = Gdx.input.getX();
            float touchY = Gdx.graphics.getHeight() - Gdx.input.getY();

            if (touchX >= playButtonX && touchX <= playButtonX + playButtonWidth &&
                touchY >= playButtonY && touchY <= playButtonY + playButtonHeight) {

                Timer.schedule(new Task() {
                    @Override
                    public void run() {
                        main.setScreen(new GamingScreen(main, batch));
                    }
                },
                    0.1f);
            }
            if (touchX >= quitButtonX && touchX <= quitButtonX + quitButtonWidth &&
                touchY >= quitButtonY && touchY <= quitButtonY + quitButtonHeight) {

                Gdx.app.exit();
            }

        }}



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
        menuBackground.dispose();
        for (Texture texture : menu1Images) {
            texture.dispose();
        }
    }
}
