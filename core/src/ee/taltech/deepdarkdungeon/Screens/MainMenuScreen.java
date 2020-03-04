package ee.taltech.deepdarkdungeon.Screens;

import com.badlogic.gdx.Game;
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

    private static final int YESBUTTON_Y_START = 590;
    private static final int YESBUTTON_Y_END = 730;
    private static final int YESBUTTON_X_START = 1055;
    private static final int YESBUTTON_X_END = 1345;
    private static final int YESBUTTON_Y_FORBUTTONCHANGE = 320;

    private static final int NOBUTTON_Y_START = 590;
    private static final int NOBUTTON_Y_END = 730;
    private static final int NOBUTTON_X_START = 575;
    private static final int NOBUTTON_X_END = 785;
    private static final int NOBUTTON_Y_FORBUTTONCHANGE = 320;


    DeepDarkDungeonGame game;
    Texture BACKGROUND;
    Texture PLAYBUTTONACTIVE;
    Texture PLAYBUTTONINACTIVE;
    Texture EXITBUTTONACTIVE;
    Texture EXITBUTTONINACTIVE;
    Texture QUITGAMEWINDOW;
    Texture NOBUTTON;
    Texture YESBUTTON;
    boolean wantingToExit = false;

    public MainMenuScreen (DeepDarkDungeonGame game) {
        this.game = game;
        QUITGAMEWINDOW = new Texture("quitgamewindow.png");
        BACKGROUND = new Texture("backgroungformainscreen.png");
        EXITBUTTONINACTIVE = new Texture("quitbutton.png");
        EXITBUTTONACTIVE = new Texture("quitbutton2.png");
        PLAYBUTTONINACTIVE = new Texture("playbutton.png");
        PLAYBUTTONACTIVE = new Texture("playbutton2.png");
        YESBUTTON = new Texture("yes.png");
        NOBUTTON = new Texture("no.png");
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
        if (wantingToExit) {
            game.batch.draw(QUITGAMEWINDOW,525, 270);
            if (Gdx.input.getX() > YESBUTTON_X_START && Gdx.input.getX() < YESBUTTON_X_END && Gdx.input.getY() > YESBUTTON_Y_START && Gdx.input.getY() < YESBUTTON_Y_END) {
                game.batch.draw(YESBUTTON, YESBUTTON_X_START, YESBUTTON_Y_FORBUTTONCHANGE);
                if (Gdx.input.isButtonJustPressed(Input.Buttons.LEFT)){
                    Gdx.app.exit();
                }
            }
            if (Gdx.input.getX() > NOBUTTON_X_START && Gdx.input.getX() < NOBUTTON_X_END && Gdx.input.getY() > NOBUTTON_Y_START && Gdx.input.getY() < NOBUTTON_Y_END) {
                game.batch.draw(NOBUTTON, NOBUTTON_X_START, NOBUTTON_Y_FORBUTTONCHANGE);
                if (Gdx.input.isButtonJustPressed(Input.Buttons.LEFT)){
                    wantingToExit = false;
                }
            }
        }
        if (!wantingToExit && Gdx.input.getX() < EXIT_BUTTON_START + EXIT_BUTTON_WIDTH && Gdx.input.getX() > EXIT_BUTTON_START && DeepDarkDungeonGame.HEIGHT - Gdx.input.getY() < EXIT_BUTTON_Y + EXIT_BUTTON_HEIGHT && DeepDarkDungeonGame.HEIGHT - Gdx.input.getY() > EXIT_BUTTON_Y) {
            game.batch.draw(EXITBUTTONACTIVE, EXIT_BUTTON_START, EXIT_BUTTON_Y, EXIT_BUTTON_WIDTH, EXIT_BUTTON_HEIGHT);
            if (Gdx.input.isButtonJustPressed(Input.Buttons.LEFT)){
                wantingToExit = true;
            }
        } else {
            game.batch.draw(EXITBUTTONINACTIVE, EXIT_BUTTON_START, EXIT_BUTTON_Y, EXIT_BUTTON_WIDTH, EXIT_BUTTON_HEIGHT);
        }
        if (!wantingToExit && Gdx.input.getX() < PLAY_BUTTON_START + PLAY_BUTTON_WIDTH && Gdx.input.getX() > PLAY_BUTTON_START && DeepDarkDungeonGame.HEIGHT - Gdx.input.getY() < PLAY_BUTTON_Y + PLAY_BUTTON_HEIGHT && DeepDarkDungeonGame.HEIGHT - Gdx.input.getY() > PLAY_BUTTON_Y) {
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
