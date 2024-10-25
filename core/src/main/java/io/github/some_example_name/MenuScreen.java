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

        menu1Images = new Texture[2]; // Assuming you want to load 3 images
        menu1Images[0] = new Texture("img2.png"); // Replace with your actual image file names
        menu1Images[1] = new Texture("img_2.png");

    }

    @Override
    public void render(float delta) {
        // Clear the screen
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        // Play button size and position
        float playButtonX = 500;
        float playButtonY = 50;
        float playButtonWidth = 500;
        float playButtonHeight = 500;

        // Draw the menu background and buttons
        batch.begin();
        batch.draw(menuBackground, 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());

        // Draw the play button
        batch.draw(menu1Images[0], playButtonX, playButtonY, playButtonWidth, playButtonHeight);

        // Optionally, draw other menu images here if you want
        float otherButtonX = 520;
        float otherButtonY = 380;
        float otherButtonWidth = 520;
        float otherButtonHeight = 520;
        batch.draw(menu1Images[1], otherButtonX, otherButtonY, otherButtonWidth, otherButtonHeight);

        batch.end();

        // Check if the play button is clicked
        if (Gdx.input.justTouched()) {
            float touchX = Gdx.input.getX();
            float touchY = Gdx.graphics.getHeight() - Gdx.input.getY();

            // Check if the touch is within the play button area
            if (touchX >= playButtonX && touchX <= playButtonX + playButtonWidth &&
                touchY >= playButtonY && touchY <= playButtonY + playButtonHeight) {

                // Add a small delay to prevent immediate screen switching
                Timer.schedule(new Task() {
                    @Override
                    public void run() {
                        main.setScreen(new GamingScreen(main, batch));
                    }
                }, 0.1f);}}}



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
