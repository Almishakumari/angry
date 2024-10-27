package io.github.some_example_name;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.ui.ImageTextButton;
import com.badlogic.gdx.scenes.scene2d.ui.ImageTextButton.ImageTextButtonStyle;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class PauseDialog {
    private final Stage stage;
    private final Skin skin;
    private final SpriteBatch batch;
    private boolean paused;
    private final Main main;
    private final float dialogWidth = 400;
    private final float dialogHeight = 300;

    public PauseDialog(Main main, SpriteBatch batch) {
        this.batch = batch;
        this.main = main;
        this.stage = new Stage();
        this.skin = new Skin(Gdx.files.internal("uiskin.json"));
        this.paused = false;
        createButtons();
    }

    private void createButtons() {

        Table table = new Table();
        table.setSize(dialogWidth, dialogHeight);
        table.setPosition((Gdx.graphics.getWidth() - table.getWidth()) / 2, (Gdx.graphics.getHeight() - table.getHeight()) / 2);
        stage.addActor(table);

        Texture resumeImage = new Texture(Gdx.files.internal("resume.png"));
        Texture restartImage = new Texture(Gdx.files.internal("restart.png"));
        Texture mainMenuImage = new Texture(Gdx.files.internal("main_menu.png"));


        ImageTextButtonStyle resumeButtonStyle = new ImageTextButtonStyle(skin.get(TextButton.TextButtonStyle.class));
        resumeButtonStyle.imageUp = new TextureRegionDrawable(new TextureRegion(resumeImage));

        ImageTextButtonStyle restartButtonStyle = new ImageTextButtonStyle(skin.get(TextButton.TextButtonStyle.class));
        restartButtonStyle.imageUp = new TextureRegionDrawable(new TextureRegion(restartImage));

        ImageTextButtonStyle mainMenuButtonStyle = new ImageTextButtonStyle(skin.get(TextButton.TextButtonStyle.class));
        mainMenuButtonStyle.imageUp = new TextureRegionDrawable(new TextureRegion(mainMenuImage));


        ImageTextButton resumeButton = new ImageTextButton("Resume", resumeButtonStyle);
        ImageTextButton restartButton = new ImageTextButton("Restart", restartButtonStyle);
        ImageTextButton mainMenuButton = new ImageTextButton("Main Menu", mainMenuButtonStyle);

        resumeButton.addListener(new ClickListener() {
            @Override
            public void clicked(com.badlogic.gdx.scenes.scene2d.InputEvent event, float x, float y) {
                paused = false;
                Gdx.input.setInputProcessor(null);
            }
        });

        restartButton.addListener(new ClickListener() {
            @Override
            public void clicked(com.badlogic.gdx.scenes.scene2d.InputEvent event, float x, float y) {
                paused = false;
                Gdx.input.setInputProcessor(null);
            }
        });

        mainMenuButton.addListener(new ClickListener() {
            @Override
            public void clicked(com.badlogic.gdx.scenes.scene2d.InputEvent event, float x, float y) {
                paused = false;
                Gdx.input.setInputProcessor(null);
                main.setScreen(new GamingScreen(main, batch));
            }
        });

        table.add(resumeButton).fillX().uniformX();
        table.row().pad(10, 0, 10, 0);
        table.add(restartButton).fillX().uniformX();
        table.row();
        table.add(mainMenuButton).fillX().uniformX();
    }

    public void render() {
        if (paused) {
            Gdx.gl.glClearColor(10, 0, 0, 0.7f);
            Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
            stage.act();
            stage.draw();
        }
    }

    public void toggle() {
        paused = !paused;
        if (paused) {
            Gdx.input.setInputProcessor(stage);
        } else {
            Gdx.input.setInputProcessor(null);
        }
    }

    public boolean isPaused() {
        return paused;
    }

    public void dispose() {
        stage.dispose();
        skin.dispose();
    }

    public Stage getStage() {
        return stage;
    }
}
