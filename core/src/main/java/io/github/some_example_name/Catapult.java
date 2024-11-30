package io.github.some_example_name;

public class Catapult {

    private final float positionX;
    private final float positionY;
    private final float width;
    private final float height;
    private float angle;
    private float power;

    public Catapult(float x, float y, float width, float height ) {
        this.positionX = x;
        this.positionY = y;
        this.width = width;
        this.height = height;
        this.angle = 45;
        this.power = 100;
    }


    public float getAngle() {
        return angle;
    }

    public float getPower() {
        return power;
    }

    public float getPositionX() {
        return positionX;
    }

    public float getPositionY() {
        return positionY;
    }

    public float getWidth() {
        return width;
    }

    public float getHeight() {
        return height;
    }



}
