package io.github.some_example_name;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import java.util.ArrayList;

public class Level1Screen implements Screen {
    private final SpriteBatch batch;
    private final Texture level1Background;
    private final Texture birdTexture;
    private final Birds bird;
    private final Texture catapultTexture;
    private final Catapult catapult;
    private final Texture pigTexture;
    private final ArrayList<Pigs> pigs;
    private final Texture blockTexture;
    private final ArrayList<Blocks> blocks;
    private final float birdWidth;
    private final float birdHeight;
    private final float catapultWidth;
    private final float catapultHeight;
    private final Texture pauseButtonTexture;
    private final float pauseButtonSize = 80f;
    private final PauseDialog pauseDialog;
    private final Main main;
    private final Texture winButtonTexture;
    private final float winButtonSize = 100f;
    private final Texture loseButtonTexture;
    private final float loseButtonSize = 100f;

    public Level1Screen(Main main,SpriteBatch batch) {
        this.batch = batch;

        this.level1Background = new Texture("level1.jpg");
        this.birdTexture = new Texture("bird.png");
        this.bird = new Birds("Sparrow", 10, 15, 5, 100);
        this.catapultTexture = new Texture("catapult.png");
        this.catapult = new Catapult(250, 200, 150, 100);
        this.blockTexture = new Texture("block.png");
        this.pigTexture = new Texture("pig.png");
        this.birdWidth = 160f;
        this.birdHeight = 160f;
        this.catapultWidth = 150f;
        this.catapultHeight = 160f;
        pauseButtonTexture = new Texture("pause.png");
        this.main = main;
        this.winButtonTexture = new Texture("win.png");
        this.loseButtonTexture = new Texture("lose.png");
        pauseDialog = new PauseDialog(main, batch);

        blocks = new ArrayList<>();
        blocks.add(new Blocks("Wood", 100, 1000, 200, 50, 100, 100));
        blocks.add(new Blocks("Wood", 100, 1000, 300, 50, 100, 100));
        blocks.add(new Blocks("Wood", 100, 1200, 200, 50, 100, 100));
        blocks.add(new Blocks("Wood", 100, 1200, 300, 50, 100, 100));
        blocks.add(new Blocks("Wood", 100, 1100, 300, 50,100, 100));

        pigs = new ArrayList<>();
        pigs.add(new Pigs("Green Pig", 100, 1110, 400, 10, 70, 70));
        pigs.add(new Pigs("Blue Pig", 100, 1110, 200, 10, 70, 70));


    }

    @Override
    public void render(float delta) {
        if (!pauseDialog.isPaused()) {
            Gdx.gl.glClearColor(0, 0, 0, 1);
            Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

            batch.begin();




            batch.draw(level1Background, 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
            batch.draw(catapultTexture, catapult.getPositionX(), catapult.getPositionY(), catapultWidth, catapultHeight);

            batch.draw(birdTexture, bird.getPosition(), 180, birdWidth, birdHeight);


            for (Blocks block : blocks) {
                batch.draw(blockTexture, block.getPositionX(), block.getPositionY(), block.getWidth(), block.getHeight());
            }


            for (Pigs pig : pigs) {
                batch.draw(pigTexture, pig.getPositionX(), pig.getPositionY(), pig.getWidth(), pig.getHeight());
            }


            float pauseButtonX = Gdx.graphics.getWidth() - pauseButtonSize - 20;
            float pauseButtonY = Gdx.graphics.getHeight() - pauseButtonSize - 20;
            batch.draw(pauseButtonTexture, pauseButtonX, pauseButtonY, pauseButtonSize, pauseButtonSize);


            float winButtonX = Gdx.graphics.getWidth() - winButtonSize - 20;
            float winButtonY = 20;
            batch.draw(winButtonTexture, winButtonX, winButtonY, winButtonSize, winButtonSize);


            float loseButtonX = winButtonX - loseButtonSize - 20;
            float loseButtonY = winButtonY;
            batch.draw(loseButtonTexture, loseButtonX, loseButtonY, loseButtonSize, loseButtonSize);


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

            // Win button input
            float winButtonX = Gdx.graphics.getWidth() - winButtonSize - 20;
            float winButtonY = 20;
            if (touchX >= winButtonX && touchX <= winButtonX + winButtonSize &&
                touchY >= winButtonY && touchY <= winButtonY + winButtonSize) {
                main.setScreen(new WinScreen(main, batch));
                return;
            }

            // Lose button input
            float loseButtonX = winButtonX - loseButtonSize - 20;
            float loseButtonY = winButtonY;
            if (touchX >= loseButtonX && touchX <= loseButtonX + loseButtonSize &&
                touchY >= loseButtonY && touchY <= loseButtonY + loseButtonSize) {
                main.setScreen(new LoseScreen(main, batch));
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
        level1Background.dispose();
        birdTexture.dispose();
        blockTexture.dispose();
        pigTexture.dispose();
        winButtonTexture.dispose();
        loseButtonTexture.dispose();
        catapultTexture.dispose();
    }
}
