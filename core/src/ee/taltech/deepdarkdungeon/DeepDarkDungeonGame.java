package ee.taltech.deepdarkdungeon;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import ee.taltech.deepdarkdungeon.Screens.MainMenuScreen;

public class DeepDarkDungeonGame extends Game {
	public static final int WIDTH = 1920;
	public static final int HEIGHT = 1080;
	public SpriteBatch batch;

	@Override
	public void create () {
		batch = new SpriteBatch();
		this.setScreen(new MainMenuScreen(this, 1));
	}

	@Override
	public void render () {
		super.render();
	}
	
	@Override
	public void dispose () {
	}
}
