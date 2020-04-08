package ee.taltech.deepdarkdungeon.Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import ee.taltech.deepdarkdungeon.DeepDarkDungeonGame;
import ee.taltech.deepdarkdungeon.Models.GameObject;
import ee.taltech.deepdarkdungeon.Models.PutMusic;

import java.util.*;

public class GameScreen implements Screen {
    private static int WHOWILLATTACK = 0;
    private static final int VBOI_X = 300;
    private static final int VBOI_Y = 300;
    private static final int VBOI_HEIGTH = 50;
    private static final int VBOI_WIDTH = 50;
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
    private static final int FRAME_COLS = 10;
    private static final int FRAME_ROWS = 5;


    List<GameObject> heroes;
    List<GameObject> monsters;
    DeepDarkDungeonGame game;
    boolean gameOver = false;
    boolean canbeattacked = false;
    boolean wait = false;
    boolean skillIsPressed = false;
    String message;
    String messageForMonsters;
    BitmapFont font = new BitmapFont();
    private long stepCount = 1;
    private Texture monstersWinScreen;
    private Texture mainMenuButton2;
    private Texture nextLevelButton;
    private Texture mainMenuButton;
    private Texture heroesWinScreen;
    private Texture powerShot;
    private Texture attackbutton;
    private Texture attackbuttonacitve;
    private Texture background;
    private Texture defenceButton;
    private Texture aciveDefenceButton;
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
    int openLevelNumber;
    int lvlPlaying;
    boolean attackAnimationStarted = false;
    private int attackedHeroX;
    private int attackedHeroY;
    int flag = 0;

    private String monsterDamage = "";
    private String heroDamage = "";

    private GameObject attackedMonster;

    Animation monsterAttackAnimation; // анимация
    Texture monsterAttackSheet; // текстура анимации
    TextureRegion[] monsterAttackFrames;  // в этом массиве мы храним все кадры конкретной анимации
    TextureRegion currentFrame; // текуший кадр анимации

    float stateTime; // количество секунд прошедших с начала анимации

    public GameScreen(List<GameObject> goodCharacters, List<GameObject> badCharacters, DeepDarkDungeonGame game, PutMusic music, int openLevelNumber, int lvlPlaying) {
        this.lvlPlaying = lvlPlaying;
        this.openLevelNumber = openLevelNumber;
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
        attackbutton = new Texture(Gdx.files.internal("atackButton1.png"));
        attackbuttonacitve = new Texture(Gdx.files.internal("atackButton2.png"));
        defenceButton = new Texture(Gdx.files.internal("defenceButton1.png"));
        aciveDefenceButton = new Texture(Gdx.files.internal("defenceButton2.png"));
        powerShot = new Texture(Gdx.files.internal("pixil-frame-0_1.png"));
        heroesWinScreen = new Texture(Gdx.files.internal("You_Win_Screen.png"));
        mainMenuButton = new Texture(Gdx.files.internal("MainMenuSelected.png"));
        nextLevelButton = new Texture(Gdx.files.internal("NextLevelSelected.png"));
        monstersWinScreen = new Texture(Gdx.files.internal("YouLostScreen.png"));
        mainMenuButton2 = new Texture(Gdx.files.internal("MainMenuSelected2.png"));
        monsterAttackSheet = new Texture(Gdx.files.internal("explosionAttack.png"));
        // Дальше идет конструктор анимации атаки:
        TextureRegion[][] tmp = TextureRegion.split(monsterAttackSheet, monsterAttackSheet.getWidth()/FRAME_COLS, monsterAttackSheet.getHeight()/FRAME_ROWS);
        monsterAttackFrames = new TextureRegion[FRAME_COLS * FRAME_ROWS];
        int index = 0;
        for (int i = 0; i < FRAME_ROWS; i++) {
            for (int j = 0; j < FRAME_COLS; j++) {
                monsterAttackFrames[index++] = tmp[i][j];
            }
        }
        monsterAttackAnimation = new Animation(0.02f, monsterAttackFrames);
        monsterAttackAnimation.setPlayMode(Animation.PlayMode.NORMAL);
        stateTime = 0f;
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(135 / 255f, 206 / 255f, 235 / 255f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        // Следующие 2 строчки запускают отсчет времени для анимации и берут необходимый кадр для данного промежутка времени:
        //stateTime += Gdx.graphics.getDeltaTime();
        //currentFrame = (TextureRegion) monsterAttackAnimation.getKeyFrame(stateTime, true); //второй параметр нужен для зацикленности анимации
        //
        batch.begin();
        batch.draw(background, 0, 0);
        batch.draw(attackbutton, VBOI_X, VBOI_Y, VBOI_WIDTH, VBOI_HEIGTH);
        batch.draw(defenceButton, VBOI_X, VBOI_Y - 70, VBOI_WIDTH, VBOI_HEIGTH);
        batch.draw(goodCharacter1.getTexture(), goodCharacter1.getX(), goodCharacter1.getY(), 200, 220); // рисует персанажа (картинка)
        batch.draw(goodCharacter2.getTexture(), goodCharacter2.getX(), goodCharacter2.getY(), 200, 220);
        batch.draw(goodCharacter3.getTexture(), goodCharacter3.getX(), goodCharacter3.getY(), 200, 220);
        batch.draw(goodCharacter4.getTexture(), goodCharacter4.getX(), goodCharacter4.getY(), 200, 220);
        batch.draw(badCharacter1.getTexture(), badCharacter1.getX(), badCharacter1.getY(), 200, 220);
        batch.draw(badCharacter2.getTexture(), badCharacter2.getX(), badCharacter2.getY(), 200, 220);
        batch.draw(badCharacter3.getTexture(), badCharacter3.getX(), badCharacter3.getY(), 200, 220);
        batch.draw(badCharacter4.getTexture(), badCharacter4.getX(), badCharacter4.getY(), 200, 220);
        font.draw(batch, "Hp: " + badCharacter1.getHealth(), 1200, 400);
        font.draw(batch, "Hp: " + badCharacter2.getHealth(), 1400, 400);
        font.draw(batch, "Hp: " + badCharacter3.getHealth(), 1600, 400);
        font.draw(batch, "Hp: " + badCharacter4.getHealth(), 1800, 400);
        font.draw(batch, "Hp: " + goodCharacter1.getHealth(), 40, 400);
        font.draw(batch, "Hp: " + goodCharacter2.getHealth(), 240, 400);
        font.draw(batch, "Hp: " + goodCharacter3.getHealth(), 440, 400);
        font.draw(batch, "Hp: " + goodCharacter4.getHealth(), 640, 400);
        font.draw(batch, "Mn: " + goodCharacter1.getMana(), 115, 400);
        font.draw(batch, "Mn: " + goodCharacter2.getMana(), 315, 400);
        font.draw(batch, "Mn: " + goodCharacter3.getMana(), 515, 400);
        font.draw(batch, "Mn: " + goodCharacter4.getMana(), 715, 400);

        if (attackAnimationStarted) {
            font.draw(batch, "Monsters turn! " + stepCount, 100, 1000);
            font.draw(batch, messageForMonsters, 100, 950);
            if (monsterDamage.contains("30") && attackedMonster.equals(monsters.get(0))) {
                font.draw(batch, monsterDamage, attackedMonster.getX() + 90, attackedMonster.getY() + 250);
                font.draw(batch, monsterDamage, attackedMonster.getX() + 290, attackedMonster.getY() + 200);
            } else if (monsterDamage.contains("30") && attackedMonster.equals(monsters.get(1))) {
                font.draw(batch, monsterDamage, attackedMonster.getX() + 90, attackedMonster.getY() + 250);
                font.draw(batch, monsterDamage, attackedMonster.getX() + 290, attackedMonster.getY() + 300);
                font.draw(batch, monsterDamage, attackedMonster.getX() - 100, attackedMonster.getY() + 300);
            } else if (monsterDamage.contains("30") && attackedMonster.equals(monsters.get(2))) {
                font.draw(batch, monsterDamage, attackedMonster.getX() + 90, attackedMonster.getY() + 250);
                font.draw(batch, monsterDamage, attackedMonster.getX() + 290, attackedMonster.getY() + 200);
                font.draw(batch, monsterDamage, attackedMonster.getX() - 100, attackedMonster.getY() + 200);
            }
            else if (monsterDamage.contains("30") && attackedMonster.equals(monsters.get(3))) {
                font.draw(batch, monsterDamage, attackedMonster.getX() + 90, attackedMonster.getY() + 250);
                font.draw(batch, monsterDamage, attackedMonster.getX() - 100, attackedMonster.getY() + 300);
            } else {
                font.draw(batch, monsterDamage, attackedMonster.getX() + 90, attackedMonster.getY() + 250);
            }
            flag++;
            if (flag > 100) {
                monsterDamage = "";
                font.draw(batch, message, 100, 900 );
                font.draw(batch, heroDamage, attackedHeroX + 90, attackedHeroY + 250);
                stateTime += Gdx.graphics.getDeltaTime();
                currentFrame = (TextureRegion) monsterAttackAnimation.getKeyFrame(stateTime);
                batch.draw(currentFrame, attackedHeroX - 50, attackedHeroY, 300, 320);
            }
            if (monsterAttackAnimation.isAnimationFinished(stateTime) && flag > 200) {
                flag = 0;
                attackAnimationStarted = false;
                stateTime = 0f;
                stepCount++;
            }
        }

        if (badCharacter1.getHealth() == 0 && badCharacter2.getHealth() == 0 && badCharacter3.getHealth() == 0 && badCharacter4.getHealth() == 0 && !attackAnimationStarted) {
            if (lvlPlaying == openLevelNumber) {
                openLevelNumber++;
            }
            gameOver = true;
            batch.draw(heroesWinScreen, WIN_SCREEN_X, WIN_SCREEN_Y, WIN_SCREEN_WIDTH, WIN_SCREEN_HEIGHT);
            if (Gdx.input.getX() > MAIN_MENU_X_START && Gdx.input.getX() < MAIN_MENU_X_END && Gdx.input.getY() > MAIN_MENU_Y_START && Gdx.input.getY() < MAIN_MENU_Y_END) {
                batch.draw(mainMenuButton, 685, 380, 220, 95);
                if (Gdx.input.isButtonJustPressed(Input.Buttons.LEFT) && gameOver) {
                    game.setScreen(new MainMenuScreen(game, openLevelNumber, music));
                }
            }
            // Ильюша, сладкий, этот код для тебя ;*
            if (Gdx.input.getX() > NEXT_X_START && Gdx.input.getX() < NEXT_X_END && Gdx.input.getY() > NEXT_Y_START && Gdx.input.getY() < NEXT_Y_END) {
                batch.draw(nextLevelButton, 970, 380, 220, 95);
                if (Gdx.input.isButtonJustPressed(Input.Buttons.LEFT) && gameOver) {
                    music.stopMusic();
                    music = new PutMusic("startMelody.mp3");
                    game.setScreen(new SingleGameChooseScreen(game, openLevelNumber, music));
                }
            }
        }
        if (goodCharacter1.getHealth() == 0 && goodCharacter2.getHealth() == 0 && goodCharacter3.getHealth() == 0 && goodCharacter4.getHealth() == 0 && !attackAnimationStarted) {
            gameOver = true;
            batch.draw(monstersWinScreen, LOST_SCREEN_X, LOST_SCREEN_Y, LOST_SCREEN_WIDTH, LOST_SCREEN_HEIGHT);
            if (Gdx.input.getX() > MAIN_MENU2_X_START && Gdx.input.getX() < MAIN_MENU2_X_END && Gdx.input.getY() > MAIN_MENU2_Y_START && Gdx.input.getY() < MAIN_MENU2_Y_END) {
                batch.draw(mainMenuButton2, 835, 385, 228, 95);
                if (Gdx.input.isButtonJustPressed(Input.Buttons.LEFT) && gameOver) {
                    game.setScreen(new MainMenuScreen(game, openLevelNumber, music));
                }
            }
        }
        //if (wait) {
            //try {
                //TimeUnit.SECONDS.sleep(5);
                //wait = false;
            //} catch (InterruptedException e) {
                //e.printStackTrace();
            //}
        //}
        if (stepCount % 2 != 0 && !gameOver && !attackAnimationStarted) {
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
            font.draw(batch, "Health: ", 300, 200);
            font.draw(batch, attacker.getHealth() + "", 360, 200);
            font.draw(batch, "Mana: ", 300, 150);
            font.draw(batch, attacker.getMana() + "", 360, 150);
            font.draw(batch, "Your turn! " + stepCount, 100, 1000); // Вызывает текст, тут например power персанажа
            batch.draw(attacker.getTexture(), 40, 130, 200, 220);
            monsterDamage = "-" + attacker.getPower() + "HP";
            if (stepCount > 1) {
                font.draw(batch, "In last step " + message, 100, 900);
            }
            if (Gdx.input.getX() < VBOI_X + VBOI_WIDTH && Gdx.input.getX() > VBOI_X && DeepDarkDungeonGame.HEIGHT - Gdx.input.getY() <= VBOI_Y + VBOI_HEIGTH + 30 && DeepDarkDungeonGame.HEIGHT - Gdx.input.getY() >= VBOI_Y + 25) {
                batch.draw(attackbuttonacitve, VBOI_X, VBOI_Y, VBOI_WIDTH, VBOI_HEIGTH);
                if (Gdx.input.isButtonJustPressed(Input.Buttons.LEFT)) {
                    canbeattacked = true;
                    if (skillIsPressed) {
                        skillIsPressed = false;
                    }
                    batch.draw(goodCharacter2.getTexture(), 200, 800);
                }
            }
            if (Gdx.input.getX() < VBOI_X + VBOI_WIDTH && Gdx.input.getX() > VBOI_X && DeepDarkDungeonGame.HEIGHT - Gdx.input.getY() <= VBOI_Y - 70 + VBOI_HEIGTH + 30 && DeepDarkDungeonGame.HEIGHT - Gdx.input.getY() >= VBOI_Y - 70 + 25) {
                if (attacker.getSkill().equals("powershot") && attacker.getMana() >= 100 || attacker.getSkill().equals("sunstrike") && attacker.getMana() >= 50) {
                    batch.draw(aciveDefenceButton, VBOI_X, VBOI_Y - 70, VBOI_WIDTH, VBOI_HEIGTH);
                    if (Gdx.input.isButtonJustPressed(Input.Buttons.LEFT)) {
                        skillIsPressed = true;
                        if (canbeattacked) {
                            canbeattacked = false;
                        }
                        batch.draw(goodCharacter2.getTexture(), 200, 800);
                    }
                }
            }
            if (Gdx.input.getX() > badCharacter1.getX() && Gdx.input.getX() < badCharacter1.getX() + 200 && DeepDarkDungeonGame.HEIGHT - Gdx.input.getY() > badCharacter1.getY() && DeepDarkDungeonGame.HEIGHT - Gdx.input.getY() < badCharacter1.getY() + 300) {
                if (Gdx.input.isButtonJustPressed(Input.Buttons.LEFT) && canbeattacked && badCharacter1.getHealth() > 0) {
                    messageForMonsters = "You attacked " + badCharacter1.getName();
                    attackedMonster = badCharacter1;
                    badCharacter1.setHealth(Math.max(badCharacter1.getHealth() - attacker.getPower(), 0));
                    stepCount += 1;
                    canbeattacked = false;
                    WHOWILLATTACK++;
                } else if (skillIsPressed && Gdx.input.isButtonJustPressed(Input.Buttons.LEFT) && badCharacter1.getHealth() > 0) {
                    if (attacker.getSkill().equals("powershot")) {
                        //Double neededX = badCharacter1.getX();
                        //int start = (int) attacker.getX() + 200;

                        //while(start < neededX) {
                        //start++;
                        //batch.draw(powerShot, start += 0.000000000000000001, (int) attacker.getY() + 100, 150, 150);
                        //}
                        messageForMonsters = "You powershoted " + badCharacter1.getName();
                        attackedMonster = badCharacter1;
                        monsterDamage = "-100HP";
                        batch.draw(powerShot, (int) attacker.getX() + 185, (int) attacker.getY() - 25, 150, 150);
                        wait = true;
                        badCharacter1.setHealth(Math.max(badCharacter1.getHealth() - 100, 0));
                        batch.draw(powerShot, (int) badCharacter1.getX(), (int) badCharacter1.getY(), 150, 150);
                        attacker.setMana(attacker.getMana() - 100);
                        wait = true;
                        WHOWILLATTACK++;
                        stepCount += 1;
                        skillIsPressed = false;
                        // x 0 y 400
                    } else if (attacker.getSkill().equals("sunstrike")) {
                        messageForMonsters = "You used sunstrike on " + badCharacter1.getName() + " and nearby enemyes with damage 30";
                        badCharacter1.setHealth(Math.max(badCharacter1.getHealth() - 30, 0));
                        badCharacter2.setHealth(Math.max(badCharacter2.getHealth() - 30, 0));
                        attackedMonster = badCharacter1;
                        monsterDamage = "-30HP";
                        attacker.setMana(attacker.getMana() - 50);
                        WHOWILLATTACK++;
                        stepCount += 1;
                        skillIsPressed = false;
                    }
                }
            }
            if (Gdx.input.getX() > badCharacter2.getX() && Gdx.input.getX() < badCharacter2.getX() + 200 && DeepDarkDungeonGame.HEIGHT - Gdx.input.getY() > badCharacter2.getY() && DeepDarkDungeonGame.HEIGHT - Gdx.input.getY() < badCharacter2.getY() + 300) {
                if (Gdx.input.isButtonJustPressed(Input.Buttons.LEFT) && canbeattacked && badCharacter2.getHealth() > 0) {
                    messageForMonsters = "You attacked " + badCharacter2.getName();
                    attackedMonster = badCharacter2;
                    badCharacter2.setHealth(Math.max(badCharacter2.getHealth() - attacker.getPower(), 0));
                    stepCount += 1;
                    canbeattacked = false;
                    WHOWILLATTACK++;
                } else if (skillIsPressed && Gdx.input.isButtonJustPressed(Input.Buttons.LEFT) && badCharacter2.getHealth() > 0) {
                    if (attacker.getSkill().equals("powershot")) {
                        messageForMonsters = "You powershoted " + badCharacter2.getName();
                        monsterDamage = "-100HP";
                        batch.draw(powerShot, attacker.getX() + 185,  attacker.getY() - 25, 150, 150);
                        wait = true;
                        badCharacter2.setHealth(Math.max(badCharacter2.getHealth() - 100, 0));
                        batch.draw(powerShot, badCharacter2.getX(), badCharacter2.getY(), 150, 150);
                        attacker.setMana(attacker.getMana() - 100);
                        wait = true;
                        WHOWILLATTACK++;
                        stepCount += 1;
                        skillIsPressed = false;
                    } else if (attacker.getSkill().equals("sunstrike")) {
                        messageForMonsters = "You used sunstrike on " + badCharacter2.getName() + " and nearby enemyes with damage 30";
                        badCharacter1.setHealth(Math.max(badCharacter1.getHealth() - 30, 0));
                        badCharacter2.setHealth(Math.max(badCharacter2.getHealth() - 30, 0));
                        badCharacter3.setHealth(Math.max(badCharacter3.getHealth() - 30, 0));
                        attackedMonster = badCharacter2;
                        monsterDamage = "-30HP";
                        attacker.setMana(attacker.getMana() - 50);
                        WHOWILLATTACK++;
                        stepCount += 1;
                        skillIsPressed = false;
                    }
                }
            }
            if (Gdx.input.getX() > badCharacter3.getX() && Gdx.input.getX() < badCharacter3.getX() + 200 && DeepDarkDungeonGame.HEIGHT - Gdx.input.getY() > badCharacter3.getY() && DeepDarkDungeonGame.HEIGHT - Gdx.input.getY() < badCharacter3.getY() + 300) {
                if (Gdx.input.isButtonJustPressed(Input.Buttons.LEFT) && canbeattacked && badCharacter3.getHealth() > 0) {
                    messageForMonsters = "You attacked " + badCharacter3.getName();
                    attackedMonster = badCharacter3;
                    badCharacter3.setHealth(Math.max(badCharacter3.getHealth() - attacker.getPower(), 0)); //замени goodCharacter1 на персанажа который атакует в данный момент, эта строчка отвечает за нанесение урона монстру.
                    stepCount += 1;
                    canbeattacked = false;
                    WHOWILLATTACK++;
                } else if (skillIsPressed && Gdx.input.isButtonJustPressed(Input.Buttons.LEFT) && badCharacter3.getHealth() > 0) {
                    if (attacker.getSkill().equals("powershot")) {
                        messageForMonsters = "You powershoted " + badCharacter3.getName();
                        monsterDamage = "-100HP";
                        batch.draw(powerShot, (int) attacker.getX() + 185, (int) attacker.getY() - 25, 150, 150);
                        wait = true;
                        badCharacter3.setHealth(Math.max(badCharacter3.getHealth() - 100, 0));
                        batch.draw(powerShot, (int) badCharacter3.getX(), (int) badCharacter3.getY(), 150, 150);
                        attacker.setMana(attacker.getMana() - 100);
                        wait = true;
                        WHOWILLATTACK++;
                        stepCount += 1;
                        skillIsPressed = false;
                    } else if (attacker.getSkill().equals("sunstrike")) {
                        messageForMonsters = "You used sunstrike on " + badCharacter3.getName() + " and nearby enemyes with damage 30";
                        badCharacter2.setHealth(Math.max(badCharacter2.getHealth() - 30, 0));
                        badCharacter3.setHealth(Math.max(badCharacter3.getHealth() - 30, 0));
                        badCharacter4.setHealth(Math.max(badCharacter4.getHealth() - 30, 0));
                        attackedMonster = badCharacter3;
                        monsterDamage = "-30HP";
                        attacker.setMana(attacker.getMana() - 50);
                        WHOWILLATTACK++;
                        stepCount += 1;
                        skillIsPressed = false;
                    }
                }
            }
            if (Gdx.input.getX() > badCharacter4.getX() && Gdx.input.getX() < badCharacter4.getX() + 200 && DeepDarkDungeonGame.HEIGHT - Gdx.input.getY() > badCharacter4.getY() && DeepDarkDungeonGame.HEIGHT - Gdx.input.getY() < badCharacter4.getY() + 300) {
                if (Gdx.input.isButtonJustPressed(Input.Buttons.LEFT) && canbeattacked && badCharacter4.getHealth() > 0) {
                    messageForMonsters = "You attacked " + badCharacter4.getName();
                    attackedMonster = badCharacter4;
                    badCharacter4.setHealth(Math.max(badCharacter4.getHealth() - attacker.getPower(), 0)); //замени goodCharacter1 на персанажа который атакует в данный момент, эта строчка отвечает за нанесение урона монстру.
                    stepCount += 1;
                    canbeattacked = false;
                    WHOWILLATTACK++;
                } else if (skillIsPressed && Gdx.input.isButtonJustPressed(Input.Buttons.LEFT) && badCharacter4.getHealth() > 0) {
                    if (attacker.getSkill().equals("powershot")) {
                        messageForMonsters = "You powershoted " + badCharacter4.getName();
                        monsterDamage = "-100HP";
                        batch.draw(powerShot, (int) attacker.getX() + 185, (int) attacker.getY() - 25, 150, 150);
                        wait = true;
                        badCharacter4.setHealth(Math.max(badCharacter4.getHealth() - 100, 0));
                        batch.draw(powerShot, (int) badCharacter4.getX(), (int) badCharacter4.getY(), 150, 150);
                        attacker.setMana(attacker.getMana() - 100);
                        wait = true;
                        WHOWILLATTACK++;
                        stepCount += 1;
                        skillIsPressed = false;
                    } else if (attacker.getSkill().equals("sunstrike")) {
                        messageForMonsters = "You used sunstrike on " + badCharacter4.getName() + " and nearby enemyes with damage 30";
                        badCharacter3.setHealth(Math.max(badCharacter3.getHealth() - 30, 0));
                        badCharacter4.setHealth(Math.max(badCharacter4.getHealth() - 30, 0));
                        attackedMonster = badCharacter4;
                        monsterDamage = "-30HP";
                        attacker.setMana(attacker.getMana() - 50);
                        WHOWILLATTACK++;
                        stepCount += 1;
                        skillIsPressed = false;
                    }
                }
            }


        } else if (stepCount % 2 == 0 && !gameOver && !attackAnimationStarted) {
            if (goodCharacter1.getMana() < 100 && goodCharacter1.getHealth() > 0) {
                goodCharacter1.setMana(goodCharacter1.getMana() + 10);
                if (goodCharacter1.getMana() > 100) {
                    goodCharacter1.setMana(100);
                }
            }
            if (goodCharacter2.getMana() < 100 && goodCharacter2.getHealth() > 0) {
                goodCharacter2.setMana(goodCharacter2.getMana() + 10);
                if (goodCharacter2.getMana() > 100) {
                    goodCharacter2.setMana(100);
                }
            }
            if (goodCharacter3.getMana() < 100 && goodCharacter3.getHealth() > 0) {
                goodCharacter3.setMana(goodCharacter3.getMana() + 10);
                if (goodCharacter3.getMana() > 100) {
                    goodCharacter3.setMana(100);
                }
            }
            if (goodCharacter4.getMana() < 100 && goodCharacter4.getHealth() > 0) {
                goodCharacter4.setMana(goodCharacter4.getMana() + 10);
                if (goodCharacter4.getMana() > 100) {
                    goodCharacter4.setMana(100);
                }
            }
            batch.draw(attacker.getTexture(), 40, 130, 200, 220);
            if (badCharacter1.getHealth() > 0) {
                for (GameObject hero : heroes) {
                    if (hero.getHealth() > 0) {
                        attackAnimationStarted = true;
                        message = badCharacter1.getName() + " attached " + hero.getName();
                        heroDamage = "-" + badCharacter1.getPower() + "HP";
                        hero.setHealth(Math.max(hero.getHealth() - badCharacter1.getPower(), 0));
                        if (hero.getHealth() == 0) {
                            message += "\n" + hero.getName() + " is dead!";
                        }
                        attackedHeroX = hero.getX();
                        attackedHeroY = hero.getY();
                        //wait = true;
                        break;
                    }
                }
            } else if (badCharacter2.getHealth() > 0) {
                for (GameObject hero : heroes) {
                    if (hero.getHealth() > 0) {
                        attackAnimationStarted = true;
                        message = badCharacter2.getName() + " attached " + hero.getName();
                        heroDamage = "-" + badCharacter2.getPower() + "HP";
                        font.draw(batch, heroDamage, hero.getX() + 50, hero.getY() + 150);
                        hero.setHealth(Math.max(hero.getHealth() - badCharacter2.getPower(), 0));
                        if (hero.getHealth() == 0) {
                            message += "\n" + hero.getName() + " is dead!";
                        }
                        attackedHeroX = hero.getX();
                        attackedHeroY = hero.getY();
                        //wait = true;
                        break;
                    }
                }
            } else if (badCharacter3.getHealth() > 0) {
                for (GameObject hero : heroes) {
                    if (hero.getHealth() > 0) {
                        attackAnimationStarted = true;
                        message = badCharacter3.getName() + " attached " + hero.getName();
                        heroDamage = "-" + badCharacter3.getPower() + "HP";
                        font.draw(batch, heroDamage, hero.getX() + 50, hero.getY() + 150);
                        hero.setHealth(Math.max(hero.getHealth() - badCharacter3.getPower(), 0));
                        if (hero.getHealth() == 0) {
                            message += "\n" + hero.getName() + " is dead!";
                        }
                        attackedHeroX = hero.getX();
                        attackedHeroY = hero.getY();
                        //wait = true;
                        break;
                    }
                }
            } else if (badCharacter4.getHealth() > 0) {
                for (GameObject hero : heroes) {
                    if (hero.getHealth() > 0) {
                        attackAnimationStarted = true;
                        message = badCharacter4.getName() + " attached " + hero.getName();
                        heroDamage = "-" + badCharacter4.getPower() + "HP";
                        font.draw(batch, heroDamage, hero.getX() + 50, hero.getY() + 150);
                        hero.setHealth(Math.max(hero.getHealth() - badCharacter4.getPower(), 0));
                        if (hero.getHealth() == 0) {
                            message += "\n" + hero.getName() + " is dead!";
                        }
                        attackedHeroX = hero.getX();
                        attackedHeroY = hero.getY();
                        //wait = true;
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