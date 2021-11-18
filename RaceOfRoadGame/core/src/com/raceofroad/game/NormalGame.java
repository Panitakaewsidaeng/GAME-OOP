package com.raceofroad.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.TimeUtils;

import java.util.Random;

public class NormalGame extends BaseScene {
    private OrthographicCamera camera;
    private SpriteBatch batch;
    private BitmapFont fontScore;

    private Texture textureBgGame;
    Texture textureRace;
    Texture textureCar;
    Texture textureCareful;
    Texture textureRock;
    Texture textureRoad;
    Texture textureRoad2;
    Texture textureHeart1;
    Texture textureHeart2;

    private Sprite spriteBgGame;
    Sprite spriteRace;
    Sprite spriteCar;
    Sprite spriteCareful;
    Sprite spriteRock;
    Sprite spriteRoad;
    Sprite spriteRoad2;
    Sprite spriteHeart1;
    Sprite spriteHeart2;


    Vector2 positionRace = new Vector2();
    Vector2 positionRoad1 = new Vector2();
    Vector2 positionRoad2 = new Vector2();
    Vector2 positionCar = new Vector2();
    Vector2 positionRock = new Vector2();
    Vector2 positionCareful = new Vector2();

    int heart;
    boolean visibleCar;
    boolean visibleRock;
    boolean visibleCareful;
    boolean heart1;
    boolean heart2;

    String obstacleList[] = {"car", "rock", "careful"};

    int posxCar[] = {220, 400};
    int posxRock[] = {275, 400};
    int posxCareful[] = {275, 335, 395, 455};

    Random random = new Random();
    String obstacleName;

    long startTime;

    public NormalGame(RaceOfRoadGame raceOfRoadGame) {
        super(raceOfRoadGame);
        this.batch = raceOfRoadGame.batch;
        this.camera = raceOfRoadGame.camera;
        this.fontScore = new BitmapFont();
        this.fontScore.getData().setScale(2,2);
        this.fontScore.setColor(Color.RED);

        textureBgGame = new Texture(Gdx.files.internal("core/assets/image/bggamenormal.png"));
        spriteBgGame = new Sprite(textureBgGame);

        textureRoad = new Texture(Gdx.files.internal("core/assets/image/road.png"));
        textureRoad.setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
        spriteRoad = new Sprite(textureRoad);

        textureRoad2 = new Texture(Gdx.files.internal("core/assets/image/road.png"));
        textureRoad2.setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
        spriteRoad2 = new Sprite(textureRoad2);

        textureRace = new Texture(Gdx.files.internal("core/assets/image/race.png"));
        spriteRace = new Sprite(textureRace);

        textureCar = new Texture(Gdx.files.internal("core/assets/image/car.png"));
        spriteCar = new Sprite(textureCar);

        textureRock = new Texture(Gdx.files.internal("core/assets/image/rock.png"));
        spriteRock = new Sprite(textureRock);

        textureCareful = new Texture(Gdx.files.internal("core/assets/image/careful.png"));
        spriteCareful = new Sprite(textureCareful);

        textureHeart1 = new Texture(Gdx.files.internal("core/assets/image/heart.png"));
        spriteHeart1 = new Sprite(textureHeart1);

        textureHeart2 = new Texture(Gdx.files.internal("core/assets/image/heart.png"));
        spriteHeart2 = new Sprite(textureHeart2);


        positionRace.x = 287;
        positionRace.y = 0;
        spriteRace.setPosition(positionRace.x, positionRace.y);
        spriteCar.setPosition(-100, -100);
        spriteRock.setPosition(-100, -100);
        spriteCareful.setPosition(-100, -100);

        positionRoad1.x = 200;
        positionRoad1.y = 0;

        positionRoad2.x = 200;
        positionRoad2.y = 618;

        heart1 = true;
        heart2 = true;


        this.startTime = TimeUtils.millis();

        obstacleName = obstacleList[random.nextInt(obstacleList.length)];
        if (obstacleName == "car") {
            positionCar.x = posxCar[random.nextInt(posxCar.length)];
            positionCar.y = 618;
            visibleCar = true;
            visibleRock = false;
            visibleCareful = false;
        }

        if (obstacleName == "rock") {
            positionRock.x = posxRock[random.nextInt(posxRock.length)];
            positionRock.y = 618;
            visibleCar = false;
            visibleRock = true;
            visibleCareful = false;
        }

        if (obstacleName == "careful") {
            positionCareful.x = posxCareful[random.nextInt(posxCareful.length)];
            positionCareful.y = 618;
            visibleCar = false;
            visibleRock = false;
            visibleCareful = true;
        }

        this.resetScene();
    }

    private void drawScene() {
        this.camera.update();
        this.batch.setProjectionMatrix(this.camera.combined);
        this.batch.begin();

        spriteBgGame.draw(batch);

        spriteRoad.setPosition(positionRoad1.x, positionRoad1.y);
        spriteRoad2.setPosition(positionRoad2.x, positionRoad2.y);

        positionRoad1.y -= 7;
        positionRoad2.y -= 7;
        spriteRoad.draw(batch);
        spriteRoad2.draw(batch);

        if (positionRoad1.y <= -618) {
            positionRoad1.y = 618;
            spriteRoad.setPosition(positionRoad1.x, positionRoad1.y);
        }

        if (positionRoad2.y <= -618) {
            positionRoad2.y = 618;
            spriteRoad2.setPosition(positionRoad2.x, positionRoad2.y);
        }

        if (obstacleName == "car") {
            spriteCar.setPosition(positionCar.x, positionCar.y);
        }

        if (obstacleName == "rock") {
            spriteRock.setPosition(positionRock.x, positionRock.y);
        }

        if (obstacleName == "careful") {
            spriteCareful.setPosition(positionCareful.x, positionCareful.y);
        }

        if (visibleCar == true) {
            spriteCar.draw(batch);
            positionCar.y -= 7;
        }

        if (visibleRock == true) {
            spriteRock.draw(batch);
            positionRock.y -= 7;
        }

        if (visibleCareful == true) {
            spriteCareful.draw(batch);
            positionCareful.y -= 7;
        }

        if (positionCar.y < 0) {
            visibleCar = false;
            positionCar.x = 0;
            positionCar.y = 0;
        }

        if (positionRock.y < 0) {
            visibleRock = false;
            positionRock.x = 0;
            positionRock.y = 0;
        }

        if (positionCareful.y < 0) {
            visibleCareful = false;
            positionCareful.x = 0;
            positionCareful.y = 0;
        }


        if (visibleCar == false && visibleRock == false && visibleCareful == false) {
            obstacleName = obstacleList[random.nextInt(obstacleList.length)];
            if (obstacleName == "car") {
                positionCar.x = posxCar[random.nextInt(posxCar.length)];
                positionCar.y = 618;
                visibleCar = true;
                visibleRock = false;
                visibleCareful = false;
            }

            if (obstacleName == "rock") {
                positionRock.x = posxRock[random.nextInt(posxRock.length)];
                positionRock.y = 618;
                visibleCar = false;
                visibleRock = true;
                visibleCareful = false;
            }

            if (obstacleName == "careful") {
                positionCareful.x = posxCareful[random.nextInt(posxCareful.length)];
                positionCareful.y = 618;
                visibleCar = false;
                visibleRock = false;
                visibleCareful = true;
            }
        }

        spriteRace.setPosition(positionRace.x, positionRace.y);
        spriteRace.draw(batch);

        if (heart2 == true) {
            spriteHeart2.setPosition(40, 0);
            spriteHeart2.draw(batch);
        }

        if (heart1 == true) {
            spriteHeart1.setPosition(0, 0);
            spriteHeart1.draw(batch);
        }

        long currentTime = TimeUtils.millis();
        raceOfRoadGame.score = (currentTime - startTime) / 1000;
        fontScore.draw(batch, "score: " + raceOfRoadGame.score, 650, 30);
        this.batch.end();
    }

    private void resetScene() {
        raceOfRoadGame.score = 0;
        this.heart = 2;
        this.heart1 = true;
        this.heart2 = true;

    }

    private void updateScene(float delta) {
        float moveAmount = 8.0f;
        if (positionRace.x > 287) {
            if (Gdx.input.isKeyPressed(Input.Keys.LEFT))
                positionRace.x -= moveAmount;
        }

        if (positionRace.x < 470) {
            if (Gdx.input.isKeyPressed(Input.Keys.RIGHT))
                positionRace.x += moveAmount;
        }

        if (heart == 0) {
            if (Intersector.overlaps(spriteRace.getBoundingRectangle(), spriteCar.getBoundingRectangle())) {
                raceOfRoadGame.setScreen(new EndScene(raceOfRoadGame));
            }

            if (Intersector.overlaps(spriteRace.getBoundingRectangle(), spriteRock.getBoundingRectangle())) {
                raceOfRoadGame.setScreen(new EndScene(raceOfRoadGame));
            }

            if (Intersector.overlaps(spriteRace.getBoundingRectangle(), spriteCareful.getBoundingRectangle())) {
                raceOfRoadGame.setScreen(new EndScene(raceOfRoadGame));
            }
        }
        if (heart == 1) {
            if (Intersector.overlaps(spriteRace.getBoundingRectangle(), spriteCar.getBoundingRectangle())) {
                positionCar.x = 0;
                positionCar.y = 0;
                visibleCar = false;
                if (visibleCareful == false && visibleRock == false && visibleCar == false) {
                    heart--;
                    heart1 = false;
                }
            }

            if (Intersector.overlaps(spriteRace.getBoundingRectangle(), spriteRock.getBoundingRectangle())) {
                positionRock.x = 0;
                positionRock.y = 0;
                visibleRock = false;
                if (visibleCareful == false && visibleRock == false && visibleCar == false) {
                    heart--;
                    heart1 = false;
                }
            }

            if (Intersector.overlaps(spriteRace.getBoundingRectangle(), spriteCareful.getBoundingRectangle())) {
                positionCareful.x = 0;
                positionCareful.y = 0;
                visibleCareful = false;
                if (visibleCareful == false && visibleRock == false && visibleCar == false) {
                    heart--;
                    heart1 = false;
                }
            }
        }



        if (heart == 2) {
            if (Intersector.overlaps(spriteRace.getBoundingRectangle(), spriteCar.getBoundingRectangle())) {
                positionCar.x = 0;
                positionCar.y = 0;
                visibleCar = false;
                if (visibleCareful == false && visibleRock == false && visibleCar == false) {
                    heart--;
                    heart2 = false;
                }
            }
            if (Intersector.overlaps(spriteRace.getBoundingRectangle(), spriteRock.getBoundingRectangle())) {
                positionRock.x = 0;
                positionRock.y = 0;
                visibleRock = false;
                if (visibleCareful == false && visibleRock == false && visibleCar == false) {
                    heart--;
                    heart2 = false;
                }
            }

            if (Intersector.overlaps(spriteRace.getBoundingRectangle(), spriteCareful.getBoundingRectangle())) {
                positionCareful.x = 0;
                positionCareful.y = 0;
                visibleCareful = false;
                if (visibleCareful == false && visibleRock == false && visibleCar == false) {
                    heart--;
                    heart2 = false;
                }
            }
        }
    }

    @Override
    public void render(float delta) {
        super.render(delta);
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        this.drawScene();
        this.updateScene(delta);
    }

    @Override
    public void dispose() {
        this.batch.dispose();
        super.dispose();
        this.textureBgGame.dispose();
        textureRoad.dispose();
        textureRace.dispose();
        textureCar.dispose();
        textureCareful.dispose();
        textureRock.dispose();
        textureHeart1.dispose();
        textureHeart2.dispose();
    }

}
