package ee.taltech.deepdarkdungeon.Screens;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import ee.taltech.deepdarkdungeon.DeepDarkDungeonGame;
import ee.taltech.deepdarkdungeon.Models.PutMusic;
import ee.taltech.deepdarkdungeon.Screens.GameScreen;
import ee.taltech.deepdarkdungeon.Screens.SingleGameChooseScreen;

import java.lang.reflect.GenericArrayType;

public class MainMenuScreen implements Screen {
    private static final int PLAY_BUTTON_WIDTH = 396;
    private static final int PLAY_BUTTON_HEIGHT = 150;
    private static final int EXIT_BUTTON_WIDTH = 396;
    private static final int EXIT_BUTTON_HEIGHT = 150;

    private static final int PLAY_BUTTON_X_END = 1420;
    private static final int PLAY_BUTTON_Y_END = 950;
    private static final int PLAY_BUTTON_Y_START = 800;
    private static final int PLAY_BUTTON_X_START = 1024;
    private static final int PLAYBUTTON_Y_FORBUTTONCHANGE = 100;

    private static final int EXIT_BUTTON_X_END = 796;
    private static final int EXIT_BUTTON_Y_END = 950;
    private static final int EXIT_BUTTON_Y_START = 800;
    private static final int EXIT_BUTTON_X_START = 400;
    private static final int EXITBUTTON_Y_FORBUTTONCHANGE = 100;

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

    private static final int YES2BUTTON_Y_START = 260;
    private static final int YES2BUTTON_Y_END = 400;
    private static final int YES2BUTTON_X_START = 775;
    private static final int YES2BUTTON_X_END = 1145;
    private static final int YES2BUTTON_Y_FORBUTTONCHANGE = 645;

    private static final int BACKBUTTON_Y_START = 605;
    private static final int BACKBUTTON_Y_END = 745;
    private static final int BACKBUTTON_X_START = 775;
    private static final int BACKBUTTON_X_END = 1145;
    private static final int BACKBUTTON_Y_FORBUTTONCHANGE = 305;

    private static final int MPBUTTON_Y_START = 435;
    private static final int MPBUTTON_Y_END = 575;
    private static final int MPBUTTON_X_START = 495;
    private static final int MPBUTTON_X_END = 1425;
    private static final int MPBUTTON_Y_FORBUTTONCHANGE = 475;

    private static final int MUSICBUTTON_Y_START = 10;
    private static final int MUSICBUTTON_Y_END = 110;
    private static final int MUSICBUTTON_X_START = 1788;
    private static final int MUSICBUTTON_X_END = 1900;
    private static final int MUSICBUTTON_Y_FORBUTTONCHANGE = 940;
    private static final int MUSIC_BUTTON_WIDTH = 112;
    private static final int MUSIC_BUTTON_HEIGHT = 100;

    DeepDarkDungeonGame game;
    Texture BACKGROUND;
    Texture PLAYBUTTONACTIVE;
    Texture PLAYBUTTONINACTIVE;
    Texture EXITBUTTONACTIVE;
    Texture EXITBUTTONINACTIVE;
    Texture QUITGAMEWINDOW;
    Texture STARTGAMEWINDOW;
    Texture NOBUTTON;
    Texture YESBUTTON;
    Texture BACKBUTTON;
    Texture MULTIPLAYERBUTTON;
    Texture MUSICBUTTON1;
    Texture MUSICBUTTON2;
    boolean wantingToExit = false;
    boolean wantingToPlay = false;
    PutMusic music;

    public MainMenuScreen(DeepDarkDungeonGame game) {
        this.game = game;
        // Fixing all textures:
        QUITGAMEWINDOW = new Texture("quitgamewindow.png");
        BACKGROUND = new Texture("backgroungformainscreen.png");
        EXITBUTTONINACTIVE = new Texture("quitbutton.png");
        EXITBUTTONACTIVE = new Texture("quitbutton2.png");
        PLAYBUTTONINACTIVE = new Texture("playbutton.png");
        PLAYBUTTONACTIVE = new Texture("playbutton2.png");
        YESBUTTON = new Texture("yes.png");
        NOBUTTON = new Texture("no.png");
        STARTGAMEWINDOW = new Texture("gameStartWindow.png");
        BACKBUTTON = new Texture("backbutton2.png");
        MULTIPLAYERBUTTON = new Texture("multiplayerButton.png");
        MUSICBUTTON1 = new Texture("musicButton1.png");
        MUSICBUTTON2 = new Texture("musicButton2.png");
        music = new PutMusic("startMelody.mp3");
    }

    public MainMenuScreen(DeepDarkDungeonGame game, PutMusic musicToStop) {
        musicToStop.stopMusic();
        this.game = game;
        // Fixing all textures:
        QUITGAMEWINDOW = new Texture("quitgamewindow.png");
        BACKGROUND = new Texture("backgroungformainscreen.png");
        EXITBUTTONINACTIVE = new Texture("quitbutton.png");
        EXITBUTTONACTIVE = new Texture("quitbutton2.png");
        PLAYBUTTONINACTIVE = new Texture("playbutton.png");
        PLAYBUTTONACTIVE = new Texture("playbutton2.png");
        YESBUTTON = new Texture("yes.png");
        NOBUTTON = new Texture("no.png");
        STARTGAMEWINDOW = new Texture("gameStartWindow.png");
        BACKBUTTON = new Texture("backbutton2.png");
        MULTIPLAYERBUTTON = new Texture("multiplayerButton.png");
        MUSICBUTTON1 = new Texture("musicButton1.png");
        MUSICBUTTON2 = new Texture("musicButton2.png");
        music = new PutMusic("startMelody.mp3");
    }

    @Override
    public void show() {

    }


    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0, 0);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        game.batch.begin();
        game.batch.draw(BACKGROUND, 0, 0);
        if (wantingToPlay) { // If to click on play button...
            game.batch.draw(STARTGAMEWINDOW, 445, 245);
            if (Gdx.input.getX() > YES2BUTTON_X_START && Gdx.input.getX() < YES2BUTTON_X_END && Gdx.input.getY() > YES2BUTTON_Y_START && Gdx.input.getY() < YES2BUTTON_Y_END) {
                game.batch.draw(PLAYBUTTONACTIVE, YES2BUTTON_X_START, YES2BUTTON_Y_FORBUTTONCHANGE);
                if (Gdx.input.isButtonJustPressed(Input.Buttons.LEFT)) {
                    game.setScreen(new SingleGameChooseScreen(game, music));
                }
            }
            if (Gdx.input.getX() > BACKBUTTON_X_START && Gdx.input.getX() < BACKBUTTON_X_END && Gdx.input.getY() > BACKBUTTON_Y_START && Gdx.input.getY() < BACKBUTTON_Y_END) {
                game.batch.draw(BACKBUTTON, BACKBUTTON_X_START, BACKBUTTON_Y_FORBUTTONCHANGE);
                if (Gdx.input.isButtonJustPressed(Input.Buttons.LEFT)) {
                    wantingToPlay = false;
                }
            }
            if (Gdx.input.getX() > MPBUTTON_X_START && Gdx.input.getX() < MPBUTTON_X_END && Gdx.input.getY() > MPBUTTON_Y_START && Gdx.input.getY() < MPBUTTON_Y_END) {
                game.batch.draw(MULTIPLAYERBUTTON, MPBUTTON_X_START, MPBUTTON_Y_FORBUTTONCHANGE);
            }
        }
        if (wantingToExit) { // If to click on exit button...
            game.batch.draw(QUITGAMEWINDOW, 525, 270);
            if (Gdx.input.getX() > YESBUTTON_X_START && Gdx.input.getX() < YESBUTTON_X_END && Gdx.input.getY() > YESBUTTON_Y_START && Gdx.input.getY() < YESBUTTON_Y_END) {
                game.batch.draw(YESBUTTON, YESBUTTON_X_START, YESBUTTON_Y_FORBUTTONCHANGE);
                if (Gdx.input.isButtonJustPressed(Input.Buttons.LEFT)) {
                    Gdx.app.exit();
                }

            }
            if (Gdx.input.getX() > NOBUTTON_X_START && Gdx.input.getX() < NOBUTTON_X_END && Gdx.input.getY() > NOBUTTON_Y_START && Gdx.input.getY() < NOBUTTON_Y_END) {
                game.batch.draw(NOBUTTON, NOBUTTON_X_START, NOBUTTON_Y_FORBUTTONCHANGE);
                if (Gdx.input.isButtonJustPressed(Input.Buttons.LEFT)) {
                    wantingToExit = false;
                }
            }
        }
        // Animation associated with exit button:
        if (!wantingToPlay && !wantingToExit && Gdx.input.getX() > EXIT_BUTTON_X_START && Gdx.input.getX() < EXIT_BUTTON_X_END && Gdx.input.getY() > EXIT_BUTTON_Y_START && Gdx.input.getY() < EXIT_BUTTON_Y_END) {
            game.batch.draw(EXITBUTTONACTIVE, EXIT_BUTTON_X_START, EXITBUTTON_Y_FORBUTTONCHANGE, EXIT_BUTTON_WIDTH, EXIT_BUTTON_HEIGHT);
            if (Gdx.input.isButtonJustPressed(Input.Buttons.LEFT)) {
                wantingToExit = true;
            }
        } else {
            game.batch.draw(EXITBUTTONINACTIVE, EXIT_BUTTON_X_START, EXITBUTTON_Y_FORBUTTONCHANGE, EXIT_BUTTON_WIDTH, EXIT_BUTTON_HEIGHT);
        }

        // Animation associated with play button:
        if (!wantingToPlay && !wantingToExit && Gdx.input.getX() > PLAY_BUTTON_X_START && Gdx.input.getX() < PLAY_BUTTON_X_END && Gdx.input.getY() > PLAY_BUTTON_Y_START && Gdx.input.getY() < PLAY_BUTTON_Y_END) {
            game.batch.draw(PLAYBUTTONACTIVE, PLAY_BUTTON_X_START, PLAYBUTTON_Y_FORBUTTONCHANGE, PLAY_BUTTON_WIDTH, PLAY_BUTTON_HEIGHT);
            if (Gdx.input.isButtonJustPressed(Input.Buttons.LEFT)) {
                wantingToPlay = true;
            }
        } else {
            game.batch.draw(PLAYBUTTONINACTIVE, PLAY_BUTTON_X_START, PLAYBUTTON_Y_FORBUTTONCHANGE, PLAY_BUTTON_WIDTH, PLAY_BUTTON_HEIGHT);
        }

        // Music pause or play
        if (!wantingToPlay && !wantingToExit && Gdx.input.getX() > MUSICBUTTON_X_START && Gdx.input.getX() < MUSICBUTTON_X_END && Gdx.input.getY() > MUSICBUTTON_Y_START && Gdx.input.getY() < MUSICBUTTON_Y_END) {
            game.batch.draw(MUSICBUTTON2, MUSICBUTTON_X_START, MUSICBUTTON_Y_FORBUTTONCHANGE, MUSIC_BUTTON_WIDTH, MUSIC_BUTTON_HEIGHT);
            if (Gdx.input.isButtonJustPressed(Input.Buttons.LEFT)) {
                music.changePlayOrStop();
            }
        } else {
            game.batch.draw(MUSICBUTTON1, MUSICBUTTON_X_START, MUSICBUTTON_Y_FORBUTTONCHANGE, MUSIC_BUTTON_WIDTH, MUSIC_BUTTON_HEIGHT);
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
