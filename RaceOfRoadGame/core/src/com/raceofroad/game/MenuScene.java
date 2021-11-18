package com.raceofroad.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

public class MenuScene extends BaseScene { 
    private SpriteBatch batch;
    private Stage stage;
    private Skin skin;
    private Table mainMenu;
    private TextButton playButton1;
    private TextButton playButton2;
    private TextButton playButton3;
    private TextButton exitButton;
    private Texture Tbgmenu;
    private Image background;
    private BitmapFont font;

    public MenuScene(final RaceOfRoadGame raceOfRoadGame) {
        super(raceOfRoadGame);
        this.batch = raceOfRoadGame.batch;
        font = new BitmapFont();
        font.getData().setScale(3,3);
        font.setColor(Color.GREEN);

        final int SCREEN_WIDTH = Gdx.graphics.getWidth();
        final int SCREEN_HEIGHT = Gdx.graphics.getHeight();

        this.Tbgmenu = new Texture(Gdx.files.internal("core/assets/image/bgmenu.png"));
        this.background = new Image(Tbgmenu);

        this.stage = new Stage(raceOfRoadGame.viewport);
        this.skin = new Skin(Gdx.files.internal("core/assets/images/uiskin.json"));
        Gdx.input.setInputProcessor(this.stage);

        this.mainMenu = new Table();
        this.mainMenu.setPosition((SCREEN_WIDTH - this.mainMenu.getWidth()) / 2, (SCREEN_HEIGHT - this.mainMenu.getHeight()) / 2);

        this.playButton1 = new TextButton("EASY", this.skin);
        this.mainMenu.add(this.playButton1).padBottom(15);
        this.mainMenu.row();
        this.playButton2 = new TextButton("NORMAL", this.skin);
        this.mainMenu.add(this.playButton2).padBottom(15);
        this.mainMenu.row();
        this.playButton3 = new TextButton("HARD",this.skin);
        this.mainMenu.add(this.playButton3).padBottom(15);
        this.mainMenu.row();
        this.exitButton = new TextButton("EXIT", this.skin);
        this.mainMenu.add(this.exitButton);
        this.mainMenu.row();

        this.stage.addActor(this.background);
        this.stage.addActor(this.mainMenu);


        playButton1.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                raceOfRoadGame.setScreen(new EasyGame(raceOfRoadGame));
            }
        });
        playButton2.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                raceOfRoadGame.setScreen(new NormalGame(raceOfRoadGame));
            }
        });
        playButton3.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                raceOfRoadGame.setScreen(new HardGame(raceOfRoadGame));
            }
        });

        exitButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                Gdx.app.exit();
            }
        });
    }

    @Override
    public void render(float delta) {
        super.render(delta);
        Gdx.gl.glClearColor(0, 0, 1, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        this.stage.act();
        this.stage.draw();
        this.batch.begin();
        font.draw(batch,"Drive away",290,550);
        font.draw(batch,"from the police",260,500);
        this.batch.end();
    }

    @Override
    protected void handleBackPress() {
        super.handleBackPress();
    }

    @Override
    public void dispose() {
        this.batch.dispose();
        super.dispose();
        this.stage.dispose();
        this.skin.dispose();
        font.dispose();
    }
}
