package ee.taltech.deepdarkdungeon.Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import ee.taltech.deepdarkdungeon.DeepDarkDungeonGame;

public class MainMenuScreen implements Screen {
    private static final int PLAY_BUTTON_WIDTH = 330;
    private static final int PLAY_BUTTON_HEIGHT = 150;
    private static final int EXIT_BUTTON_WIDTH = 300;
    private static final int EXIT_BUTTON_HEIGHT = 150;
    private static final int EXIT_BUTTON_Y = 100;
    private static final int PLAY_BUTTON_Y = 300;
    DeepDarkDungeonGame game;
    Texture BACKGROUND;
    Texture PLAYBUTTONACTIVE;
    Texture PLAYBUTTONINACTIVE;
    Texture EXITBUTTONACTIVE;
    Texture EXITBUTTONINACTIVE;

    public MainMenuScreen (DeepDarkDungeonGame game) {
        this.game = game;
        BACKGROUND = new Texture("deepdark.png");
        EXITBUTTONINACTIVE = new Texture("exit.png");
        EXITBUTTONACTIVE = new Texture("exit-button-png.png");
        PLAYBUTTONINACTIVE = new Texture("unnamed.png");
        PLAYBUTTONACTIVE = new Texture("lets-go-button.png");
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0, 0);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        game.batch.begin();
        int exitX = DeepDarkDungeonGame.WIDTH / 2 - EXIT_BUTTON_WIDTH / 2;
        if (Gdx.input.getX() < exitX + EXIT_BUTTON_WIDTH && Gdx.input.getX() > exitX && DeepDarkDungeonGame.HEIGHT - Gdx.input.getY() < EXIT_BUTTON_Y + EXIT_BUTTON_HEIGHT && DeepDarkDungeonGame.HEIGHT - Gdx.input.getY() > EXIT_BUTTON_Y) {
            game.batch.draw(EXITBUTTONACTIVE, DeepDarkDungeonGame.WIDTH / 2 - EXIT_BUTTON_WIDTH / 2, EXIT_BUTTON_Y, EXIT_BUTTON_WIDTH, EXIT_BUTTON_HEIGHT);
        } else {
            game.batch.draw(EXITBUTTONINACTIVE, DeepDarkDungeonGame.WIDTH / 2 - EXIT_BUTTON_WIDTH / 2, EXIT_BUTTON_Y, EXIT_BUTTON_WIDTH, EXIT_BUTTON_HEIGHT);
        }
        int playX = DeepDarkDungeonGame.WIDTH / 2 - PLAY_BUTTON_WIDTH / 2;
        if (Gdx.input.getX() < playX + PLAY_BUTTON_WIDTH && Gdx.input.getX() > playX && DeepDarkDungeonGame.HEIGHT - Gdx.input.getY() < PLAY_BUTTON_Y + PLAY_BUTTON_HEIGHT && DeepDarkDungeonGame.HEIGHT - Gdx.input.getY() > PLAY_BUTTON_Y) {
            game.batch.draw(PLAYBUTTONACTIVE, DeepDarkDungeonGame.WIDTH / 2 - PLAY_BUTTON_WIDTH / 2, PLAY_BUTTON_Y, PLAY_BUTTON_WIDTH, PLAY_BUTTON_HEIGHT);
        } else {
            game.batch.draw(PLAYBUTTONINACTIVE, DeepDarkDungeonGame.WIDTH / 2 - PLAY_BUTTON_WIDTH / 2, PLAY_BUTTON_Y, PLAY_BUTTON_WIDTH, PLAY_BUTTON_HEIGHT);
        }
        game.batch.end();
    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {

    }
}
