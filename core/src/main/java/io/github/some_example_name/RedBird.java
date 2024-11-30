package io.github.some_example_name;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;



public class RedBird extends Birds {

    private static final int health = 100 ;
    private static final int score = 200;
    private Texture texture;


    public RedBird(float positionX, float positionY, float width, float height) {
        super("RedBird",health, positionX, positionY, score, width, height);
        texture = new Texture("bird.png");
    }

    public void render(SpriteBatch batch) {
        batch.draw(texture, getPositionX(), getPositionY(), getWidth(), getHeight());
    }


    public Texture getTexture() {
        return texture;
    }



}
