package ee.taltech.deepdarkdungeon.Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import ee.taltech.deepdarkdungeon.DeepDarkDungeonGame;
import ee.taltech.deepdarkdungeon.Screens.GameScreen;
import ee.taltech.deepdarkdungeon.Screens.SingleGameChooseScreen;

public class MainMenuScreen implements Screen {
    private static final int PLAY_BUTTON_WIDTH = 396;
    private static final int PLAY_BUTTON_HEIGHT = 150;
    private static final int EXIT_BUTTON_WIDTH = 396;
    private static final int EXIT_BUTTON_HEIGHT = 150;
    private static final int EXIT_BUTTON_Y = 100;
    private static final int PLAY_BUTTON_Y = 100;
    private static final int EXIT_BUTTON_START = 500;
    private static final int PLAY_BUTTON_START = 1024;

    DeepDarkDungeonGame game;
    Texture BACKGROUND;
    Texture PLAYBUTTONACTIVE;
    Texture PLAYBUTTONINACTIVE;
    Texture EXITBUTTONACTIVE;
    Texture EXITBUTTONINACTIVE;

    public MainMenuScreen (DeepDarkDungeonGame game) {
        this.game = game;
        BACKGROUND = new Texture("backgroungformainscreen.png");
        EXITBUTTONINACTIVE = new Texture("quitbutton.png");
        EXITBUTTONACTIVE = new Texture("quitbutton2.png");
        PLAYBUTTONINACTIVE = new Texture("playbutton.png");
        PLAYBUTTONACTIVE = new Texture("playbutton2.png");
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0, 0);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        game.batch.begin();
        game.batch.draw(BACKGROUND,0, 0);
        if (Gdx.input.getX() < EXIT_BUTTON_START + EXIT_BUTTON_WIDTH && Gdx.input.getX() > EXIT_BUTTON_START && DeepDarkDungeonGame.HEIGHT - Gdx.input.getY() < EXIT_BUTTON_Y + EXIT_BUTTON_HEIGHT && DeepDarkDungeonGame.HEIGHT - Gdx.input.getY() > EXIT_BUTTON_Y) {
            game.batch.draw(EXITBUTTONACTIVE, EXIT_BUTTON_START, EXIT_BUTTON_Y, EXIT_BUTTON_WIDTH, EXIT_BUTTON_HEIGHT);
            if (Gdx.input.isButtonJustPressed(Input.Buttons.LEFT)){
                Gdx.app.exit();
            }
        } else {
            game.batch.draw(EXITBUTTONINACTIVE, EXIT_BUTTON_START, EXIT_BUTTON_Y, EXIT_BUTTON_WIDTH, EXIT_BUTTON_HEIGHT);
        }
        if (Gdx.input.getX() < PLAY_BUTTON_START + PLAY_BUTTON_WIDTH && Gdx.input.getX() > PLAY_BUTTON_START && DeepDarkDungeonGame.HEIGHT - Gdx.input.getY() < PLAY_BUTTON_Y + PLAY_BUTTON_HEIGHT && DeepDarkDungeonGame.HEIGHT - Gdx.input.getY() > PLAY_BUTTON_Y) {
            game.batch.draw(PLAYBUTTONACTIVE, PLAY_BUTTON_START, PLAY_BUTTON_Y, PLAY_BUTTON_WIDTH, PLAY_BUTTON_HEIGHT);
            if (Gdx.input.isButtonJustPressed(Input.Buttons.LEFT)){
                game.setScreen(new SingleGameChooseScreen(game));
            }
        } else {
            game.batch.draw(PLAYBUTTONINACTIVE, PLAY_BUTTON_START, PLAY_BUTTON_Y, PLAY_BUTTON_WIDTH, PLAY_BUTTON_HEIGHT);
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
