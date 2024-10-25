package io.github.some_example_name;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;

public class Main extends Game {
    private SpriteBatch batch;
    private Texture loadingImage;
    private Texture menuscreen;
    private  Texture play;


    @Override
    public void create() {
        batch = new SpriteBatch();
        loadingImage = new Texture("img.png");
        menuscreen = new Texture("img_1.png");
        play = new Texture("img2.png");

        setScreen(new LoadingScreen(batch, loadingImage, this));

    }

    @Override
    public void render() {

        super.render();
    }

    @Override
    public void dispose() {
        batch.dispose();
        loadingImage.dispose();
        menuscreen.dispose();
        play.dispose();


    }


}
