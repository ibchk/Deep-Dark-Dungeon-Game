package ee.taltech.deepdarkdungeon;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Screen;
import ee.taltech.deepdarkdungeon.View.GameScreen;

public class DeepDarkDungeonGame extends Game {

	private Screen gameScreen;

	@Override
	public void create() {
		gameScreen = new GameScreen();
		setScreen(gameScreen);
	}
}
