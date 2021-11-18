package com.raceofroad.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class EndScene extends BaseScene{
    private OrthographicCamera camera;
    private SpriteBatch batch;
    BitmapFont font1;
    BitmapFont font2;
    private Texture Tbgend;
    private Sprite Sbgend;

    public EndScene(final RaceOfRoadGame raceOfRoadGame) {
        super(raceOfRoadGame);
        this.batch = raceOfRoadGame.batch;
        this.camera = raceOfRoadGame.camera;

        Tbgend = new Texture(Gdx.files.internal("core/assets/image/bgmenu.png"));
        Sbgend = new Sprite(Tbgend);

        font1 = new BitmapFont();
        font1.getData().setScale(3,3);
        font1.setColor(Color.RED);

        font2 = new BitmapFont();
        font2.getData().setScale(3,3);
        font2.setColor(Color.RED);

        this.resetScene();
    }

    private void drawScene() {
        this.camera.update();
        this.batch.setProjectionMatrix(this.camera.combined);
        this.batch.begin();
        Sbgend.setPosition(0, 0);
        Sbgend.draw(batch);
        font1.draw(batch, "Game Over", 300, 400);
        font2.draw(batch, "Your Score : " + raceOfRoadGame.score,270,200);
        this.batch.end();
    }

    private void resetScene(){

    }

    private void updateScene(float delta){

    }

    @Override
    public void render(float delta){
        super.render(delta);
        Gdx.gl.glClearColor(0,0,0,1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        this.drawScene();
        this.updateScene(delta);
    }

    @Override
    protected void handleBackPress(){
        super.handleBackPress();
    }

    @Override
    public void dispose(){
        this.batch.dispose();
        super.dispose();
        Tbgend.dispose();
        font1.dispose();
        font2.dispose();
    }
}



