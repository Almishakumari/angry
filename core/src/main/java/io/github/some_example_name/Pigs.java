package io.github.some_example_name;

public class Pigs {
    private String type;
    private int health;
    private float position;
    private int score;
    private boolean checkIsLive;

    // Constructor
    public Pigs(String type, int health, float position, int score, boolean checkIsLive) {
        this.type = type;
        this.health = health;
        this.position = position;
        this.score = score;
        this.checkIsLive = checkIsLive;
    }

    // Getters (optional, for accessing the private fields)
    public String getType() {
        return type;
    }

    public int getHealth() {
        return health;
    }

    public float getPosition() {
        return position;
    }

    public int getScore() {
        return score;
    }

    public boolean isCheckIsLive() {
        return checkIsLive;
    }

    // Setters (optional, for modifying the private fields)
    public void setType(String type) {
        this.type = type;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public void setPosition(float position) {
        this.position = position;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public void setCheckIsLive(boolean checkIsLive) {
        this.checkIsLive = checkIsLive;
    }
}
