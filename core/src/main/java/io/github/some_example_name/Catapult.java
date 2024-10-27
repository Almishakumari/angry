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



    public void adjustAngle(float delta) {
        this.angle += delta;


        if (this.angle < 0) {
            this.angle = 0;
        }
        if (this.angle > 90) {
            this.angle = 90;
        }
    }


    public void adjustPower(float delta) {
        this.power += delta;


        if (this.power < 50) {
            this.power = 50;
        }
        if (this.power > 200) {
            this.power = 200;
        }
    }


    public void fire(Birds bird) {
        float velocityX = (float) (power * Math.cos(Math.toRadians(angle)));
        float velocityY = (float) (power * Math.sin(Math.toRadians(angle)));

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
