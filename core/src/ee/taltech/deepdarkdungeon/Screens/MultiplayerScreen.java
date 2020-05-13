package ee.taltech.deepdarkdungeon.Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import ee.taltech.deepdarkdungeon.Client.MPClient;
import ee.taltech.deepdarkdungeon.DeepDarkDungeonGame;
import ee.taltech.deepdarkdungeon.Models.GameObject;
import ee.taltech.deepdarkdungeon.Models.PutMusic;
import ee.taltech.deepdarkdungeon.Models.characterClasses.Archer;
import ee.taltech.deepdarkdungeon.Models.characterClasses.Magic;
import ee.taltech.deepdarkdungeon.Models.characterClasses.Paladin;
import ee.taltech.deepdarkdungeon.Models.characterClasses.Warrior;
import ee.taltech.deepdarkdungeon.animation.AnimationClass;

import java.util.LinkedList;
import java.util.List;

public class MultiplayerScreen implements Screen {
    private static final int VBOI_X = 300;
    private static final int VBOI_Y = 280;
    private static final int VBOI_HEIGTH = 50;
    private static final int VBOI_WIDTH = 50;
    private SpriteBatch batch;
    BitmapFont font = new BitmapFont();
    private Texture background;
    private boolean write = true;
    private List<GameObject> myCharacters;
    private List<String> heroNames;
    private MPClient client;
    private int WHOWILLATTACK = 0;
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
    private static final int FRAME_COLS_POWERSHOT = 4;
    private static final int FRAME_ROWS_POWERSHOT = 4;
    private static final int FRAME_COLS_HERO_HEAL = 5;
    private static final int FRAME_ROWS_HERO_HEAL = 5;
    private static final int FRAME_COLS_AGR = 5;
    private static final int FRAME_ROWS_AGR = 5;
    List<GameObject> enemyCharacters;
    DeepDarkDungeonGame game;
    boolean gameOver = false;
    boolean canbeattacked = false;
    boolean skillIsPressed = false;
    String messageForMonsters = "";
    private Texture monstersWinScreen;
    private Texture mainMenuButton2;
    private Texture attackbutton;
    private Texture attackbuttonacitve;
    private Texture healButton;
    private Texture healButtonActive;
    private Texture powershotButton;
    private Texture powershotButtonActive;
    private Texture sunstrikeButton;
    private Texture sunstrikeButtonActive;
    private Texture defenceButton;
    private Texture aciveDefenceButton;
    private GameObject goodCharacter1;
    private GameObject goodCharacter2;
    private GameObject goodCharacter3;
    private GameObject goodCharacter4;
    private GameObject badCharacter1;
    private GameObject badCharacter2;
    private GameObject badCharacter3;
    private GameObject badCharacter4;
    private GameObject attacker;
    private PutMusic music;
    private int openLevelNumber;

    private Texture backgroundIcons;
    private Texture heroIcon;


    public int enemyWhoAttacked;
    public int myAttackedCharacter;
    public boolean enemyUsedSkill;

    public boolean calculateDamage = true;

    private String monsterDamage = "";

    private GameObject attackedMonster;

    private boolean addManaMonsters = false;

    private boolean agrUsed = false;
    public GameObject heroUsedAgr;



    public MultiplayerScreen(List<GameObject> myChars, DeepDarkDungeonGame game, PutMusic music, int openLevelNumber) {
        this.game = game;
        this.music = music;
        this.openLevelNumber = openLevelNumber;
        this.myCharacters = myChars;
        heroNames = new LinkedList<>();
        for (GameObject hero : myChars) {
            heroNames.add(hero.name);
        }
        this.client = new MPClient(heroNames);
        boolean playing = music.isPlaying();
        this.music.setMusic("gameMelody.mp3");
        if (playing) {
            this.music.playMusic();
        } else {
            this.music.stopMusic();
        }
    }

    Texture heroAttackSheet;
    AnimationClass heroAttackAnimation;

    Texture sunstrikeSheet;
    AnimationClass sunstrikeAnimation;

    Texture powershotSheet;
    AnimationClass powershotAnimation;

    Texture heroHealSheet;
    AnimationClass heroHealAnimation;

    Texture agrSheet;
    AnimationClass agrAnimation;

    AnimationClass currentAnimation;
    boolean animationStarted = false;


    @Override
    public void show() {
        batch = new SpriteBatch();
        attackbutton = new Texture(Gdx.files.internal("atackButton1.png"));
        attackbuttonacitve = new Texture(Gdx.files.internal("atackButton2.png"));

        healButton = new Texture(Gdx.files.internal("healButton2.png"));
        healButtonActive = new Texture(Gdx.files.internal("healButton1.png"));
        powershotButton = new Texture(Gdx.files.internal("powershotButton2.png"));
        powershotButtonActive = new Texture(Gdx.files.internal("powershotButton1.png"));
        sunstrikeButton = new Texture(Gdx.files.internal("sunstrikeButton2.png"));
        sunstrikeButtonActive = new Texture(Gdx.files.internal("sunstrikeButton1.png"));healButton = new Texture(Gdx.files.internal("healButton2.png"));
        healButtonActive = new Texture(Gdx.files.internal("healButton1.png"));
        powershotButton = new Texture(Gdx.files.internal("powershotButton2.png"));
        powershotButtonActive = new Texture(Gdx.files.internal("powershotButton1.png"));
        sunstrikeButton = new Texture(Gdx.files.internal("sunstrikeButton2.png"));
        sunstrikeButtonActive = new Texture(Gdx.files.internal("sunstrikeButton1.png"));

        defenceButton = new Texture(Gdx.files.internal("defenceButton1.png"));
        aciveDefenceButton = new Texture(Gdx.files.internal("defenceButton2.png"));

        powershotSheet = new Texture(Gdx.files.internal("powershot.png"));
        monstersWinScreen = new Texture(Gdx.files.internal("YouLostScreen.png"));
        mainMenuButton2 = new Texture(Gdx.files.internal("MainMenuSelected2.png"));
        sunstrikeSheet = new Texture(Gdx.files.internal("explosionAttack.png"));
        heroAttackSheet = new Texture(Gdx.files.internal("atacka.png"));
        heroHealSheet = new Texture(Gdx.files.internal("heroHealAnimation.png"));
        agrSheet = new Texture(Gdx.files.internal("agrAnimation.png"));

        background = new Texture(Gdx.files.internal("dungeonBackground.png"));
        heroIcon = new Texture(Gdx.files.internal("heroIcon.png"));
        backgroundIcons = new Texture(Gdx.files.internal("backgroundIcons.png"));

        heroAttackAnimation =  new AnimationClass(heroAttackSheet, HERO_FRAME_ROWS, HERO_FRAME_COLS);

        sunstrikeAnimation = new AnimationClass(sunstrikeSheet, FRAME_ROWS, FRAME_COLS);

        powershotAnimation = new AnimationClass(powershotSheet, FRAME_ROWS_POWERSHOT, FRAME_COLS_POWERSHOT);

        heroHealAnimation = new AnimationClass(heroHealSheet, FRAME_ROWS_HERO_HEAL, FRAME_COLS_HERO_HEAL);

        agrAnimation = new AnimationClass(agrSheet, FRAME_ROWS_AGR, FRAME_COLS_AGR);
    }

    @Override
    public void render(float delta) {
        if (WHOWILLATTACK >= 4) {
            WHOWILLATTACK = 0;
        }
        while (myCharacters.get(WHOWILLATTACK).getHealth() == 0) {
            WHOWILLATTACK++;
            if (WHOWILLATTACK >= 4) {
                WHOWILLATTACK = 0;
            }
        }
        if (client.game) {
            System.out.println("hui");
            if (write) {
                List<String> enemyCharactersString = client.enemy;
                write = false;
                this.enemyCharacters = createEnemies(enemyCharactersString);
                badCharacter1 = enemyCharacters.get(0);
                badCharacter2 = enemyCharacters.get(1);
                badCharacter3 = enemyCharacters.get(2);
                badCharacter4 = enemyCharacters.get(3);
                goodCharacter1 = myCharacters.get(0);
                goodCharacter2 = myCharacters.get(1);
                goodCharacter3 = myCharacters.get(2);
                goodCharacter4 = myCharacters.get(3);

            }
            if (!client.myTurn) {
                client.canIAttack();
            }
            Gdx.gl.glClearColor(135 / 255f, 206 / 255f, 235 / 255f, 1);
            Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
            batch.begin();
            batch.draw(background, 0, 0, 1920, 1080);
            batch.draw(heroIcon, 30, 120);
            batch.draw(backgroundIcons, 250, 113);
            batch.draw(attackbutton, VBOI_X, VBOI_Y, VBOI_WIDTH, VBOI_HEIGTH);
            GameObject myHero = myCharacters.get(WHOWILLATTACK);
            switch (myHero.getName()) {
                case "Warrior":
                    batch.draw(defenceButton, VBOI_X, VBOI_Y - 70, VBOI_WIDTH, VBOI_HEIGTH);
                    break;
                case "Archer":
                    batch.draw(powershotButton, VBOI_X, VBOI_Y - 70, VBOI_WIDTH, VBOI_HEIGTH);
                    break;
                case "Wizard":
                    batch.draw(sunstrikeButton, VBOI_X, VBOI_Y - 70, VBOI_WIDTH, VBOI_HEIGTH);
                    break;
                case "Paladin":
                    batch.draw(healButton, VBOI_X, VBOI_Y - 70, VBOI_WIDTH, VBOI_HEIGTH);
                    break;
            }

            this.enemyWhoAttacked = client.characterWhoAttacked;
            this.myAttackedCharacter = client.attachedCharacter;
            this.enemyUsedSkill = client.skillUsed;
            for (GameObject hero : myCharacters) {
                batch.draw(hero.getTexture(), hero.getX(), hero.getY(), 200, 220);
                font.draw(batch, "Hp: " + hero.getHealth(), hero.getX() + 30, hero.getY() - 10);
                font.draw(batch, "Mn: " + hero.getMana(), hero.getX() + 100, hero.getY() - 10);
            }
            for (GameObject monster :enemyCharacters) {
                batch.draw(monster.getTexture(), monster.getX(), monster.getY(), 200, 220);
                font.draw(batch, "Hp: " + monster.getHealth(), monster.getX() + 50, monster.getY() - 10);
            }
            font.draw(batch, messageForMonsters, 100, 950);

            if (client.myTurn) {
                font.draw(batch, "Your turn! ", 100, 1000);
            } else {
                font.draw(batch, "Enemy turn! ", 100, 1000);
            }
            if ((client.myTurn) && !gameOver && !animationStarted) {
                if (addManaMonsters) {
                    for (GameObject monster :myCharacters) {
                        if (monster.getHealth() > 0 && monster.getMana() < 100) {
                            monster.setMana(monster.getMana() + 10);
                            if (monster.getMana() > 100) {
                                monster.setMana(100);
                            }
                        }
                    }
                    addManaMonsters = false;
                }

                GameObject badCharacter = null;
                GameObject myAttackedHero = null;

                for (GameObject enemy : enemyCharacters) {
                    if (enemy.getPlace() == enemyWhoAttacked) {
                        badCharacter = enemy;
                        break;
                    }
                }
                for (GameObject hero : myCharacters) {
                    if (hero.getPlace() == myAttackedCharacter) {
                        myAttackedHero = hero;
                        break;
                    }
                }
                if (calculateDamage && !enemyUsedSkill && !(myAttackedHero == null) && !(badCharacter == null)) {
                    attacker = badCharacter;
                    attackUs(myAttackedHero);
                }
                if (calculateDamage && enemyUsedSkill && !(myAttackedHero == null) && !(badCharacter == null)) {
                    attacker = badCharacter;
                    if ((badCharacter.getSkill().equals("powershot") && attacker.getMana() >= 100)) {
                        powerShotUs(myAttackedHero);
                    } else if ((badCharacter.getSkill().equals("sunstrike") && badCharacter.getMana() >= 100)) {
                        if (myAttackedCharacter == 2) {
                            sunstrikeUs(goodCharacter1, myAttackedHero, goodCharacter3);
                        } else if (myAttackedCharacter == 3) {
                            sunstrikeUs(goodCharacter2, myAttackedHero, goodCharacter4);
                        } else if (myAttackedCharacter == 1) {
                            sunstrike2Us(myAttackedHero, goodCharacter2);
                        } else {
                            sunstrike2Us(goodCharacter3, myAttackedHero);
                        }
                    } else if (badCharacter.getSkill().equals("purification")) {
                        healThem();
                    } else if (badCharacter.getSkill().equals("berserk call")){
                        agr(attacker);
                    }
                }
                attacker = myCharacters.get(WHOWILLATTACK);
                font.draw(batch, "Health: ", 300, 200);
                font.draw(batch, attacker.getHealth() + "", 360, 200);
                font.draw(batch, "Mana: ", 300, 150);
                font.draw(batch, attacker.getMana() + "", 360, 150);
                batch.draw(attacker.getTexture(), 40, 130, 200, 220);
                if (agrUsed && heroUsedAgr != null) {
                    agrUsed = false;
                    System.out.println(heroUsedAgr.getPlace()); //TODO: короче, ошибка здесь, он не переписывает героя который юзал агр, как исправлять я не ебу, время 3 часа ночи бляд
                    defAttack(heroUsedAgr);
                }
                if (Gdx.input.getX() < VBOI_X + VBOI_WIDTH && Gdx.input.getX() > VBOI_X && DeepDarkDungeonGame.HEIGHT - Gdx.input.getY() <= VBOI_Y + VBOI_HEIGTH + 30 && DeepDarkDungeonGame.HEIGHT - Gdx.input.getY() >= VBOI_Y + 25) {
                    batch.draw(attackbuttonacitve, VBOI_X, VBOI_Y, VBOI_WIDTH, VBOI_HEIGTH);
                    if (Gdx.input.isButtonJustPressed(Input.Buttons.LEFT)) {
                        canbeattacked = true;
                        if (skillIsPressed) {
                            skillIsPressed = false;
                        }
                    }
                }
                //  || ||  attacker.getSkill().equals("berserk call") && attacker.getMana() >= 40
                if (Gdx.input.getX() < VBOI_X + VBOI_WIDTH && Gdx.input.getX() > VBOI_X && DeepDarkDungeonGame.HEIGHT - Gdx.input.getY() <= VBOI_Y - 70 + VBOI_HEIGTH + 30 && DeepDarkDungeonGame.HEIGHT - Gdx.input.getY() >= VBOI_Y - 70 + 25) {
                    if (attacker.getSkill().equals("powershot") && attacker.getMana() >= 100) {
                        batch.draw(powershotButtonActive, VBOI_X, VBOI_Y - 70, VBOI_WIDTH, VBOI_HEIGTH);
                    } else if (attacker.getSkill().equals("sunstrike") && attacker.getMana() >= 50) {
                        batch.draw(sunstrikeButtonActive, VBOI_X, VBOI_Y - 70, VBOI_WIDTH, VBOI_HEIGTH);
                    } else if (attacker.getSkill().equals("purification") && attacker.getMana() >= 40) {
                        batch.draw(healButtonActive, VBOI_X, VBOI_Y - 70, VBOI_WIDTH, VBOI_HEIGTH);
                    } else if (attacker.getSkill().equals("berserk call") && attacker.getMana() >= 40) {
                        batch.draw(aciveDefenceButton, VBOI_X, VBOI_Y - 70, VBOI_WIDTH, VBOI_HEIGTH);
                    }
                    if (Gdx.input.isButtonJustPressed(Input.Buttons.LEFT)) {
                        skillIsPressed = true;
                        if (canbeattacked) {
                            canbeattacked = false;
                        }
                    }
                }
                if (skillIsPressed && attacker.getSkill().equals("purification")) {
                    heal();
                }
                if (skillIsPressed && attacker.getSkill().equals("berserk call")) {
                    attackedMonster = attacker;
                    skillIsPressed = false;
                    WHOWILLATTACK++;
                    attacker.setMana(attacker.getMana() - 40);
                    messageForMonsters = attacker.getName() + " used berserk call";
                    monsterDamage = "";
                    calculateDamage = true;
                    animationStarted = true;
                    currentAnimation = agrAnimation;
                    client.sendGameInfo(attacker.getPlace(), attackedMonster.getPlace(), true);
                }
                for (GameObject monster : enemyCharacters) {
                    if (Gdx.input.getX() > monster.getX() && Gdx.input.getX() < monster.getX() + 200 && DeepDarkDungeonGame.HEIGHT - Gdx.input.getY() > monster.getY() && DeepDarkDungeonGame.HEIGHT - Gdx.input.getY() < monster.getY() + 300) {
                        if (Gdx.input.isButtonJustPressed(Input.Buttons.LEFT) && canbeattacked && monster.getHealth() > 0) {
                            defAttack(monster);
                        } else if (skillIsPressed && Gdx.input.isButtonJustPressed(Input.Buttons.LEFT) && monster.getHealth() > 0) {
                            if (attacker.getSkill().equals("powershot")) {
                                powerShot(monster);
                            } else if (attacker.getSkill().equals("sunstrike") && enemyCharacters.indexOf(monster) == 0) {
                                sunstrike2(badCharacter1, badCharacter2);
                            } else if (attacker.getSkill().equals("sunstrike") && enemyCharacters.indexOf(monster) == 3) {
                                sunstrike2(badCharacter3, badCharacter4);
                            } else if (attacker.getSkill().equals("sunstrike") && enemyCharacters.indexOf(monster) == 2) {
                                sunstrike(badCharacter2, badCharacter3, badCharacter4);
                            } else if (attacker.getSkill().equals("sunstrike") && enemyCharacters.indexOf(monster) == 1) {
                                sunstrike(badCharacter1, badCharacter2, badCharacter3);
                            }
                        }
                    }
                }
            }
            if (animationStarted) {
                if (currentAnimation.equals(sunstrikeAnimation)) {
                    if (attackedMonster.equals(enemyCharacters.get(0))) {
                        font.draw(batch, monsterDamage, attackedMonster.getX() + 90, attackedMonster.getY() + 230);
                        font.draw(batch, monsterDamage, attackedMonster.getX() + 290, attackedMonster.getY() + 180);
                    } else if (attackedMonster.equals(myCharacters.get(0))) {
                        font.draw(batch, monsterDamage, attackedMonster.getX() + 90, attackedMonster.getY() + 230);
                        font.draw(batch, monsterDamage, attackedMonster.getX() + 290, attackedMonster.getY() + 280);
                    } else if (attackedMonster.equals(enemyCharacters.get(1))) {
                        font.draw(batch, monsterDamage, attackedMonster.getX() + 90, attackedMonster.getY() + 230);
                        font.draw(batch, monsterDamage, attackedMonster.getX() + 290, attackedMonster.getY() + 280);
                        font.draw(batch, monsterDamage, attackedMonster.getX() - 100, attackedMonster.getY() + 280);
                    } else if (attackedMonster.equals(myCharacters.get(1))) {
                        font.draw(batch, monsterDamage, attackedMonster.getX() + 90, attackedMonster.getY() + 230);
                        font.draw(batch, monsterDamage, attackedMonster.getX() + 290, attackedMonster.getY() + 180);
                        font.draw(batch, monsterDamage, attackedMonster.getX() - 100, attackedMonster.getY() + 180);
                    } else if (attackedMonster.equals(enemyCharacters.get(2))) {
                        font.draw(batch, monsterDamage, attackedMonster.getX() + 90, attackedMonster.getY() + 230);
                        font.draw(batch, monsterDamage, attackedMonster.getX() + 290, attackedMonster.getY() + 180);
                        font.draw(batch, monsterDamage, attackedMonster.getX() - 100, attackedMonster.getY() + 180);
                    } else if (attackedMonster.equals(myCharacters.get(2))) {
                        font.draw(batch, monsterDamage, attackedMonster.getX() + 90, attackedMonster.getY() + 230);
                        font.draw(batch, monsterDamage, attackedMonster.getX() + 290, attackedMonster.getY() + 280);
                        font.draw(batch, monsterDamage, attackedMonster.getX() - 100, attackedMonster.getY() + 280);
                    } else if (attackedMonster.equals(enemyCharacters.get(3))) {
                        font.draw(batch, monsterDamage, attackedMonster.getX() + 90, attackedMonster.getY() + 230);
                        font.draw(batch, monsterDamage, attackedMonster.getX() - 100, attackedMonster.getY() + 280);
                    } else if (attackedMonster.equals(myCharacters.get(3))) {
                        font.draw(batch, monsterDamage, attackedMonster.getX() + 90, attackedMonster.getY() + 230);
                        font.draw(batch, monsterDamage, attackedMonster.getX() - 100, attackedMonster.getY() + 180);
                    }
                } else {
                    font.draw(batch, monsterDamage, attackedMonster.getX() + 90, attackedMonster.getY() + 250);
                }
                currentAnimation.startAnimation();
                if (currentAnimation.equals(sunstrikeAnimation)) {
                    if (attackedMonster.equals(enemyCharacters.get(0))) {
                        batch.draw(sunstrikeAnimation.currentFrame, attackedMonster.getX() - 40, attackedMonster.getY(), 300, 320);
                        batch.draw(sunstrikeAnimation.currentFrame, attackedMonster.getX() + 150, attackedMonster.getY() - 50, 300, 320);
                    } else if (attackedMonster.equals(myCharacters.get(0))) {
                        batch.draw(sunstrikeAnimation.currentFrame, attackedMonster.getX() - 40, attackedMonster.getY(), 300, 320);
                        batch.draw(sunstrikeAnimation.currentFrame, attackedMonster.getX() + 150, attackedMonster.getY() + 50, 300, 320);
                    } else if (attackedMonster.equals(enemyCharacters.get(1))) {
                        batch.draw(sunstrikeAnimation.currentFrame, attackedMonster.getX() - 40, attackedMonster.getY(), 300, 320);
                        batch.draw(sunstrikeAnimation.currentFrame, attackedMonster.getX() + 150, attackedMonster.getY() + 50, 300, 320);
                        batch.draw(sunstrikeAnimation.currentFrame, attackedMonster.getX() - 230, attackedMonster.getY() + 50, 300, 320);
                    } else if (attackedMonster.equals(myCharacters.get(1))) {
                        batch.draw(sunstrikeAnimation.currentFrame, attackedMonster.getX() - 40, attackedMonster.getY(), 300, 320);
                        batch.draw(sunstrikeAnimation.currentFrame, attackedMonster.getX() + 150, attackedMonster.getY() - 50, 300, 320);
                        batch.draw(sunstrikeAnimation.currentFrame, attackedMonster.getX() - 230, attackedMonster.getY() - 50, 300, 320);
                    } else if (attackedMonster.equals(enemyCharacters.get(2))) {
                        batch.draw(sunstrikeAnimation.currentFrame, attackedMonster.getX() - 40, attackedMonster.getY(), 300, 320);
                        batch.draw(sunstrikeAnimation.currentFrame, attackedMonster.getX() + 150, attackedMonster.getY() - 50, 300, 320);
                        batch.draw(sunstrikeAnimation.currentFrame, attackedMonster.getX() - 230, attackedMonster.getY() - 50, 300, 320);
                    } else if (attackedMonster.equals(myCharacters.get(2))) {
                        batch.draw(sunstrikeAnimation.currentFrame, attackedMonster.getX() - 40, attackedMonster.getY(), 300, 320);
                        batch.draw(sunstrikeAnimation.currentFrame, attackedMonster.getX() + 150, attackedMonster.getY() + 50, 300, 320);
                        batch.draw(sunstrikeAnimation.currentFrame, attackedMonster.getX() - 230, attackedMonster.getY() + 50, 300, 320);
                    }  else if (attackedMonster.equals(enemyCharacters.get(3))) {
                        batch.draw(sunstrikeAnimation.currentFrame, attackedMonster.getX() - 40, attackedMonster.getY(), 300, 320);
                        batch.draw(sunstrikeAnimation.currentFrame, attackedMonster.getX() - 230, attackedMonster.getY() + 50, 300, 320);
                    } else if (attackedMonster.equals(myCharacters.get(3))) {
                        batch.draw(sunstrikeAnimation.currentFrame, attackedMonster.getX() - 40, attackedMonster.getY(), 300, 320);
                        batch.draw(sunstrikeAnimation.currentFrame, attackedMonster.getX() - 230, attackedMonster.getY() - 50, 300, 320);
                    }
                } else {
                    batch.draw(currentAnimation.currentFrame, attackedMonster.getX() - 40, attackedMonster.getY() - 50, 300, 320);
                }
                if (currentAnimation.animation.isAnimationFinished(currentAnimation.stateTime)) {
                    animationStarted = false;
                    currentAnimation.stateTime = 0f;
                    if (currentAnimation.equals(agrAnimation)) {
                        agrUsed = true;
                    }
                }
            }

            if (((badCharacter1.getHealth() == 0 && badCharacter2.getHealth() == 0 && badCharacter3.getHealth() == 0 && badCharacter4.getHealth() == 0) || goodCharacter1.getHealth() == 0 && goodCharacter2.getHealth() == 0 && goodCharacter3.getHealth() == 0 && goodCharacter4.getHealth() == 0) && !animationStarted) {
                gameOver = true;
                batch.draw(monstersWinScreen, LOST_SCREEN_X, LOST_SCREEN_Y, LOST_SCREEN_WIDTH, LOST_SCREEN_HEIGHT);
                if (Gdx.input.getX() > MAIN_MENU2_X_START && Gdx.input.getX() < MAIN_MENU2_X_END && Gdx.input.getY() > MAIN_MENU2_Y_START && Gdx.input.getY() < MAIN_MENU2_Y_END) {
                    batch.draw(mainMenuButton2, 835, 385, 228, 95);
                    if (Gdx.input.isButtonJustPressed(Input.Buttons.LEFT) && gameOver) {
                        client.client.close();
                        game.setScreen(new MainMenuScreen(game, openLevelNumber, music, false)); // TODO: хуй его знает что делать с openLevelNumber, после игры в мультиплеер он будет 1; нужно дисконнектнуться от сервера
                    }
                }
            }
            batch.end();
        }
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

    private void agr(GameObject gameObject) {
        animationStarted = true;
        currentAnimation = agrAnimation;
        attackedMonster = gameObject;
        calculateDamage = false;
        heroUsedAgr = attackedMonster;
    }

    private void powerShot(GameObject gameObject) {
        messageForMonsters = "You powershoted and killed" + gameObject.getName();
        attackedMonster = gameObject;
        monsterDamage = "-100 HP";
        gameObject.setHealth(Math.max(gameObject.getHealth() - 100, 0));
        attacker.setMana(attacker.getMana() - 100);
        skillIsPressed = false;
        calculateDamage = true;
        animationStarted = true;
        currentAnimation = powershotAnimation;
        WHOWILLATTACK++;
        client.sendGameInfo(attacker.getPlace(), attackedMonster.getPlace(), true);
    }

    private void powerShotUs(GameObject gameObject) {
        messageForMonsters = "Your " + gameObject.getName() + " was powershoted and killed!";
        attackedMonster = gameObject;
        monsterDamage = "-100 HP";
        gameObject.setHealth(Math.max(gameObject.getHealth() - 100, 0));
        attacker.setMana(attacker.getMana() - 100);
        calculateDamage = false;
        animationStarted = true;
        currentAnimation = powershotAnimation;
    }

    private void defAttack(GameObject gameObject) {
        messageForMonsters = "You attacked " + gameObject.getName();
        attackedMonster = gameObject;
        monsterDamage = "-" + attacker.getPower() + " HP";
        gameObject.setHealth(Math.max(gameObject.getHealth() - attacker.getPower(), 0));
        canbeattacked = false;
        calculateDamage = true;
        WHOWILLATTACK++;
        addManaMonsters = true;
        animationStarted = true;
        skillIsPressed = false;
        currentAnimation = heroAttackAnimation;
        client.sendGameInfo(attacker.getPlace(), attackedMonster.getPlace(), false);
    }

    private void attackUs(GameObject gameObject) {
        messageForMonsters = "Your " + gameObject.getName() + " was attacked!";
        attackedMonster = gameObject;
        monsterDamage = "-" + attacker.getPower() + " HP";
        gameObject.setHealth(Math.max(gameObject.getHealth() - attacker.getPower(), 0));
        calculateDamage = false;
        animationStarted = true;
        currentAnimation = heroAttackAnimation;
    }

    private void sunstrike(GameObject gameObject1, GameObject gameObject2, GameObject gameObject3) {
        messageForMonsters = "You used sunstrike on " + gameObject2.getName() + " and nearby enemyes with damage 30";
        gameObject1.setHealth(Math.max(gameObject1.getHealth() - 30, 0));
        gameObject2.setHealth(Math.max(gameObject2.getHealth() - 30, 0));
        gameObject3.setHealth(Math.max(gameObject3.getHealth() - 30, 0));
        attackedMonster = gameObject2;
        monsterDamage = "-30 HP";
        attacker.setMana(attacker.getMana() - 50);
        canbeattacked = false;
        animationStarted = true;
        currentAnimation = sunstrikeAnimation;
        skillIsPressed = false;
        calculateDamage = true;
        WHOWILLATTACK++;
        addManaMonsters = true;
        client.sendGameInfo(attacker.getPlace(), attackedMonster.getPlace(), true);
    }

    private void sunstrikeUs(GameObject gameObject1, GameObject gameObject2, GameObject gameObject3) {
        messageForMonsters = "Your " + gameObject2.getName() + " and nearby heroes were sunstriked with damage 30!";
        gameObject1.setHealth(Math.max(gameObject1.getHealth() - 30, 0));
        gameObject2.setHealth(Math.max(gameObject2.getHealth() - 30, 0));
        gameObject3.setHealth(Math.max(gameObject3.getHealth() - 30, 0));
        attackedMonster = gameObject2;
        monsterDamage = "-30 HP";
        attacker.setMana(attacker.getMana() - 50);
        calculateDamage = false;
        animationStarted = true;
        currentAnimation = sunstrikeAnimation;
    }

    private void sunstrike2(GameObject gameObject1, GameObject gameObject2) {
        messageForMonsters = "You used sunstrike on " + gameObject2.getName() + " and nearby enemyes with damage 30";
        gameObject1.setHealth(Math.max(gameObject1.getHealth() - 30, 0));
        gameObject2.setHealth(Math.max(gameObject2.getHealth() - 30, 0));
        if (gameObject1 == badCharacter1) {
            attackedMonster = gameObject1;
        } else {
            attackedMonster = gameObject2;
        }
        monsterDamage = "-30 HP";
        attacker.setMana(attacker.getMana() - 50);
        WHOWILLATTACK++;
        animationStarted = true;
        currentAnimation = sunstrikeAnimation;
        calculateDamage = true;
        addManaMonsters = true;
        skillIsPressed = false;
        client.sendGameInfo(attacker.getPlace(), attackedMonster.getPlace(), true);
    }

    private void sunstrike2Us(GameObject gameObject1, GameObject gameObject2) {
        messageForMonsters = "Your " + gameObject2.getName() + " and nearby heroes were sunstriked with damage 30!";
        gameObject1.setHealth(Math.max(gameObject1.getHealth() - 30, 0));
        gameObject2.setHealth(Math.max(gameObject2.getHealth() - 30, 0));
        if (gameObject1 == goodCharacter1) {
            attackedMonster = gameObject1;
        } else {
            attackedMonster = gameObject2;
        }
        monsterDamage = "-30 HP";
        attacker.setMana(attacker.getMana() - 50);
        calculateDamage = false;
        animationStarted = true;
        currentAnimation = sunstrikeAnimation;
    }

    private void heal() {
        GameObject needed = myCharacters.get(0);
        for (GameObject hero : myCharacters) {
            if (needed.getHealth() > hero.getHealth()) {
                needed = hero;
            }
        }
        needed.setHealth(needed.getHealth() + 30);
        attacker.setMana(attacker.getMana() - 40);
        skillIsPressed = false;
        messageForMonsters = "You healed " + needed.getName();
        monsterDamage = "+30 HP";
        attackedMonster = needed;
        calculateDamage = true;
        WHOWILLATTACK++;
        animationStarted = true;
        currentAnimation = heroHealAnimation;
        client.sendGameInfo(attacker.getPlace(), attackedMonster.getPlace(), true);
    }

    private void healThem() {
        GameObject needed = enemyCharacters.get(0);
        for (GameObject hero : enemyCharacters) {
            if (needed.getHealth() > hero.getHealth()) {
                needed = hero;
            }
        }
        needed.setHealth(needed.getHealth() + 30);
        attacker.setMana(attacker.getMana() - 40);
        calculateDamage = false;
        skillIsPressed = false;
        monsterDamage = "+30 HP";
        attackedMonster = needed;
        messageForMonsters = needed.getName() + " was healed!";
        animationStarted = true;
        currentAnimation = heroHealAnimation;
    }
    public List<GameObject> createEnemies(List<String> enemyList) {
        List<GameObject> enemyCharacterList = new LinkedList<>();
        int place = 1;
        int x = 900; // +200
        int y; // 450 ili 400
        for (String name : enemyList) {
            x += 200;
            if (place % 2 == 1) {
                y = 450;
            } else {
                y = 400;
            }
            GameObject enemy = null;
            switch (name) {
                case "Warrior":
                    enemy = new Warrior(new Texture(Gdx.files.internal("GoodCharacter1Reversed.png")), "Warrior", 100, 100, x, y, 200, 277, GameObject.CharacterClass.WARIOR, GameObject.CharacterType.GOOD1, place);
                    break;
                case "Archer":
                    enemy = new Archer(new Texture(Gdx.files.internal("GoodCharacter2Reversed.png")), "Archer", 100, 100, x, y, 200, 277, GameObject.CharacterClass.ARCHER, GameObject.CharacterType.GOOD2, place);
                    break;
                case "Wizard":
                    enemy = new Magic(new Texture(Gdx.files.internal("GoodCharacter3Reversed.png")), "Wizard", 200, 100, x, y, 200, 277, GameObject.CharacterClass.MAGIC, GameObject.CharacterType.GOOD3, place);
                    break;
                case "Paladin":
                    enemy = new Paladin(new Texture(Gdx.files.internal("GoodCharacter4Reversed.png")), "Paladin", 100, 100, x, y, 200, 277, GameObject.CharacterClass.PALADIN, GameObject.CharacterType.GOOD4, place);
                    break;
            }
            enemyCharacterList.add(enemy);
            place++;
        }
        return enemyCharacterList;
    }
}
