package io.github.some_example_name;

public class Birds {
    private String type;
    private int power;
    private int speed;
    private int weight;
    private float position;

    // Constructor
    public Birds(String type, int power, int speed, int weight, float position) {
        this.type = type;
        this.power = power;
        this.speed = speed;
        this.weight = weight;
        this.position = position;
    }

    // Getters (optional, for accessing the private fields)
    public String getType() {
        return type;
    }

    public int getPower() {
        return power;
    }

    public int getSpeed() {
        return speed;
    }

    public int getWeight() {
        return weight;
    }

    public float getPosition() {
        return position;
    }

    // Setters (optional, for modifying the private fields)
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

    public void setPosition(float position) {
        this.position = position;
    }
}
