package ee.taltech.deepdarkdungeon.Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import ee.taltech.deepdarkdungeon.DeepDarkDungeonGame;
import ee.taltech.deepdarkdungeon.Models.GameObject;
import ee.taltech.deepdarkdungeon.Models.PutMusic;
import javafx.beans.binding.When;

import java.util.*;
import java.util.concurrent.TimeUnit;

public class GameScreen implements Screen {
    private static int WHOWILLATTACK = 0;
    private static final int VBOI_X = 850;
    private static final int VBOI_Y = 50;
    private static final int VBOI_HEIGTH = 80;
    private static final int VBOI_WIDTH = 201;
    private static final int WIN_SCREEN_X = 650;
    private static final int WIN_SCREEN_Y = 350;
    private static final int WIN_SCREEN_WIDTH = 592;
    private static final int WIN_SCREEN_HEIGHT = 341;
    private static final int MAIN_MENU_X_START = 690;
    private static final int MAIN_MENU_Y_START = 580;
    private static final int MAIN_MENU_X_END = 900;
    private static final int MAIN_MENU_Y_END = 675;
    private static final int NEXT_X_START = 970;
    private static final int NEXT_Y_START = 580;
    private static final int NEXT_X_END = 1185;
    private static final int NEXT_Y_END = 675;
    private static final int LOST_SCREEN_X = 650;
    private static final int LOST_SCREEN_Y = 350;
    private static final int LOST_SCREEN_WIDTH = 592;
    private static final int LOST_SCREEN_HEIGHT = 341;
    private static final int MAIN_MENU2_X_START = 835;
    private static final int MAIN_MENU2_Y_START = 580;
    private static final int MAIN_MENU2_X_END = 1060;
    private static final int MAIN_MENU2_Y_END = 675;

    List<GameObject> heroes;
    List<GameObject> monsters;
    DeepDarkDungeonGame game;
    boolean gameOver = false;
    boolean canbeattacked = false;
    boolean wait = false;
    String message;
    String messageForMonsters;
    BitmapFont font = new BitmapFont();
    private long stepCount = 1;
    private Texture monstersWinScreen;
    private Texture mainMenuButton2;
    private Texture nextLevelButton;
    private Texture mainMenuButton;
    private Texture heroesWinScreen;
    private Texture vboiButton;
    private Texture background;
    private SpriteBatch batch;
    private GameObject goodCharacter1;
    private GameObject goodCharacter2;
    private GameObject goodCharacter3;
    private GameObject goodCharacter4;
    private GameObject badCharacter1;
    private GameObject badCharacter2;
    private GameObject badCharacter3;
    private GameObject badCharacter4;
    private GameObject attacker;
    PutMusic music;

    public GameScreen(List<GameObject> goodCharacters, List<GameObject> badCharacters, DeepDarkDungeonGame game, PutMusic music) {
        this.music = music;
        this.game = game;
        heroes = goodCharacters;
        monsters = badCharacters;
        goodCharacter1 = goodCharacters.get(0);
        goodCharacter2 = goodCharacters.get(1);
        goodCharacter3 = goodCharacters.get(2);
        goodCharacter4 = goodCharacters.get(3);
        badCharacter1 = badCharacters.get(0);
        badCharacter2 = badCharacters.get(1);
        badCharacter3 = badCharacters.get(2);
        badCharacter4 = badCharacters.get(3);
        this.music.setMusic("gameMelody.mp3");
    }

    @Override
    public void show() {
        batch = new SpriteBatch();
        background = new Texture(Gdx.files.internal("GameBackground.png"));
        vboiButton = new Texture(Gdx.files.internal("buttonVBOI.png"));
        heroesWinScreen = new Texture(Gdx.files.internal("You_Win_Screen.png"));
        mainMenuButton = new Texture(Gdx.files.internal("MainMenuSelected.png"));
        nextLevelButton = new Texture(Gdx.files.internal("NextLevelSelected.png"));
        monstersWinScreen = new Texture(Gdx.files.internal("YouLostScreen.png"));
        mainMenuButton2 = new Texture(Gdx.files.internal("MainMenuSelected2.png"));
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(135 / 255f, 206 / 255f, 235 / 255f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        batch.begin();
        batch.draw(background, 0, 0);
        batch.draw(vboiButton, VBOI_X, VBOI_Y, VBOI_WIDTH, VBOI_HEIGTH);
        batch.draw(goodCharacter1.getTexture(), 0, 400, 200, 220); // рисует персанажа (картинка)
        batch.draw(goodCharacter2.getTexture(), 200, 450, 200, 220);
        batch.draw(goodCharacter3.getTexture(), 400, 400, 200, 220);
        batch.draw(goodCharacter4.getTexture(), 600, 450, 200, 220);
        batch.draw(badCharacter1.getTexture(), 1100, (int) badCharacter1.getY(), 200, 220);
        batch.draw(badCharacter2.getTexture(), 1300, (int) badCharacter2.getY(), 200, 220);
        batch.draw(badCharacter3.getTexture(), 1500, (int) badCharacter3.getY(), 200, 220);
        batch.draw(badCharacter4.getTexture(), 1700, (int) badCharacter4.getY(), 200, 220);
        font.draw(batch, badCharacter1.getHealth() + "", 1200, 400);
        font.draw(batch, badCharacter2.getHealth() + "", 1400, 400);
        font.draw(batch, badCharacter3.getHealth() + "", 1600, 400);
        font.draw(batch, badCharacter4.getHealth() + "", 1800, 400);
        font.draw(batch, goodCharacter1.getHealth() + "", 80, 400);
        font.draw(batch, goodCharacter2.getHealth() + "", 280, 400);
        font.draw(batch, goodCharacter3.getHealth() + "", 480, 400);
        font.draw(batch, goodCharacter4.getHealth() + "", 680, 400);
        if (badCharacter1.getHealth() == 0 && badCharacter2.getHealth() == 0 && badCharacter3.getHealth() == 0 && badCharacter4.getHealth() == 0) {
            gameOver = true;
            batch.draw(heroesWinScreen, WIN_SCREEN_X, WIN_SCREEN_Y, WIN_SCREEN_WIDTH, WIN_SCREEN_HEIGHT);
            if (Gdx.input.getX() > MAIN_MENU_X_START && Gdx.input.getX() < MAIN_MENU_X_END && Gdx.input.getY() > MAIN_MENU_Y_START && Gdx.input.getY() < MAIN_MENU_Y_END) {
                batch.draw(mainMenuButton, 685, 380, 220, 95);
                if (Gdx.input.isButtonJustPressed(Input.Buttons.LEFT) && gameOver) {
                    game.setScreen(new MainMenuScreen(game, music));
                }
            }
            // Ильюша, сладкий, этот код для тебя ;*
            if (Gdx.input.getX() > NEXT_X_START && Gdx.input.getX() < NEXT_X_END && Gdx.input.getY() > NEXT_Y_START && Gdx.input.getY() < NEXT_Y_END) {
                batch.draw(nextLevelButton, 970, 380, 220, 95);
                if (Gdx.input.isButtonJustPressed(Input.Buttons.LEFT) && gameOver) {
                    // Для перехода в след уровень вставь код для открытия окна с уровнем сюда!
                }
            }
        }
        if (goodCharacter1.getHealth() == 0 && goodCharacter2.getHealth() == 0 && goodCharacter3.getHealth() == 0 && goodCharacter4.getHealth() == 0) {
            gameOver = true;
            batch.draw(monstersWinScreen, LOST_SCREEN_X, LOST_SCREEN_Y, LOST_SCREEN_WIDTH, LOST_SCREEN_HEIGHT);
            if (Gdx.input.getX() > MAIN_MENU2_X_START && Gdx.input.getX() < MAIN_MENU2_X_END && Gdx.input.getY() > MAIN_MENU2_Y_START && Gdx.input.getY() < MAIN_MENU2_Y_END) {
                batch.draw(mainMenuButton2, 835, 385, 228, 95);
                if (Gdx.input.isButtonJustPressed(Input.Buttons.LEFT) && gameOver) {
                    game.setScreen(new MainMenuScreen(game, music));
                }
            }
        }
        if (wait) {
            try {
                TimeUnit.SECONDS.sleep(5);
                wait = false;
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        if (stepCount % 2 != 0 && !gameOver) {
            if (WHOWILLATTACK == 4) {
                WHOWILLATTACK = 0;
            }
            if (WHOWILLATTACK == 0) {
                attacker = goodCharacter1;
            } else if (WHOWILLATTACK == 1) {
                attacker = goodCharacter2;
            } else if (WHOWILLATTACK == 2) {
                attacker = goodCharacter3;
            } else {
                attacker = goodCharacter4;
            }
            font.draw(batch, "Your turn! " + stepCount, 100, 1000); // Вызывает текст, тут например power персанажа
            batch.draw(attacker.getTexture(), 40, 130, 200, 220);
            if (stepCount > 1) {
                font.draw(batch, "In last step " + message, 100, 900);
            }
            //TODO
            if (Gdx.input.getX() < VBOI_X + VBOI_WIDTH && Gdx.input.getX() > VBOI_X && DeepDarkDungeonGame.HEIGHT - Gdx.input.getY() <= VBOI_Y + VBOI_HEIGTH && DeepDarkDungeonGame.HEIGHT - Gdx.input.getY() >= VBOI_Y) {
                if (Gdx.input.isButtonJustPressed(Input.Buttons.LEFT)) {
                    canbeattacked = true;
                    batch.draw(goodCharacter2.getTexture(), 200, 800);
                }
            }
            if (Gdx.input.getX() > badCharacter1.getX() && Gdx.input.getX() < badCharacter1.getX() + 200 && DeepDarkDungeonGame.HEIGHT - Gdx.input.getY() > badCharacter1.getY() && DeepDarkDungeonGame.HEIGHT - Gdx.input.getY() < badCharacter1.getY() + 300) {
                batch.draw(goodCharacter1.getTexture(), 200, 500);
                if (Gdx.input.isButtonJustPressed(Input.Buttons.LEFT) && canbeattacked && badCharacter1.getHealth() > 0) {
                    messageForMonsters = "You attacked " + badCharacter1.getName() + " with damage " + attacker.getPower(); //замени goodCharacter1 на персанажа который атакует в данный момент
                    badCharacter1.setHealth(Math.max(badCharacter1.getHealth() - attacker.getPower(), 0)); //замени goodCharacter1 на персанажа который атакует в данный момент, эта строчка отвечает за нанесение урона монстру.
                    stepCount += 1;
                    canbeattacked = false;
                    WHOWILLATTACK++;
                }
            }
            if (Gdx.input.getX() > badCharacter2.getX() && Gdx.input.getX() < badCharacter2.getX() + 200 && DeepDarkDungeonGame.HEIGHT - Gdx.input.getY() > badCharacter2.getY() && DeepDarkDungeonGame.HEIGHT - Gdx.input.getY() < badCharacter2.getY() + 300) {
                batch.draw(goodCharacter2.getTexture(), 200, 500);
                if (Gdx.input.isButtonJustPressed(Input.Buttons.LEFT) && canbeattacked && badCharacter2.getHealth() > 0) {
                    messageForMonsters = "You attacked " + badCharacter2.getName() + " with damage " + attacker.getPower(); //замени goodCharacter1 на персанажа который атакует в данный момент
                    badCharacter2.setHealth(Math.max(badCharacter2.getHealth() - attacker.getPower(), 0)); //замени goodCharacter1 на персанажа который атакует в данный момент, эта строчка отвечает за нанесение урона монстру.
                    stepCount += 1;
                    canbeattacked = false;
                    WHOWILLATTACK++;
                }
            }
            if (Gdx.input.getX() > badCharacter3.getX() && Gdx.input.getX() < badCharacter3.getX() + 200 && DeepDarkDungeonGame.HEIGHT - Gdx.input.getY() > badCharacter3.getY() && DeepDarkDungeonGame.HEIGHT - Gdx.input.getY() < badCharacter3.getY() + 300) {
                batch.draw(goodCharacter3.getTexture(), 200, 500);
                if (Gdx.input.isButtonJustPressed(Input.Buttons.LEFT) && canbeattacked && badCharacter3.getHealth() > 0) {
                    messageForMonsters = "You attacked " + badCharacter3.getName() + " with damage " + attacker.getPower(); //замени goodCharacter1 на персанажа который атакует в данный момент
                    badCharacter3.setHealth(Math.max(badCharacter3.getHealth() - attacker.getPower(), 0)); //замени goodCharacter1 на персанажа который атакует в данный момент, эта строчка отвечает за нанесение урона монстру.
                    stepCount += 1;
                    canbeattacked = false;
                    WHOWILLATTACK++;
                }
            }
            if (Gdx.input.getX() > badCharacter4.getX() && Gdx.input.getX() < badCharacter4.getX() + 200 && DeepDarkDungeonGame.HEIGHT - Gdx.input.getY() > badCharacter4.getY() && DeepDarkDungeonGame.HEIGHT - Gdx.input.getY() < badCharacter4.getY() + 300) {
                batch.draw(goodCharacter4.getTexture(), 200, 500);
                if (Gdx.input.isButtonJustPressed(Input.Buttons.LEFT) && canbeattacked && badCharacter4.getHealth() > 0) {
                    messageForMonsters = "You attacked " + badCharacter4.getName() + " with damage " + attacker.getPower(); //замени goodCharacter1 на персанажа который атакует в данный момент
                    badCharacter4.setHealth(Math.max(badCharacter4.getHealth() - attacker.getPower(), 0)); //замени goodCharacter1 на персанажа который атакует в данный момент, эта строчка отвечает за нанесение урона монстру.
                    stepCount += 1;
                    canbeattacked = false;
                    WHOWILLATTACK++;
                }
            }
        } else if (stepCount % 2 == 0 && !gameOver) {
            font.draw(batch, "Monsters turn! " + stepCount, 100, 1000);// Вызывает текст, тут например power персанажа
            font.draw(batch, messageForMonsters, 100, 950);
            batch.draw(attacker.getTexture(), 40, 130, 200, 220);
            if (badCharacter1.getHealth() > 0) {
                for (GameObject hero : heroes) {
                    if (hero.getHealth() > 0) {
                        message = badCharacter1.getName() + " attached " + hero.getName() + "." + "\n" + hero.getName() + " got " + badCharacter1.getPower() + " damage!";
                        font.draw(batch, message, 100, 900);
                        hero.setHealth(Math.max(hero.getHealth() - badCharacter1.getPower(), 0));
                        if (hero.getHealth() == 0) {
                            message += "\n" + hero.getName() + " is dead!";
                        }
                        stepCount ++;
                        wait = true;
                        break;
                    }
                }
            } else if (badCharacter2.getHealth() > 0) {
                for (GameObject hero : heroes) {
                    if (hero.getHealth() > 0) {
                        message = badCharacter2.getName() + " attached " + hero.getName() + "." + "\n" + hero.getName() + " got " + badCharacter2.getPower() + " damage!";
                        font.draw(batch, message, 100, 900);
                        hero.setHealth(Math.max(hero.getHealth() - badCharacter2.getPower(), 0));
                        if (hero.getHealth() == 0) {
                            message += "\n" + hero.getName() + " is dead!";
                        }
                        stepCount ++;
                        wait = true;
                        break;
                    }
                }
            } else if (badCharacter3.getHealth() > 0) {
                for (GameObject hero : heroes) {
                    if (hero.getHealth() > 0) {
                        message = badCharacter3.getName() + " attached " + hero.getName() + "." + "\n" + hero.getName() + " got " + badCharacter3.getPower() + " damage!";
                        font.draw(batch, message, 100, 900);
                        hero.setHealth(Math.max(hero.getHealth() - badCharacter3.getPower(), 0));
                        if (hero.getHealth() == 0) {
                            message += "\n" + hero.getName() + " is dead!";
                        }
                        stepCount ++;
                        wait = true;
                        break;
                    }
                }
            } else if (badCharacter4.getHealth() > 0) {
                for (GameObject hero : heroes) {
                    if (hero.getHealth() > 0) {
                        message = badCharacter4.getName() + " attached " + hero.getName() + "." + "\n" + hero.getName() + " got " + badCharacter4.getPower() + " damage!";
                        font.draw(batch, message, 100, 900);
                        hero.setHealth(Math.max(hero.getHealth() - badCharacter4.getPower(), 0));
                        if (hero.getHealth() == 0) {
                            message += "\n" + hero.getName() + " is dead!";
                        }
                        stepCount ++;
                        wait = true;
                        break;
                    }
                }
            }
        }
        //try {
            //Thread.sleep(3000);
        //} catch (InterruptedException e) {
            //e.printStackTrace();
        //}
        batch.end();
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
        batch.dispose();
        font.dispose();
    }
}