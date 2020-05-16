package ee.taltech.deepdarkdungeon.Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import ee.taltech.deepdarkdungeon.DeepDarkDungeonGame;
import ee.taltech.deepdarkdungeon.Models.PutMusic;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.StringReader;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class InfoAboutUsScreen implements Screen, InputProcessor {

    DeepDarkDungeonGame game;
    BitmapFont font = new BitmapFont();
    Texture MUSICBUTTON1;
    Texture MUSICBUTTON2;
    Texture BACKGROUND;
    Texture BACKGROUND2;
    Texture UPBUTTON;
    Texture DOWNBUTTON;
    Texture BACKBUTTON;
    Texture BACKBUTTON2;
    int openLevelNumber;
    PutMusic music;
    Texture SCREENSHOT = new Texture("playerChooseScreenshot.png");
    Texture ATACKBUTTON = new Texture("atackButton1.png");
    Texture HEALBUTTON = new Texture("healButton1.png");
    Texture POWERSHOTBUTTON = new Texture("powershotButton1.png");
    Texture SUNSTRIKEBUTTON = new Texture("sunstrikeButton1.png");
    Texture DEFBUTTON = new Texture("defenceButton1.png");

    String text = "-----------------------------------------------------------KOLM ANNAT COMPANY----------------------------------------------------------------\n" +
            "HOW TO START???\n" +
            "\n" +
            "1. Choose between single and multiplayer game.\n" +
            "\n" +
            "2. Select 4 characters for whom you will play. Think over\n" +
            "game tactics when choosing characters, as the outcome of\n" +
            "the match depends on this. There is info button on top of\n" +
            "each character, where is a detailed information about\n" +
            "selected character.\n" +
            "\n" +
            "3. If single game, choose level.\n" +
            "\n" +
            "4. Click ==>> START\n" +
            "\n" +
            "5. If multiplayer game, just wait for another character for a while\n" +
            "\n" +
            "HOW TO PLAY???\n" +
            "\n" +
            "The game takes place in turn ==>> First beats > second beats > first beats...\n" +
            "\n" +
            "If your turn:\n" +
            "\n" +
            "1. Click on atack button\n" +
            "\n" +
            "2. Click on the enemy character, who you wanna beat.\n" +
            "\n" +
            "Also depending on the character you have a superpower:\n" +
            "\n" +
            "1. Archer - POWERSHOT ==>> Sniper strike, instant kill.\n" +
            "\n" +
            "2. Wizard - SUNSTRIKE ==>> Stabs the enemy and his neighbors on left and right.\n" +
            "\n" +
            "3. Paladin - HEAL ==>> Heals the weakest character.\n" +
            "\n" +
            "4. Warrior - BERSERK CALL ==>> The next blow of the opponent will be on this character.\n" +
            "\n" +
            "If single game and you win, the next lvl will be unlocked.\n" +
            "\n" +
            "\n" +
            "ABOUT US\n" +
            "\n" +
            "Creators:\n" +
            "Nikita Birjukovs\n" +
            "Ilya Boichuk\n" +
            "Artur-Aleksander Pärnoja\n" +
            "\n" +
            "Speccial thanks to:\n" +
            "Anna Grund\n" +
            "Janar Keit Jaakson\n" +
            "-----------------------------------------------------------KOLM ANNAT COMPANY----------------------------------------------------------------";

    private static final int TEXTHEIGHESTPLACE = 950;
    private static int TEXTLOWESTPLACE = 0;
    private static int TEXTX = 200;
    private static double TEXTY = TEXTHEIGHESTPLACE;

    private static final int UP_BUTTON_WIDTH = 112;
    private static final int UP_BUTTON_HEIGHT = 100;
    private static final int UP_X = 1765;
    private static final int UP_Y = 900;
    private static final int SCREENSHOT_X = 1000;
    private static int SCREENSHOT_Y = 500;
    private static final int ATTACKBUTTON_X = 540;
    private static int ATTACKBUTTON_Y = 87;
    private static final int HEALBUTTON_X = 900;
    private static int HEALBUTTON_Y = -313;
    private static final int POWERSHOTBUTTON_X = 940;
    private static int POWERSHOTBUTTON_Y = -160;
    private static final int SUNSTRIKEBUTTON_X = 1290;
    private static int SUNSTRIKEBUTTON_Y = -240;
    private static final int DEFBUTTON_X = 1390;
    private static int DEFBUTTON_Y = -390;

    private static final int DOWN_BUTTON_WIDTH = 112;
    private static final int DOWN_BUTTON_HEIGHT = 100;
    private static final int DOWN_X = 1765;
    private static final int DOWN_Y = 130;

    private static final int BACK_BUTTON_WIDTH = 150;
    private static final int BACK_BUTTON_HEIGHT = 58;
    private static final int BACKBUTTON_Y_START = 50;
    private static final int BACKBUTTON_Y_END = 108;
    private static final int BACKBUTTON_X_START = 30;
    private static final int BACKBUTTON_X_END = 180;

    public InfoAboutUsScreen(DeepDarkDungeonGame game, int openLevelNumber, PutMusic musicToStop) {
        this.openLevelNumber = openLevelNumber;
        this.game = game;
        this.music = musicToStop;
        Pattern pattern = Pattern.compile("\n");
        Matcher matcher = pattern.matcher(text);
        TEXTLOWESTPLACE = (int) (matcher.results().count() + 1) * 40;
        Gdx.input.setInputProcessor(this);
        font.setColor(new Color(Color.rgb888(0f, 0f, 72f)));
        font.getData().setScale(2);
    }

    @Override
    public void show() {
        MUSICBUTTON1 = new Texture("musicButton1.png");
        MUSICBUTTON2 = new Texture("musicButton2.png");
        BACKGROUND = new Texture("infoPageBackground.png");
        BACKGROUND2 = new Texture("infoPageBackground2.png");
        UPBUTTON = new Texture("scrollUp.png");
        DOWNBUTTON = new Texture("scrollDown.png");
        BACKBUTTON = new Texture("backbutton.png");
        BACKBUTTON2 = new Texture("backbutton2.png");
    }

    @Override
    public void render(float delta) {
        game.batch.begin();
        game.batch.draw(BACKGROUND, 0, 0, DeepDarkDungeonGame.WIDTH, DeepDarkDungeonGame.HEIGHT);
        font.draw(game.batch, text, TEXTX, (int) TEXTY);
        game.batch.draw(SCREENSHOT, SCREENSHOT_X, SCREENSHOT_Y, 663, 420);
        game.batch.draw(ATACKBUTTON, ATTACKBUTTON_X, ATTACKBUTTON_Y, 100, 100);
        game.batch.draw(HEALBUTTON, HEALBUTTON_X, HEALBUTTON_Y, 100, 100);
        game.batch.draw(POWERSHOTBUTTON, POWERSHOTBUTTON_X, POWERSHOTBUTTON_Y, 100, 100);
        game.batch.draw(SUNSTRIKEBUTTON, SUNSTRIKEBUTTON_X, SUNSTRIKEBUTTON_Y, 100, 100);
        game.batch.draw(DEFBUTTON, DEFBUTTON_X, DEFBUTTON_Y, 100, 100);
        game.batch.draw(UPBUTTON, UP_X, UP_Y, UP_BUTTON_WIDTH, UP_BUTTON_HEIGHT);
        game.batch.draw(BACKGROUND2, 0, 0, DeepDarkDungeonGame.WIDTH, DeepDarkDungeonGame.HEIGHT);
        game.batch.draw(UPBUTTON, UP_X, UP_Y, UP_BUTTON_WIDTH, UP_BUTTON_HEIGHT);
        if (Gdx.input.getX() < UP_X + UP_BUTTON_WIDTH && Gdx.input.getX() > UP_X && Gdx.input.getY() < DeepDarkDungeonGame.HEIGHT - UP_Y - 27 && Gdx.input.getY() > DeepDarkDungeonGame.HEIGHT - UP_Y - 27 - UP_BUTTON_HEIGHT) {
            game.batch.draw(MUSICBUTTON2, UP_X, UP_Y, UP_BUTTON_WIDTH, UP_BUTTON_HEIGHT);
            if (Gdx.input.isTouched() && TEXTY > TEXTHEIGHESTPLACE) {
                TEXTY -= 3;
                SCREENSHOT_Y -= 3;
                ATTACKBUTTON_Y -= 3;
                HEALBUTTON_Y -= 3;
                POWERSHOTBUTTON_Y -= 3;
                SUNSTRIKEBUTTON_Y -= 3;
                DEFBUTTON_Y -= 3;
            }
        }
        game.batch.draw(DOWNBUTTON, DOWN_X, DOWN_Y, DOWN_BUTTON_WIDTH, DOWN_BUTTON_HEIGHT);
        if (Gdx.input.getX() < DOWN_X + DOWN_BUTTON_WIDTH && Gdx.input.getX() > DOWN_X && Gdx.input.getY() < DeepDarkDungeonGame.HEIGHT - DOWN_Y - 27 && Gdx.input.getY() > DeepDarkDungeonGame.HEIGHT - DOWN_Y - 27 - DOWN_BUTTON_HEIGHT) {
            game.batch.draw(MUSICBUTTON2, DOWN_X, DOWN_Y, DOWN_BUTTON_WIDTH, DOWN_BUTTON_HEIGHT);
            if (Gdx.input.isTouched() && TEXTY < TEXTLOWESTPLACE) {
                TEXTY += 3;
                SCREENSHOT_Y += 3;
                ATTACKBUTTON_Y += 3;
                HEALBUTTON_Y += 3;
                POWERSHOTBUTTON_Y += 3;
                SUNSTRIKEBUTTON_Y += 3;
                DEFBUTTON_Y += 3;
            }
        }
        game.batch.draw(BACKBUTTON, BACKBUTTON_X_START, BACKBUTTON_Y_START, BACK_BUTTON_WIDTH, BACK_BUTTON_HEIGHT);
        if (Gdx.input.getX() > BACKBUTTON_X_START && Gdx.input.getX() < BACKBUTTON_X_END && Gdx.input.getY() > DeepDarkDungeonGame.HEIGHT - BACKBUTTON_Y_END - 27 && Gdx.input.getY() < DeepDarkDungeonGame.HEIGHT - BACKBUTTON_Y_START - 27) {
            game.batch.draw(BACKBUTTON2, BACKBUTTON_X_START, BACKBUTTON_Y_START, BACK_BUTTON_WIDTH, BACK_BUTTON_HEIGHT);
            if (Gdx.input.isButtonJustPressed(Input.Buttons.LEFT)) {
                game.setScreen(new MainMenuScreen(game, openLevelNumber, music, true));
            }
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

    @Override
    public boolean keyDown(int keycode) {
        return false;
    }

    @Override
    public boolean keyUp(int keycode) {
        return false;
    }

    @Override
    public boolean keyTyped(char character) {
        return false;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        return false;
    }

    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        return false;
    }

    @Override
    public boolean scrolled(int amount) {
        if (amount == 1 && TEXTY < TEXTLOWESTPLACE) {
            TEXTY += 30;
            SCREENSHOT_Y += 30;
            ATTACKBUTTON_Y += 30;
            HEALBUTTON_Y += 30;
            POWERSHOTBUTTON_Y += 30;
            SUNSTRIKEBUTTON_Y += 30;
            DEFBUTTON_Y += 30;
        } else if (amount == -1 && TEXTY > TEXTHEIGHESTPLACE) {
            TEXTY -= 30;
            SCREENSHOT_Y -= 30;
            ATTACKBUTTON_Y -= 30;
            HEALBUTTON_Y -= 30;
            POWERSHOTBUTTON_Y -= 30;
            SUNSTRIKEBUTTON_Y -= 30;
            DEFBUTTON_Y -= 30;
        }
        return false;
    }
}
