package io.github.some_example_name;

public class Pigs {
    private String type;
    private int health;
    private float positionX;
    private float positionY;
    private int score;
    private float width;
    private float height;
    private boolean destroyed;

    // Constructor
    public Pigs(String type, int health, float positionX, float positionY, int score, float width, float height) {
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

    public int getHealth() {
        return health;
    }

    public float getWidth() {
        return width;
    }

    public void setPositionX(float positionX) {
        this.positionX = positionX;
    }

    public void setPositionY(float positionY) {
        this.positionY = positionY;
    }
    public void destroy() {
        destroyed = true;
        // Add visual effects like explosions or particles here
    }

    public void takeDamage(int damage) {
        health -= damage;
        if (health <= 0) {
            destroy();
        }
    }



    public boolean isDestroyed() {
        return destroyed;
    }
    public float getHeight() {
        return height;
    }

    public float getPositionX() {
        return positionX;
    }

    public float getPositionY() {
        return positionY;
    }

    public int getScore() {
        return score;
    }


    public void setType(String type) {
        this.type = type;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public void setWidth(float width) {
        this.width = width;
    }

    public void setHeight(float height) {
        this.height = height;
    }
}
