package ee.taltech.deepdarkdungeon;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import ee.taltech.deepdarkdungeon.Screens.MainMenuScreen;

public class DeepDarkDungeonGame extends Game {
    //Gdx.graphics.getWidth();
    //Gdx.graphics.getHeight();
    public static int WIDTH;
    public static int HEIGHT;
    public SpriteBatch batch;

    public DeepDarkDungeonGame(double width, double height) {
        DeepDarkDungeonGame.WIDTH = (int) width;
        DeepDarkDungeonGame.HEIGHT = (int) height;
    }

    @Override
    public void create() {
        batch = new SpriteBatch();
        this.setScreen(new MainMenuScreen(this, 1));
    }

    @Override
    public void render() {
        super.render();
    }

    @Override
    public void dispose() {
    }
}
