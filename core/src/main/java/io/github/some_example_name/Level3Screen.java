package io.github.some_example_name;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;

import java.util.ArrayList;
import java.util.Iterator;

public class Level3Screen implements Screen {
    private final SpriteBatch batch;
    private final Texture level3Background;
    private final Texture pauseButtonTexture;
    private final float pauseButtonSize = 80f;
    private final PauseDialog pauseDialog;
    private final Main main;
    private static float PIXELS_TO_METERS = 100f ;
    private final Texture birdTexture;
    private boolean isPaused;
    private Skin skin;
    private BlackBird blackBird;
    private final Birds bird;
    private final Texture catapultTexture;
    private final Catapult catapult;
    private final Texture pigTexture;
    private final ArrayList<Pigs> pigs;
    private final Texture blockTexture;
    private final ArrayList<Blocks> blocks;
    private final float birdWidth;
    private final float birdHeight;
    private final float catapultWidth;
    private final float catapultHeight;
    private final float winButtonSize = 100f;
    private final float loseButtonSize = 100f;
    private boolean isBirdFlying = false;
    private boolean isDragging = false;
    private float dragStartX, dragStartY;
    private ArrayList<Body> pigBodies;
    private ArrayList<Body> bodiesToDestroy = new ArrayList<>();
    private boolean isGameOver = false;
    private boolean isWin = false;
    private boolean isLose = false;

    private World world;
    private Box2DDebugRenderer debugRenderer;
    private Body groundBody;
    private Body birdBody;
    private ArrayList<Body> blockBodies;

    public Level3Screen(Main main,SpriteBatch batch) {
        this.batch = batch;
        this.level3Background = new Texture("level3.jpg");
        pauseButtonTexture = new Texture("pause.png");

        this.birdTexture = new Texture("blackbird.png");
        this.catapultTexture = new Texture("catapult.png");
        this.catapult = new Catapult(250, 120, 150, 100);
        this.blockTexture = new Texture("block.png");
        this.pigTexture = new Texture("pig.png");
        this.birdWidth = 90f;
        this.birdHeight = 105f;
        this.catapultWidth = 100f;
        this.catapultHeight = 160f;

        this.main = main;
        pauseDialog = new PauseDialog(main, batch);

        pigBodies = new ArrayList<>();
        this.bird = new Birds("angrybird", 10, 150, 500, 150, birdWidth,birdHeight);

        blocks = new ArrayList<>();
        blocks.add(new Blocks("Wood", 100, 1000, 500, 50, 100, 100));
        blocks.add(new Blocks("Wood", 100, 1000, 500, 50, 100, 100));
        blocks.add(new Blocks("Wood", 100, 1100, 500, 50, 100, 100));

        pigs = new ArrayList<>();
        pigs.add(new Pigs("Green Pig", 100, 1001, 600, 10, 70, 70));
        pigs.add(new Pigs("Blue Pig", 100, 1110, 600, 10, 70, 70));

        this.skin = new Skin(Gdx.files.internal("uiskin.json"));
        world = new World(new Vector2(0, -9.8f), true);
        debugRenderer = new Box2DDebugRenderer();

        createGround();
        createBird();
        createBlocks();

        createPigs();
        setupContactListener();
    }

    private void createGround() {
        BodyDef groundDef = new BodyDef();
        groundDef.type = BodyDef.BodyType.StaticBody;
        groundDef.position.set(0, 120 / PIXELS_TO_METERS);

        groundBody = world.createBody(groundDef);

        PolygonShape groundShape = new PolygonShape();
        groundShape.setAsBox(Gdx.graphics.getWidth() / PIXELS_TO_METERS, 7 / PIXELS_TO_METERS);

        FixtureDef groundFixture = new FixtureDef();
        groundFixture.shape = groundShape;
        groundFixture.friction = 0.5f;
        groundFixture.restitution = 0.1f;
        groundBody.createFixture(groundFixture);

        groundShape.dispose();
    }

    private void createPigs() {
        for (Pigs pig : pigs) {
            BodyDef pigDef = new BodyDef();
            pigDef.type = BodyDef.BodyType.DynamicBody;
            pigDef.position.set(
                pig.getPositionX() / PIXELS_TO_METERS,
                pig.getPositionY() / PIXELS_TO_METERS
            );

            Body pigBody = world.createBody(pigDef);

            CircleShape pigShape = new CircleShape();
            float radius = (Math.min(pig.getWidth(), pig.getHeight()) / 2) / PIXELS_TO_METERS;
            pigShape.setRadius(radius);

            FixtureDef pigFixture = new FixtureDef();
            pigFixture.shape = pigShape;
            pigFixture.density = 2.0f;
            pigFixture.restitution = 0.1f;
            pigFixture.friction = 0.8f;

            pigBody.createFixture(pigFixture);
            pigBody.setFixedRotation(true);

            pigBody.setLinearDamping(0.5f);

            pigShape.dispose();
            pigBodies.add(pigBody);
        }
    }

    private void createBird() {

        BodyDef birdDef = new BodyDef();
        birdDef.type = BodyDef.BodyType.DynamicBody;
        birdDef.position.set(250 / PIXELS_TO_METERS, 300 / PIXELS_TO_METERS);

        birdBody = world.createBody(birdDef);

        CircleShape birdShape = new CircleShape();
        float radius = (Math.min(birdWidth, birdHeight) / 2) / PIXELS_TO_METERS;
        birdShape.setRadius(radius);

        FixtureDef birdFixture = new FixtureDef();
        birdFixture.shape = birdShape;

        birdFixture.density = 0.5f;
        birdFixture.restitution = 0.2f;
        birdFixture.friction = 0.4f;

        birdBody.createFixture(birdFixture);
        birdBody.setFixedRotation(true);

        birdShape.dispose();
    }

    private void createBlocks() {

        blockBodies = new ArrayList<>();

        for (Blocks block : blocks) {
            BodyDef blockDef = new BodyDef();
            blockDef.type = BodyDef.BodyType.DynamicBody;
            blockDef.position.set(
                block.getPositionX() / PIXELS_TO_METERS,
                block.getPositionY() / PIXELS_TO_METERS
            );

            blockDef.linearVelocity.set(0, 0);
            blockDef.angularVelocity = 0;
            blockDef.linearDamping = 0.9f;
            blockDef.angularDamping = 0.8f;


            Body blockBody = world.createBody(blockDef);

            PolygonShape blockShape = new PolygonShape();
            blockShape.setAsBox(
                (block.getWidth() / 2) / PIXELS_TO_METERS,
                (block.getHeight() / 2) / PIXELS_TO_METERS
            );

            FixtureDef blockFixture = new FixtureDef();
            blockFixture.shape = blockShape;
            blockFixture.density = 5.0f;
            blockFixture.restitution = 0.1f;
            blockFixture.friction = 0.9f;
            blockBody.createFixture(blockFixture);

            blockShape.dispose();
            blockBodies.add(blockBody);
        }
    }

    public void onTouchDown(float x, float y) {
        dragStartX = x;
        dragStartY = y;
        isDragging = true;

        bird.setPosition(dragStartX, dragStartY);
    }


    public void onTouchUp(float x, float y) {
        if (isDragging) {
            float deltaX = dragStartX - x;
            float deltaY = dragStartY - y;

            float angle = (float) Math.atan2(deltaY, deltaX);
            float power = (float) Math.sqrt(deltaX * deltaX + deltaY * deltaY);

            float impulseX = deltaX * 2f;
            float impulseY = deltaY * 2f;

            birdBody.applyLinearImpulse(new Vector2(impulseX / PIXELS_TO_METERS, impulseY / PIXELS_TO_METERS),
                birdBody.getWorldCenter(), true);


            birdBody.applyAngularImpulse(10f, true);

            isBirdFlying = true;
            isDragging = false;
        }
    }

    @Override
    public void render(float delta) {

        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        if (!pauseDialog.isPaused()) {


            world.step(1 / 60f, 6, 2);

            batch.begin();


            batch.draw(level3Background, 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());

            batch.draw(catapultTexture, catapult.getPositionX(), catapult.getPositionY(), catapultWidth, catapultHeight);


            float pauseButtonX = Gdx.graphics.getWidth() - pauseButtonSize - 20;
            float pauseButtonY = Gdx.graphics.getHeight() - pauseButtonSize - 20;
            batch.draw(pauseButtonTexture, pauseButtonX, pauseButtonY, pauseButtonSize, pauseButtonSize);

            for (int i = 0; i < blocks.size(); i++) {
                Body blockBody = blockBodies.get(i);
                Blocks block = blocks.get(i);

                float blockX = blockBody.getPosition().x * PIXELS_TO_METERS - block.getWidth() / 2;
                float blockY = blockBody.getPosition().y * PIXELS_TO_METERS - block.getHeight() / 2;
                batch.draw(blockTexture, blockX, blockY, block.getWidth(), block.getHeight());


            }

            float birdX = birdBody.getPosition().x * PIXELS_TO_METERS - birdWidth / 2;
            float birdY = birdBody.getPosition().y * PIXELS_TO_METERS - birdHeight / 2;
            batch.draw(birdTexture, birdX, birdY, birdWidth, birdHeight);

            for (int i = 0; i < pigs.size(); i++) {
                Body pigBody = pigBodies.get(i);
                Pigs pig = pigs.get(i);
                float pigX = pigBody.getPosition().x * PIXELS_TO_METERS - pig.getWidth() / 2;
                float pigY = pigBody.getPosition().y * PIXELS_TO_METERS - pig.getHeight() / 2;
                batch.draw(pigTexture, pigX, pigY, pig.getWidth(), pig.getHeight());
            }

            batch.end();
            destroyBodiesAfterPhysicsStep(bodiesToDestroy);

            handleInput();
        } else {
            pauseDialog.render();
        }
    }

    public void setupContactListener() {

        world.setContactListener(new ContactListener() {
            @Override
            public void beginContact(Contact contact) {
                Fixture fixtureA = contact.getFixtureA();
                Fixture fixtureB = contact.getFixtureB();
                Body bodyA = fixtureA.getBody();
                Body bodyB = fixtureB.getBody();

                handleCollisionWithBlocks(bodyA, bodyB, bodiesToDestroy);

                handleCollisionWithPigs(bodyA, bodyB, bodiesToDestroy);
            }

            @Override
            public void endContact(Contact contact) {

            }

            @Override
            public void preSolve(Contact contact, Manifold oldManifold) {

            }

            @Override
            public void postSolve(Contact contact, ContactImpulse impulse) {

            }
        });

    }



    private void handleCollisionWithBlocks(Body bodyA, Body bodyB, ArrayList<Body> bodiesToDestroy) {
        for (Iterator<Body> blockIterator = blockBodies.iterator(); blockIterator.hasNext(); ) {
            Body blockBody = blockIterator.next();
            if ((bodyA == birdBody && bodyB == blockBody) || (bodyB == birdBody && bodyA == blockBody)) {
                System.out.println("Collision detected between bird and blockBody: " + blockBody);


                blocks.remove(blockBodies.indexOf(blockBody));
                bodiesToDestroy.add(blockBody);
                blockIterator.remove();
                System.out.println("Marked blockBody for destruction.");
                break;
            }
        }
    }

    private void handleCollisionWithPigs(Body bodyA, Body bodyB, ArrayList<Body> bodiesToDestroy) {

        for (Iterator<Body> pigIterator = pigBodies.iterator(); pigIterator.hasNext(); ) {
            Body pigBody = pigIterator.next();
            if ((bodyA == birdBody && bodyB == pigBody) || (bodyB == birdBody && bodyA == pigBody)) {
                System.out.println("Collision detected between bird and pigBody: " + pigBody);


                pigs.remove(pigBodies.indexOf(pigBody));
                bodiesToDestroy.add(pigBody);
                pigIterator.remove();
                System.out.println("Marked pigBody for destruction.");
                break;
            }
        }
    }

    private void destroyBodiesAfterPhysicsStep(ArrayList<Body> bodiesToDestroy) {

        if (!bodiesToDestroy.isEmpty()) {
            System.out.println("Destroying bodies...");


            for (Iterator<Body> destroyIterator = bodiesToDestroy.iterator(); destroyIterator.hasNext(); ) {
                Body body = destroyIterator.next();
                if (body != null) {
                    System.out.println("Destroying body: " + body);
                    world.destroyBody(body);
                } else {
                    System.out.println("Encountered a null body in the destruction list.");
                }
                destroyIterator.remove();
            }
        } else {
            System.out.println("No bodies to destroy this frame.");
        }
    }

    public void handleInput() {
        if (Gdx.input.isTouched()) {
            float touchX = Gdx.input.getX();
            float touchY = Gdx.graphics.getHeight() - Gdx.input.getY();


            float pauseButtonX = Gdx.graphics.getWidth() - pauseButtonSize - 20;
            float pauseButtonY = Gdx.graphics.getHeight() - pauseButtonSize - 20;

            if (touchX >= pauseButtonX && touchX <= pauseButtonX + pauseButtonSize &&
                touchY >= pauseButtonY && touchY <= pauseButtonY + pauseButtonSize) {
                pauseDialog.toggle();
                return;
            }

            if (isDragging) {
                onTouchMove(touchX, touchY);
            } else {
                onTouchDown(touchX, touchY);
            }
        } else if (isDragging) {
            float touchX = Gdx.input.getX();
            float touchY = Gdx.graphics.getHeight() - Gdx.input.getY();
            onTouchUp(touchX, touchY);
        }
    }

    public void onTouchMove(float x, float y) {
        if (isDragging) {
            bird.setPosition(x - birdWidth / 2, y - birdHeight / 2);
        }
    }

    @Override
    public void resize(int width, int height) {}

    @Override
    public void show() {}

    @Override
    public void pause() {}

    @Override
    public void resume() {}

    @Override
    public void hide() {}

    @Override
    public void dispose() {
        level3Background.dispose();

    }
}
