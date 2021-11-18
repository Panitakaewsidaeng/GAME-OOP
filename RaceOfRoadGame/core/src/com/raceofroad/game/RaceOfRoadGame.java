package com.raceofroad.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.FPSLogger;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.viewport.FillViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

public class RaceOfRoadGame extends Game {
	final int SCREEN_WIDTH = 800;
	final int SCREEN_HEIGHT = 600;

	public SpriteBatch batch;
	public FPSLogger fpsLogger;
	public OrthographicCamera camera;
	public Viewport viewport;
	public BitmapFont font;
	public long score;

	public RaceOfRoadGame() {
		this.fpsLogger = new FPSLogger();
		this.camera = new OrthographicCamera();
		this.camera.position.set(SCREEN_WIDTH / 2, SCREEN_HEIGHT / 2, 0);
		this.viewport = new FillViewport(SCREEN_WIDTH, SCREEN_HEIGHT, camera);
		this.score = 0;
	}

	@Override
	public void dispose() {
		this.batch.dispose();
	}

	@Override
	public void render() {
		super.render();
		this.fpsLogger.log();
	}

	@Override
	public void resize(int width, int height) {
		this.viewport.update(width, height);
	}

	@Override
	public void create() {
		this.batch = new SpriteBatch();
		this.setScreen(new MenuScene(this));

	}

}
