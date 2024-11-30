package io.github.some_example_name;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;


public class Birds {
    private String type;
    private int power;
    private int speed;
    private int weight;
    private float position;
    private float positionX;
    private float positionY;
    private float velocityX;
    private float velocityY;
    private boolean isLaunched;
    private float health;

    private float x;
    private float y;
    private Texture texture;
    private int score;
    private float  width, height;





    public Birds(String type, int health, float positionX, float positionY, int score, float width, float height) {
        this.type = type;
        this.health = health;
        this.positionX = positionX;
        this.positionY = positionY;
        this.score = score;
        this.width = width;
        this.height = height;


    }


    public String getType() {
        return type;
    }




    public float getWidth() {
        return this.width;
    }

    // Getter for bird's height
    public float getHeight() {
        return this.height;
    }


    public void setType(String type) {
        this.type = type;
    }

    public void setPower(int power) {
        this.power = power;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }


    public void setWeight(int weight) {
        this.weight = weight;
    }


    public void setPosition(float x, float y) {
        this.x = x;
        this.y = y;
    }


    public float getPositionX() {
        return positionX;
    }

    public float getPositionY() {
        return positionY;
    }



    public void render(SpriteBatch batch) {
    }

    public Texture getTexture() {
        return texture;
    }


}
