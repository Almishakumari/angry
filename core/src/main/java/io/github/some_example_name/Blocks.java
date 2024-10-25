package io.github.some_example_name;

public class Blocks {
    private String type;
    private int health;
    private float position;
    private int score;

    // Constructor
    public Blocks(String type, int health, float position, int score) {
        this.type = type;
        this.health = health;
        this.position = position;
        this.score = score;
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
}
