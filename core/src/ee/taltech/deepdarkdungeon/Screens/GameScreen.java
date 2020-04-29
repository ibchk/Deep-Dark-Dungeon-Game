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

import java.util.LinkedList;
import java.util.List;
import java.util.Random;

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
    private static final int HERO_FRAME_COLS = 5;
    private static final int HERO_FRAME_ROWS = 3;
    private static final int FRAME_COLS_MONSTERS_ATTACK = 5;
    private static final int FRAME_ROWS_MONSTERS_ATTACK = 3;
    private static final int FRAME_COLS_MONSTERS_HEAL = 5;
    private static final int FRAME_ROWS_MONSTERS_HEAL = 4;


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
    boolean agr = false;
    boolean attackAnimationStarted = false;
    boolean sunstrikeAnimationStarted = false;
    boolean heroAttackAnimationStarted = false;
    private GameObject attackedHero;
    int flag = 0;
    int checkRandom = 0;

    private String monsterDamage = "";
    private String heroDamage = "";

    private GameObject attackedMonster;
    List<GameObject> monsterAttackedLast = new LinkedList<>();
    Random random = new Random();

    private boolean monsterHealAnimationStarted = false;

    private boolean addManaMonsters = false;

    Animation heroAttack;
    Texture heroAttackSheet;
    TextureRegion[] heroAttackFrames;
    TextureRegion currentHeroAttackFrames;

    float heroAttackStateTime;

    Animation sunstrikeAnimation; // анимация
    Texture sunstrikeSheet; // текстура анимации
    TextureRegion[] sunstrikeFrames;  // в этом массиве мы храним все кадры конкретной анимации
    TextureRegion currentSunstrikeFrame; // текуший кадр анимации

    float stateTime; // количество секунд прошедших с начала анимации

    Animation monsterAttackAnimation; // анимация
    Texture monsterAttackSheet; // текстура анимации
    TextureRegion[] monsterAttackFrames;  // в этом массиве мы храним все кадры конкретной анимации
    TextureRegion currentMonsterAttackFrame; // текуший кадр анимации
    float stateTimeMonsterAttack;

    Animation monsterHealAnimation; // анимация
    Texture monsterHealSheet; // текстура анимации
    TextureRegion[] monsterHealFrames;  // в этом массиве мы храним все кадры конкретной анимации
    TextureRegion currentMonsterHealFrame;
    float stateTimeMonsterHeal;

    public GameScreen(List<GameObject> goodCharacters, List<GameObject> badCharacters, DeepDarkDungeonGame game, PutMusic music, int openLevelNumber, int lvlPlaying) {
        this.lvlPlaying = lvlPlaying;
        this.openLevelNumber = openLevelNumber;
        this.music = music;
        this.game = game;
        heroes = goodCharacters;
        monsters = badCharacters;
        boolean playing = music.isPlaying();
        goodCharacter1 = goodCharacters.get(0);
        goodCharacter2 = goodCharacters.get(1);
        goodCharacter3 = goodCharacters.get(2);
        goodCharacter4 = goodCharacters.get(3);
        badCharacter1 = badCharacters.get(0);
        badCharacter2 = badCharacters.get(1);
        badCharacter3 = badCharacters.get(2);
        badCharacter4 = badCharacters.get(3);
        this.music.setMusic("gameMelody.mp3");
        if (playing) {
            this.music.playMusic();
        } else {
            this.music.stopMusic();
        }
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
        sunstrikeSheet = new Texture(Gdx.files.internal("explosionAttack.png"));
        monsterAttackSheet = new Texture(Gdx.files.internal("newMonsterAttackAnimation.png"));
        monsterHealSheet = new Texture(Gdx.files.internal("MonsterHealAnimation.png"));
        heroAttackSheet = new Texture(Gdx.files.internal("atacka.png"));

        TextureRegion[][] tmp4 = TextureRegion.split(heroAttackSheet, heroAttackSheet.getWidth() / HERO_FRAME_COLS, heroAttackSheet.getHeight() / HERO_FRAME_ROWS);
        heroAttackFrames = new TextureRegion[HERO_FRAME_ROWS * HERO_FRAME_COLS];
        int index4 = 0;
        for (int i = 0; i < HERO_FRAME_ROWS; i++) {
            for (int j = 0; j < HERO_FRAME_COLS; j++) {
                heroAttackFrames[index4++] = tmp4[i][j];
            }
        }
        heroAttack = new Animation(0.04f, heroAttackFrames);
        heroAttack.setPlayMode(Animation.PlayMode.NORMAL);
        heroAttackStateTime = 0f;
        // Дальше идет конструктор анимации: (это анимация санстрайка здесь менять ничего не нужно)
        TextureRegion[][] tmp = TextureRegion.split(sunstrikeSheet, sunstrikeSheet.getWidth() / FRAME_COLS, sunstrikeSheet.getHeight() / FRAME_ROWS);
        sunstrikeFrames = new TextureRegion[FRAME_COLS * FRAME_ROWS];
        int index = 0;
        for (int i = 0; i < FRAME_ROWS; i++) {
            for (int j = 0; j < FRAME_COLS; j++) {
                sunstrikeFrames[index++] = tmp[i][j];
            }
        }
        sunstrikeAnimation = new Animation(0.02f, sunstrikeFrames);
        sunstrikeAnimation.setPlayMode(Animation.PlayMode.NORMAL);
        stateTime = 0f;

        // Monster Attack Animation:

        TextureRegion[][] tmp2 = TextureRegion.split(monsterAttackSheet, monsterAttackSheet.getWidth() / FRAME_COLS_MONSTERS_ATTACK, monsterAttackSheet.getHeight() / FRAME_ROWS_MONSTERS_ATTACK);
        monsterAttackFrames = new TextureRegion[FRAME_COLS_MONSTERS_ATTACK * FRAME_ROWS_MONSTERS_ATTACK];
        int index2 = 0;
        for (int i = 0; i < FRAME_ROWS_MONSTERS_ATTACK; i++) {
            for (int j = 0; j < FRAME_COLS_MONSTERS_ATTACK; j++) {
                monsterAttackFrames[index2++] = tmp2[i][j];
            }
        }
        monsterAttackAnimation = new Animation(0.04f, monsterAttackFrames);
        monsterAttackAnimation.setPlayMode(Animation.PlayMode.NORMAL);
        stateTimeMonsterAttack = 0f;

        // Monster Heal Animation:

        TextureRegion[][] tmp3 = TextureRegion.split(monsterHealSheet, monsterHealSheet.getWidth() / FRAME_COLS_MONSTERS_HEAL, monsterHealSheet.getHeight() / FRAME_ROWS_MONSTERS_HEAL);
        monsterHealFrames = new TextureRegion[FRAME_COLS_MONSTERS_HEAL * FRAME_ROWS_MONSTERS_HEAL];
        int index3 = 0;
        for (int i = 0; i < FRAME_ROWS_MONSTERS_HEAL; i++) {
            for (int j = 0; j < FRAME_COLS_MONSTERS_HEAL; j++) {
                monsterHealFrames[index3++] = tmp3[i][j];
            }
        }
        monsterHealAnimation = new Animation(0.04f, monsterHealFrames);
        monsterHealAnimation.setPlayMode(Animation.PlayMode.NORMAL);
        stateTimeMonsterHeal = 0f;

    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(135 / 255f, 206 / 255f, 235 / 255f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        batch.begin();
        batch.draw(background, 0, 0);
        batch.draw(attackbutton, VBOI_X, VBOI_Y, VBOI_WIDTH, VBOI_HEIGTH);
        batch.draw(defenceButton, VBOI_X, VBOI_Y - 70, VBOI_WIDTH, VBOI_HEIGTH);
        for (GameObject hero : heroes) {
            batch.draw(hero.getTexture(), hero.getX(), hero.getY(), 200, 220);
            font.draw(batch, "Hp: " + hero.getHealth(), hero.getX() + 30, hero.getY() - 10);
            font.draw(batch, "Mn: " + hero.getMana(), hero.getX() + 100, hero.getY() - 10);
        }
        for (GameObject monster: monsters) {
            batch.draw(monster.getTexture(), monster.getX(), monster.getY(), 200, 220);
            font.draw(batch, "Hp: " + monster.getHealth(), monster.getX() + 30, monster.getY() - 10);
            if (monster.getBadCharacterClass().equals(GameObject.BadCharacterClass.NECROMANCER)) {
                font.draw(batch, "Mn: " + monster.getMana(), monster.getX() + 100, monster.getY() - 10);
            }
        }
        if (attackAnimationStarted || sunstrikeAnimationStarted || monsterHealAnimationStarted || heroAttackAnimationStarted) {
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
            } else if (monsterDamage.contains("30") && attackedMonster.equals(monsters.get(3))) {
                font.draw(batch, monsterDamage, attackedMonster.getX() + 90, attackedMonster.getY() + 250);
                font.draw(batch, monsterDamage, attackedMonster.getX() - 100, attackedMonster.getY() + 300);
            } else {
                font.draw(batch, monsterDamage, attackedMonster.getX() + 90, attackedMonster.getY() + 250);
            }
            flag++;
            if (flag > 100 && attackAnimationStarted) {
                monsterDamage = "";
                font.draw(batch, message, 100, 900);
                font.draw(batch, heroDamage, attackedHero.getX() + 90, attackedHero.getY() + 250);
                stateTimeMonsterAttack += Gdx.graphics.getDeltaTime();
                currentMonsterAttackFrame = (TextureRegion) monsterAttackAnimation.getKeyFrame(stateTimeMonsterAttack);
                batch.draw(currentMonsterAttackFrame, attackedHero.getX(), attackedHero.getY() - 20, 300, 320);
            } else if (flag > 100 && monsterHealAnimationStarted) {
                font.draw(batch, message, 100, 900);
                stateTimeMonsterHeal += Gdx.graphics.getDeltaTime();
                currentMonsterHealFrame = (TextureRegion) monsterHealAnimation.getKeyFrame(stateTimeMonsterHeal);
                batch.draw(currentMonsterHealFrame, attackedMonster.getX() - 50, attackedMonster.getY() - 40, 300, 320);
            } else if (flag > 100 && heroAttackAnimationStarted) {
                font.draw(batch, monsterDamage, attackedMonster.getX() + 90, attackedMonster.getY() + 250);
                heroAttackStateTime += Gdx.graphics.getDeltaTime();
                currentHeroAttackFrames = (TextureRegion) heroAttack.getKeyFrame(heroAttackStateTime);
                batch.draw(currentHeroAttackFrames, attackedMonster.getX() - 40, attackedMonster.getY() - 50, 300, 320);
            } else if (flag > 100 && sunstrikeAnimationStarted) {
                monsterDamage = "";
                stateTime += Gdx.graphics.getDeltaTime();
                currentSunstrikeFrame = (TextureRegion) sunstrikeAnimation.getKeyFrame(stateTime);
                if (attackedMonster.equals(monsters.get(0))) {
                    batch.draw(currentSunstrikeFrame, badCharacter1.getX(), badCharacter1.getY(), 300, 320);
                    batch.draw(currentSunstrikeFrame, badCharacter2.getX(), badCharacter2.getY(), 300, 320);
                } else if (attackedMonster.equals(monsters.get(1))) {
                    batch.draw(currentSunstrikeFrame, badCharacter1.getX(), badCharacter1.getY(), 300, 320);
                    batch.draw(currentSunstrikeFrame, badCharacter2.getX(), badCharacter2.getY(), 300, 320);
                    batch.draw(currentSunstrikeFrame, badCharacter3.getX(), badCharacter3.getY(), 300, 320);
                } else if (attackedMonster.equals(monsters.get(2))) {
                    batch.draw(currentSunstrikeFrame, badCharacter2.getX(), badCharacter2.getY(), 300, 320);
                    batch.draw(currentSunstrikeFrame, badCharacter3.getX(), badCharacter3.getY(), 300, 320);
                    batch.draw(currentSunstrikeFrame, badCharacter4.getX(), badCharacter4.getY(), 300, 320);
                } else if (attackedMonster.equals(monsters.get(3))) {
                    batch.draw(currentSunstrikeFrame, badCharacter3.getX(), badCharacter3.getY(), 300, 320);
                    batch.draw(currentSunstrikeFrame, badCharacter4.getX(), badCharacter4.getY(), 300, 320);
                }
            }
            if (heroAttack.isAnimationFinished(heroAttackStateTime) && flag > 200) {
                flag = 0;
                heroAttackAnimationStarted = false;
                heroAttackStateTime = 0f;
                stepCount++;
                WHOWILLATTACK++;
            }
            if (monsterAttackAnimation.isAnimationFinished(stateTimeMonsterAttack) && flag > 200) {
                flag = 0;
                attackAnimationStarted = false;
                stateTimeMonsterAttack = 0f;
                stepCount++;
            }
            if (sunstrikeAnimation.isAnimationFinished(stateTime) && flag > 200) {
                flag = 0;
                sunstrikeAnimationStarted = false;
                stateTime = 0f;
                stepCount++;
                WHOWILLATTACK++;
            }
            if (monsterHealAnimation.isAnimationFinished(stateTimeMonsterHeal) && flag > 200) {
                flag = 0;
                monsterHealAnimationStarted = false;
                stateTimeMonsterHeal = 0f;
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
                    game.setScreen(new MainMenuScreen(game, openLevelNumber, music, false));
                }
            }
            // Ильюша, сладкий, этот код для тебя ;*
            if (Gdx.input.getX() > NEXT_X_START && Gdx.input.getX() < NEXT_X_END && Gdx.input.getY() > NEXT_Y_START && Gdx.input.getY() < NEXT_Y_END) {
                batch.draw(nextLevelButton, 970, 380, 220, 95);
                if (Gdx.input.isButtonJustPressed(Input.Buttons.LEFT) && gameOver) {
                    boolean isMusicPlaying = music.isPlaying();
                    music.stopMusic();
                    music = new PutMusic("startMelody.mp3");
                    if (isMusicPlaying) {
                        music.playMusic();
                    } else {
                        music.stopMusic();
                    }
                    game.setScreen(new SingleGameChooseScreen(game, openLevelNumber, music, true));
                }
            }
        }
        if (goodCharacter1.getHealth() == 0 && goodCharacter2.getHealth() == 0 && goodCharacter3.getHealth() == 0 && goodCharacter4.getHealth() == 0 && !attackAnimationStarted) {
            gameOver = true;
            batch.draw(monstersWinScreen, LOST_SCREEN_X, LOST_SCREEN_Y, LOST_SCREEN_WIDTH, LOST_SCREEN_HEIGHT);
            if (Gdx.input.getX() > MAIN_MENU2_X_START && Gdx.input.getX() < MAIN_MENU2_X_END && Gdx.input.getY() > MAIN_MENU2_Y_START && Gdx.input.getY() < MAIN_MENU2_Y_END) {
                batch.draw(mainMenuButton2, 835, 385, 228, 95);
                if (Gdx.input.isButtonJustPressed(Input.Buttons.LEFT) && gameOver) {
                    game.setScreen(new MainMenuScreen(game, openLevelNumber, music, false));
                }
            }
        }
        if (stepCount % 2 != 0 && !gameOver && !attackAnimationStarted && !sunstrikeAnimationStarted && !heroAttackAnimationStarted) {

            if (addManaMonsters) {
                for (GameObject monster : monsters) {
                    if (monster.getBadCharacterClass().equals(GameObject.BadCharacterClass.NECROMANCER) && monster.getHealth() > 0 && monster.getMana() < 100) {
                        monster.setMana(monster.getMana() + 10);
                        if (monster.getMana() > 100) {
                            monster.setMana(100);
                        }
                    }
                }
                addManaMonsters = false;
            }

            if (WHOWILLATTACK >= 4) {
                WHOWILLATTACK = 0;
            }
            while (heroes.get(WHOWILLATTACK).getHealth() == 0) {
                WHOWILLATTACK++;
                if (WHOWILLATTACK >= 4) {
                    WHOWILLATTACK = 0;
                }
            }
            attacker = heroes.get(WHOWILLATTACK);
            font.draw(batch, "Health: ", 300, 200);
            font.draw(batch, attacker.getHealth() + "", 360, 200);
            font.draw(batch, "Mana: ", 300, 150);
            font.draw(batch, attacker.getMana() + "", 360, 150);
            font.draw(batch, "Your turn! " + stepCount, 100, 1000); // Вызывает текст, тут например power персанажа
            batch.draw(attacker.getTexture(), 40, 130, 200, 220);
            monsterDamage = "-" + attacker.getPower() + " HP";
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
                if (attacker.getSkill().equals("powershot") && attacker.getMana() >= 100 || attacker.getSkill().equals("sunstrike") && attacker.getMana() >= 50 || attacker.getSkill().equals("purification") && attacker.getMana() >= 40 || attacker.getSkill().equals("berserk call") && attacker.getMana() >= 40) {
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
            if (skillIsPressed && attacker.getSkill().equals("purification")) {
                GameObject needed = heroes.get(0);
                for (GameObject hero : heroes) {
                    if (needed.getHealth() > hero.getHealth()) {
                        needed = hero;
                    }
                }
                needed.setHealth(needed.getHealth() + 30);
                attacker.setMana(attacker.getMana() - 40);
                skillIsPressed = false;
                stepCount++;
                WHOWILLATTACK++;
                messageForMonsters = "";
                monsterDamage = "";
                attackedMonster = badCharacter1;

            }
            if (skillIsPressed && attacker.getSkill().equals("berserk call")) {
                stepCount++;
                WHOWILLATTACK++;
                skillIsPressed = false;
                attacker.setMana(attacker.getMana() - 40);
                attackedHero = attacker;
                messageForMonsters = "";
                monsterDamage = "";
                attackedMonster = badCharacter1;
                agr = true;
            }
            if (Gdx.input.getX() > badCharacter1.getX() && Gdx.input.getX() < badCharacter1.getX() + 200 && DeepDarkDungeonGame.HEIGHT - Gdx.input.getY() > badCharacter1.getY() && DeepDarkDungeonGame.HEIGHT - Gdx.input.getY() < badCharacter1.getY() + 300) {
                if (Gdx.input.isButtonJustPressed(Input.Buttons.LEFT) && canbeattacked && badCharacter1.getHealth() > 0) {
                    defAttack(badCharacter1);
                } else if (skillIsPressed && Gdx.input.isButtonJustPressed(Input.Buttons.LEFT) && badCharacter1.getHealth() > 0) {
                    if (attacker.getSkill().equals("powershot")) {
                        powerShot(badCharacter1);
                    } else if (attacker.getSkill().equals("sunstrike")) {
                        sunstrike2(badCharacter1, badCharacter2);
                    }
                }
            }
            if (Gdx.input.getX() > badCharacter2.getX() && Gdx.input.getX() < badCharacter2.getX() + 200 && DeepDarkDungeonGame.HEIGHT - Gdx.input.getY() > badCharacter2.getY() && DeepDarkDungeonGame.HEIGHT - Gdx.input.getY() < badCharacter2.getY() + 300) {
                if (Gdx.input.isButtonJustPressed(Input.Buttons.LEFT) && canbeattacked && badCharacter2.getHealth() > 0) {
                    defAttack(badCharacter2);
                } else if (skillIsPressed && Gdx.input.isButtonJustPressed(Input.Buttons.LEFT) && badCharacter2.getHealth() > 0) {
                    if (attacker.getSkill().equals("powershot")) {
                        powerShot(badCharacter2);
                    } else if (attacker.getSkill().equals("sunstrike")) {
                        sunstrike(badCharacter1, badCharacter2, badCharacter3);
                    }
                }
            }
            if (Gdx.input.getX() > badCharacter3.getX() && Gdx.input.getX() < badCharacter3.getX() + 200 && DeepDarkDungeonGame.HEIGHT - Gdx.input.getY() > badCharacter3.getY() && DeepDarkDungeonGame.HEIGHT - Gdx.input.getY() < badCharacter3.getY() + 300) {
                if (Gdx.input.isButtonJustPressed(Input.Buttons.LEFT) && canbeattacked && badCharacter3.getHealth() > 0) {
                    defAttack(badCharacter3);
                } else if (skillIsPressed && Gdx.input.isButtonJustPressed(Input.Buttons.LEFT) && badCharacter3.getHealth() > 0) {
                    if (attacker.getSkill().equals("powershot")) {
                        powerShot(badCharacter3);
                    } else if (attacker.getSkill().equals("sunstrike")) {
                        sunstrike(badCharacter2, badCharacter3, badCharacter4);
                    }
                }
            }
            if (Gdx.input.getX() > badCharacter4.getX() && Gdx.input.getX() < badCharacter4.getX() + 200 && DeepDarkDungeonGame.HEIGHT - Gdx.input.getY() > badCharacter4.getY() && DeepDarkDungeonGame.HEIGHT - Gdx.input.getY() < badCharacter4.getY() + 300) {
                if (Gdx.input.isButtonJustPressed(Input.Buttons.LEFT) && canbeattacked && badCharacter4.getHealth() > 0) {
                    defAttack(badCharacter4);
                } else if (skillIsPressed && Gdx.input.isButtonJustPressed(Input.Buttons.LEFT) && badCharacter4.getHealth() > 0) {
                    if (attacker.getSkill().equals("powershot")) {
                        powerShot(badCharacter4);
                    } else if (attacker.getSkill().equals("sunstrike")) {
                        sunstrike2(badCharacter3, badCharacter4);
                    }
                }
            }
        } else if (stepCount % 2 == 0 && !gameOver && !attackAnimationStarted && !sunstrikeAnimationStarted && !monsterHealAnimationStarted && !heroAttackAnimationStarted) {
            boolean clear = true;
            addManaMonsters = true;
            for (GameObject monster : monsters) {
                if (monster.getHealth() > 0 && !monsterAttackedLast.contains(monster)) {
                    clear = false;
                    break;
                }
            }
            if (clear) {
                monsterAttackedLast.clear();
            }
            int count = 0;
            GameObject hero = heroes.get(0);
            if (checkRandom % 2 == 1) {
                for (GameObject heros : heroes) {
                    if (count >= 4) {
                        break;
                    }
                    if (heros.getCharacterClass() == GameObject.CharacterClass.PALADIN && heros.getHealth() > 0) {
                        hero = heros;
                        break;
                    } else if (heros.getCharacterClass() == GameObject.CharacterClass.MAGIC && heros.getHealth() > 0) {
                        hero = heros;
                    } else if (heros.getCharacterClass() == GameObject.CharacterClass.ARCHER && hero.getCharacterClass()
                            != GameObject.CharacterClass.MAGIC && heros.getHealth() > 0) {
                        hero = heros;
                    } else if (heros.getCharacterClass() == GameObject.CharacterClass.WARIOR && hero.getCharacterClass()
                            != GameObject.CharacterClass.MAGIC && hero.getCharacterClass() != GameObject.CharacterClass.ARCHER
                            && heros.getHealth() > 0) {
                        hero = heros;
                    } // логика выбора кого атаковать, но может быть сломана, это ещё потестим.
                    count++;
                }
                checkRandom++;
            } else {
                do {
                    hero = heroes.get(random.nextInt(heroes.size()));
                } while (hero.getHealth() <= 0);
                checkRandom++;
            }
            for (GameObject weak : heroes) {
                if (weak.getHealth() <= 20 && weak.getHealth() > 0) {
                    hero = weak;
                    break;
                }
            }
            if (agr) {
                hero = attackedHero;
                agr = false;
            }
            for (GameObject manaUpdate : heroes) { //добовляет ману
                if (manaUpdate.getMana() < 100 && manaUpdate.getHealth() > 0) {
                    manaUpdate.setMana(manaUpdate.getMana() + 10);
                    if (manaUpdate.getMana() > 100) {
                        manaUpdate.setMana(100);
                    }
                }
            }
            batch.draw(attacker.getTexture(), 40, 130, 200, 220);
            for (GameObject monster : monsters) {
                boolean attackFlag = true;
                if (monster.getHealth() > 0 && !monsterAttackedLast.contains(monster)) {
                    if (monster.getBadCharacterClass().equals(GameObject.BadCharacterClass.NECROMANCER) && monster.getMana() >= 30) {
                        for (GameObject monsterToHeal : monsters) {
                            if (monsterToHeal.getHealth() <= 30) {
                                monsterHealAnimationStarted = true;
                                monsterToHeal.setHealth(monsterToHeal.getHealth() + 50);
                                message = monster.getName() + " cured " + monsterToHeal.getName();

                                heroDamage = "";
                                attackFlag = false;
                                monsterDamage = "+50 HP";
                                attackedMonster = monsterToHeal;
                                monster.setMana(monster.getMana() - 30);
                                break;
                            }
                        }
                    }
                    if (attackFlag) {
                        attackAnimationStarted = true;
                        message = monster.getName() + " attached " + hero.getName();
                        heroDamage = "-" + monster.getPower() + " HP";
                        hero.setHealth(Math.max(hero.getHealth() - monster.getPower(), 0));
                        if (hero.getHealth() == 0) {
                            message += "\n" + hero.getName() + " is dead!";
                        }
                    }
                    attackedHero = hero;
                    monsterAttackedLast.add(monster);
                    break;
                }
            }
        }
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

    private void powerShot(GameObject gameObject) {
        messageForMonsters = "You powershoted " + gameObject.getName();
        attackedMonster = badCharacter2;
        monsterDamage = "-100 HP";
        batch.draw(powerShot, attacker.getX() + 185, attacker.getY() - 25, 150, 150);
        wait = true;
        gameObject.setHealth(Math.max(gameObject.getHealth() - 100, 0));
        batch.draw(powerShot, gameObject.getX(), gameObject.getY(), 150, 150);
        attacker.setMana(attacker.getMana() - 100);
        wait = true;
        WHOWILLATTACK++;
        stepCount += 1;
        skillIsPressed = false;
    }

    private void defAttack(GameObject gameObject) {
        messageForMonsters = "You attacked " + gameObject.getName();
        attackedMonster = gameObject;
        monsterDamage = "-" + attacker.getPower() + " HP";
        gameObject.setHealth(Math.max(gameObject.getHealth() - attacker.getPower(), 0));
        canbeattacked = false;
        heroAttackAnimationStarted = true;
    }

    private void sunstrike(GameObject gameObject1, GameObject gameObject2, GameObject gameObject3) {
        messageForMonsters = "You used sunstrike on " + gameObject2.getName() + " and nearby enemyes with damage 30";
        gameObject1.setHealth(Math.max(gameObject1.getHealth() - 30, 0));
        gameObject2.setHealth(Math.max(gameObject2.getHealth() - 30, 0));
        gameObject3.setHealth(Math.max(gameObject3.getHealth() - 30, 0));
        attackedMonster = gameObject3;
        monsterDamage = "-30 HP";
        attacker.setMana(attacker.getMana() - 50);
        sunstrikeAnimationStarted = true;
        skillIsPressed = false;
    }

    private void sunstrike2(GameObject gameObject1, GameObject gameObject2) {
        messageForMonsters = "You used sunstrike on " + gameObject2.getName() + " and nearby enemyes with damage 30";
        gameObject1.setHealth(Math.max(gameObject1.getHealth() - 30, 0));
        gameObject2.setHealth(Math.max(gameObject2.getHealth() - 30, 0));
        attackedMonster = gameObject2;
        monsterDamage = "-30 HP";
        attacker.setMana(attacker.getMana() - 50);
        sunstrikeAnimationStarted = true;
        skillIsPressed = false;
    }
}