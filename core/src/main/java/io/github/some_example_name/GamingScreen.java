package io.github.some_example_name;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class GamingScreen implements Screen {

    private final SpriteBatch batch;
    private final Texture gameBackground;
    private final Texture game1Background;
    private final Texture game2Background;
    private final Texture game3Background;
    private final Texture game4Background;
    private final Main main;



    public GamingScreen(Main main , SpriteBatch batch) {
        this.main = main;
        this.batch = batch;
        this.gameBackground = new Texture("img_3.png");
        this.game1Background = new Texture("1.png");
        this.game2Background = new Texture("2.png");
        this.game3Background = new Texture("3.png");
        this.game4Background  = new Texture("4.png");

    }

    @Override
    public void render(float delta) {

        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        float screenWidth = Gdx.graphics.getWidth();
        float screenHeight = Gdx.graphics.getHeight();
        float imageY = screenHeight / 2;
        float buttonWidth = screenWidth / 5f;
        float buttonHeight = screenHeight / 3f;
        float spacing = 10;
        float image1X = spacing;
        float image2X = image1X + buttonWidth + spacing;
        float image3X = image2X + buttonWidth + spacing;
        float image4X = image3X + buttonWidth + spacing;

        batch.begin();
        batch.draw(gameBackground, 0, 0, screenWidth, screenHeight);
        batch.draw(game1Background, image1X, imageY - buttonHeight / 2, buttonWidth, buttonHeight);
        batch.draw(game2Background, image2X, imageY - buttonHeight / 2, buttonWidth, buttonHeight);
        batch.draw(game3Background, image3X, imageY - buttonHeight / 2, buttonWidth, buttonHeight);
        batch.draw(game4Background, image4X, imageY - buttonHeight / 2, buttonWidth, buttonHeight);
        batch.end();

        if (Gdx.input.justTouched()) {
            float touchX = Gdx.input.getX();
            float touchY = Gdx.graphics.getHeight() - Gdx.input.getY();


            if (touchX >= image1X && touchX <= image1X + buttonWidth &&
                touchY >= imageY - buttonHeight / 2 && touchY <= imageY + buttonHeight / 2) {
                main.setScreen(new Level1Screen(main, batch));
            }


            if (touchX >= image2X && touchX <= image2X + buttonWidth &&
                touchY >= imageY - buttonHeight / 2 && touchY <= imageY + buttonHeight / 2) {
                main.setScreen(new Level2Screen(main,batch));
            }


            if (touchX >= image3X && touchX <= image3X + buttonWidth &&
                touchY >= imageY - buttonHeight / 2 && touchY <= imageY + buttonHeight / 2) {
                main.setScreen(new Level3Screen(main, batch));
            }


            if (touchX >= image4X && touchX <= image4X + buttonWidth &&
                touchY >= imageY - buttonHeight / 2 && touchY <= imageY + buttonHeight / 2) {
                main.setScreen(new MenuScreen(batch, new Texture("img_1.png"), main));
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
        gameBackground.dispose();
        game1Background.dispose();
        game2Background.dispose();
        game3Background.dispose();
        game4Background.dispose();
    }
}
